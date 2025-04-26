package com.faj.aula4carroapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.faj.aula4carroapp.model.CarroModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CarrosAdapter.Listener {

    private TextView warningCenter;
    private ListView carList;
    private AppCompatButton btnInserir;

    private ArrayList<CarroModel> carros;

    private CarrosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        carros = new ArrayList<>();
        updateList();
        setupViews();
    }

    private void updateList() {
        adapter = new CarrosAdapter(this, carros, this);
    }

    private void verifyState() {
        adapter.notifyDataSetChanged();

        if (!carros.isEmpty()) {
            warningCenter.setVisibility(View.GONE);
            carList.setVisibility(View.VISIBLE);
        } else {
            warningCenter.setVisibility(View.VISIBLE);
            carList.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        verifyState();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        Bundle bundle = data.getBundleExtra("novocarro");
        if (resultCode == Activity.RESULT_OK && data != null) {
            carros.add(new CarroModel(bundle));
        }
    }

    private void goToNewCar() {
        Intent intent = new Intent(this, NovoCarroActivity.class);
        startActivityForResult(intent, 1);
    }

    private void setupViews() {
        warningCenter = findViewById(R.id.non_content_warning);
        carList = findViewById(R.id.list_car);
        btnInserir = findViewById(R.id.btn_inserir);

        warningCenter.setVisibility(View.VISIBLE);
        carList.setAdapter(adapter);
        carList.setVisibility(View.GONE);

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewCar();
            }
        });
    }

    @Override
    public void onRemove(CarroModel carro) {
        carros.remove(carro);
        verifyState();
    }
}
