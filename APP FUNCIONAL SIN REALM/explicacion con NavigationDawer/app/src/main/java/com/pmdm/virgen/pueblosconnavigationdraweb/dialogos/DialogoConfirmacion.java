package com.pmdm.virgen.pueblosconnavigationdraweb.dialogos;

import android.app.AlertDialog;
import android.content.Context;

import com.pmdm.virgen.pueblosconnavigationdraweb.listener.OnJuegoInteractionDialogListener;
import com.pmdm.virgen.pueblosconnavigationdraweb.ui.pueblos.JuegoFragment;

public class DialogoConfirmacion {


    public static void newInstance(Context contexto, JuegoFragment frag, long id){
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setTitle("Eliminación del Juego");
        builder.setMessage("¿Desea eliminar ese juego?");
        builder.setPositiveButton("Si",
                (dialogo, i)->{
                    OnJuegoInteractionDialogListener mListener = (OnJuegoInteractionDialogListener) frag;
                    mListener.eliminarJuego(id);
                    dialogo.dismiss();
                });
        builder.setNegativeButton("Cancelar",
                (dialogo, i)->{
                    dialogo.dismiss();
                });
        AlertDialog dialogo = builder.create();
        dialogo.show();

    }
}
