package com.example.appgustavo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnProximo,btnVoltar;
    ImageView imageView;
    Integer[] imagens = new Integer[]{
            R.drawable.cachorro,
            R.drawable.gardem,
            R.drawable.happy,
            R.drawable.patinho,
            R.drawable.porquinho
    };
    int posicao=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnVoltar=findViewById(R.id.btnVoltar);
        btnProximo=findViewById(R.id.btnAvancar);
        imageView=findViewById(R.id.imageView2);
        TextView resultado = findViewById(R.id.Resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnProximo.setOnClickListener(v -> {
            if(posicao > imagens.length) posicao = 0;
            imageView.setImageResource(imagens[posicao]);
            posicao++;
            EditText edPeso = findViewById(R.id.IdPeso);
            float peso = Float.parseFloat(edPeso.getText().toString());

            EditText edAltura = findViewById(R.id.IdAltura);
            float altura = Float.parseFloat(edAltura.getText().toString());

            float imc = peso / (altura * altura);

            if(imc < 18.5) {
                imageView.setImageResource(R.drawable.abaixopeso);
            }
            if(imc >= 18.5 && imc < 25 ) {
                imageView.setImageResource(R.drawable.normal);
            }
            if(imc >= 25 && imc < 30) {
                imageView.setImageResource(R.drawable.obesidade1);
            }
            if(imc >= 30 && imc < 35) {
                imageView.setImageResource(R.drawable.obesidade2);
            }
            if(imc > 40) {
                imageView.setImageResource(R.drawable.obesidade3);
            }

            resultado.setText(String.valueOf(imc));

        });

        btnVoltar.setOnClickListener(v -> {
            imageView.setImageResource(imagens[posicao]);
            posicao--;
            if(posicao < 0) posicao = imagens.length -1;
        });



    }
}