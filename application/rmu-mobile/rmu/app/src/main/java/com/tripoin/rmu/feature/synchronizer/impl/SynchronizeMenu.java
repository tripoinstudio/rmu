package com.tripoin.rmu.feature.synchronizer.impl;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tripoin.rmu.feature.synchronizer.base.ASynchronizeData;
import com.tripoin.rmu.model.DTO.image.ImageItemDTO;
import com.tripoin.rmu.model.DTO.menu.MenuDTO;
import com.tripoin.rmu.model.DTO.menu.MenuItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.ImageModel;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.ImageMenuDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.MenuDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.IMenuPost;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.rest.impl.MenuListRest;
import com.tripoin.rmu.util.ImageDownloader;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeMenuList;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/1/2015 : 12:07 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class SynchronizeMenu extends ASynchronizeData implements IMenuPost, ISynchronizeMenuList{

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;
    private ISynchronizeMenuList iSynchronizeMenuList;
    private List<MenuModel> menuModels;

    protected SynchronizeMenu(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    public SynchronizeMenu(PropertyUtil securityUtil, Context context, String tableName, ISynchronizeMenuList iSynchronizeMenuList) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.securityUtil = securityUtil;
        this.iSynchronizeMenuList = iSynchronizeMenuList;
        MenuDBManager.init(context);
        ImageMenuDBManager.init(context);
    }

    @Override
    public void updateContent(String latestVersion) {
        this.latestVersion = latestVersion;

        //select new Object
        MenuListRest menuListRest = new MenuListRest(this) {
            @Override
            public Context getContext() {
                return context;
            }
        };
        menuListRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void selectRelatedTable() {
        onPostContSyncOrderList(MenuDBManager.getInstance().getAllData());
    }

    @Override
    public void onPostSyncMenu(Object objectResult) {
        if(objectResult != null){
            MenuDTO menuDTO = (MenuDTO) objectResult;
            /*List<MenuModel> menuModels = new ArrayList<MenuModel>();*/
            MenuModel menuModel = null;
            ImageModel imageModel = null;
            for(MenuItemDTO itemDTO : menuDTO.getMenuItemDTOs()){
                menuModel = new MenuModel();
                menuModel.setMenuName(itemDTO.getMenuName());
                menuModel.setMenuPrice(itemDTO.getMenuPrice());
                menuModel.setMenuStock(itemDTO.getMenuStock());
                menuModel.setMenuType(itemDTO.getMenuType());
                menuModel.setMenuImageURL(itemDTO.getMenuImage());
                menuModel.setMenuCode(itemDTO.getMenuCode());
                menuModel.setMenuRating(itemDTO.getMenuRating());
                MenuModel menuModelCompare = MenuDBManager.getInstance().getDataFromQuery(ModelConstant.MENU_CODE, itemDTO.getMenuCode());
                if(menuModelCompare == null) {
                    MenuDBManager.getInstance().insertEntity(menuModel);
                }else{
                    menuModel.setId(menuModelCompare.getId());
                    MenuDBManager.getInstance().updateEntity(menuModel);
                }
                /*new ImageDownloader(RestConstant.BASE_URL.toString().concat(RestConstant.IMAGE.toString()).concat(itemDTO.getMenuImage()), PropertyConstant.PROPERTIES_PATH.toString().concat(params[0].toString())).downloadImage();*/
                new DownloadImage().execute(itemDTO.getMenuImage());
                MenuModel menuModelGetImage = MenuDBManager.getInstance().getDataFromQuery(ModelConstant.MENU_CODE, itemDTO.getMenuCode());

                for(ImageItemDTO imageItemDTO : itemDTO.getImageItemDTOList()){
                    imageModel = new ImageModel();
                    imageModel.setImageCode(imageItemDTO.getImageCode());
                    imageModel.setImageName(imageItemDTO.getImageName());
                    imageModel.setImageStatus(imageItemDTO.getImageStatus());
                    imageModel.setMenuId(menuModelGetImage.getId());
                    ImageModel imageModelCompare = ImageMenuDBManager.getInstance().getDataFromQuery(ModelConstant.IMAGE_CODE, imageItemDTO.getImageCode());
                    if(imageModelCompare == null){
                        ImageMenuDBManager.getInstance().insertEntity(imageModel);
                    }else{
                        imageModel.setId(imageModelCompare.getId());
                        ImageMenuDBManager.getInstance().updateEntity(imageModel);
                    }
                }
            }

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);

            menuModels = MenuDBManager.getInstance().getAllData();
            iSynchronizeMenuList.onPostFirstSyncOrderList(menuModels);
        }else{
            Log.d("Sync Menu Object Result", "not found");
        }
    }

    @Override
    public void onPostFirstSyncOrderList(List<MenuModel> menuModels) {
        iSynchronizeMenuList.onPostFirstSyncOrderList(menuModels);
    }

    @Override
    public void onPostContSyncOrderList(List<MenuModel> menuModels) {
        iSynchronizeMenuList.onPostContSyncOrderList(menuModels);
    }

    private class DownloadImage extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            new ImageDownloader(RestConstant.BASE_URL.toString().concat(RestConstant.IMAGE.toString()).concat(params[0].toString()), PropertyConstant.PROPERTIES_PATH.toString().concat(params[0].toString())).downloadImage();
            return null;
        }
    }

}
