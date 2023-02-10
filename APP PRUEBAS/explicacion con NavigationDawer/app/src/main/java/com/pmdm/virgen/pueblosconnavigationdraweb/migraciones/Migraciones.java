package com.pmdm.virgen.pueblosconnavigationdraweb.migraciones;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migraciones implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        Long version = oldVersion;
        if (oldVersion == 0){
            //instrucciones migracion desde version 0
            schema.get("Juego")
                    .addField("provincia", String.class, FieldAttribute.REQUIRED);
            schema.get("Juego")
                    .addField("localidad", String.class, FieldAttribute.REQUIRED);
            version++;
        }
        if (oldVersion == 1){
            //instrucciones migracion desde version 1
            schema.get("Juego")
                    .addField("provin_localidad", String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            String provinciaYLocalidad = obj.get("provincia").toString() + obj.get("locaclidad").toString();
                            obj.setString("provin_localidad", provinciaYLocalidad);
                        }
                    })
                    .removeField("provincia")
                    .removeField("localidad");
            version++;
        }

        //Resto de versiones si fuera necesario
    }
}
