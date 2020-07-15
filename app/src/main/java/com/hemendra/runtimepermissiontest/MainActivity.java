package com.hemendra.runtimepermissiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //clearAppData();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Permission is granted",Toast.LENGTH_SHORT).show();
            }
            else{
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Permission is granted",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode== 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //Toast granted  Savefile();
            }
            else{
                //permisiion is denied
            }

        }
        if(requestCode== 120) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearAppData();
    }
    private void clearAppData() {
        try {
            // clearing app data
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT) {
                ((ActivityManager)getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();
            } else {
                String packageName = getApplicationContext().getPackageName();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear "+packageName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
