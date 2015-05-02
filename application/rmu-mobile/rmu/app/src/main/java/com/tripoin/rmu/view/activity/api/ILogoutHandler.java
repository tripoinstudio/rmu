package com.tripoin.rmu.view.activity.api;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 11:09 AM.
 * mailto : achmad.fauzi@sigma.co.id
 *
 * Handling logout and detecting login status
 */
public interface ILogoutHandler {

    public void logout();

    public boolean checkLoginStatus();

}
