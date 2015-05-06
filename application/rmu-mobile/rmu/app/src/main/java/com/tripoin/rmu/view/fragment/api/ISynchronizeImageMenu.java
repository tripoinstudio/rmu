package com.tripoin.rmu.view.fragment.api;

import com.tripoin.rmu.model.persist.ImageModel;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 4:45 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface ISynchronizeImageMenu {

    public void onPostFirstSyncImageMenu(List<ImageModel> imageModels);

    public void onPostContSyncImageMenu(List<ImageModel> imageModels);
}
