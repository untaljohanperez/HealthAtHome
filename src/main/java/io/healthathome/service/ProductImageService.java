package io.healthathome.service;

import com.google.common.io.ByteStreams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private static final String IMAGE_PREFIX = "product_";

    @Value("${aws.s3ProductFolderUrl}")
    private String s3BucketUrl;

    public byte[] getProductImageByIdProductAndIdImage(String idProduct, String idImage) throws IOException {
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(getUrlImage(idProduct, idImage));
            CloseableHttpResponse response = httpclient.execute(httpGet);
            return ByteStreams.toByteArray(response.getEntity().getContent());
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        } finally {
            if (httpclient != null)
                httpclient.close();
        }
    }

    private String getUrlImage(String idProduct, String idImage) {
        return s3BucketUrl + IMAGE_PREFIX + idProduct + "_" + idImage + ".jpg";
    }
}
