package me.ibrahimrafi.vaultblasterriant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class QActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q);
        TextView textView = findViewById(R.id.qmsg);
        textView.setText(getIntent().getStringExtra("QMSG"));
    }
}
