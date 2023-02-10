package com.pmdm.virgen.pueblosconnavigationdraweb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityLogin extends AppCompatActivity {
private Button btnLogin;
private EditText userEdit, passwordEdit;
private SharedPreferences shared;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnLogin = findViewById(R.id.buttoAcceder);

        userEdit = findViewById(R.id.edit_correo);
        passwordEdit = findViewById(R.id.edit_contraseña);

        inicializarCampos();
        cargaPreferenciasCompartidas();

        if (isLogueado()){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }

        btnLogin.setOnClickListener(v -> {
            iniciarLogin();
        });


        //login antiguo, no usar porfi
        /*String Usuario = "pepe";
        String Password = "pepe";

            btnLogin.setOnClickListener(v -> {
                if(userEdit.getText().toString().equals(Usuario) && passwordEdit.getText().toString().equals(Password)) {
                    Intent intentLogin = new Intent(MainActivityLogin.this, MainActivity.class);
                    startActivity(intentLogin);
                }else {
                    Toast.makeText(MainActivityLogin.this, "Pon bien tu cuenta tonto", Toast.LENGTH_SHORT).show();
                }

            });*/


    }

    private void inicializarCampos() {

    }

    public void iniciarLogin(){
        String email = userEdit.getText().toString();
        String pass = passwordEdit.getText().toString();
        userEdit.setText("");
        passwordEdit.setText("");

        if (email.equals("pepe") && pass.equals("pepe")){
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("preferenciasEmail",email);
            editor.putBoolean("isLogin",true);
            editor.commit();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }else
            Toast.makeText(this, "Email y/o constraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    private void cargaPreferenciasCompartidas() {
        String fichPreferencias = "preferenciasAppPueblos";
        shared = this.getSharedPreferences(fichPreferencias, Context.MODE_PRIVATE);
    }

    private boolean isLogueado() {
        Boolean isLogin = shared.getBoolean("isLogin",false);
        return isLogin;
    }
}