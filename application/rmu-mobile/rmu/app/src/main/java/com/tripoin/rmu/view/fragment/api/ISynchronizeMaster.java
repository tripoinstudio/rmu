package com.tripoin.rmu.view.fragment.api;

import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.model.persist.TrainModel;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 4:45 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public interface ISynchronizeMaster {

    public void onPostFirstSyncMasterCarriage(List<CarriageModel> carriageModels);

    public void onPostContSyncMasterCarriage(List<CarriageModel> carriageModels);

    public void onPostFirstSyncMasterSeat(List<SeatModel> seatModels);

    public void onPostContSyncMasterSeat(List<SeatModel> seatModels);

    public void onPostFirstSyncMasterTrain(List<TrainModel> trainModels);

    public void onPostContSyncMasterTrain(List<TrainModel> trainModels);
}
