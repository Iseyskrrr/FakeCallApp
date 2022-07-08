package ch.zh.zli.mainproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class CallActivity extends AppCompatActivity {
    TextView callerName;
    TextView callerNumber;
    Button decline;
    Button accept;

    SharedPreferences sharedPreferences;
    private Vibrator vibrator;
    MediaPlayer ybBetter;

    private static final String SHARED_PREF_NAME ="mypref";
    private static final String KEY_NAME ="name";
    private static final String KEY_NUMBER ="number";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        callerName = findViewById(R.id.callerName);
        callerNumber = findViewById(R.id.phoneNumber);
        decline = findViewById(R.id.decline);
        accept = findViewById(R.id.accept);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);
        String number = sharedPreferences.getString(KEY_NUMBER,null);

        if (name != null || number != null){
            callerName.setText(name);
            callerNumber.setText(number);
        }
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {0, 100, 1000, 200, 2000};
        vibrator.vibrate(pattern,  0);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });






    }
}