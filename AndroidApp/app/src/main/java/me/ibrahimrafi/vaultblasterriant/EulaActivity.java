package me.ibrahimrafi.vaultblasterriant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EulaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eula);
        final Button button = findViewById(R.id.i_agree_btn);
        CheckBox checkBox = findViewById(R.id.i_agree_chk);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    button.setEnabled(true);
                }
                else {
                    button.setEnabled(false);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getFilesDir(),getResources().getString(R.string.eula_file));
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(255);
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(EulaActivity.this, FinderActivity.class));
                finish();
            }
        });
    }
}
