package com.example111.saurabhomer.firebaseauth;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example111.saurabhomer.firebaseauth.model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    EditText emailEdittext;
    EditText passwordEditText;
    Button loginBtn;
    private FirebaseAuth mAuth;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_login, container, false);
        emailEdittext = v.findViewById(R.id.et_email);
        passwordEditText = v.findViewById(R.id.et_password);
        loginBtn = v.findViewById(R.id.btn_login);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        checkAuth(currentUser);

    }

    private void checkAuth(FirebaseUser currentUser) {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailEdittext.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"emai should not empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwordEditText.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"password should not empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                ((MainActivity)getActivity()).showProgressBar();
                mAuth.signInWithEmailAndPassword(emailEdittext.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    ((MainActivity)getActivity()).hideProgressBar();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserViewModel userViewModel = new UserViewModel(App.appContext);
                                    UserData userData = new UserData();
                                    if (user != null) {

                                        userData.setName(emailEdittext.getText().toString());
                                        userViewModel.insert(userData);
                                    }
                                    ((MainActivity)getActivity()).openHomeActivity();
                                }
                                else {
                                    Toast.makeText(getContext(),"oops ! some thing went worng please try after some tiim",Toast.LENGTH_SHORT).show();
                                    ((MainActivity)getActivity()).hideProgressBar();
                                }


                            }
                        });
            }
        });


    }

}
