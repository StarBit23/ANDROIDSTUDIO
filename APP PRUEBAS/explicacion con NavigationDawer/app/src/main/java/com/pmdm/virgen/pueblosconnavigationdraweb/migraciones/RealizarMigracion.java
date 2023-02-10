package com.pmdm.virgen.pueblosconnavigationdraweb.migraciones;

import io.realm.RealmConfiguration;

public class RealizarMigracion {

    public static void aplicarMigracion() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migraciones())
                .build();
    }
}
