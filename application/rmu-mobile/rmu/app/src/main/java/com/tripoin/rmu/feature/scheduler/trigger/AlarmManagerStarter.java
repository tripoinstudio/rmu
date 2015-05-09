package com.tripoin.rmu.feature.scheduler.trigger;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tripoin.rmu.feature.scheduler.constant.ISchedulerConstant;
import com.tripoin.rmu.feature.scheduler.listener.SchedulerServiceListener;

import java.util.Calendar;


/**
 * Created by Achmad Fauzi on 11/23/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Daemon Class to start/ restart the Scheduler
 */
public class AlarmManagerStarter {

    private Context context;
    private PendingIntent pendingIntent;
    private Intent myIntent;
    private AlarmManager alarmManager;
    private Service service;

    public AlarmManagerStarter(Context context, Service service, PendingIntent pendingIntent ) {
        if ( context != null ){
            this.context = context;
        }else{
            this.service = service;
        }
        this.pendingIntent = pendingIntent;
    }

    public void startAlarmManager(){
        Log.d("ALARM MANAGER", "STARTED");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, ISchedulerConstant.SPACE_TIME);

        if ( context != null ){
            myIntent = new Intent( context, SchedulerServiceListener.class);
            pendingIntent = PendingIntent.getService( context, 0, myIntent, 0);
            if ( alarmManager == null ){
                alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), ISchedulerConstant.SPACE_TIME * 1000, pendingIntent);
            }else{
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), ISchedulerConstant.SPACE_TIME * 1000, pendingIntent);
            }
        }else{
            myIntent = new Intent( service, SchedulerServiceListener.class );
            pendingIntent = PendingIntent.getService( service, 0, myIntent, 0);
            alarmManager = (AlarmManager) service.getSystemService(Context.ALARM_SERVICE);
        }
    }


    public void stopSchedulerService(){
        myIntent = new Intent( context, SchedulerServiceListener.class );
        context.stopService( myIntent );
    }

}
