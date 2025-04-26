package com.faj.aula4carroapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.faj.aula4carroapp.model.CarroModel;

public class NovoCarroActivity extends AppCompatActivity {

    private Spinner spinnerCor;
    private Spinner spinnerState;
    private ImageView action;

    private EditText fieldPlaca;
    private EditText fieldModelo;
    private EditText fieldAno;

    private Button btnCadastrar;

    private ArrayAdapter<CharSequence> adapterState;
    private ArrayAdapter<CharSequence> adapterCor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_carro);
        setupViews();
    }

    private void setupViews() {
        spinnerCor = findViewById(R.id.spinner_cor);
        spinnerState = findViewById(R.id.spinner_state);
        action = findViewById(R.id.action);
        fieldAno = findViewById(R.id.field_ano);
        fieldModelo = findViewById(R.id.field_modelo);
        fieldPlaca = findViewById(R.id.field_placa);

        btnCadastrar = findViewById(R.id.btn_inserir);

        action.setOnClickListener(v -> finish());
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String placa = fieldPlaca.getText().toString();
                String modelo = fieldModelo.getText().toString();
                String cor = spinnerCor.getSelectedItem().toString();
                String ano = fieldAno.getText().toString();
                String state = spinnerState.getSelectedItem().toString();

                if(placa.isEmpty() || modelo.isEmpty() || cor.isEmpty() || ano.isEmpty() || state.isEmpty()) {
                    Toast.makeText(NovoCarroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("placa", placa);
                bundle.putString("modelo", modelo);
                bundle.putString("cor", cor);
                bundle.putString("ano", ano);
                bundle.putString("state", state);

                Intent intent = new Intent();
                intent.putExtra("novocarro", bundle);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        setupSpinnerCor();
        setupSpinnerState();
    }

    private void setupSpinnerState() {
        adapterState = ArrayAdapter.createFromResource(
                this,
                R.array.estado_array,
                android.R.layout.simple_spinner_item
        );
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapterState);
    }

    private void setupSpinnerCor() {
        adapterCor = ArrayAdapter.createFromResource(
                this,
                R.array.cor_array,
                android.R.layout.simple_spinner_item
        );
        adapterCor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCor.setAdapter(adapterCor);
    }
}
