package com.example.appgustavo;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class MainActivity extends AppCompatActivity {
    ArrayList<String> nomes  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

       Button button =findViewById(R.id.Botao);
       EditText edit =findViewById(R.id.editNome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listanomes = findViewById(R.id.listView);


        ArrayAdapter <String> usb = new ArrayAdapter<>(getApplicationContext(),R.layout.item_lista,R.id.textView,nomes);
        listanomes.setAdapter(usb);
        listanomes.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(),nomes.get(position),Toast.LENGTH_LONG).show();
        });
button.setOnClickListener(v -> {

    nomes.add(edit.getText().toString());
    edit.setText("");
    usb.notifyDataSetChanged();
});




    }
}