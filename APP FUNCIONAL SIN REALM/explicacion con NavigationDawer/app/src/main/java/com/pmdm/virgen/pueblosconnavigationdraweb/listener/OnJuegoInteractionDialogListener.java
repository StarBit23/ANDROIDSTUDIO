package com.pmdm.virgen.pueblosconnavigationdraweb.listener;

public interface OnJuegoInteractionDialogListener {
    //Inserta un nuevo pueblo


    public void insertarJuego(String nombre, String descripcion, String nVentas );
    //Edita un pueblo ya existente.

    public void editarJuego(long id, String nombre, String descripcion, String nVentas);

    public void eliminarJuego(long id);

}
