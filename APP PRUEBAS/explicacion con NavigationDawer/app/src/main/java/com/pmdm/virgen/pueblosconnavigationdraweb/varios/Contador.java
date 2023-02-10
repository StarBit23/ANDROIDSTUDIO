package com.pmdm.virgen.pueblosconnavigationdraweb.varios;


public class Contador {
    private static long id=0;

    public static void increId(){
        id++;
    }
    public static long dameId(){
        return id;
    }
}
