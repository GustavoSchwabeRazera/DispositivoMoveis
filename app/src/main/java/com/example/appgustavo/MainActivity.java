package com.example.appgustavo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;

    EditText edText;
    Button button;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=openOrCreateDatabase("notinhas", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS notas(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, conteudo VARCHAR)");
        edText=findViewById(R.layout.activity_main);
        button=findViewById(R.layout.activity_main);
        listView=findViewById(R.layout.activity_main);

        button.setOnClickListener(view -> {
            String titulo=edText.getText().toString();
            ContentValues values=new ContentValues();
            values.put("título", titulo);
            int id = (int) database.insert("notinhas", null, values);
            if(id > 0){
                Toast.makeText(getApplicationContext(),
                        "Nota salva com sucesso",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),
                        "Erro ao salvar nota",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}