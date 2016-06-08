package ethiccoders.crowdweather;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RecoverySystem;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.AlarmClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.CalendarView;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.DigitalClock;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Timer;


public class MainActivity extends ActionBarActivity {
    private static TextView textViewRef;
    private static Button buttonSdm;
    private static DigitalClock digital;
    private static AnalogClock analog;
    TextView mTextFieldCondition;
    Firebase mRef;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    private String mRepeat;
    String ID;
  //  private long mRepeatTime;
   // private String mReapeatText;
  SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButtonclicklistner();
        findViewById(R.color.green);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Notification_reciever.class);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

              //  PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                Calendar firingCal= Calendar.getInstance();
                Calendar currentCal = Calendar.getInstance();

                firingCal.set(Calendar.HOUR_OF_DAY,13); // At the hour you wanna fire
                firingCal.set(Calendar.MINUTE,05); // Particular minute
                firingCal.set(Calendar.SECOND, 00); // particular second

                long intendedTime = firingCal.getTimeInMillis();
                long currentTime = currentCal.getTimeInMillis();

                if(intendedTime >= currentTime){
                    // you can add buffer time too here to ignore some small differences in milliseconds
                    // set from today
                    alarmManager.setRepeating(AlarmManager.RTC, intendedTime, AlarmManager.INTERVAL_DAY, pendingIntent);
                } else{
                    // set from next day
                    // you might consider using calendar.add() for adding one day to the current day
                    firingCal.add(Calendar.DAY_OF_MONTH, 8);
                    intendedTime = firingCal.getTimeInMillis();

                    alarmManager.setRepeating(AlarmManager.RTC, intendedTime, AlarmManager.INTERVAL_DAY, pendingIntent);
                }
                Toast.makeText(MainActivity.this, "Start Alarm", Toast.LENGTH_LONG)
                        .show();
                }







        });
    }



//public void open(View view) {

        // AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

        // alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
        //@Override
        // public void onClick(DialogInterface arg0, int arg1) {

        //Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
        //  Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
        //   onButtonclicklistner();
        //  }
        //});

        //    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
        //      @Override
        //          public void onClick(DialogInterface dialog, int which) {
        //              finish();
        //    }
//        });

        //AlertDialog alertDialog = alertDialogBuilder.create();
        //  alertDialog.show();

        //}

    public void onButtonclicklistner() {
        buttonSdm = (Button) findViewById(R.id.button);
        digital = (DigitalClock) findViewById(R.id.digitalClock);
        analog = (AnalogClock) findViewById(R.id.analogClock);

        buttonSdm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (digital.getVisibility() == DigitalClock.GONE) {
                            digital.setVisibility(DigitalClock.VISIBLE);
                            analog.setVisibility(AnalogClock.GONE);
                        } else {
                            digital.setVisibility(DigitalClock.GONE);
                            analog.setVisibility(AnalogClock.VISIBLE);
                        }
                    }
                }

        );
    }


}







