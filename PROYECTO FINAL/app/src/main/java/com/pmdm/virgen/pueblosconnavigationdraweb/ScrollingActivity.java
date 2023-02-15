package com.pmdm.virgen.pueblosconnavigationdraweb;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.pmdm.virgen.pueblosconnavigationdraweb.databinding.ActivityScrollingBinding;
import com.pmdm.virgen.pueblosconnavigationdraweb.modelos.Juego;

import io.realm.Realm;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = this.getIntent().getExtras();
        long idPueblo = bundle.getLong(Juego.ARGUMENTO_ID);
        Realm realm = Realm.getDefaultInstance();
        Juego juego = realm.where(Juego.class).equalTo(Juego.ARGUMENTO_ID,idPueblo).findFirst();




        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(juego.getNombre());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}