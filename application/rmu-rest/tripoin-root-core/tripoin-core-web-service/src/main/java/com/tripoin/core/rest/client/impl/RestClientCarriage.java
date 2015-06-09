package com.tripoin.core.rest.client.impl;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.tripoin.core.rest.client.base.ARestClient;
import com.tripoin.core.rest.util.RestConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("restClientCarriage")
public class RestClientCarriage extends ARestClient {
	
	public String getUrl() {
		return RestConstant.WS_CARRIAGE;
	}

	public String getAccept() {
		return RestConstant.ACCEPT_JSON;
	}

	public HttpMethod getHttpMethod() {
		return HttpMethod.GET;
	}

}
