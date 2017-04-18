package com.example.stalker.runtimepermissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS = 201;
    private static final int OPEN_SETTING_FOR_PERMISSION = 100;
    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;
    private String[] requiredPermissions = new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);
        getReadContactandGPSPermission();

    }
    private void gotAllPermissions() {
        Toast.makeText(MainActivity.this,"We already got the permission",Toast.LENGTH_SHORT).show();
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_SETTING_FOR_PERMISSION) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, requiredPermissions[0]) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                gotAllPermissions();

            }
        }
    }









    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(sentToSettings){
            if(ActivityCompat.checkSelfPermission(MainActivity.this,requiredPermissions[0]) == PackageManager.PERMISSION_GRANTED){
                gotAllPermissions();
            }
        }
    }
    private void getReadContactandGPSPermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, requiredPermissions[0]) != PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(MainActivity.this, requiredPermissions[1]) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, requiredPermissions[0])
                    ||ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,requiredPermissions[1])) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Need Multiple Permission");
                builder.setMessage("This app needs GPS and Contact permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(MainActivity.this, requiredPermissions, MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
            else if (permissionStatus.getBoolean(requiredPermissions[0],false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Need Multiple Permission");
                builder.setMessage("This app needs GPS and Contact permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, OPEN_SETTING_FOR_PERMISSION);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this, requiredPermissions, MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.READ_CONTACTS,true);
            editor.commit();
        }
        else {

            gotAllPermissions();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS: {
                // If request is cancelled, the result arrays are empty.
                boolean allgranted = false;
                for(int i=0;i<grantResults.length;i++){
                    if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                        allgranted = true;
                    } else {
                        allgranted = false;
                        break;
                    }
                }

                if(allgranted) {
                    gotAllPermissions();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                else if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, requiredPermissions[0])
                        ||ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,requiredPermissions[1])) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Need Multiple Permission");
                    builder.setMessage("This app needs GPS and Contact permission.");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(MainActivity.this, requiredPermissions, MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();

                }
                else{
                    Toast.makeText(getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
                }

            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
