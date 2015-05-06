package com.tripoin.core.rest.util.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.pojo.Version;
import com.tripoin.core.rest.util.IVersionHelper;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("versionHelper")
public class VersionHelperImpl implements IVersionHelper {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	public void updateVerision(String tableName) throws Exception{
		List<Version> versionList = iGenericManagerJpa.getObjectsUsingParameter(Version.class, new String[]{"table"}, new Object[]{tableName}, null, null);
		Version version = versionList.get(0);
		version.setTimestamp(new Date());
		iGenericManagerJpa.updateObject(version);
	}
}
