package com.tripoin.core.rest.sync.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;

import com.tripoin.core.dto.VersionDTO;
import com.tripoin.core.dto.Versions;
import com.tripoin.core.pojo.Version;
import com.tripoin.core.rest.client.IRestClient;
import com.tripoin.core.rest.sync.ISynchronize;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.ParameterConstant;

import static ch.lambdaj.Lambda.*;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ASynchronize implements ISynchronize {
	
	protected Date currentVersion;
	
	protected Date lastVersion;
	
	protected IGenericManagerJpa iGenericManagerJpa;
	
	private IRestClient restClientVersion;
	
	private SimpleDateFormat formatDateVersion = new SimpleDateFormat(ParameterConstant.DEFAULT_FORMAT_DATE);	
	
	public void setiGenericManagerJpa(IGenericManagerJpa iGenericManagerJpa) {
		this.iGenericManagerJpa = iGenericManagerJpa;
	}

	public void setRestClientVersion(IRestClient restClientVersion) {
		this.restClientVersion = restClientVersion;
	}

	public Date getCurrentVersion() {
		Versions versions = restClientVersion.execute(Versions.class);
		List<VersionDTO> versionDTOs = versions.getMaster_version();
		Date date;
		try{
			VersionDTO versionDTO = select(versionDTOs,having(on(VersionDTO.class).getVersion_table(),Matchers.equalToIgnoringCase(getNameVersion()))).get(0);
			date = formatDateVersion.parse(versionDTO.getVersion_timestamp());
		}catch(Exception e){
			date = new Date();
		}
		currentVersion = date;
		return date;
	}
	
	public Date getLastVersion(){
		lastVersion = iGenericManagerJpa.getObjectsUsingParameter(Version.class, new String[]{"table"}, new Object[]{getNameVersion()}, null, null).get(0).getTimestamp();		
		return lastVersion;
	}

	public Integer getDiffVersion() {		
		return getLastVersion().compareTo(getCurrentVersion());
	}

}
