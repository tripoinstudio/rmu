package com.tripoin.core.rest.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.codec.Base64;
import org.springframework.stereotype.Service;

import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.util.IBase64Authorization;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.util.RoleConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("base64Authorization")
public class Base64AuthorizationImpl implements IBase64Authorization {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	public String getBase64Authorization() {
		User user = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"role.code"}, new Object[]{RoleConstant.ROLE_DEFAULT}, null, null).get(0);
		return user.getAuth();
	}
	
	public HttpHeaders encodeUserCredentials(String username, String password){
		HttpHeaders headers = new HttpHeaders();
		String combinedUsernamePassword = username+":"+password;
		byte[] base64Token = Base64.encode(combinedUsernamePassword.getBytes());
		String base64EncodedToken = new String (base64Token);
		headers.add("Authorization","Basic ".concat(base64EncodedToken));
		return headers;
	}
}
