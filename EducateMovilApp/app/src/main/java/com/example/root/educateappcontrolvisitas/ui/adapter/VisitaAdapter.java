package com.example.root.educateappcontrolvisitas.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.api.model.Visita;

import java.util.List;

import static androidx.core.content.ContextCompat.getColor;

public class VisitaAdapter extends ArrayAdapter<Visita> {

    private int mColorResourceId;
    private List<Visita> visitas;


    /**
     * Create a new {@link VisitaAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param visitas is the list of  to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    public VisitaAdapter(Context context, List<Visita> visitas, int colorResourceId) {
        super(context, 0, visitas);
        mColorResourceId = colorResourceId;
        this.visitas = visitas;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.visita_item, parent, false);
        }


        Visita currentVisita = visitas.get(position);


        TextView visita_numero = (TextView) listItemView.findViewById(R.id.visita_numero);
        visita_numero.setText("N°000000190");

        TextView visita_hora = (TextView) listItemView.findViewById(R.id.visita_hora);
        visita_hora.setText(currentVisita.getHora_inicio_visita());

        TextView escuela_MIE = (TextView) listItemView.findViewById(R.id.escuela_MIE);
        escuela_MIE.setText(currentVisita.getAmie());

        TextView escuela_jornada = (TextView) listItemView.findViewById(R.id.escuela_jornada);
        escuela_jornada.setText("VESPERTINA");

        TextView escuela_direccion = (TextView) listItemView.findViewById(R.id.escuela_direccion);
        escuela_direccion.setText(currentVisita.getDireccion());


        TextView escuela_nombre = (TextView) listItemView.findViewById(R.id.escuela_nombre);
        escuela_nombre.setText(currentVisita.getNombreEscuela());
        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.visita_text_container);
        // Find the color that the resource ID maps to
        int color = getColor(getContext(), mColorResourceId);

        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}
