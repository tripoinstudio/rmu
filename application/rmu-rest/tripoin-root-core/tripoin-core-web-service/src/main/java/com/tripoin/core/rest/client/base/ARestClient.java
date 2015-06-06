package com.tripoin.core.rest.client.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.rest.client.IRestClient;
import com.tripoin.core.rest.util.IBase64Authorization;
import com.tripoin.core.service.util.ISystemParameterService;
import com.tripoin.core.service.util.ParameterConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ARestClient implements IRestClient {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(ARestClient.class);
	
	private String xReturnStatus;
	
	private String xReturnStatusMsg;

	private RestTemplate restTemplate;
	
	private IBase64Authorization base64Authorization;
	
	private ISystemParameterService systemParameterService;

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setSystemParameterService(ISystemParameterService systemParameterService) {
		this.systemParameterService = systemParameterService;
	}
	
	public void setBase64Authorization(IBase64Authorization base64Authorization) {
		this.base64Authorization = base64Authorization;
	}
		
	public String getBaseUrl(){
		SystemParameter systemParameter = this.systemParameterService.getParameter(ParameterConstant.HOST_SERVER);
		return systemParameter.getValue();
	}
		
	public HttpHeaders getHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic ".concat(this.base64Authorization.getBase64Authorization()));
		headers.add("Accept", getAccept());
		return headers;
	}

	public <T> T execute(Class<T> objectClass) {
		HttpEntity<Object> request = new HttpEntity<Object>(getHeaders());
		String url = getBaseUrl().concat(getUrl());
		LOGGER.debug("URL : ".concat(url));
		ResponseEntity<T> httpResponse = this.restTemplate.exchange(url, HttpMethod.GET, request, objectClass);
		this.xReturnStatus = httpResponse.getHeaders().get("X-Return-Status").get(0);
		this.xReturnStatusMsg = httpResponse.getHeaders().get("X-Return-Status-Msg").get(0);
		return httpResponse.getBody();
	}

	public String getXReturnStatus(){
		return this.xReturnStatus;
	}
	
	public String getXReturnStatusMsg(){
		return this.xReturnStatusMsg;
	}
}
