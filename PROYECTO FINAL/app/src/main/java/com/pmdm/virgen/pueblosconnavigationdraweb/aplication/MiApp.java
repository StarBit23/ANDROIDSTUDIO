package com.pmdm.virgen.pueblosconnavigationdraweb.aplication;

import android.app.Application;
import android.content.Context;

import com.pmdm.virgen.pueblosconnavigationdraweb.modelos.Juego;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;


public class MiApp extends Application {

    public static AtomicLong IdPueblo = new AtomicLong();
    public static AtomicLong IdFiesta = new AtomicLong();

    @Override
    public void onCreate() {
        super.onCreate();

        inicializarRealm(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        IdPueblo = dameUltimoId(realm, Juego.class);
        realm.close();

    }

    private <T extends RealmObject> AtomicLong dameUltimoId(Realm realm, Class<T> clase) {
        RealmResults resultados = realm.where(clase).findAll();
        if (resultados.size()>0)
            return  new AtomicLong(resultados.max("id").intValue());
        else
            return  new AtomicLong();

    }

    private void inicializarRealm(Context contexto) {
        String realmName = "My Project";
        Realm.init(contexto);

        RealmConfiguration configuration = new RealmConfiguration.Builder().name(realmName).schemaVersion(1).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
