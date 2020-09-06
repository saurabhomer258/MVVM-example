package com.example111.saurabhomer.firebaseauth;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginapp.R;
import com.example111.saurabhomer.firebaseauth.Dao.UserDao;
import com.example111.saurabhomer.firebaseauth.model.UserData;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
        Button btnLogout;
    private FirebaseAuth mAuth;
    private TextView userDataEt;
    UserViewModel mUserViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userDataEt = findViewById(R.id.userdata);
        btnLogout = findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                openLoginPage();
            }
        });
        mUserViewModel = new UserViewModel(App.appContext);

        mUserViewModel.getAllUser().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(@Nullable List<UserData> userData) {
                if (userData!=null) {
                    for (int i = 0; i < userData.size(); i++) {
                        userDataEt.setText(userDataEt.getText().toString() + " \n  " + userData.get(i).getName());
                    }
                }
            }
        });
    }

    private void openLoginPage(){
        Intent i =new Intent(HomeActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }


}
