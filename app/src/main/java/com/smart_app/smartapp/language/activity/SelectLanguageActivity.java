package com.smart_app.smartapp.language.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.login.activity.LoginActivity;
import com.smart_app.smartapp.util.CustomSharedPreference;

public class SelectLanguageActivity extends AppCompatActivity {

    Button nextButton;
    RadioGroup languageRadioGroup;
    RadioButton englishRadioButton,marathiRadioButton;
    String selectedLanguageId = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        getSupportActionBar().hide();

        nextButton = (Button) findViewById(R.id.nextButton);
        languageRadioGroup = (RadioGroup) findViewById(R.id.languageRadioGroup);
        englishRadioButton = (RadioButton) findViewById(R.id.englishRadioButton);
        marathiRadioButton = (RadioButton) findViewById(R.id.marathiRadioButton);

        languageRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.marathiRadioButton:
                        selectedLanguageId = "0";
                        break;
                    case R.id.englishRadioButton:
                        selectedLanguageId = "1";
                        break;
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomSharedPreference.putUserProfile(SelectLanguageActivity.this,"language_id",selectedLanguageId);
                Intent intent = new Intent(SelectLanguageActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
