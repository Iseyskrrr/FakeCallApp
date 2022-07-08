package ch.zh.zli.mainproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText callerName;
    EditText callerNumber;
    RadioGroup timerDuration;
    TextView countDown;
    Button callButton;
    SharedPreferences sharedPreferences;

        private static final String SHARED_PREF_NAME ="mypref";
        private static final String KEY_NAME ="name";
        private static final String KEY_NUMBER ="number";

        final Handler handler = new Handler();


    int timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callerName = findViewById(R.id.callerNameInput);
        callerNumber = findViewById(R.id.phoneNumberInput);
        callButton = findViewById(R.id.call);
        countDown = findViewById(R.id.countDown);



        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.firstTimer:
                        timer = 15;
                        break;
                    case R.id.secondTimer:
                        timer = 30;
                        break;
                    case R.id.thirdTimer:
                        timer = 45;
                        break;
                    case R.id.fourthTimer:
                        timer = 60;
                        break;
                }
                timer *= 1000;
            }
        });
        callButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new CountDownTimer(timer, 1000) {

                    public void onTick(long millisUntilFinished) {
                        countDown.setText("Call in: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                    }
                }.start();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences.Editor  editor = sharedPreferences.edit();
                        editor.putString(KEY_NAME,callerName.getText().toString());
                        editor.putString(KEY_NUMBER,callerNumber.getText().toString());
                        editor.apply();


                        Intent intent = new Intent(MainActivity.this,CallActivity.class);


                        startActivity(intent);

                    }
                }, timer);
            }
        });
    }
    private void threadRunner(){


    }


}
