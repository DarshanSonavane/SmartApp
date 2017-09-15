package com.smart_app.smartapp.forgotpassword.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.login.activity.LoginActivity;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mobileNoEditText;
    TextView mobileNoErrorTextView;
    Button submitButton;
    String mobileNo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mobileNoEditText = (EditText) findViewById(R.id.mobileNoEditText);
        mobileNoErrorTextView = (TextView) findViewById(R.id.mobileNoErrotTextView);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v != null && v.getId() == R.id.submitButton && validate()){
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Snackbar.make(submitButton,getResources().getString(R.string.forgot_password_activity_error_message_snack_bar_text),Snackbar.LENGTH_LONG).show();
        }
    }

    private boolean validate() {

        mobileNo = mobileNoEditText.getText().toString();
        boolean flag = true;
        if(mobileNo.length()<10 || mobileNo.isEmpty() || mobileNo == null){
            mobileNoErrorTextView.setVisibility(View.VISIBLE);
            mobileNoErrorTextView.setText(getResources().getString(R.string.forgot_password_activity_mobile_no_error_text));
            flag = false;
        }else{
            mobileNoErrorTextView.setVisibility(View.GONE);
            mobileNoErrorTextView.setText("");
            flag = true;
        }
        return flag;
    }
}
