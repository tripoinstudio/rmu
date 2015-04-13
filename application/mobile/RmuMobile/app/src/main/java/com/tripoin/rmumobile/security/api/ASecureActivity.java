package com.tripoin.rmumobile.security.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.inject.Inject;
import com.tripoin.rmumobile.R;
import com.tripoin.rmumobile.model.DTO.PasswordExpiredDTO;
import com.tripoin.rmumobile.rest.api.ISyncReloadConfiguration;
import com.tripoin.rmumobile.rest.api.ISyncSecureResponse;
import com.tripoin.rmumobile.util.enumeration.PropertyConstant;

import java.util.Date;
import roboguice.inject.InjectResource;

/**
 * Created by Achmad Fauzi on 11/20/2014.
 * achmad.fauzi@sigma.co.id
 *
 * This class is used for Activity with basic security
 */
public abstract class ASecureActivity extends ACustomActivity implements ISyncSecureResponse, ISyncReloadConfiguration {

    @Inject protected TelephonyManager telephonyManager;

    @InjectResource( R.string.string_is_not_valid ) public String stringIsNotValid;
    @InjectResource( R.string.string_connection_error ) public String stringConnectionError;
    @InjectResource( R.string.string_password ) public String stringPassword;
    @InjectResource( R.string.string_new_password ) public String stringNewPassword;
    @InjectResource( R.string.string_confirm_new_password ) public String stringConfirmNewPassword;
    @InjectResource( R.string.string_current_password ) public String stringCurrentPassword;
    @InjectResource( R.string.string_password ) public String stringRePassword;
    @InjectResource( R.string.string_cannot_be_empty ) public String stringCannotBeEmpty;
    @InjectResource( R.string.string_an_error_occured ) public String stringAnErrorOccured;
    @InjectResource( R.string.string_not_equal_to ) public String stringNotEqualTo;
    @InjectResource( R.string.string_space ) public String stringSpace;
    @InjectResource( R.string.string_changed_successfully ) public String stringChangedSuccessfully;

    @InjectResource( R.string.string_empty ) public String stringEmpty;
    @InjectResource( R.string.string_mobile_phone_number ) public String stringMobilePhoneNumber;
    @InjectResource( R.string.string_information ) public String stringInformation;
    @InjectResource( R.string.string_fail_get_configuration ) private String stringFailGetConfiguration;

    private String mnumber;

    @Override
    public void setupActionBar() {
        this.actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable( new ColorDrawable( getResources().getColor( R.color.red_dark_holo ) ) );
        actionBar.setIcon( new ColorDrawable( getResources().getColor( android.R.color.transparent ) ) );
        actionBar.setTitle( initActionBarTitle() );
    }

    protected void detectLoginStatus() {
        if( checkLoginStatus() ){
            gotoNextActivity(ActMain.class, PropertyConstant.INTENT_MNUMBER_DEFAULT_KEY.toString(), securityUtil.getValuePropertyMap(PropertyConstant.MNUMBER.toString()));
        }
    }

    /**
     * Check whether Map property value of mnumber and status is exist
     * @return boolean
     */
    public boolean checkLoginStatus(){
        boolean result;
        try {
            result = (!securityUtil.getValuePropertyMap(PropertyConstant.MNUMBER.toString()).equals(stringEmpty)) && !(securityUtil.getValuePropertyMap(PropertyConstant.LOGIN_STATUS_KEY.toString()).equals(stringEmpty));
        }catch ( Exception e){
            result = false;
        }
        return result;
    }


