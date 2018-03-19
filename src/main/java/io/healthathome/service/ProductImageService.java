package io.healthathome.service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.google.common.io.ByteStreams;
import io.healthathome.dto.OperationResult;
import io.healthathome.models.Product;
import io.healthathome.repository.ProductRepository;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ProductImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductImageService.class);

    private static final String IMAGE_PREFIX = "product_";
    private static final String BUCKET_FOLDER = "product/";

    @Value("${aws.s3ProductFolderUrl}")
    private String s3BucketUrl;
    @Value("${aws.s3BucketName}")
    private String s3BucketName;
    @Value("${aws.s3AccessKey}")
    private String s3AccessKey;
    @Value("${aws.s3SecretKey}")
    private String s3SecretKey;
    @Autowired
    private ProductRepository productRepository;

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
        return s3BucketUrl + getImageName(idProduct, idImage);
    }

    private String getImageName(String idProduct, String idImage) {
        return IMAGE_PREFIX + idProduct + "_" + idImage + ".jpg";
    }

    public OperationResult uploadImage(String idProduct, InputStream image, byte[] bytes) {
        try {
            Product product = productRepository.findFirstByIdProduct(idProduct);
            String idImage = String.valueOf(Integer.valueOf(product.getPhotos().size()) + 1);

            AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(s3AccessKey, s3SecretKey));

            ObjectMetadata objectMetadata = getImageMetadata(idProduct, bytes);
            PutObjectRequest request = new PutObjectRequest(s3BucketName, BUCKET_FOLDER + getImageName(idProduct, idImage), image, objectMetadata);
            request.setCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult putObjectResult = s3client.putObject(request);

            product.getPhotos().add(idImage);
            productRepository.save(product);
            return OperationResult.newSuccessOperationResult(idImage);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return OperationResult.newFailedOperationResponse(e.toString());
        }
    }

    private ObjectMetadata getImageMetadata(String idProduct, byte[] bytes) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpeg");
        objectMetadata.addUserMetadata("id", idProduct);
        objectMetadata.setContentLength(bytes.length);
        return objectMetadata;
    }
}
