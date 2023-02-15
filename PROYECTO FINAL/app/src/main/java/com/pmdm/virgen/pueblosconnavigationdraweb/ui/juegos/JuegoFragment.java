package com.pmdm.virgen.pueblosconnavigationdraweb.ui.juegos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pmdm.virgen.pueblosconnavigationdraweb.MainActivity;
import com.pmdm.virgen.pueblosconnavigationdraweb.R;
import com.pmdm.virgen.pueblosconnavigationdraweb.ScrollingActivity;
import com.pmdm.virgen.pueblosconnavigationdraweb.adapter.MyPuebloRecyclerViewAdapter;
import com.pmdm.virgen.pueblosconnavigationdraweb.dialogos.DialogoConfirmacion;
import com.pmdm.virgen.pueblosconnavigationdraweb.dialogos.EditarJuegoDialogo;
import com.pmdm.virgen.pueblosconnavigationdraweb.dialogos.NuevoJuego;
import com.pmdm.virgen.pueblosconnavigationdraweb.listener.OnPuebloInteractionDialogListener;
import com.pmdm.virgen.pueblosconnavigationdraweb.listener.OnPuebloInteractionListener;
import com.pmdm.virgen.pueblosconnavigationdraweb.modelos.Juego;

import io.realm.Realm;
import io.realm.RealmResults;


public class JuegoFragment extends Fragment implements OnPuebloInteractionListener, OnPuebloInteractionDialogListener {
    private static final String TAG = "MiActivity";
   // private List<Juego> listaPueblos;
    private Context contexto;
    private FloatingActionButton bFlotanteInsertar;
    private Realm realm;
    private RealmResults<Juego> listaPueblos;


  //  private OnPuebloInteractionListener listener;
    private MyPuebloRecyclerViewAdapter miAdaptador;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public JuegoFragment(){
       // crearObjetosDinamicos();
        realm = Realm.getDefaultInstance();
        listaPueblos= realm.where(Juego.class).findAll();

    }

    public JuegoFragment(RealmResults<Juego> lista) {

        listaPueblos = lista;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm =  Realm.getDefaultInstance();
        crearObjetosDinamicos();



       /*listaPueblos = new Realm.RealmObjectContext()

        listaPueblos.add(new Juego(null, "Socuellamos","Juego de Ciudad Read", 12000));
        listaPueblos.add(new Juego(null, "Lezuza", "Juego ibero-romano de Albacete", 1500));
        listaPueblos.add(new Juego(null, "Tomelloso", "Juego de Ciudad Read, tiene buenos quesos", 20000));
        listaPueblos.add(new Juego(null, "Almagro","Juego de Ciudad Real, joer que buenas las berenjenas", 5000));
*/


    }



    public void crearObjetosDinamicos(){
        realm = Realm.getDefaultInstance();
        listaPueblos= realm.where(Juego.class).findAll();
     /*   listaPueblos.add(new Juego(0, null, "Jaén", "Ciudad del Santo Reino", 120000));
        listaPueblos.add(new Juego(1, null, "Villargordo", "Vive Lorenzo", 4000));
        listaPueblos.add(new Juego(2, null, "Sabiote", "El que no es tonto es cipote", 2000));
        listaPueblos.add(new Juego(3, null, "Los Villares", "Aguas Sierras de Jaén", 5000));
        listaPueblos.add(new Juego(4, null, "Torredelcampo", "Cerro de Santa Ana", 18000));
        listaPueblos.add(new Juego(5, null, "Las Infantas", "Buenos Chorizos", 800));
        listaPueblos.add(new Juego(6, null, "Mengíbar", "Vive mi cuñado", 12000));
        listaPueblos.add(new Juego(7, null, "Linares", "3 huevos son dos pares", 60000));
        for (int i=0; i<4; i++ )
            Contador.increId();*/
    }


