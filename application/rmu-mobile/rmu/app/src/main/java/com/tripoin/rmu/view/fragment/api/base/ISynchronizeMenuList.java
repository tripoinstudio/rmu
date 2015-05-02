package com.tripoin.rmu.view.fragment.api.base;

import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.OrderListModel;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 4:45 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface ISynchronizeMenuList {

    public void onPostFirstSyncOrderList(List<MenuModel> menuModels);

    public void onPostContSyncOrderList(List<MenuModel> menuModels);
}
