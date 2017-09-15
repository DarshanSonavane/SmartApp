package com.smart_app.smartapp.login.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.gson.Gson;
import com.smart_app.smartapp.R;
import com.smart_app.smartapp.dashboard.activity.DashboardActivity;
import com.smart_app.smartapp.forgotpassword.activity.ForgotPasswordActivity;
import com.smart_app.smartapp.register.activity.RegistrationActivity;
import com.smart_app.smartapp.register.model.Registration;
import com.smart_app.smartapp.util.CustomSharedPreference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button loginButton,facebookButton,googleButton;
    TextView usernameTextViewError,passwordTextViewError,forgotPasswordTextView,newAccountTextView;
    EditText usernameEditText,passwordEditText;
    String usernameText=null,passwordText=null;
    String registerObj = null;
    Registration registration = null;
    LoginButton facebookLoginButton;
    CallbackManager mFacebookCallbackManager = null;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    private static final String TAG = "MainActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(LoginActivity.this);
        FirebaseApp.initializeApp(this);
        FacebookSdk.setApplicationId("458473234536555");
        setContentView(R.layout.activity_login);

        mFacebookCallbackManager = CallbackManager.Factory.create();
        //mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        registration = new Registration();

        forgotPasswordTextView = (TextView) findViewById(R.id.forgotPasswordTextView);
        forgotPasswordTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        String lang = (CustomSharedPreference.getString(LoginActivity.this,"language_id"));
        Toast.makeText(this,"Selected Language--"+lang,Toast.LENGTH_SHORT).show();

        if(CustomSharedPreference.getString(LoginActivity.this,"user_details") != null){
            registerObj = CustomSharedPreference.getString(LoginActivity.this,"user_details");
            Log.d("UserDetails-----","UserDetails--------"+registerObj);
        }else {
            Log.d("UserDetails-----","UserDetails--------");
        }

        usernameTextViewError = (TextView) findViewById(R.id.usernameErrTextView);
        passwordTextViewError = (TextView) findViewById(R.id.passwordErrTextView);
        newAccountTextView = (TextView) findViewById(R.id.newAccountTextView);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        facebookButton = (Button) findViewById(R.id.facebookButton);
        facebookLoginButton = (LoginButton) findViewById(R.id.facebookLoginButton);
        facebookLoginButton.setReadPermissions("email","public_profile");
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginButton.performClick();
            }
        });

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginButton.registerCallback(mFacebookCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(LoginActivity.this,"Inside Success",Toast.LENGTH_SHORT).show();
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this,"Inside Cancel",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(LoginActivity.this,"Inside Error",Toast.LENGTH_SHORT).show();
                        Log.d("Error-------","Error---------"+error);
                    }
                });
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                    Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,getResources().getString(R.string.login_activity_error_toast_message),Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        newAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        /*mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d("", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("", "onAuthStateChanged:signed_out");
                }
            }
        };*/
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        googleButton = (Button) findViewById(R.id.googleButton);


    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User Null!", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {

        try{
            Toast.makeText(LoginActivity.this,"Inside handleFacebookAccessToken",Toast.LENGTH_SHORT).show();
            AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Success---", "signInWithCredential:success");
                                Toast.makeText(LoginActivity.this, "User Authentication Success: ", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                //verifyFacebookAccount(user);


                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Failure", "signInWithCredential:failure", task.getException());
                            /*Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();*/

                                Toast.makeText(LoginActivity.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                            //customProgressDialog.hideProgressDialog();


                        }


                    });
        }catch (Exception ex){
            ex.getMessage();
        }
    }

    private boolean validate() {
        usernameText = usernameEditText.getText().toString();
        passwordText = passwordEditText.getText().toString();
        boolean flag = true;
        if(usernameText == null || usernameText.isEmpty() || usernameText.length() <10 ){
            flag = false;
            usernameTextViewError.setVisibility(View.VISIBLE);
            usernameTextViewError.setText(getResources().getString(R.string.login_activity_username_text_view_error));
        }else{
            flag = true;
            usernameTextViewError.setText("");
            usernameTextViewError.setVisibility(View.GONE);
        }

        if(passwordText == null || passwordText.isEmpty()){
            flag = false;
            passwordTextViewError.setVisibility(View.VISIBLE);
            passwordTextViewError.setText(getResources().getString(R.string.login_activity_password_text_view_error));
        }else {
            flag = true;
            passwordTextViewError.setText("");
            passwordTextViewError.setVisibility(View.GONE);

        }

        return flag;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mFacebookCallbackManager.onActivityResult(requestCode,resultCode,data)){
            Log.d("TAG","Data----"+data);
            mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        }


    }
}
