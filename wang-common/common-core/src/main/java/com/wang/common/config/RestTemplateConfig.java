package com.wang.common.config;

import com.wang.common.util.MessageConverterUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Value("${http.maxConnection}")
    private int maxConnection;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestFactory factory = clientHttpRequestFactory();
        restTemplate.setRequestFactory(factory);
        this.coverConverters(restTemplate);
        return restTemplate;
    }

    private void coverConverters(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        List<HttpMessageConverter<?>> messageConvertersNew = new ArrayList<>();
        for (HttpMessageConverter httpMessageConverter : messageConverters) {
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                messageConvertersNew.add(MessageConverterUtil.fastJsonHttpMessageConverter());
            } else {
                if (httpMessageConverter instanceof StringHttpMessageConverter) {
                    ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(StandardCharsets.UTF_8);
                }
                messageConvertersNew.add(httpMessageConverter);
            }
        }
        restTemplate.setMessageConverters(messageConvertersNew);
    }

    @Bean(GBK_REST_TEMPLATE)
    public RestTemplate restTemplateGbk() {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestFactory factory = clientHttpRequestFactory();
        restTemplate.setRequestFactory(factory);
        this.coverConvertersForGbk(restTemplate);
        return restTemplate;
    }

    private void coverConvertersForGbk(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        List<HttpMessageConverter<?>> messageConvertersNew = new ArrayList<>();
        for (HttpMessageConverter httpMessageConverter : messageConverters) {
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                messageConvertersNew.add(MessageConverterUtil.fastJsonHttpMessageConverterGbk());
            } else {
                if (httpMessageConverter instanceof StringHttpMessageConverter) {
                    ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("GBK"));
                }
                messageConvertersNew.add(httpMessageConverter);
            }
        }
        restTemplate.setMessageConverters(messageConvertersNew);
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setMaxConnTotal(maxConnection);
        httpClientBuilder.setMaxConnPerRoute(maxConnection);
        httpClientBuilder.setConnectionTimeToLive(5, TimeUnit.SECONDS);
//        httpClientBuilder.setProxy(new HttpHost("127.0.0.1", 8888));
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectTimeout);
        factory.setConnectionRequestTimeout(connectionRequestTimeout);
        factory.setBufferRequestBody(false);
        return factory;
    }

    @Bean(DEFAULT_HEADER)
    public HttpHeaders defaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONNECTION, "close");
        headers.add(HttpHeaders.CONTENT_ENCODING, "utf-8");
        return headers;
    }

    @Bean(GBK_HEADER)
    public HttpHeaders gbkHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONNECTION, "close");
        headers.add(HttpHeaders.CONTENT_ENCODING, "gbk");
        return headers;
    }

}
