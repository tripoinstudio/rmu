package com.tripoin.core.rest.client;

import org.springframework.http.HttpMethod;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IRestClient {

	public <T> T execute(Class<T> objectClass);
	
	public String getUrl();
	
	public String getAccept();
	
	public HttpMethod getHttpMethod();
	
	public String getXReturnStatus();
	
	public String getXReturnStatusMsg();
	
}
