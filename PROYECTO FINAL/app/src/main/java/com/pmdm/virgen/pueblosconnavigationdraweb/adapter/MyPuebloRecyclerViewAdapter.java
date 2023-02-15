package com.pmdm.virgen.pueblosconnavigationdraweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pmdm.virgen.pueblosconnavigationdraweb.R;
import com.pmdm.virgen.pueblosconnavigationdraweb.listener.OnPuebloInteractionListener;
import com.pmdm.virgen.pueblosconnavigationdraweb.modelos.Juego;


import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MyPuebloRecyclerViewAdapter extends RecyclerView.Adapter<MyPuebloRecyclerViewAdapter.ViewPueblo> {

    private final RealmResults<Juego> mValues;
    private final RealmChangeListener listenerCambioDatos;
    private OnPuebloInteractionListener listenerPueblo;
    private Context contexto;

    public MyPuebloRecyclerViewAdapter(RealmResults<Juego> items, OnPuebloInteractionListener listener) {
        mValues = items;
        listenerPueblo = listener;

        this.listenerCambioDatos = new RealmChangeListener(){
            @Override
            public void onChange(Object o) {
                notifyDataSetChanged();}
        };
        if (items != null)
            //items.addChangeListener(listenerCambioDatos);
            addListenerCambioDatos(items);
    }

    private void addListenerCambioDatos(RealmResults <Juego> items) {
        if (items instanceof  RealmResults){
            RealmResults realmResults = (RealmResults) items;
            realmResults.addChangeListener(listenerCambioDatos);

        }else
            throw new IllegalArgumentException("no es un objeto RealmResults");
    }

    @Override
    public ViewPueblo onCreateViewHolder(ViewGroup parent, int viewType) {
        View vistaElementoPueblo = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.elemento_pueblo, parent, false);

        return new ViewPueblo(vistaElementoPueblo);

    }

    @Override
    public void onBindViewHolder(final ViewPueblo holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewNombre.setText(holder.mItem.getNombre());
        holder.textViewDescripcion.setText(holder.mItem.getDescripcion());
        holder.textViewHabitantes.setText(String.valueOf(holder.mItem.getNumVentas()));

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /*
    Esta clase, es la encargada de recibir la vista inflada del elemento gráfico, asociarle
    elementos de la vista con objetos java para poder setear datos en sus campos.
     */
    public class ViewPueblo extends RecyclerView.ViewHolder {
        public final TextView textViewNombre;
        public final TextView textViewDescripcion;
        public final TextView textViewHabitantes;
        public final ImageView imageViewPueblo;
        public final ImageView imageViewEditarPueblo;
        public final ImageView imageViewBorrarPueblo;
        public Juego mItem;  //es el objeto pueblo que representa el objeto
        public View vista;  //será el constrain layout del layout_pueblos_item



        public ViewPueblo(View view) {
            super(view.getRootView()); //le pasamos la vista a la que tiene que asociar elementos.
            vista = view;

            /**
             * Debo enlazar cada uno de los elementos de nuestro layout_pueblos_item
             * con cada propiedad de nuestra objeto interno.
             */
            textViewNombre = vista.findViewById(R.id.texto_nombre);
            textViewDescripcion = vista.findViewById(R.id.texto_descripcion);
            textViewHabitantes = vista.findViewById(R.id.texto_numerohabitantes);
            imageViewPueblo = vista.findViewById(R.id.imagen_pueblo);
            imageViewEditarPueblo = vista.findViewById(R.id.imagen_editar);
            imageViewBorrarPueblo = vista.findViewById(R.id.imageView_borrar_pueblo);

            imageViewEditarPueblo.setOnClickListener(
                    (evento)->{
                        listenerPueblo.onPuebloEditarClick(mItem);
                    }
            );

            imageViewBorrarPueblo.setOnClickListener(
                    (evento)->{
                        listenerPueblo.onPuebloBorrarClick(mItem);
                    }
            );

            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerPueblo.onPuebloClick(mItem);
                }
            });


           // vista.setOnClickListener();
        }



    }
}