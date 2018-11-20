package com.example.shareme.Controller;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shareme.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class Signup extends AppCompatActivity {

    private CallbackManager mCallbackManager;
    private LoginButton loginButton;
    private static final String TAG = "FACELOG";
    private EditText user, email, password;
    private TextInputLayout inputUser, inputEmail, inputPass;
    private Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                openActivity();
                //handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

        inputUser = findViewById(R.id.input_username);
        inputEmail = findViewById(R.id.input_email);
        inputPass = findViewById(R.id.input_pass);

        user = findViewById(R.id.usernameinput);
        email = findViewById(R.id.emailinput);
        password = findViewById(R.id.passwordinput);
        btnSignUp = findViewById(R.id.btn_signup);

        user.addTextChangedListener(new MyTextWatcher(user));
        email.addTextChangedListener(new MyTextWatcher(email));
        password.addTextChangedListener(new MyTextWatcher(password));


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishSignup();

            }
        });
    }


    private void finishSignup() {
        if (!validUsername() || !validEmail() || !validPassword()) {
            return;
        }
        else {

            Toast.makeText(Signup.this, "Welcome to ShareMe!", Toast.LENGTH_SHORT).show();
            openActivity();
    }
}
    private void openActivity() {
        Intent intent=new Intent(this,SignupDone.class);
        startActivity(intent);
    }

    private boolean validUsername(){
        if(user.getText().toString().trim().isEmpty() || user.getText().length()<6){
            inputUser.setError(getString(R.string.error_invalid_username));
            requestFocus(user);
            return false;
        }
        else{
            inputUser.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validEmail(){
        String mail = email.getText().toString().trim();
        if(mail.isEmpty()|| !isValidEmail(mail)){
            inputEmail.setError(getString(R.string.error_invalid_email));
            requestFocus(email);
            return false;
        }else {
            inputEmail.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validPassword(){
        if(password.getText().toString().trim().isEmpty()||password.getText().length()<4){
            inputPass.setError(getString(R.string.error_invalid_password));
            requestFocus(password);
            return false;
        }else{
            inputPass.setErrorEnabled(false);
        }
        return true;
    }


    private static boolean isValidEmail(String mail){
        return !TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }


    private void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private class MyTextWatcher implements TextWatcher{
        private View view;
        private  MyTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()){
                case R.id.usernameinput:
                    validUsername();
                    break;
                case R.id.emailinput:
                validEmail();
                break;
                case R.id.passwordinput:
                validPassword();
                break;

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }




}
