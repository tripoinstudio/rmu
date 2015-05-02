package com.tripoin.rmu.view.fragment.impl;

import android.content.Context;
import android.util.Log;

import com.tripoin.rmu.model.DTO.menu.MenuDTO;
import com.tripoin.rmu.model.DTO.menu.MenuItemDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.MenuDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.IMenuPost;
import com.tripoin.rmu.rest.impl.MenuListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.base.ASynchronizeData;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/1/2015 : 12:07 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class SynchronizeMenu extends ASynchronizeData implements IMenuPost{

    private String tableName;
    private Context context;
    private PropertyUtil securityUtil;
    private String latestVersion;

    protected SynchronizeMenu(PropertyUtil securityUtil, Context context) {
        super(securityUtil, context);
    }

    protected SynchronizeMenu(PropertyUtil securityUtil, Context context, String tableName) {
        super(securityUtil, context);
        this.tableName = tableName;
        this.context = context;
        this.securityUtil = securityUtil;
        MenuDBManager.init(context);
    }

    @Override
    public void updateContent(String latestVersion) {
        this.latestVersion = latestVersion;
        //drop
        MenuDBManager.getInstance().executeRaw("Delete from ".concat(ModelConstant.MENU_TABLE));

        //select new Object
        MenuListRest menuListRest = new MenuListRest(this) {
            @Override
            protected Context getContext() {
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

    }


    @Override
    public void onPostSyncMenu(Object objectResult) {
        if(objectResult != null){
            MenuDTO menuDTO = (MenuDTO) objectResult;
            /*List<MenuModel> menuModels = new ArrayList<MenuModel>();*/
            MenuModel menuModel = null;
            for(MenuItemDTO itemDTO : menuDTO.getMenuItemDTOs()){
                menuModel = new MenuModel();
                menuModel.setMenuName(itemDTO.getMenuName());
                menuModel.setMenuPrice(itemDTO.getMenuPrice());
                menuModel.setMenuType(itemDTO.getMenuType());
                menuModel.setMenuImageURL(itemDTO.getMenuImage());
                /*menuModels.add(menuModel);*/
                MenuDBManager.getInstance().insertEntity(menuModel);
            }

            Log.d("MENU", "post detect version");
            List<MenuModel> menuModels = MenuDBManager.getInstance().getAllData();
            for(MenuModel model: menuModels){
                Log.d("MenuModel", model.toString());
            }

            VersionModel versionModel = VersionDBManager.getInstance().selectCustomVersionModel(ModelConstant.VERSION_NAMETABLE, getTableName());
            versionModel.setVersionTimestamp(latestVersion);
            VersionDBManager.getInstance().updateEntity(versionModel);
        }else{
            Log.d("Sync Menu Object Result", "not found");
        }
    }
}
