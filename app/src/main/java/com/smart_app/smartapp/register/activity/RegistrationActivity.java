package com.smart_app.smartapp.register.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smart_app.smartapp.R;
import com.smart_app.smartapp.login.activity.LoginActivity;
import com.smart_app.smartapp.register.model.Registration;
import com.smart_app.smartapp.util.Constants;
import com.smart_app.smartapp.util.CustomSharedPreference;

public class RegistrationActivity extends AppCompatActivity {

    EditText firstNameEditText,middleNameEditText,lastNameEditText,emaileditText,mobileNoEditText,emergencyContactEditText;
    TextView firstNameErrorTextView,middleNameErrorTextView,lastNameErrorTextView,emailErrorTextView,mobileNoErrorTextView,emergencyContactErrorTextView;
    RadioGroup genderRadioGroup;
    RadioButton genderRadioButton;
    Button registerButton;
    Registration registration = null;
    Gson gson = null;

    String firstName,middleName,lastName,email,mobileNo,emergencyContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        gson = new Gson();
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        middleNameEditText = (EditText) findViewById(R.id.middleNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        emaileditText = (EditText) findViewById(R.id.emailEditText);
        mobileNoEditText = (EditText) findViewById(R.id.mobileNoEditText);
        emergencyContactEditText = (EditText) findViewById(R.id.emergencyContactEditText);

        firstNameErrorTextView = (TextView) findViewById(R.id.firstNameErrorTextView);
        middleNameErrorTextView = (TextView) findViewById(R.id.middleNameErrorTextView);
        lastNameErrorTextView = (TextView) findViewById(R.id.lastNameErrorTextView);
        emailErrorTextView = (TextView) findViewById(R.id.emailErrorTextView);
        mobileNoErrorTextView = (TextView) findViewById(R.id.mobileNoErrotTextView);
        emergencyContactErrorTextView = (TextView) findViewById(R.id.emergencyContactErrorTextView);

        genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);

        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate()){
                    Toast.makeText(RegistrationActivity.this,getResources().getString(R.string.registration_failure),Toast.LENGTH_SHORT).show();

                }else {

                    try{
                        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
                        genderRadioButton = (RadioButton) findViewById(selectedId);
                        registration = new Registration();
                        registration.setFirstName(firstName);
                        registration.setMiddleName(middleName);
                        registration.setLastName(lastName);
                        registration.setEmail(email);
                        registration.setMobileNo(mobileNo);
                        registration.setEmergencyContact(emergencyContact);
                        registration.setGender(genderRadioButton.getText().toString());

                        String registrationObj = gson.toJson(registration);
                        //CustomSharedPreference.putUserProfile(RegistrationActivity.this,"user_details", String.valueOf(registration));
                        Constants.userList.add(registration);

                        CustomSharedPreference.putUserProfile(RegistrationActivity.this,"user_details", String.valueOf(Constants.userList));
                        Toast.makeText(RegistrationActivity.this,getResources().getString(R.string.registration_success),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }catch (Exception ex){
                        ex.getMessage();
                    }
                }
            }
        });

    }

    private boolean validate() {

        boolean flag = true;

        firstName = firstNameEditText.getText().toString();
        middleName = middleNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();
        email = emaileditText.getText().toString();
        mobileNo = mobileNoEditText.getText().toString();
        emergencyContact = emergencyContactEditText.getText().toString();

        if(firstName == null || firstName.isEmpty() || !firstName.matches(Constants.NAME_REGEX)){
            flag = false;
            firstNameErrorTextView.setVisibility(View.VISIBLE);
            firstNameErrorTextView.setText(getResources().getString(R.string.first_name_error_message));
        }else{
            flag = true;
            firstNameErrorTextView.setVisibility(View.GONE);
            firstNameErrorTextView.setText("");
        }

        if(!middleName.isEmpty() && !middleName.matches(Constants.NAME_REGEX)){
            flag = false;
            middleNameErrorTextView.setVisibility(View.VISIBLE);
            middleNameErrorTextView.setText(getResources().getString(R.string.middle_name_error_message));
        }else{
            flag = true;
            middleNameErrorTextView.setVisibility(View.GONE);
            middleNameErrorTextView.setText("");
        }

        if(lastName == null || lastName.isEmpty() || !lastName.matches(Constants.NAME_REGEX)){
            flag = false;
            lastNameErrorTextView.setVisibility(View.VISIBLE);
            lastNameErrorTextView.setText(getResources().getString(R.string.last_name_error_message));
        }else{
            flag = true;
            lastNameErrorTextView.setVisibility(View.GONE);
            lastNameErrorTextView.setText("");
        }

        if(email == null || email.isEmpty()){
            flag = false;
            emailErrorTextView.setVisibility(View.VISIBLE);
            emailErrorTextView.setText(getResources().getString(R.string.email_error_message));
        }else if(!email.matches(Constants.EMAIL_REGEX)){
            flag = false;
            emailErrorTextView.setVisibility(View.VISIBLE);
            emailErrorTextView.setText(getResources().getString(R.string.email_error_message));
        }else{
            flag = true;
            emailErrorTextView.setVisibility(View.GONE);
            emailErrorTextView.setText("");
        }

        if(mobileNo == null || mobileNo.isEmpty()  || mobileNo.length()<10){
            flag = false;
            mobileNoErrorTextView.setVisibility(View.VISIBLE);
            mobileNoErrorTextView.setText(getResources().getString(R.string.mobile_number_error_message));
        }else if(!mobileNo.matches(Constants.NUMBER_REGEX )){
            flag = false;
            mobileNoErrorTextView.setVisibility(View.VISIBLE);
            mobileNoErrorTextView.setText(getResources().getString(R.string.mobile_number_error_message));
        }else{
            flag = true;
            mobileNoErrorTextView.setVisibility(View.GONE);
            mobileNoErrorTextView.setText("");
        }

        if(emergencyContact == null || emergencyContact.isEmpty()  || emergencyContact.length()<10){
            flag = false;
            emergencyContactErrorTextView.setVisibility(View.VISIBLE);
            emergencyContactErrorTextView.setText(getResources().getString(R.string.emergency_contact_error_message));
        }else if(!emergencyContact.matches(Constants.NUMBER_REGEX )){
            flag = false;
            emergencyContactErrorTextView.setVisibility(View.VISIBLE);
            emergencyContactErrorTextView.setText(getResources().getString(R.string.emergency_contact_error_message));
        }else{
            flag = true;
            emergencyContactErrorTextView.setVisibility(View.GONE);
            emergencyContactErrorTextView.setText("");
        }


        return flag;

    }

}
