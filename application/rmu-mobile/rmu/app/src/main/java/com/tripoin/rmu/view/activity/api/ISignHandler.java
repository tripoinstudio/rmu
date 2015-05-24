package com.tripoin.rmu.view.activity.api;

import com.tripoin.rmu.model.DTO.user.UserDTO;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 11:09 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 *
 * Handling signIn, signOut and detecting login status
 */
public interface ISignHandler {

    public void signOut();

    public void detectLoginStatus();

    public boolean checkLoginStatus();

}
