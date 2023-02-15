package com.pmdm.virgen.pueblosconnavigationdraweb.listener;

public interface OnPuebloInteractionDialogListener {
    //Inserta un nuevo pueblo


    public void insertarPueblo(String url, String nombre, String descripcion, String nHabitantes );
    //Edita un pueblo ya existente.

    public void editarPueblo(long id, String nombre, String descripcion, String nHabitantes);

    public void eliminarPueblo(long id);

}
