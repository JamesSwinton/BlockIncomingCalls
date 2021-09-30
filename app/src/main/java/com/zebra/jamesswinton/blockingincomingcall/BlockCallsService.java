package com.zebra.jamesswinton.blockingincomingcall;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class BlockCallsService extends Service {

  private IncomingCallReceiver mIncomingCallReceiver;

  @Override
  public void onCreate() {
    super.onCreate();

    // Register Receiver
    mIncomingCallReceiver = new IncomingCallReceiver();
    registerReceiver(mIncomingCallReceiver,
        new IntentFilter("android.intent.action.PHONE_STATE"));

    // StartUp
    startForeground(NotificationHelper.NOTIFICATION_ID,
        NotificationHelper.createNotification(this));
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    return START_STICKY;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    unregisterReceiver(mIncomingCallReceiver);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
