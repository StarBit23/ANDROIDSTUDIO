package com.pmdm.virgen.pueblosconnavigationdraweb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button boton_cancelar_regis;
    Button boton_aceptar_regis;
    EditText usuario_regis;
    EditText email_regis;
    EditText contraseña_regis;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrase);

        boton_cancelar_regis = findViewById(R.id.boton_cancelar_regis);
        boton_aceptar_regis = findViewById(R.id.boton_aceptar_regis);
        usuario_regis = findViewById(R.id.usuario_regis);
        email_regis = findViewById(R.id.email_regis);
        contraseña_regis = findViewById(R.id.contraseña_regis);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        this.setTitle("Regristarse en World Serpent");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        boton_aceptar_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUsuario = usuario_regis.getText().toString().trim();
                String email = email_regis.getText().toString().trim();
                String password = contraseña_regis.getText().toString().trim();

                if (nombreUsuario.isEmpty() && email.isEmpty() && password.isEmpty()){
                    Toast.makeText(Register.this, "pichita, que no me has metido la informacion que quiero, damelas o vete", Toast.LENGTH_SHORT).show();
                }
                else{
                    registarNuevoUsuario(nombreUsuario,email,password);
                }
            }

            private void registarNuevoUsuario(String nombreUsuario, String email, String password) {
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String id= mAuth.getCurrentUser().getUid();
                        Map<String,Object>map = new HashMap<>();
                        map.put("id",id);
                        map.put("name",nombreUsuario);
                        map.put("email",email);
                        map.put("password",password);

                        mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();
                                startActivity(new Intent(Register.this, LoginActivity.class));
                                Toast.makeText(Register.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, "No puedo hacerlo dame un momento", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Parece que no te voy a hacer tu cuenta ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        boton_cancelar_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, LoginActivity.class));

            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  false;
    }
}