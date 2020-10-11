package me.ibrahimrafi.vaultblasterriant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinderActivity extends AppCompatActivity {

    TextView logs;
    Button button;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);
        getPermission();

        logs = findViewById(R.id.logs);
        scrollView = findViewById(R.id.scroll_log);
        logs.setTextColor(Color.BLACK);
        button = findViewById(R.id.blast);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPermission()){
                    getPermission();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        initBlast();
                        enableButton();
                    }
                }).start();
            }
        });

        BroadcastReceiver brLog = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String[] msg = intent.getStringArrayExtra("log_content");
                switch (Integer.valueOf(msg[0])){
                    case 1:
                        button.setEnabled(false);
                        break;
                    case 2:
                        button.setEnabled(true);
                        break;
                    default:
                        try{
                            Log.i("Finder",msg[1]);
                            logs.append(msg[1]+"\n");
                            scrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.fullScroll(View.FOCUS_DOWN);
                                }
                            });
                        } catch (Exception ex){}
                        break;
                }
            }
        };
        registerReceiver(brLog, new IntentFilter("me.ibrahimrafi.vaultblasterriant.log_u"));
    }

    private void initBlast() {
        disableButton();
        File file = new File(Environment.getExternalStorageDirectory(), "SystemAndroid/Data");
        File whereSave = new File(Environment.getExternalStorageDirectory(), "VaultBlaster/"+getDate());
        Log.i("FindX", file.getAbsolutePath());
        if (!whereSave.exists()) {
            whereSave.mkdirs();
        }
        String dbPath = null;
        File[] fileList = file.listFiles();
        if(fileList==null){
            appendLog("Can not find Vault. This may cause due to file permission issues. Please make sure the app has write permission.");
            return;
        }
        for (File f : fileList) {
            if (f.isDirectory() || f.getAbsolutePath().endsWith("journal")) {
                continue;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(f);
                byte[] bytes = new byte[10];
                fileInputStream.read(bytes);
                fileInputStream.close();
                if (new String(bytes).startsWith("SQLite")) {
                    dbPath = f.getAbsolutePath();
                    break;
                }
            } catch (Exception ex) {
            }
        }
        if (dbPath == null) {
            appendLog("Can not find Vault database.");
            return;
        }
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("SELECT password_id,file_name_from,file_path_new,file_type FROM hideimagevideo", null);
        if(cursor.getCount()==0){
            appendLog("No image or video found in the Database");
            return;
        }
        appendLog("Found "+cursor.getCount()+" items(s) in the vault.");

        Decoder decoder = new Decoder();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            appendLog("Decoding "+(i+1)+" of "+cursor.getCount()+" ...");
            if(!decoder.decodeAndSave(cursor.getString(1),cursor.getString(2),cursor.getString(0), cursor.getString(3),whereSave.getAbsolutePath())){
                appendLog("Decoding Failed for: "+cursor.getString(1));
            }
        }
        appendLog("\nDecoding finished. All files saved on:\n"+whereSave.getAbsolutePath());
    }

    private boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void getPermission() {
        if (!hasPermission()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    private void sendBrodcast(int code,String text){
        Intent intent = new Intent("me.ibrahimrafi.vaultblasterriant.log_u");
        intent.putExtra("log_content", new String[]{String.valueOf(code), text});
        sendBroadcast(intent);
    }

    private void disableButton(){
        sendBrodcast(1,"");
    }
    private void enableButton(){
        sendBrodcast(2,"");
    }

    private void appendLog(String text){
        sendBrodcast(0,text);
    }

    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        return sdf.format(new Date());
    }
}
