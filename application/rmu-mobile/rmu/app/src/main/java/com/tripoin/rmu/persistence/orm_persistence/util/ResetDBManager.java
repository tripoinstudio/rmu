package com.tripoin.rmu.persistence.orm_persistence.util;

import android.util.Log;

import com.tripoin.rmu.view.activity.ActivityMain;
import com.tripoin.rmu.model.persist.SelfTestModel;
import com.tripoin.rmu.persistence.orm_persistence.service.SelfTestDBManager;

import java.util.Iterator;
import java.util.List;


/**
 * Created by Achmad Fauzi on 1/30/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class ResetDBManager {

    public ResetDBManager(ActivityMain actMain) {
        SelfTestDBManager.init(actMain);
    }

    public void resetSelfTestManager(){
        Log.d("REST", "WITHMANAGER");
        resetSelfTest();
    }

    public void resetSelfTestWithoutManager(){
        Log.d("REST", "WITHOUT-MANAGER");
    }


    private void resetSelfTest(){
        try{
            List<SelfTestModel> selfTestModels = SelfTestDBManager.getInstance().getAllData();
            Log.d("SELFTESTMODELRESET-1", String.valueOf(selfTestModels.size()));
            if( selfTestModels.size()>0 ){
                /*for( SelfTestModel model: selfTestModels ){
                    SelfTestDBManager.getInstance().deleteEntity( model.getId() );
                }*/
                for(Iterator<SelfTestModel> modelIterator = selfTestModels.iterator(); modelIterator.hasNext();){
                    SelfTestModel selfTestModel = modelIterator.next();
                    SelfTestDBManager.getInstance().deleteEntity( selfTestModel.getId() );
                }
                List<SelfTestModel> selfTestModels2 = SelfTestDBManager.getInstance().getAllData();
                Log.d("SELFTESTMODELRESET-2", String.valueOf(selfTestModels2.size()));
            }
            List<SelfTestModel> selfTestModels3 = SelfTestDBManager.getInstance().getAllData();
            Log.d("SELFTESTMODELRESET-3", String.valueOf(selfTestModels3.size()));
            //SelfTestDBManager.getInstance().updateStatement( "UPDATE sqlite_sequence set seq = 0 where name = ".concat("Tbl") );
        }catch (Exception e){
            Log.e("ERROR", "RESET SELFT TEST");
        }
    }

}
