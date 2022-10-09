package com.fragment.android_suz.ServiceNotification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationCompat;

import com.fragment.android_suz.R;
import com.fragment.android_suz.StartActivity;
import com.fragment.android_suz.model.promo.Promo;
import com.fragment.android_suz.model.stoks.Storage;
import com.fragment.android_suz.utils.ApiUtils;
import com.fragment.android_suz.utils.AppDelegate;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyServiceNotification extends Service {
    private static final String TAG = "MyServiceNotification";
    private ScheduledExecutorService mScheduledExecutorService;
    private NotificationManager mManager;
    private NotificationCompat.Builder mBuilder;
    Storage mStorage;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        Log.d("array", "contactList-");
        mStorage = new Storage(((AppDelegate) getApplication()).getmMySUZDatabase().getMySUZDao());
        mScheduledExecutorService = Executors.newScheduledThreadPool( 1);
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = getNotificationBuilder()
                .setSmallIcon(R.drawable.pion)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.appstore));

    }

    private NotificationCompat.Builder getNotificationBuilder() {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return new NotificationCompat.Builder(this);
        } else {
            String channel_id = "my_channel_id";

            if(mManager.getNotificationChannel(channel_id) == null) { NotificationChannel channel = new NotificationChannel(channel_id, "Text for user",
                    NotificationManager.IMPORTANCE_LOW);
                mManager.createNotificationChannel(channel);
            }

            return new NotificationCompat.Builder(this, channel_id);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        alarmNotification(getApplicationContext());
        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
               /* ApiUtils.getApiService().getAllPromos()
                        .doOnSuccess(Promo->mStorage.insertPromo(Promo))
                        .onErrorReturn(throwable ->
                                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getPromo() : null)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(Promo -> {

                                    startForeground(123,getNotification("start notification",mStorage.getStartAndEndPromo()));
                                    mManager.notify(123, getNotification("time is Promo" , mStorage.getStartAndEndPromo()));
                                    showMessage(R.string.succses);
                                },
                                throwable -> {
                                    showMessage(R.string.request_error);
                                });*/
            }

        },0,10, TimeUnit.MINUTES);

        return START_STICKY;
    }

    public void alarmNotification(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);
        long time = calendar.getTimeInMillis();
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), NotifResiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(context, 123, intent, PendingIntent.FLAG_IMMUTABLE);


        am.setRepeating(AlarmManager.RTC_WAKEUP, time,AlarmManager.INTERVAL_HALF_HOUR, pi);

    }

    @NonNull
    private Notification getNotification(String contentText, List<Promo> header){
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra(String.valueOf(System.currentTimeMillis()), contentText);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2123, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.InboxStyle notification = new NotificationCompat.InboxStyle();
        for(int i = 0; i < header.size(); i++) {
            notification.addLine(header.get(i).getBrend() + "-" + header.get(i).getEndPromo());
        }

        return mBuilder.setContentText(contentText).setContentIntent(pendingIntent).setStyle(notification).build();

    }
    @Override
    public void onDestroy(){
        Log.d(TAG,  "onDestroy: ");
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();
    }
}