    /*
    Comprobamos si el Activity implementa de la interfaz.
     */
   /*
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPuebloInteractionListener)
            listener = (OnPuebloInteractionListener) context;
        else
            throw new IllegalArgumentException("Debe implementar el Listener OnPuebloInteractionListener");

    }

    */



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contexto = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pueblo_root2, container, false);

        bFlotanteInsertar = view.findViewById(R.id.floatingActionButton2);
        bFlotanteInsertar.setOnClickListener(
                (evento)->{
                    NuevoJuego dNuevo = new NuevoJuego(this);
                    MainActivity acti = (MainActivity)contexto;
                    dNuevo.show(acti.getSupportFragmentManager(), "Insertar Nuevo Juego");
                }
        );

        // Set the adapter
  //      if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = view.findViewById(R.id.lista_pueblos);
         //   RecyclerView recyclerView = (RecyclerView) view;
        miAdaptador = new MyPuebloRecyclerViewAdapter(listaPueblos, this);
        recyclerView.setAdapter(miAdaptador);
   //     }
        return view;
    }

    public void actualizaAdaptador(){
        miAdaptador.notifyDataSetChanged();
    }

    @Override
    public void onPuebloClick(Juego juego) {
        Intent intent = new Intent(this.contexto, ScrollingActivity.class);
        intent.putExtra(Juego.ARGUMENTO_ID, juego.getId());
        startActivity(intent);
    }



    @Override
    public void onPuebloEditarClick(Juego juego) {
        Toast.makeText(contexto, "Edición: " + juego.toString(), Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Edicion: " + juego.toString());
        EditarJuegoDialogo dialogoEditar =  EditarJuegoDialogo.newInstance(this,
                juego.getId(),
                juego.getNombre(),
                juego.getDescripcion(),
                juego.getNumVentas(),
                juego.getUrlFoto());
        MainActivity acti = (MainActivity)contexto;
        dialogoEditar.show(acti.getSupportFragmentManager(), "Edición datos del Juego "+ juego.getNombre());

    }

    @Override


    public void onPuebloBorrarClick(Juego juego) {
        //Toast.makeText(this, "Borrado: "+juego.toString(), Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Borrado: "+ juego.toString());
        MainActivity acti = (MainActivity)contexto;
        DialogoConfirmacion.newInstance(acti, this, juego.getId());



    }

    @Override
    public void insertarPueblo(String url, String nombre, String descripcion, String nHabitantes) {
        /*Contador.increId();
        listaPueblos.add(new Juego( Contador.dameId(),null,nombre, descripcion, nHabitantes));*/
        realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm -> {
            Juego juego = new Juego(0, null, nombre, descripcion, nHabitantes);
            realm.copyToRealm(juego);
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                realm.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
                realm.close();
            }
        });
    }

    @Override
    public void editarPueblo(long id, String nombre, String descripcion, String nHabitantes) {
        /*int i=0;
        Juego aux=null;
        int tam = listaPueblos.size();
        while (aux==null && i<tam){
            if (listaPueblos.get(i).getId()==id)
                aux = listaPueblos.get(i);
            i++;
        }
        if (aux!=null){
            aux.setNombre(nombre);
            aux.setDescripcion(descripcion);
            aux.setNumHabitantes(nHabitantes);
            aux.setUrlFoto(null);  //ya modificaremos esto cuando toque.
            Toast.makeText (contexto, "Se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
            //debo notificar el cambio desde el fragmento.
            actualizaAdaptador();
        }else
            Toast.makeText (contexto, "Se ha producido algun error", Toast.LENGTH_SHORT).show();
*/
        realm.executeTransactionAsync(new Realm.Transaction() {
            /*@Override
            public void execute(Realm realm) {
                Juego pueblo = new Juego();
                pueblo.setId(id);
                pueblo.setNombre(nombre);
                pueblo.setDescripcion(descripcion);
                pueblo.setNumHabitantes(nHabitantes);
                pueblo.setUrlFoto(null);
                realm.copyToRealmOrUpdate(pueblo);*/
                @Override
                public void execute(Realm realm) {
                    Juego juego = new Juego();
                    juego.setId(id);
                    juego.setNombre(nombre);
                    juego.setDescripcion(descripcion);
                    juego.setNumVentas(nHabitantes);
                    juego.setUrlFoto(null);
                    realm.copyToRealmOrUpdate(juego);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {

                    realm.close();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    error.printStackTrace();
                    realm.close();
                }
            });

    }

    @Override
    public void eliminarPueblo(long id) {
        /*int i=0;
        boolean encontrado = false;
        int tam = listaPueblos.size();
        while (!encontrado && i<tam){
            if (listaPueblos.get(i).getId()==id) {
                listaPueblos.remove(i);
                encontrado = true;
            }
            i++;
        }
        if (encontrado){
            Toast.makeText (contexto, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
            //debo notificar el cambio desde el fragmento.
            actualizaAdaptador();
        }else
            Toast.makeText (contexto, "Se ha producido algun error", Toast.LENGTH_SHORT).show();
*/
        realm.executeTransactionAsync(new Realm.Transaction() {
            /*@Override
            public void execute(Realm realm) {
                Juego pueblo = new Juego();
                pueblo.setId(id);
                pueblo.setNombre(nombre);
                pueblo.setDescripcion(descripcion);
                pueblo.setNumHabitantes(nHabitantes);
                pueblo.setUrlFoto(null);
                realm.copyToRealmOrUpdate(pueblo);*/
            @Override
            public void execute(Realm realm) {
                Juego puebloBorrar = realm.where(Juego.class).equalTo(Juego.ARGUMENTO_ID,id).findFirst();
                if (puebloBorrar!=null)
                    puebloBorrar.deleteFromRealm();


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                realm.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
                realm.close();
            }
        });

    }
    public void nuevoPueblo(){

        NuevoJuego dNuevo = new NuevoJuego(this);
        MainActivity acti = (MainActivity)contexto;
        dNuevo.show(acti.getSupportFragmentManager(), "Insertar Nuevo Juego");
    }
}