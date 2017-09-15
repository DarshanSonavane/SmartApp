package com.smart_app.smartapp.changepassword.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.dashboard.activity.DashboardActivity;

/**
 * Created by lenovo on 2017-09-04.
 */

public class ChangePasswordFragment extends android.support.v4.app.Fragment {

    EditText currentPasswordEditText,newPasswordEditText,confirmPasswordEditText;
    TextView currentPasswordErrorTextView,newPasswordErrorTextView,confirmPasswordErrorTextView;
    Button submitButton;
    String currentPassword,newPassword,confirmPassword;
    boolean flag = true;

    public static ChangePasswordFragment newInstance() {
        
        Bundle args = new Bundle();

        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_change_password,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((DashboardActivity) getActivity()).setActionBarTitle(getResources().getString(R.string.change_password_fragment_title));
        currentPasswordEditText = (EditText) view.findViewById(R.id.currentPasswordEditText);
        newPasswordEditText = (EditText) view.findViewById(R.id.newPasswordEditText);
        confirmPasswordEditText = (EditText) view.findViewById(R.id.confirmPasswordEditText);
        submitButton = (Button) view.findViewById(R.id.submitButton);

        currentPasswordErrorTextView = (TextView) view.findViewById(R.id.currentPasswordErrorTextView);
        newPasswordErrorTextView = (TextView) view.findViewById(R.id.newPasswordErrorTextView);
        confirmPasswordErrorTextView = (TextView) view.findViewById(R.id.confirmPasswordErrorTextView);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validate() {

        currentPassword = currentPasswordEditText.getText().toString();
        newPassword = newPasswordEditText.getText().toString();
        confirmPassword = confirmPasswordEditText.getText().toString();

        if(currentPassword == null || currentPassword.isEmpty()){
            currentPasswordErrorTextView.setVisibility(View.VISIBLE);
            currentPasswordErrorTextView.setText(getResources().getString(R.string.change_password_fragment_current_password_error_message));
            flag = false;
        }else{
            currentPasswordErrorTextView.setVisibility(View.GONE);
            currentPasswordErrorTextView.setText("");
            flag = true;
        }

        if(newPassword == null || newPassword.isEmpty()){
            newPasswordErrorTextView.setVisibility(View.VISIBLE);
            newPasswordErrorTextView.setText(getResources().getString(R.string.change_password_fragment_new_password_error_message));
            flag = false;
        }else {
            newPasswordErrorTextView.setVisibility(View.GONE);
            newPasswordErrorTextView.setText("");
            flag = false;
        }

        if(confirmPassword == null || confirmPassword.isEmpty()){
            confirmPasswordErrorTextView.setVisibility(View.VISIBLE);
            confirmPasswordErrorTextView.setText(getResources().getString(R.string.change_password_fragment_confirm_password_error_message));
            flag = false;
        }else if(!confirmPassword.equals(newPassword)){
            confirmPasswordErrorTextView.setVisibility(View.VISIBLE);
            confirmPasswordErrorTextView.setText(getResources().getString(R.string.change_password_fragment_password_do_not_matched_error_message));
            flag = false;
        }else{
            confirmPasswordErrorTextView.setVisibility(View.GONE);
            confirmPasswordErrorTextView.setText("");
            flag = true;
        }
        return flag;
    }
}
