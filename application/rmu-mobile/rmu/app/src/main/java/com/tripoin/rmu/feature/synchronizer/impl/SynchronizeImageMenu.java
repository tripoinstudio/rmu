package com.tripoin.rmu.feature.synchronizer.impl;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tripoin.rmu.feature.synchronizer.base.ASynchronizeData;
import com.tripoin.rmu.model.DTO.image.ImageDTO;
import com.tripoin.rmu.model.DTO.image.ImageItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.ImageModel;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.ImageMenuDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.MenuDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.IImageMenuPost;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.rest.impl.ImageMenuRest;
import com.tripoin.rmu.util.ImageDownloader;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeImageMenu;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/1/2015 : 12:07 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class SynchronizeImageMenu extends ASynchronizeData implements IImageMenuPost, ISynchronizeImageMenu{

    private String tableName;
    private String menuCode;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;
    private ISynchronizeImageMenu iSynchronizeImageMenu;
    private List<ImageModel> imageModels;

    protected SynchronizeImageMenu(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    public SynchronizeImageMenu(PropertyUtil securityUtil, Context context, String tableName, String menuCode, ISynchronizeImageMenu iSynchronizeImageMenu) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.menuCode = menuCode;
        this.context = context;
        this.securityUtil = securityUtil;
        this.iSynchronizeImageMenu = iSynchronizeImageMenu;
        ImageMenuDBManager.init(context);
    }

    @Override
    public void updateContent(String latestVersion) {
        this.latestVersion = latestVersion;

        ImageMenuRest imageMenuRest = new ImageMenuRest(this) {
            @Override
            public Context getContext() {
                return context;
            }

            @Override
            public String getMenuCode() {
                return menuCode;
            }
        };
        imageMenuRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void selectRelatedTable() {
        onPostContSyncImageMenu(ImageMenuDBManager.getInstance().getAllData());
    }

    @Override
    public void onPostSyncImageMenu(Object objectResult) {
        if(objectResult != null){
            ImageDTO imageMenuDTO = (ImageDTO) objectResult;
            ImageModel imageMenuModel = null;
            for(ImageItemDTO itemDTO : imageMenuDTO.getImageItemDTOs()){
                imageMenuModel = new ImageModel();
                imageMenuModel.setImageCode(itemDTO.getImageCode());
                imageMenuModel.setImageName(itemDTO.getImageName());
                imageMenuModel.setImageStatus(itemDTO.getImageStatus());
                MenuModel menuModel = MenuDBManager.getInstance().getDataFromQuery(ModelConstant.MENU_CODE, menuCode);
                ImageModel imageMenuModelCompare = ImageMenuDBManager.getInstance().getDataFromQuery(ModelConstant.IMAGE_CODE, itemDTO.getImageCode());
                imageMenuModel.setMenuId(menuModel.getId());
                if(imageMenuModelCompare == null) {
                    ImageMenuDBManager.getInstance().insertEntity(imageMenuModel);
                }else{
                    imageMenuModel.setId(menuModel.getId());
                    ImageMenuDBManager.getInstance().updateEntity(imageMenuModel);
                }
                new DownloadImage().execute(itemDTO.getImageName());
            }

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);

            imageModels = ImageMenuDBManager.getInstance().getAllData();
            iSynchronizeImageMenu.onPostFirstSyncImageMenu(imageModels);
        }else{
            Log.d("Sync Image", "not found");
        }
    }

    @Override
    public void onPostFirstSyncImageMenu(List<ImageModel> imageModels) {
        iSynchronizeImageMenu.onPostFirstSyncImageMenu(imageModels);
    }

    @Override
    public void onPostContSyncImageMenu(List<ImageModel> imageModels) {
        iSynchronizeImageMenu.onPostContSyncImageMenu(imageModels);
    }

    private class DownloadImage extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            new ImageDownloader(RestConstant.BASE_URL.toString().concat(RestConstant.IMAGE.toString()).concat(params[0].toString()), PropertyConstant.PROPERTIES_PATH.toString().concat(params[0].toString())).downloadImage();
            return null;
        }
    }

}
