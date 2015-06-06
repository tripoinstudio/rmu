package com.tripoin.core.rest.util;

import org.springframework.http.HttpHeaders;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IBase64Authorization {
	
	public String getBase64Authorization();

	public HttpHeaders encodeUserCredentials(String username, String password);
	
}
