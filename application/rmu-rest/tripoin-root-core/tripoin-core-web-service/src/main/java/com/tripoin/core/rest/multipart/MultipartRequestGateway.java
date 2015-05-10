package com.tripoin.core.rest.multipart;

import java.util.Map;

import org.springframework.http.HttpStatus;

public interface MultipartRequestGateway {

	HttpStatus postMultipartRequest(Map<String, Object> multipartRequest);
}
