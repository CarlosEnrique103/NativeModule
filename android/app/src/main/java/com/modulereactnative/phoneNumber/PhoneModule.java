package com.modulereactnative.phoneNumber;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import android.telephony.TelephonyManager;
import android.provider.Settings;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import androidx.core.content.ContextCompat;


public class PhoneModule extends ReactContextBaseJavaModule {
  private static ReactApplicationContext reactContext;

  public PhoneModule( ReactApplicationContext context) {

    super(context);
    reactContext = context;
  }
//  public static final String TELECOM_SERVICE = "telelcom";


  @Override
  public String getName() {
    return "PhoneModule";
  }

  @ReactMethod
  public void getPhoneNumber(String phone) {
    Log.d("PhoneModule", "Create event called with name: " + phone);
  }

  @ReactMethod
  public void getPhone(Promise promise) {


//    TelephonyManager tm = (TelephonyManager) reactContext.getSystemService(TELECOM_SERVICE);
//    String details = tm.getSimSerialNumber() + "\n" + tm.getDeviceId() + "\n" + tm.getNetworkOperatorName()
//            + "\n" + tm.getNetworkCountryIso();
//    Log.d( tm.getSimSerialNumber())
//    String android_id = Settings.Secure.getString(reactContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    try {
//      if(android.os.Build.VERSION.SDK_INT >= 29) {

//        promise.resolve("Si tienes los permisos " + " tiene los permisoos garantizado: " + ContextCompat.checkSelfPermission(reactContext, READ_PHONE_STATE) + " Tiene los permisos de phone numbers:" + ContextCompat.checkSelfPermission(reactContext, READ_PHONE_STATE) +  " tiene los permisoos SMS: " + ContextCompat.checkSelfPermission(reactContext, READ_SMS));
      if ( ContextCompat.checkSelfPermission(reactContext, READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED  && ContextCompat.checkSelfPermission(reactContext, READ_SMS) == PackageManager.PERMISSION_GRANTED) {
//        if (ContextCompat.checkSelfPermission(reactContext, READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(reactContext, READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(reactContext, READ_SMS) == PackageManager.PERMISSION_GRANTED) {
//          TelephonyManager telephonyManager = (TelephonyManager) reactContext.getSystemService(TELECOM_SERVICE);
//          String phoneNumber = telephonyManager.getLine1Number();
////          phone_number.setText(phoneNumber);
          TelephonyManager telephonyManager = (TelephonyManager) reactContext.getSystemService(reactContext.TELEPHONY_SERVICE);
          String phoneNumber = telephonyManager.getLine1Number();
          promise.resolve("Si tienes los permisos: " + phoneNumber );
        } else {
          promise.resolve("NO hay Permiso" +  ActivityCompat.checkSelfPermission(reactContext, READ_PHONE_STATE) +  "raad phone numbers" + ActivityCompat.checkSelfPermission(reactContext, READ_PHONE_NUMBERS) );

        }

    }catch (Exception e) {
      promise.reject("Error:", e);
    }

  }
}