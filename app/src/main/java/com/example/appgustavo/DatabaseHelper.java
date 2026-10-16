package com.example.appgustavo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "notas.db";
    private static final int VERSAO_BANCO = 1;

    public static final String TABELA_NOTAS = "notas";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_TEXTO = "texto";

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABELA_NOTAS + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_TITULO + " TEXT, " +
                COLUNA_TEXTO + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_NOTAS);
        onCreate(db);
    }

    public long inserirNota(Nota nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_TITULO, nota.getTitulo());
        values.put(COLUNA_TEXTO, nota.getTexto());
        long id = db.insert(TABELA_NOTAS, null, values);
        db.close();
        return id;
    }

    public List<Nota> listarNotas() {
        List<Nota> listaNotas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABELA_NOTAS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_ID));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_TITULO));
                String texto = cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_TEXTO));
                listaNotas.add(new Nota(id, titulo, texto));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaNotas;
    }
}