    protected void processPhoneNumber(String mNumber, String deviceId){
        this.mnumber = mNumber;
        if ( generalValidation.isValidPhoneNumber( mNumber ) ){
            urlBuilder.initiate();
            urlBuilder.getContentToBuild().put( PropertyConstant.MNUMBER.toString(), mNumber );
            urlBuilder.getContentToBuild().put( RestConstant.DEVICE_ID.toString(), deviceId );
            urlBuilder.getContentToBuild().put( RestConstant.DATE_TIME.toString(), generalConverter.getDateToFormatDateWS( new Date() ) );
            urlBuilder.buildUrl( RestConstant.TSK_CHECK_PHONE_NUMBER.toString(), mNumber );
            if ( networkConnectivity.checkConnectivity() ){
                CheckPhoneNumberRest checkPhoneNumberRest = new CheckPhoneNumberRest( ASecureActivity.this );
                checkPhoneNumberRest.execute();
            }
        }else{
            Toast.makeText( this, stringMobilePhoneNumber.concat(stringSpace).concat(stringIsNotValid), Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onCheckPhoneNumberFinished(BaseRESTDTO baseRESTDTO) {
        try{
            if ( baseRESTDTO.getResult().equals( RestConstant.OK.toString() ) ){
                //mnumber is exist and matches to registered device id
                securityUtil.saveSingleProperty( PropertyConstant.MNUMBER.toString(), mnumber );
                readAdminConfiguration();
                gotoNextActivity( ActLoginValidation.class, PropertyConstant.INTENT_MNUMBER_DEFAULT_KEY.toString(), mnumber );
            }else if( baseRESTDTO.getResult().equals( RestConstant.NOT_FOUND.toString() ) ) {
                //mnumber is not exist
                gotoNextActivity( ActActivateAccount.class, PropertyConstant.INTENT_MNUMBER_DEFAULT_KEY.toString(), mnumber);
            }else{
                Toast.makeText(this, baseRESTDTO.getErr_msg(), Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, stringConnectionError.concat(stringSpace).concat(e.toString()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to read configuration from admin setup
     */
    public void readAdminConfiguration(){
        if ( networkConnectivity.checkConnectivity() ){
            urlBuilder.initiate();
            urlBuilder.buildUrl( RestConstant.TSK_GET_ADMIN_CONFIGURATION.toString(), mnumber );
            new ReadAdminConfigRest( this ).execute();
        }else{
            Toast.makeText(this, stringConnectionError, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to read configuration from admin setup
     */
    public void readAdminConfiguration2( String deviceId ){
        if ( networkConnectivity.checkConnectivity() ){
            urlBuilder.initiate();
            urlBuilder.buildUrl( RestConstant.TSK_GET_ADMIN_CONFIGURATION2.toString(), deviceId );
            new ReadAdminConfigRest( this ).execute();
        }else{
            Toast.makeText(this, stringConnectionError, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Main method Process of Login
     * @param email mnumber input
     * @param deviceId imsi device
     * @param password password input
     */
    protected void processLogin( String email, String deviceId, String password ){
        if (password.equals(stringEmpty)){
            Toast.makeText( this, stringPassword.concat(stringSpace).concat(stringCannotBeEmpty), Toast.LENGTH_LONG ).show();
        }else{
            urlBuilder.initiate();
            urlBuilder.getContentToBuild().put( RestConstant.MNUMBER.toString(), email);
            urlBuilder.getContentToBuild().put( RestConstant.PASSWORD.toString(), password );
            urlBuilder.getContentToBuild().put( RestConstant.DEVICE_ID.toString() , deviceId );
            urlBuilder.buildUrl( RestConstant.TSK_LOGIN.toString(), deviceId );
            if ( networkConnectivity.checkConnectivity() ){
                ProcessLoginRest processLoginRest = new ProcessLoginRest( this );
                processLoginRest.execute();
            }
        }
    }

    @Override
    public void onCheckPasswordFinished(LoginRestDTO loginRestDTO) {
        try{
            if( loginRestDTO.getResult().equals( RestConstant.OK.toString() )){
                securityUtil.saveSingleProperty(PropertyConstant.LOGIN_STATUS_KEY.toString(), PropertyConstant.LOGIN_STATUS_VALUE.toString());
                securityUtil.saveSingleProperty( PropertyConstant.PASSWORD_EXPIRED_DATE.toString(), loginRestDTO.getPasswordExpiredDate() );
                goToMainView(PropertyConstant.INTENT_MNUMBER_DEFAULT_KEY.toString(), mnumber);
            }else{
                Toast.makeText( this, loginRestDTO.getErr_msg(), Toast.LENGTH_SHORT ).show();
            }
        }catch ( Exception e ){
            Toast.makeText( this, stringConnectionError, Toast.LENGTH_SHORT ).show();
        }
    }

    protected class CheckPhoneNumberRest extends ARestDialogPOSTAsyncTask {

        private ISyncSecureResponse delegate = null;

        public CheckPhoneNumberRest(ISyncSecureResponse delegate) {
            this.delegate = delegate;
        }

        @Override
        public String initUrl() {
            return urlBuilder.getUrlResult();
        }

        @Override
        public Class<?> initClassResult() {
            return BaseRESTDTO.class;
        }

        @Override
        protected Context getContext() {
            return ASecureActivity.this;
        }

        @Override
        protected String getProgressDialogTitle() {
            return RestConstant.CHECKING_PHONE_NUMBER.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            BaseRESTDTO baseRESTDTO = null;
            try{
                baseRESTDTO = (BaseRESTDTO) objectResult;
            }catch (Exception e){
                Toast.makeText(ASecureActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
            delegate.onCheckPhoneNumberFinished(baseRESTDTO);
        }
    }

    protected class ProcessLoginRest extends ARestDialogPOSTAsyncTask {

        private ISyncSecureResponse delegate = null;

        public ProcessLoginRest(ISyncSecureResponse delegate) {
            this.delegate = delegate;
        }

        @Override
        public String initUrl() {
            return urlBuilder.getUrlResult();
        }

        @Override
        public Class<?> initClassResult() {
            return LoginRestDTO.class;
        }

        @Override
        protected Context getContext() {
            return ASecureActivity.this;
        }

        @Override
        protected String getProgressDialogTitle() {
            return RestConstant.AUTHENTICATING.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            LoginRestDTO loginRestDTO= null;
            try{
                loginRestDTO = (LoginRestDTO) objectResult;
            }catch (Exception e){
                Toast.makeText(ASecureActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
            delegate.onCheckPasswordFinished( loginRestDTO );
        }
    }

    protected class ReadAdminConfigRest extends ARestGETAsyncTask {

        private ISyncReloadConfiguration delegate = null;

        public ReadAdminConfigRest(ISyncReloadConfiguration delegate) {
            this.delegate = delegate;
        }

        @Override
        public String initUrl() {
            return urlBuilder.getUrlResult();
        }

        @Override
        public Class<?> initClassResult() {
            return AdminConfigurationDTO.class;
        }

        @Override
        protected void onPostExecute(String s) {
            AdminConfigurationDTO adminConfigurationDTO;
            try{
                adminConfigurationDTO = (AdminConfigurationDTO) objectResult;
                Log.d("ADMIN CONFIG DTO", adminConfigurationDTO.toString());
                String videoId = "";
                String videoView = "";
                String url = "";
                String videoResoution= "";
                propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), ASecureActivity.this);
                if(adminConfigurationDTO.getInterval() == null) {
                    propertyUtil.saveSingleProperty( PropertyConstant.INTERVAL_REQUEST_KEY.toString(), PropertyConstant.INTERVAL_REQUEST_DEFAULT_VALUE.toString());
                }else {
                    propertyUtil.saveSingleProperty(PropertyConstant.INTERVAL_REQUEST_KEY.toString(), String.valueOf(adminConfigurationDTO.getInterval()));
                }
                if(adminConfigurationDTO.getServerHost()==null){
                    propertyUtil.saveSingleProperty( PropertyConstant.SERVER_HOST_KEY.toString(), PropertyConstant.SERVER_HOST_DEFAULT_VALUE.toString());
                }else{
                    propertyUtil.saveSingleProperty( PropertyConstant.SERVER_HOST_KEY.toString(), adminConfigurationDTO.getServerHost());
                }
                if(adminConfigurationDTO.getServerPort()==null){
                    propertyUtil.saveSingleProperty( PropertyConstant.SERVER_PORT_KEY.toString(), PropertyConstant.SERVER_PORT_DEFAULT_VALUE.toString());
                }else{
                    propertyUtil.saveSingleProperty( PropertyConstant.SERVER_PORT_KEY.toString(), adminConfigurationDTO.getServerPort());
                }
                if(adminConfigurationDTO.getStartWorkingHour()==null){
                    propertyUtil.saveSingleProperty( PropertyConstant.START_WORKING_HOUR_KEY.toString(), PropertyConstant.START_WORKING_HOUR_DEFAULT_VALUE.toString());
                }else{
                    propertyUtil.saveSingleProperty( PropertyConstant.START_WORKING_HOUR_KEY.toString(), adminConfigurationDTO.getStartWorkingHour());
                }
                if(adminConfigurationDTO.getStopWorkingHour()==null){
                    propertyUtil.saveSingleProperty( PropertyConstant.STOP_WORKING_HOUR_KEY.toString(), PropertyConstant.STOP_WORKING_HOUR_DEFAULT_VALUE.toString());
                }else{
                    propertyUtil.saveSingleProperty( PropertyConstant.STOP_WORKING_HOUR_KEY.toString(), adminConfigurationDTO.getStopWorkingHour());
                }
                if( adminConfigurationDTO.getVideoId().size()<1){
                    propertyUtil.saveSingleProperty( PropertyConstant.VIDEO_ID_TEST.toString(), PropertyConstant.VIDEO_ID_TEST_DEFAULT_VALUE.toString() );
                }else{
                    for( String str: adminConfigurationDTO.getVideoId() ){
                        videoId+=str.concat(",");
                    }
                    videoId = generalConverter.removeLastChar( videoId );
                    propertyUtil.saveSingleProperty( PropertyConstant.VIDEO_ID_TEST.toString(), videoId );
                }
                if( adminConfigurationDTO.getUrl().size()<1){
                    propertyUtil.saveSingleProperty( PropertyConstant.BROWSER_URL_TEST.toString(), PropertyConstant.BROWSER_URL_TEST_DEFAULT_VALUE.toString() );
                }else{
                    for (String str : adminConfigurationDTO.getUrl()){
                        url+=str.concat(",");
                    }
                    url = generalConverter.removeLastChar( url );
                    propertyUtil.saveSingleProperty( PropertyConstant.BROWSER_URL_TEST.toString(), url );
                }
                if( adminConfigurationDTO.getVideo_resolution().size()<1){
                    propertyUtil.saveSingleProperty( PropertyConstant.VIDEO_RESOLUTION_KEY.toString(), PropertyConstant.VIDEO_RESOLUTION_DEFAULT_VALUE.toString() );
                }else{
                    for (String str : adminConfigurationDTO.getVideo_resolution()){
                        videoResoution+=str.concat(",");
                    }
                    videoResoution = generalConverter.removeLastChar( videoResoution );
                    propertyUtil.saveSingleProperty( PropertyConstant.VIDEO_RESOLUTION_KEY.toString(), videoResoution );
                }
                if( adminConfigurationDTO.getLatency() == null ){
                    propertyUtil.saveSingleProperty( PropertyConstant.LATENCY_URL_KEY.toString(), PropertyConstant.LATENCY_URL_DEFAULT_VALUE.toString());
                }else{
                    propertyUtil.saveSingleProperty( PropertyConstant.LATENCY_URL_KEY.toString(), adminConfigurationDTO.getLatency());
                }
                if( adminConfigurationDTO.getDownload() == null ){
                    propertyUtil.saveSingleProperty( PropertyConstant.DOWNLOAD_URL_KEY.toString(), PropertyConstant.DOWNLOAD_URL_DEFAULT_VALUE.toString());
                }else{
                    propertyUtil.saveSingleProperty( PropertyConstant.DOWNLOAD_URL_KEY.toString(), adminConfigurationDTO.getDownload());
                }
                if( adminConfigurationDTO.getUpload() == null ){
                    propertyUtil.saveSingleProperty( PropertyConstant.UPLOAD_URL_KEY.toString(), PropertyConstant.UPLOAD_URL_DEFAULT_VALUE.toString());
                }else{
                    propertyUtil.saveSingleProperty( PropertyConstant.UPLOAD_URL_KEY.toString(), adminConfigurationDTO.getUpload());
                }
                if( adminConfigurationDTO.getVideoview().size()<1){
                    propertyUtil.saveSingleProperty( PropertyConstant.VIDEO_VIEW_KEY.toString(), PropertyConstant.VIDEO_VIEW_DEFAULT_VALUE.toString() );
                }else{
                    for( String str: adminConfigurationDTO.getVideoview() ){
                        videoView+=str.concat(",");
                    }
                    videoView = generalConverter.removeLastChar( videoView );
                    propertyUtil.saveSingleProperty( PropertyConstant.VIDEO_VIEW_KEY.toString(), videoView );
                }
            }catch (Exception e){
                Toast.makeText(ASecureActivity.this, stringFailGetConfiguration,Toast.LENGTH_SHORT).show();
            }
            propertyUtil.readFullProperty();
            delegate.onReloadConfigurationFinished();
        }
    }

    @Override
    public void onReloadConfigurationFinished() {
    }

    public void signOut( String email, String deviceId, boolean exit, String message ){
        urlBuilder.initiate();
        urlBuilder.getContentToBuild().put( RestConstant.MNUMBER.toString(), email);
        urlBuilder.buildUrl( RestConstant.TSK_LOGOUT.toString(), deviceId );
        if ( networkConnectivity.checkConnectivity() ){
            LogoutRest logoutRest = new LogoutRest(exit, message);
            logoutRest.execute();
        }
    }

    protected class LogoutRest extends ARestDialogGETAsyncTask{

        private boolean exit;
        private String message;

        public LogoutRest(boolean exit, String message) {
            this.exit = exit;
            this.message = message;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage( message );
            progressDialog.show();
        }

        @Override
        protected Context getContext() {
            return ASecureActivity.this;
        }

        @Override
        protected String getProgressDialogTitle() {
            return RestConstant.SIGN_OUT.toString();
        }

        @Override
        public String initUrl() {
            return urlBuilder.getUrlResult();
        }

        @Override
        public Class<?> initClassResult() {
            return BaseRESTDTO.class;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            BaseRESTDTO baseRESTDTO = null;
            try {
                baseRESTDTO = (BaseRESTDTO)objectResult;
            }catch (Exception e){
                Toast.makeText(ASecureActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
            try{
                if ( baseRESTDTO.getResult().equals( RestConstant.OK.toString() )){
                    propertyUtil.deleteFile( PropertyConstant.PROPERTIES_PATH.toString().concat( PropertyConstant.PROPERTY_FILE_NAME.toString() ) );
                    securityUtil.saveSingleProperty( PropertyConstant.LOGIN_STATUS_KEY.toString(), stringEmpty );
                    if( exit == true ){
                        exitApplication( ASecureActivity.this );
                    }else{
                        try{
                            if( networkConnectivity.checkConnectivityNoUI() ){
                                /*SharedPreferences settings = getSharedPreferences( "hasRunBefore", 0 );
                                SharedPreferences.Editor edit = settings.edit();
                                edit.putBoolean( "hasRun", true );
                                edit.commit();*/
                                gotoNextActivity(ActLogin.class, null, null );
                            }else{
                                exitApplication( ASecureActivity.this );
                            }
                        }catch (Exception e){
                            exitApplication( ASecureActivity.this);
                        }
                    }
                }else{
                    Toast.makeText( ASecureActivity.this, baseRESTDTO.getErr_msg(), Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText( ASecureActivity.this, "can not sign out", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * If true : password is expired, else password is valid
     * @return boolean
     */
    public PasswordExpiredDTO isPasswordExpired(){
        PasswordExpiredDTO passwordExpiredDTO = new PasswordExpiredDTO();
        Date expiredDate = generalConverter.getDateToComparator(securityUtil.getValuePropertyMap(PropertyConstant.PASSWORD_EXPIRED_DATE.toString()));
        Date currentDate = generalConverter.getDateToComparator(generalConverter.getDateToFormatDateHyphenSeparator(new Date() ) );
        int diffInDays = (int) ((expiredDate.getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24));
        passwordExpiredDTO.setDiffDays( diffInDays );
        if( diffInDays <= 2 ){
            passwordExpiredDTO.setExpired(true);
        }
        return passwordExpiredDTO;
    }

}
