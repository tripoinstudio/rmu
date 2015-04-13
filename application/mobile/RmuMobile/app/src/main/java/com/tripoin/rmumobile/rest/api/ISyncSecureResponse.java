package com.tripoin.rmumobile.rest.api;


import com.tripoin.rmumobile.model.DTO.BaseRESTDTO;
import com.tripoin.rmumobile.model.DTO.LoginRestDTO;

/**
 * Created by Achmad Fauzi on 12/4/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Interface to Hold output from REST Communication
 */
public interface ISyncSecureResponse {

    /**
     * Action to hold CheckEmail result
      * @param baseRESTDTO Object
     */
    public void onCheckPhoneNumberFinished(BaseRESTDTO baseRESTDTO);

    /**
     * Action to hold CheckLogin result
     * @param loginRestDTO Object
     */
    public void onCheckPasswordFinished(LoginRestDTO loginRestDTO);
}
