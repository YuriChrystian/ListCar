package com.faj.aula4carroapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.faj.aula4carroapp.model.CarroModel;

import java.util.ArrayList;

public class CarrosAdapter extends ArrayAdapter<CarroModel> {

    interface Listener {
        void onRemove(CarroModel carro);
    }

    private ArrayList<CarroModel> carros;
    private Listener listener;

    public CarrosAdapter(@NonNull Context context, ArrayList<CarroModel> carros, Listener listener) {
        super(context, 0, carros);
        this.carros = carros;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        View cor = view.findViewById(R.id.cor);
        TextView modelo = view.findViewById(R.id.modelo);
        TextView placa = view.findViewById(R.id.placa);
        TextView estado = view.findViewById(R.id.estado);
        TextView actionRemove = view.findViewById(R.id.acrtion_remove);

        CarroModel carro = carros.get(position);

        switch (carro.getCor()) {
            case "Branca":
                cor.setBackgroundColor(getContext().getResources().getColor(R.color.white));
                break;
            case "Preto":
                cor.setBackgroundColor(getContext().getResources().getColor(R.color.black));
                break;
            case "Vinho":
                cor.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_purple));
                break;
            case "Amarelo":
                cor.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_orange_light));
                break;
            case "Vermelho":
                cor.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_red_light));
        }

        actionRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemove(carro);
            }
        });

        modelo.setText(carro.getModelo());
        placa.setText(carro.getPlaca());
        estado.setText(carro.getEstadoConservacao());

        return view;
    }
}
