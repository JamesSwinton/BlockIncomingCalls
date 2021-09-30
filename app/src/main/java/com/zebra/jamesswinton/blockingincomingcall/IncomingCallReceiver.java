package com.zebra.jamesswinton.blockingincomingcall;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.telephony.ITelephony;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class IncomingCallReceiver extends BroadcastReceiver {
  @Override
  @SuppressLint("MissingPermission")
  public void onReceive(Context context, Intent intent) {
    try {
      String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
      if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
          TelecomManager tm = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
          if (tm != null) {
            boolean success = tm.endCall();
            Toast.makeText(context, "Call Ended: " + success, Toast.LENGTH_LONG).show();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
