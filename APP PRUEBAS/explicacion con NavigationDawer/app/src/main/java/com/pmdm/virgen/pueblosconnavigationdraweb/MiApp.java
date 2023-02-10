package com.pmdm.virgen.pueblosconnavigationdraweb;

import android.app.Application;
import android.content.Context;

import com.pmdm.virgen.pueblosconnavigationdraweb.modelos.Juego;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MiApp extends Application {
    public static AtomicLong IdPueblo = new AtomicLong();

    @Override
    public void onCreate() {
        super.onCreate();

        inicializarRealm(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        IdPueblo = dameUltimoId(realm, Juego.class);
        realm.close();
    }

    private void inicializarRealm(Context contexto) {
        String realmName = "My Project";
        Realm.init(contexto);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(realmName)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                //.allowWritesOnUiThread(true)
                .build();
        //cargamos la configuracion
        Realm.setDefaultConfiguration(config);
    }

    private AtomicLong dameUltimoId(Realm realm, Class<Juego> juegoClass) {

        return null;
    }
}
