package com.pmdm.virgen.pueblosconnavigationdraweb.modelos;



public class Juego {
    /**
         * Declaramos constantes con los argumentos que se le pasaránpublic static final String ARGUMENTO_ID = "id";
         * del Main al Dialogo Edición del Juego.public static final String ARGUMENTO_NOMBRE = "nombre";
   */
    public static final String ARGUMENTO_ID = "id";
    public static final String ARGUMENTO_NOMBRE = "nombre";
    public static final String ARGUMENTO_DESCRIPCION = "descripcion";
    public static final String ARGUMENTO_NUM_VENTAS = "numVentas";
    public static final String ARGUMENTO_URL_FOTO = "urlFoto";

    private long id;
    private String urlFoto;
    private String nombre;
    private String descripcion;
    private String numVentas;




    public Juego() {

    }

    public Juego(long id, String urlFoto, String nombre, String descripcion, String numVentas) {
        this.id = id;
        this.urlFoto = urlFoto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numVentas = numVentas;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNumVentas() {
        return numVentas;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNumVentas(String numVentas) {
        this.numVentas = numVentas;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "id=" + id +
                ", urlFoto='" + urlFoto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", numVentas=" + numVentas +
                '}';
    }
}
