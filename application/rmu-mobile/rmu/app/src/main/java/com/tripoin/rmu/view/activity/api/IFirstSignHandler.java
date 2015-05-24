package com.tripoin.rmu.view.activity.api;

import com.tripoin.rmu.model.DTO.user.UserDTO;

/**
 * Created by Achmad Fauzi on 5/24/2015 : 12:00 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface IFirstSignHandler extends ISignHandler{

    public void signIn(UserDTO userDTO, String userName, String chipperAuth);
}
