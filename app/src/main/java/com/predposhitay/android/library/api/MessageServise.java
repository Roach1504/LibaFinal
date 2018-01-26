package com.predposhitay.android.library.api;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.predposhitay.android.library.MainActivity;
import com.predposhitay.android.library.R;
import com.predposhitay.android.library.model.Message;
import com.predposhitay.android.library.model.MessageModel;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageServise extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    SharedPreferences message;
    SharedPreferences user;
    SharedPreferences setting;

    private Timer mTimer;
    private MyTimer mMyTimer;
    String last;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("servise", "Служба запущена");

        mTimer = new Timer();
        mMyTimer = new MyTimer();
        user = getSharedPreferences("user", Context.MODE_PRIVATE);
        message = getSharedPreferences("message", Context.MODE_PRIVATE);
        setting = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        mTimer.schedule(mMyTimer, 1000, 5 * 1000);
        last = message.getString("last", "9999999999");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("servise", "Служба остановлена");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("servise", "Служба создана");
    }

    void show(int cunt, String textLast){
        Log.e("PUSH", "ADD " + cunt);
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }


        long[] vibrate = new long[]{0, 2000};
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_mail_outline_amber_500_24dp)
                        .setContentTitle("+"+cunt+"")
                        .setContentText(textLast)
                        .setVibrate(vibrate)
                        .setAutoCancel(true);

        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra("push", "1");
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());


    }


    int count = 0;
    String tex = "";
    String last2;
    class MyTimer extends TimerTask {
        @Override
        public void run() {
            if (setting.getBoolean("Check", false)) {
                last2 = last;
                    App.getApi().getMessage(last, "1", "20").enqueue(new Callback<MessageModel>() {
                        @Override
                        public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                            if (response.message().equals("OK")) {
                                if (!response.body().getMessages().isEmpty()) {
                                    Log.e("size", response.body().getMessages().size()+" int");
                                    for (Message i : response.body().getMessages()) {
                                        Log.e("PUSH", i.getAuthorId() + ", " + user.getString("id", "-1"));
                                        if(!i.getAuthorId().equals(user.getString("id", "-1"))){
                                            count++;
                                            tex = i.getText();
                                            last = i.getId();
                                        }
                                    }
                                    if(!last2.equals(last)) {
                                        show(count, response.body().getMessages().get(response.body().getMessages().size() - 1).getText());
                                        count = 0;
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MessageModel> call, Throwable t) {

                        }
                    });
            }
        }
    }
}
