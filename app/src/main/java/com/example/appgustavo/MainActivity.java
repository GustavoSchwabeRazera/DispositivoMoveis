package com.example.appgustavo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTitulo;
    private EditText editTexto;
    private Button btnSalvar;
    private ListView listView;

    private DatabaseHelper dbHelper;
    private NotaAdapter adapter;
    private List<Nota> listaNotas;

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

        editTitulo = findViewById(R.id.editTitulo);
        editTexto = findViewById(R.id.editTexto);
        btnSalvar = findViewById(R.id.Botao);
        listView = findViewById(R.id.listView);

        dbHelper = new DatabaseHelper(this);
        listaNotas = new ArrayList<>();

        adapter = new NotaAdapter(this, listaNotas);
        listView.setAdapter(adapter);

        carregarNotas();

        btnSalvar.setOnClickListener(v -> {
            String titulo = editTitulo.getText().toString().trim();
            String texto = editTexto.getText().toString().trim();

            if (titulo.isEmpty() && texto.isEmpty()) {
                Toast.makeText(MainActivity.this, "Preencha os campos para salvar!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (titulo.isEmpty()) {
                titulo = "Nota sem título";
            }

            Nota nota = new Nota(titulo, texto);
            long id = dbHelper.inserirNota(nota);

            if (id != -1) {
                Toast.makeText(MainActivity.this, "Nota salva com sucesso!", Toast.LENGTH_SHORT).show();
                editTitulo.setText("");
                editTexto.setText("");
                carregarNotas();
            } else {
                Toast.makeText(MainActivity.this, "Erro ao salvar nota.", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Nota notaSelecionada = listaNotas.get(position);
            Toast.makeText(getApplicationContext(), "ID: " + notaSelecionada.getId() + "\nTítulo: " + notaSelecionada.getTitulo() + "\nTexto: " + notaSelecionada.getTexto(), Toast.LENGTH_LONG).show();
        });
    }

    private void carregarNotas() {
        listaNotas.clear();
        listaNotas.addAll(dbHelper.listarNotas());
        adapter.notifyDataSetChanged();
    }
}
