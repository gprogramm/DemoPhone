package com.android.demophone.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.demophone.R;
import com.android.demophone.utils.DateUtils;
import com.android.demophone.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean b = PermissionUtils.requestPermissions(this, PermissionUtils.RECORDING_PERMISSION);

        System.out.println("-------- This is major bug fixed by SecondUser---->");
        System.out.println("-------- This is major bug fixed by Main User---->");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String dateTime = DateUtils.segregateNextInteractionDateTime("2024-07-25T09:01:01Z");
            if(!TextUtils.isEmpty(dateTime)) {
                String[] arr = dateTime.split("\\|");
                if(arr != null && arr.length == 2) {

                }
            }
        }

        if(b) {
            startActivity(new Intent(this, SpeechToTextActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtils.PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, do nothing
                System.out.println("--------Permission granted---->");
                startActivity(new Intent(this, SpeechToTextActivity.class));
                finish();

            } else {
                // Permission denied
                System.out.println("--------Handle Permission Denied Done---->");
            }
        }
    }
}