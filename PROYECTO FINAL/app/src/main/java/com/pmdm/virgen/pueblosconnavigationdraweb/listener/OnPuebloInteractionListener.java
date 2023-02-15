package com.pmdm.virgen.pueblosconnavigationdraweb.listener;


import com.pmdm.virgen.pueblosconnavigationdraweb.modelos.Juego;

/**
 * Esta interfaz, se encargará de gestionar los
 * eventos click sobre un pueblo y el botón de editar y borrar.
 */
public interface OnPuebloInteractionListener {

    public void onPuebloClick(Juego juego);
    public void onPuebloEditarClick(Juego juego);
    public void onPuebloBorrarClick(Juego juego);
}
