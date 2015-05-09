package com.tripoin.rmu.feature.scheduler.listener;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tripoin.rmu.feature.scheduler.trigger.AlarmManagerStarter;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Achmad Fauzi on 5/9/2015 : 10:50 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class ServiceBroadcastListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManagerStarter alarmManagerStarter;
        Intent schedulerIntent = new Intent( context, SchedulerServiceListener.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, schedulerIntent, 0);
        alarmManagerStarter = new AlarmManagerStarter( context, null, pendingIntent);

        if ( intent.getAction().equals(ViewConstant.RESTART_SERVICE.toString()) ){
            alarmManagerStarter.startAlarmManager();
        }else{
            Intent i = new Intent( context, SchedulerServiceListener.class );
            context.startService( i );
        }
    }
}
