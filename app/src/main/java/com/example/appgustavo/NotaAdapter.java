package com.example.appgustavo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotaAdapter extends ArrayAdapter<Nota> {

    public NotaAdapter(Context context, List<Nota> notas) {
        super(context, 0, notas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_lista, parent, false);
        }

        Nota nota = getItem(position);

        TextView txtTitulo = convertView.findViewById(R.id.txtTitulo);
        TextView txtTexto = convertView.findViewById(R.id.txtTexto);

        if (nota != null) {
            if (txtTitulo != null) {
                txtTitulo.setText(nota.getId() + " - " + nota.getTitulo());
            }
            if (txtTexto != null) {
                txtTexto.setText(nota.getTexto());
            }
        }

        return convertView;
    }
}
