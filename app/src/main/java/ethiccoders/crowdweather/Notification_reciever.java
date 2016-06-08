package ethiccoders.crowdweather;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.Calendar;
import java.util.TimeZone;

import ethiccoders.crowdweather.Repeating_activity;

/**
 * Created by Santhosh on 5/26/2016.
 */
public class Notification_reciever  extends  BroadcastReceiver {

    boolean isNotificAtive = false;

    int notifID = 33;
    private long intervalDay;


    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, Repeating_activity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Notification title")
                .setContentText("Notification text")
                .setAutoCancel(true);
        //  .setDefaults(NotificationCompat.DEFAULT_SOUND)
        //  .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        notificationManager.notify(100, builder.build());
        notificationManager.cancel((int) System.currentTimeMillis());
        notificationManager.equals(NotificationManager.INTERRUPTION_FILTER_ALARMS);
      // {
            //Calendar calendar=Calendar.getInstance();
         //   calendar.set(Calendar.HOUR_OF_DAY, 20);
       //     calendar.set(Calendar.MINUTE,33);



          //  return;
        }
    }

    //if (isNotificAtive) {
         //   notificationManager.cancel(notifID);




//}


