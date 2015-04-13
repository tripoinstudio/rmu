package com.tripoin.rmumobile.persistence.orm_persistence.util;

import android.util.Log;

import com.j256.ormlite.table.TableUtils;

import java.util.Iterator;
import java.util.List;

import id.co.telkomsigma.ariumm_force.model.BSTModel;
import id.co.telkomsigma.ariumm_force.model.BSTTempModel;
import id.co.telkomsigma.ariumm_force.model.DownloadModel;
import id.co.telkomsigma.ariumm_force.model.FailedTestStatusModel;
import id.co.telkomsigma.ariumm_force.model.LatencyModel;
import id.co.telkomsigma.ariumm_force.model.SelfTestModel;
import id.co.telkomsigma.ariumm_force.model.VVSTModel;
import id.co.telkomsigma.ariumm_force.model.VVSTTempModel;
import id.co.telkomsigma.ariumm_force.model.enumeration.ModelConstant;
import id.co.telkomsigma.ariumm_force.persistence.orm_persistence.service.BSTDBManager;
import id.co.telkomsigma.ariumm_force.persistence.orm_persistence.service.BSTTempDBManager;
import id.co.telkomsigma.ariumm_force.persistence.orm_persistence.service.DownloadDBManager;
import id.co.telkomsigma.ariumm_force.persistence.orm_persistence.service.FailedTestStatusDBManager;
import id.co.telkomsigma.ariumm_force.persistence.orm_persistence.service.LatencyDBManager;
import id.co.telkomsigma.ariumm_force.persistence.orm_persistence.service.SelfTestDBManager;
import id.co.telkomsigma.ariumm_force.persistence.orm_persistence.service.VVSTDBManager;
import id.co.telkomsigma.ariumm_force.persistence.orm_persistence.service.VVSTTempDBManager;
import id.co.telkomsigma.ariumm_force.view.activity.ActMain;

/**
 * Created by Achmad Fauzi on 1/30/2015.
 * achmad.fauzi@sigma.co.id
 */
public class ResetDBManager {

    public ResetDBManager(ActMain actMain) {
        SelfTestDBManager.init( actMain );
        BSTDBManager.init( actMain );
        BSTTempDBManager.init( actMain );
        DownloadDBManager.init( actMain );
        LatencyDBManager.init( actMain );
        VVSTDBManager.init( actMain );
        VVSTTempDBManager.init( actMain );
    }

    public void resetSelfTestManager(){
        Log.d("REST", "WITHMANAGER");
        resetSelfTest();
        resetLatency();
        resetDownload();
        resetTempVVST();
        resetVVST();
        resetTempBST();
        resetBST();
    }

    public void resetSelfTestWithoutManager(){
        Log.d("REST", "WITHOUT-MANAGER");
        resetLatency();
        resetDownload();
        resetTempVVST();
        resetVVST();
        resetTempBST();
        resetBST();
    }

    public void resetFailedStatus(){
        try{
            List<FailedTestStatusModel> failedTestStatusModels = FailedTestStatusDBManager.getInstance().getAllData();
            if( failedTestStatusModels.size()>0 ){
                for( FailedTestStatusModel model: failedTestStatusModels){
                    FailedTestStatusDBManager.getInstance().deleteEntity( model.getId() );
                }
            }
        }catch (Exception e){
            Log.e("ERROR", "RESET Failed Status TEST");
        }
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

    private void resetLatency(){
        List<LatencyModel> latencyModels = LatencyDBManager.getInstance().getAllData();
        try{
            /*for( LatencyModel model: latencyModels ){
                LatencyDBManager.getInstance().deleteEntity( model.getId() );
            }*/
            for(Iterator<LatencyModel> modelIterator = latencyModels.iterator(); modelIterator.hasNext();){
                LatencyModel latencyModel = modelIterator.next();
                LatencyDBManager.getInstance().deleteEntity(latencyModel.getId());
            }
        }catch (Exception e){
            Log.e("ERROR", "RESET LATENCY TEST");
        }

    }

    private void resetDownload(){
        List<DownloadModel> downloadModels = DownloadDBManager.getInstance().getAllData();
        try{
            /*for( DownloadModel model: downloadModels ){
                DownloadDBManager.getInstance().deleteEntity( model.getId() );
            }*/
            for(Iterator<DownloadModel> modelIterator = downloadModels.iterator(); modelIterator.hasNext();){
                DownloadModel downloadModel = modelIterator.next();
                DownloadDBManager.getInstance().deleteEntity(downloadModel.getId());
            }
        }catch ( Exception e){
            Log.e("ERROR", "RESET LATENCY TEST");
        }
    }

    private void resetTempVVST(){
        List<VVSTTempModel> vvstTempModels = VVSTTempDBManager.getInstance().getAllData();
        try{
            /*for( VVSTTempModel model: vvstTempModels ){
                VVSTTempDBManager.getInstance().deleteEntity( model.getId() );
            }*/
            for(Iterator<VVSTTempModel> modelIterator = vvstTempModels.iterator(); modelIterator.hasNext();){
                VVSTTempModel vvstTempModel = modelIterator.next();
                VVSTTempDBManager.getInstance().deleteEntity(vvstTempModel.getId());
            }
        }catch ( Exception e){
            Log.e("ERROR", "RESET VVST TEMP TEST");
        }
    }

    private void resetVVST(){
        List<VVSTModel> vvstModels = VVSTDBManager.getInstance().getAllData();
        try{
            /*for( VVSTModel model: vvstModels ){
                VVSTDBManager.getInstance().deleteEntity( model.getId() );
            }*/
            for(Iterator<VVSTModel> modelIterator = vvstModels.iterator(); modelIterator.hasNext();){
                VVSTModel vvstModel = modelIterator.next();
                VVSTDBManager.getInstance().deleteEntity(vvstModel.getId());
            }
        }catch ( Exception e){
            Log.e("ERROR", "RESET VVST TEST");
        }
    }

    private void resetTempBST(){
        List<BSTTempModel> bstTempModels = BSTTempDBManager.getInstance().getAllData();
        try{
            /*for( BSTTempModel model: bstTempModels ){
                BSTTempDBManager.getInstance().deleteEntity( model.getId() );
            }*/
            for(Iterator<BSTTempModel> modelIterator = bstTempModels.iterator(); modelIterator.hasNext();){
                BSTTempModel bstTempModel = modelIterator.next();
                BSTTempDBManager.getInstance().deleteEntity(bstTempModel.getId());
            }
        }catch ( Exception e){
            Log.e("ERROR", "RESET BST TEMP TEST");
        }
    }

    private void resetBST(){
        List<BSTModel> bstModels = BSTDBManager.getInstance().getAllData();
        try{
            /*for( BSTModel model: bstModels ){
                BSTDBManager.getInstance().deleteEntity( model.getId() );
            }*/
            for(Iterator<BSTModel> modelIterator = bstModels.iterator(); modelIterator.hasNext();){
                BSTModel bstModel = modelIterator.next();
                BSTDBManager.getInstance().deleteEntity(bstModel.getId());
            }
        }catch ( Exception e){
            Log.e("ERROR", "RESET BST TEST");
        }
    }

}
