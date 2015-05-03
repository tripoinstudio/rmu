package com.tripoin.rmu.view.fragment.api;

import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.model.persist.TrainModel;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 4:45 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface ISynchronizeMaster {

    public void onPostFirstSyncOrderList(List<CarriageModel> carriageModels, List<SeatModel> seatModels, List<TrainModel> trainModels);

    public void onPostContSyncOrderList(List<CarriageModel> carriageModels, List<SeatModel> seatModels, List<TrainModel> trainModels);
}
