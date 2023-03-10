package com.pmdm.virgen.pueblosconnavigationdraweb.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.pmdm.virgen.pueblosconnavigationdraweb.R;
import com.pmdm.virgen.pueblosconnavigationdraweb.listener.OnPuebloInteractionDialogListener;
import com.pmdm.virgen.pueblosconnavigationdraweb.ui.juegos.JuegoFragment;


public class NuevoJuego extends AppCompatDialogFragment {
    private EditText editNombre, editDescripcion, editNHabitantes;
    private ImageView imgFoto;
    private Context contexto;
    private OnPuebloInteractionDialogListener listener;
  //  private JuegoFragment contextoFragmentPueblo;

    public NuevoJuego(JuegoFragment p){
        listener = (OnPuebloInteractionDialogListener) p;
    }

  /*  @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        contexto = context;
        try{
            listener = (OnPuebloInteractionDialogListener) contexto;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "Necesita implementar la interfaz OnPuebloInteractionListener");
        }
    }
*/
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  // o getActivity()
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.formulario_nuevo_pueblo, null);
        asociaCampos(vista);
        builder.setView(vista)
                .setTitle("Nuevo Juego")
                .setNegativeButton("Cancelar",
                        (dialogo, i )->{
                            Toast.makeText(contexto, "Se ha cancelado la inserci??n", Toast.LENGTH_SHORT).show();
                            dialogo.dismiss();
                        }

                )
                //De momento, el tema de la foto para los pueblos, lo dejamos sin utilizar. Ya tenemos bastante...
                .setPositiveButton("A??adir",
                        (dialogo,i)->{
                            String nombre = editNombre.getText().toString();
                            String descripcion = editDescripcion.getText().toString();
                            String url = "";
                            String nHabitantes= "";
                            boolean error = false;
                            try{
                                nHabitantes = editNHabitantes.getText().toString();
                            }catch (NumberFormatException e){  //Mas usss vale, que record??is el tema de las excepciones.
                                e.printStackTrace();
                                error = true;
                            }

                            if (nombre.isEmpty() || descripcion.isEmpty() || error)
                                Toast.makeText(contexto, "Campos incorrectos", Toast.LENGTH_SHORT).show();
                            else
                                listener.insertarPueblo(null,nombre, descripcion, nHabitantes);
                            dialogo.dismiss();
                        }
                );
        return builder.create();

    }

    public void asociaCampos(View vista){
        editNombre = (EditText) vista.findViewById(R.id.edit_nombre);
        editDescripcion = (EditText) vista.findViewById(R.id.edit_descripcion);
        editNHabitantes = (EditText) vista.findViewById(R.id.edit_n_habitantes);
    }
}




