package com.tuandc.interview.hrs_hotel_booking.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        long start = System.currentTimeMillis();
        logRequestDetails(request);

        ClientHttpResponse response = execution.execute(request, body);

        long duration = System.currentTimeMillis() - start;
        logResponseDetails(response, duration);

        return response;
    }

    private void logRequestDetails(HttpRequest request) {
        logger.info("Request URL: {}", request.getURI());
        // You can log other request details like headers, method, etc.
    }

    private void logResponseDetails(ClientHttpResponse response, long duration) throws IOException {
        logger.info("Response Status Code: {}", response.getStatusCode());
        logger.info("Time taken: {} ms", duration);
        // You can log other response details like headers, etc.
    }
}
