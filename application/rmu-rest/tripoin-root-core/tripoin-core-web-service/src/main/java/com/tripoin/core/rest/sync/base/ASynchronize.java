package com.tripoin.core.rest.sync.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;

import com.tripoin.core.dto.VersionDTO;
import com.tripoin.core.dto.Versions;
import com.tripoin.core.rest.client.IRestClient;
import com.tripoin.core.rest.sync.ISynchronize;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.ParameterConstant;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*; 

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ASynchronize implements ISynchronize {
	
	private IRestClient restClientVersion;
	
	private IGenericManagerJpa iGenericManagerJpa;
	
	private SimpleDateFormat formatDateVersion = new SimpleDateFormat(ParameterConstant.DEFAULT_FORMAT_DATE);

	public void setRestClientVersion(IRestClient restClientVersion) {
		this.restClientVersion = restClientVersion;
	}	
	
	public void setiGenericManagerJpa(IGenericManagerJpa iGenericManagerJpa) {
		this.iGenericManagerJpa = iGenericManagerJpa;
	}

	public String doSync(){
		return null;
	}

	public Date getVersion() {
		Versions versions = restClientVersion.execute(Versions.class);
		List<VersionDTO> versionDTOs = versions.getMaster_version();
		List<VersionDTO> versionDTOFilterList = select(versionDTOs, having(on(VersionDTO.class).getVersion_table(),Matchers.equalToIgnoringCase(getNameVersion())));
		VersionDTO versionDTO = versionDTOFilterList.get(0);
		Date date = new Date();
		try{
			date = formatDateVersion.parse(versionDTO.getVersion_timestamp());
		}catch(Exception e){
			date = new Date();
		}
		return date;
	}

	public Integer getDiffVersion() {
		// TODO Auto-generated method stub
		return null;
	}

}
