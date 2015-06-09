package com.tripoin.rmu.feature.scheduler.listener;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.scheduler.constant.IOrderStatusConstant;
import com.tripoin.rmu.feature.synchronizer.impl.BackgroundSynchronizeApplicationVersion;
import com.tripoin.rmu.feature.synchronizer.impl.BackgroundSynchronizeOrderList;
import com.tripoin.rmu.feature.version.IVersionPost;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.rest.api.IMasterVersionPost;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.ActivityMain;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderList;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderDetail;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/9/2015 : 10:30 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class SchedulerServiceListener extends Service implements ISynchronizeOrderList, IVersionPost{

    private PropertyUtil securityUtil;
    private NotificationManager mNotificationManager;
    private int newSize = 0;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        intent.setAction( ViewConstant.RESTART_SERVICE.toString() );
        sendBroadcast( intent );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), this);
        getNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    public void getNotification(){
        BackgroundSynchronizeOrderList synchronizeOrderList = new BackgroundSynchronizeOrderList(securityUtil, this, ModelConstant.REST_ORDER_HEADER_TABLE,this);
        synchronizeOrderList.detectVersionDiff();

        BackgroundSynchronizeApplicationVersion synchronizeApplicationVersion = new BackgroundSynchronizeApplicationVersion(securityUtil, this, ModelConstant.REST_APPLICATION_VERSION.toString(), this);
        synchronizeApplicationVersion.detectVersionDiff();
    }

    @Override
    public void onPostFirstSyncOrderList(List<OrderListModel> orderListModels) {
        for(OrderListModel orderListModel: orderListModels){
            if(orderListModel.getProcessStatus() == IOrderStatusConstant.DONE){
                buildNotification(orderListModel);
            }
        }
        if(newSize == 0){
            notifRunningService();
        }else{
            mNotificationManager.cancel(R.string.app_name);
        }
    }

    @Override
    public void onPostContSyncOrderList(List<OrderListModel> orderListModels) {
        for(OrderListModel orderListModel: orderListModels){
            if(orderListModel.getProcessStatus() == IOrderStatusConstant.PREPARED){
                buildNotification(orderListModel);
            }
        }
        if(newSize != 0){
            mNotificationManager.cancel(R.string.app_name);
        }
    }

    private void notifRunningService(){
        Notification notification = new Notification(R.drawable.ic_launcher, ViewConstant.SERVICE_RUNNING.toString(), System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, FragmentOrderDetail.class);
        notificationIntent.putExtra("ORDER_ID", "");
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
        notification.setLatestEventInfo(this, ViewConstant.DEFAULT_ACTION_BAR_TITLE.toString(), ViewConstant.SERVICE_RUNNING.toString(), intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotificationManager.notify(R.string.app_name, notification);
    }

    private void buildNotification(OrderListModel orderListModel){
        newSize++;
        String orderId = orderListModel.getOrderId();
        Notification notification = new Notification(R.drawable.ic_launcher, orderId.concat(" - Prepared"), System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, ActivityMain.class);
        notificationIntent.setAction(orderId);
        notificationIntent.putExtra("ORDER_ID", orderId);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        PendingIntent intent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
        notification.setLatestEventInfo(this, ViewConstant.DEFAULT_ACTION_BAR_TITLE.toString(), orderId.concat(" - Ready to deliver"), intent);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        try{
            mNotificationManager.notify(Integer.parseInt(orderListModel.getOrderId().substring(orderId.length()-5)), notification);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onPostVersion(String latestVersion) {
        Notification notification = new Notification(R.drawable.ic_update_software, ViewConstant.NEW_VERSION.toString(), System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, ActivityMain.class);
        notificationIntent.setAction("UPDATE");
        notificationIntent.putExtra("LATEST_VERSION", latestVersion);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        PendingIntent intent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
        notification.setLatestEventInfo(this, ViewConstant.DEFAULT_ACTION_BAR_TITLE.toString(), ViewConstant.NEW_VERSION.toString(), intent);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        try{
            mNotificationManager.notify(R.string.app_name, notification);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
