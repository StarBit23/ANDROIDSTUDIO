package com.pmdm.virgen.pueblosconnavigationdraweb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    final static String nombreUsuario = "pepe";
    final static String password = "pepe";
    FirebaseAuth mAuth;

    private Button entrarSinReg, entrarConReg;
    private EditText usuario, pass;
    private Button botonDeRegistro;
    private SharedPreferences shared;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        //entrarSinReg = findViewById(R.id.boton_entrar);
        entrarConReg = findViewById(R.id.botonAcceder);
        usuario = findViewById(R.id.texto_usuario);
        pass = findViewById(R.id.editTextTextPassword);
        entrarSinReg = findViewById(R.id.buttonSinRegistro);
        botonDeRegistro = findViewById(R.id.botonDeRegistro);

        mAuth = FirebaseAuth.getInstance();

        entrarSinReg.setOnClickListener(v -> entrarAlSegundoSinReg());

        /*
        entrarConReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarAcceso();
            }
        });
         */

        botonDeRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
                //entrarAlSegundoSinReg();
            }
        });

        inicializarCampos();
        cargarPreferenciasCompartidas();

        if (isLogeado()){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        entrarConReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUsuario = usuario.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if (nombreUsuario.isEmpty()&& password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "pon algo huevón", Toast.LENGTH_SHORT).show();
                }else {
                    loginUser(nombreUsuario,password);
                }


                iniciarLoging();
                comprobarAcceso();
            }

            private void loginUser(String nombreUsuario, String password) {
                mAuth.signInWithEmailAndPassword(nombreUsuario,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString("preferenciasUsuario",nombreUsuario);
                            editor.putBoolean("isLogin",true);
                            editor.commit();
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Toast.makeText(LoginActivity.this, "Parece que no", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void inicializarCampos() {
    }

    private  void crearUsuario(){
        Intent a = new Intent(this, Register.class);
        startActivity(a);
    }

    private boolean isLogeado() {
        Boolean isLogin = shared.getBoolean("isLogin",false);
        return isLogin;
    }

    public void entrarAlSegundoSinReg(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Entrando sin registrar...",Toast.LENGTH_SHORT).show();

    }
    public void entrarAlSegundoConReg(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Usuario registrado",Toast.LENGTH_SHORT).show();

    }
    public void comprobarAcceso(){

        String usuarioIntroducido= usuario.getText().toString();
        String passIntroducida=pass.getText().toString();

        if (nombreUsuario.equals(usuarioIntroducido) && password.equals(passIntroducida)){
            entrarAlSegundoConReg();
            Toast.makeText(this, "Bienvenido "+usuarioIntroducido, Toast.LENGTH_SHORT).show();

        }else {
            //Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();

        }
    }

    private void cargarPreferenciasCompartidas(){
        String fichPreferencias = "preferenciasAppPueblos";
        shared = this.getSharedPreferences(fichPreferencias, Context.MODE_PRIVATE);
    }


    public void iniciarLoging(){
        String usuarioIn = usuario.getText().toString();
        String passwordIn = pass.getText().toString();
        usuario.setText("");
        pass.setText("");

        if (usuarioIn.equals("pepe") && passwordIn.equals("pepe")){
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("preferenciasUsuario",usuarioIn);
            editor.putBoolean("isLogin",true);
            editor.commit();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }




}