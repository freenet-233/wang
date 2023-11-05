package com.wang.common.config;


import com.wang.common.exception.ErrorCode;
import com.wang.common.exception.UncheckedBusinessException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author wang
 */
@Configuration
@ConditionalOnProperty(prefix = "http.", value = "readTimeout")
public class RestTemplateConfig {

    public static final String DEFAULT_HEADER = "defaultHeader";

    public static final String GBK_HEADER = "gbkHeader";

    public static final String GBK_REST_TEMPLATE = "restTemplateForGbk";

    @Value("${http.readTimeout}")
    private int readTimeout;
    @Value("${http.connectTimeout}")
    private int connectTimeout;
    @Value("${http.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(getRequestFactory());
        converter(restTemplate);
        return restTemplate;
    }
    private void converter(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for(HttpMessageConverter<?> messageConverter : messageConverters){
            if(messageConverter instanceof StringHttpMessageConverter){
                ((StringHttpMessageConverter) messageConverter).setDefaultCharset(StandardCharsets.UTF_8);
            }

        }
    }

    public HttpComponentsClientHttpRequestFactory getRequestFactory(){
        CloseableHttpClient httpClient;
        TrustStrategy trustStrategy = (x509Certificates, s) -> true;
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null,trustStrategy).build();
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
            httpClient = HttpClients.custom().setConnectionManager(PoolingHttpClientConnectionManagerBuilder
                    .create().setSSLSocketFactory(socketFactory).build()).build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            requestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
            requestFactory.setConnectTimeout(connectTimeout);
            return requestFactory;
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new UncheckedBusinessException(ErrorCode.ERROR_510, e.getMessage());
        }
    }


}
