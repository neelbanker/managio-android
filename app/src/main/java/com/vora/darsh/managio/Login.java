package com.vora.darsh.managio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText ET_NAME,ET_PASS;
    String login_name,login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ET_NAME = findViewById(R.id.editTextUsername);
        ET_PASS = findViewById(R.id.editTextPass);
    }

    public void userLogin(View view)
    {
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this,"login");
        backgroundTask.execute(method,login_name,login_pass);
    }

    public void clear(){
        this.finish();
    }
}
