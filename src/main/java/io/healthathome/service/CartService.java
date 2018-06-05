
package io.healthathome.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.healthathome.dto.ItemInput;
import io.healthathome.dto.Pay;
import io.healthathome.dto.OperationResult;
import io.healthathome.dto.payu.*;
import io.healthathome.models.Cart;
import io.healthathome.models.Item;
import io.healthathome.repository.CartRepository;
import io.healthathome.repository.PayRepository;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PayRepository payRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${payU.apiKey}")
    private String apiKey;
    @Value("${payU.apiLogin}")
    private String apiLogin;
    @Value("${payU.urlApi}")
    private String payuUrl;
    @Value("${payU.merchantId}")
    private String merchantId;
    @Value("${payU.accountId}")
    private String accountId;


    public io.healthathome.dto.Cart getCartByUser(String user) {
        Cart cartStored = cartRepository.getByUserAndStateIsTrue(user);
        io.healthathome.dto.Cart cartDto = new io.healthathome.dto.Cart();
        cartDto.setUser(user);
        if (cartStored == null)
            return cartDto;
        setItemsToCart(cartStored, cartDto);
        return cartDto;
    }

    public io.healthathome.dto.Cart getCartById(String id) {
        Cart cartStored = cartRepository.findById(id).get();
        io.healthathome.dto.Cart cartDto = new io.healthathome.dto.Cart();
        if (cartStored == null)
            return cartDto;
        setItemsToCart(cartStored, cartDto);
        return cartDto;
    }

    private void setItemsToCart(Cart cartStored, io.healthathome.dto.Cart cartDto) {
        cartDto.setItems(cartStored.getItems().stream()
                .map(x -> new io.healthathome.dto.Item(productService.getProductById(x.getProduct()), x.getQuantity()))
                .collect(Collectors.toList()));
    }

    public io.healthathome.dto.Cart addProduct(ItemInput item, String idUser) {
        Cart cart = cartRepository.getByUserAndStateIsTrue(idUser);
        if (cart == null)
            cart = new Cart(String.valueOf(Instant.now().getEpochSecond()));
        List items = cart.getItems();
        items.add(buildItem(item));
        cart.setItems(items);
        cart.setState(true);
        cart.setUser(idUser);
        return Mapper.map(cartRepository.save(cart));
    }

    private Item buildItem(ItemInput item) {
        Item itemModel = new Item();
        itemModel.setProduct(item.getProduct().getId());
        itemModel.setQuantity(item.getQuantity());
        itemModel.setEachPrice(item.getProduct().getEachPrice());
        return itemModel;
    }

    public OperationResult pay(Pay pay) throws IOException {
        CloseableHttpClient httpclient = null;
        try {
            final Cart cart = cartRepository.getByUserAndStateIsTrue(pay.getUser());
            if (cart == null)
                return OperationResult.newFailedOperationResponse("Not cart found");

            if (!pay.isTest()) {

                PayURequest payURequest = getPayURequest(pay, cart);
                httpclient = HttpClients.createDefault();
                HttpPost httpPost = getPayUHttpPost(payURequest);
                CloseableHttpResponse response = httpclient.execute(httpPost);

                int statusCode = response.getStatusLine().getStatusCode();
                PayUResponse payUResponse = getPayUResponse(response, statusCode);

                if (statusCode != 200 || "ERROR".equals(payUResponse.getCode()))
                    return OperationResult.newFailedOperationResponse(payUResponse.getError());

            }

            io.healthathome.models.Pay payModel = payRepository.insert(buildPay(pay, cart));
            cart.setState(false);
            cartRepository.save(cart);

            return OperationResult.newSuccessOperationResult(payModel.get_id());
        } catch (Exception e) {
            e.printStackTrace();
            return OperationResult.newFailedOperationResponse(e.toString());
        } finally {
            if (httpclient != null)
                httpclient.close();
        }
    }

    private PayUResponse getPayUResponse(CloseableHttpResponse response, int statusCode) throws IOException {
        HttpEntity entityResponse = response.getEntity();
        BufferedReader reader = new BufferedReader(new InputStreamReader(entityResponse.getContent()));
        PayUResponse payUResponse = objectMapper.readValue(reader.readLine(), PayUResponse.class);

        LOGGER.info("*****************PayU*******************");
        LOGGER.info("statusCode: " + statusCode);
        LOGGER.info("response: " + payUResponse.getError());
        return payUResponse;
    }

    private io.healthathome.models.Pay buildPay(Pay pay, Cart cart) {
        io.healthathome.models.Pay payModel = new io.healthathome.models.Pay();
        payModel.setCartId(cart.getCartId());
        payModel.setUser(cart.getUser());
        payModel.setShippingAddress(Mapper.map(pay.getShippingAddress()));
        return payModel;
    }

    private HttpPost getPayUHttpPost(PayURequest payURequest) throws JsonProcessingException, UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(payuUrl);
        httpPost.setHeader("ACCEPT", "application/json");
        httpPost.setHeader("CONTENT-TYPE", "application/json");
        String json = objectMapper.writeValueAsString(payURequest);
        LOGGER.info("PayUJson: " + json);
        httpPost.setEntity(new StringEntity(json));
        return httpPost;
    }

    private PayURequest getPayURequest(Pay pay, Cart cart) throws Exception {
        PayURequest payURequest = new PayURequest();
        payURequest.setLanguage("es");
        payURequest.setCommand("SUBMIT_TRANSACTION");
        payURequest.setMerchant(new Merchant(apiKey, apiLogin));
        payURequest.setTransaction(getTrasaction(pay, cart));
        payURequest.setTest(true);
        return payURequest;
    }

    private Transaction getTrasaction(Pay pay, Cart cart) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setOrder(getOrder(pay, cart));
        transaction.setPayer(getPayer(pay.getBuyer()));
        transaction.setCreditCard(pay.getCreditCard());
        transaction.setExtraParameters(getExtraParameters());
        transaction.setType("AUTHORIZATION_AND_CAPTURE");
        transaction.setPaymentMethod(pay.getPaymentMethod());
        transaction.setPaymentCountry("CO");
        transaction.setDeviceSessionId("vghs6tvkcle931686k1900o6e1");
        transaction.setIpAddress("127.0.0.1");
        transaction.setCookie("pt1t38347bs6jc9ruv2ecpv7o2");
        transaction.setUserAgent("Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0");
        return transaction;
    }

    private ExtraParameters getExtraParameters() {
        return new ExtraParameters(1);
    }

    private Payer getPayer(Buyer buyer) {
        Payer payer = new Payer();
        payer.setBillingAddress(buyer.getShippingAddress());
        payer.setMerchantPayerId(buyer.getMerchantBuyerId());
        payer.setFullName(buyer.getFullName());
        payer.setEmailAddress(buyer.getEmailAddress());
        payer.setContactPhone(buyer.getContactPhone());
        payer.setDniNumber(buyer.getDniNumber());
        return payer;
    }

    private Order getOrder(Pay pay, Cart cart) throws Exception {
        Order order = new Order();
        order.setAccountId(accountId);
        order.setReferenceCode("CartPay");
        order.setDescription("CartPay");
        order.setLanguage("es");
        order.setSignature(getSignature(pay, cart));
        order.setNotifyUrl("https://13.90.130.197/pay/confimation");
        order.setAdditionalValues(getAdditionalValues(cart));
        order.setBuyer(pay.getBuyer());
        order.setShippingAddress(pay.getBuyer().getShippingAddress());
        return order;
    }

    private String getSignature(Pay pay, Cart cart) throws Exception {
        //"ApiKey~merchantId~referenceCode~tx_value~currency"
        final String rayita = "~";
        String signature = apiKey + rayita + merchantId + rayita + cart.getCartId()
                + rayita + String.valueOf(getPayValue(cart)) + rayita + "COP";
        String signatureMd5 = MessageDigest.getInstance("MD5").digest(signature.getBytes("UTF-8")).toString();
        System.out.println("Signature: " + signature);
        System.out.println("SignatureMD5: " + signatureMd5);
        return signatureMd5;
    }

    private AdditionalValues getAdditionalValues(Cart cart) {
        AdditionalValues additionalValues = new AdditionalValues();
        additionalValues.setTX_VALUE(new AdditionalValue(getPayValue(cart), "COP"));
        additionalValues.setTX_TAX(new AdditionalValue(0, "COP"));
        additionalValues.setTX_TAX_RETURN_BASE(new AdditionalValue(0, "COP"));
        return additionalValues;
    }

    private double getPayValue(Cart cart) {
        BigDecimal total = cart.getItems().stream().map(x -> BigDecimal.valueOf(x.getQuantity()).multiply(BigDecimal.valueOf(x.getEachPrice()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("total: " + total.doubleValue());
        return total.doubleValue();
    }
}