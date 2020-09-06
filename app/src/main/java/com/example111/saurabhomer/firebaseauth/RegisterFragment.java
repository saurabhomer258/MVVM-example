package com.example111.saurabhomer.firebaseauth;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class RegisterFragment extends Fragment {

    EditText name;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button registerbtn;
    private FirebaseAuth mAuth;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_register, container, false);
        name = v.findViewById(R.id.et_name);
       email=  v.findViewById(R.id.et_email);
        password = v.findViewById(R.id.et_password);
       confirmPassword =  v.findViewById(R.id.et_repassword);
       registerbtn =  v.findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();
       return  v;
    }

    @Override
    public void onStart() {
        super.onStart();


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"name should not empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"emai should not empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"password should not empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"password should not empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast.makeText(getContext(),"password is not match",Toast.LENGTH_SHORT).show();
                    return;
                }
                ((MainActivity)getActivity()).showProgressBar();
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    ((MainActivity)getActivity()).openHomeActivity();

                                    UserViewModel userViewModel = new UserViewModel(App.appContext);
                                    UserData userData = new UserData();
                                    if (user!=null) {


                                            userData.setName(email.getText().toString());
                                           userViewModel.insert(userData);
                                    }
                                    ((MainActivity)getActivity()).hideProgressBar();
                                }
                                else {
                                    ((MainActivity)getActivity()).hideProgressBar();
                                    Toast.makeText(getContext(),"oops ! some thing went worng please try after some tiim",Toast.LENGTH_SHORT).show();
                                }


                            }

                        });
            }
        });

    }
}
