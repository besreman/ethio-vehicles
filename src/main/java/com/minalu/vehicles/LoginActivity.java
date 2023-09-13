package com.minalu.vehicles;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout textUsernameLayout;
    private TextInputLayout textPasswordLayout;
    private Button loginButton;
    private ProgressBar progressBar;
    private BlogPreferences blogPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        blogPreferences= new BlogPreferences(this);
        //if(blogPreferences.isLoggedIn()){
        //    startMainActivity();
        //    finish();
        //    return;
        //}

        textUsernameLayout = findViewById(R.id.textUsernameLayout);
        textPasswordLayout = findViewById(R.id.textPasswordInput);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);

        textUsernameLayout.getEditText().
                addTextChangedListener(createTextWatcher(textUsernameLayout));

        textPasswordLayout.getEditText().
                addTextChangedListener(createTextWatcher(textPasswordLayout));

        loginButton.setOnClickListener(v -> onLoginClicked());
    }

    private void onLoginClicked(){
        String username = textUsernameLayout.getEditText().getText().toString();
        String password = textPasswordLayout.getEditText().getText().toString();
        if(username.isEmpty()){
            textUsernameLayout.setError("username should not be empty!");
        } else if (password.isEmpty()) {
            textPasswordLayout.setError("please fill your password");
        } else if (!(username.equals("admin")&&password.equals("admin"))) {
            showErrorDialog();
        } else performLogin();
    }

    private TextWatcher createTextWatcher(TextInputLayout textInputLayout){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    private void showErrorDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Login failed!")
                .setMessage("username or password is not correct. please try again.")
                .setPositiveButton("ok",(dialog, which)->dialog.dismiss())
                .show();
    }

    private void performLogin(){
        blogPreferences.setLoggedIn(true);
        loginButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        textPasswordLayout.setEnabled(false);
        textUsernameLayout.setEnabled(false);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startMainActivity();
            finish();
        }, 2000);

    }

    public void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

