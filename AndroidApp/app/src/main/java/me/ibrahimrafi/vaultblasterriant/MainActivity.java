package me.ibrahimrafi.vaultblasterriant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Build.VERSION.SDK_INT<Build.VERSION_CODES.Q) {
                    File file = new File(getFilesDir(),getResources().getString(R.string.eula_file));
                    if(file.exists()){
                        startActivity(new Intent(MainActivity.this, FinderActivity.class));
                        finish();
                    } else{
                        startActivity(new Intent(MainActivity.this, EulaActivity.class));
                        finish();
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, QActivity.class);
                    intent.putExtra("QMSG","The app currently supports SDK version 28 or lower (Android 9 or lower).\nYou have SDK version "+Build.VERSION.SDK_INT);
                    startActivity(intent);
                    finish();
                }
            }
        },1000);
    }
}
