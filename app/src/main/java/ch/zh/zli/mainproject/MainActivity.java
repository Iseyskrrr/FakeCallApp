package ch.zh.zli.mainproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        EditText callerName;
        EditText callerNumber;
        RadioGroup timerDuration;
        Button callButton;
        SharedPreferences sharedPreferences;

        private static final String SHARED_PREF_NAME ="mypref";
        private static final String KEY_NAME ="name";
        private static final String KEY_NUMBER ="number";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callerName = findViewById(R.id.calllerName);
        callerNumber = findViewById(R.id.phoneNumber);
        callButton = findViewById(R.id.call);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);
        if (name!= null){
            Intent intent = new Intent(MainActivity.this,CallActivity.class);
            startActivity(intent);
        }


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor  editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,callerName.getText().toString());
                editor.putString(KEY_NUMBER,callerNumber.getText().toString());
                editor.apply();


                Intent intent = new Intent(MainActivity.this,CallActivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

            }
        });

    }


}