package com.pmdm.virgen.pueblosconnavigationdraweb;

import android.app.Application;
import android.content.Context;

import com.pmdm.virgen.pueblosconnavigationdraweb.modelos.Juego;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MiApp extends Application {
    public static AtomicLong IdJuego = new AtomicLong();

    @Override
    public void onCreate() {
        super.onCreate();

        inicializarRealm(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        IdJuego = dameUltimoId(realm, Juego.class);
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

    private <T extends RealmObject> AtomicLong dameUltimoId(Realm realm, Class<T> clase){
        RealmResults resutados = realm.where(clase).findAll();
        if (resutados.size() > 0)
            return  new AtomicLong(resutados.max("id").intValue());
        else
            return new AtomicLong();
    }
}
