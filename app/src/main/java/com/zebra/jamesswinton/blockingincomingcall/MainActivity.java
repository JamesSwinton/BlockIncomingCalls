package com.zebra.jamesswinton.blockingincomingcall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;
import com.android.internal.telephony.ITelephony;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  //
  private PermissionsHelper mPermissionsHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mPermissionsHelper = new PermissionsHelper(this, () -> {
      startService(new Intent(this, BlockCallsService.class));
      finish();
    });
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    mPermissionsHelper.onRequestPermissionsResult();
  }

//  @SuppressLint("MissingPermission")
//  private void test() {
//    ITelephony telephonyService;
//    try {
//      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//        TelecomManager tm = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
//        if (tm != null) {
//          boolean success = tm.endCall();
//          Log.i("", "");
//        }
//        return;
//      }
//
//      TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//      try {
//        Method m = tm.getClass().getDeclaredMethod("getITelephony");
//        m.setAccessible(true);
//        telephonyService = (ITelephony) m.invoke(tm);
//        Method[] methods = telephonyService.getClass().getMethods();
//        List<String> methodNames = new ArrayList<>();
//        for (Method method : methods) {
//          String methodName = method.getName();
//          methodNames.add(methodName);
//        }
//        telephonyService.endCall();
//        Toast.makeText(this, "Ending the call from: ", Toast.LENGTH_SHORT).show();
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
}