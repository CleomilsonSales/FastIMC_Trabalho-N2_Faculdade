package com.example.fastimc_trabalhon1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conexao extends SQLiteOpenHelper {
	private static final String nomeBanco = "FastIMC";
	private static final int versaoBanco = 1;
	
	public Conexao(Context c){
		super(c, nomeBanco,null,versaoBanco);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		CriarTabelaHistorico(db);
		CriarTabelaLogin(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		dropHistorico(db);
		dropLogin(db);  	
    	
		onCreate(db);
	}
	
	public void dropHistorico(SQLiteDatabase db){
		
		StringBuilder codSQL = new StringBuilder();
		codSQL.append("DROP TABLE HistoricoIMC");
    	db.execSQL(codSQL.toString());
    	
	}
	
	public void dropLogin(SQLiteDatabase db){
		
		StringBuilder codSQL = new StringBuilder();
		codSQL.append("DROP TABLE LoginIMC");
    	db.execSQL(codSQL.toString());
    	
	}
	
	public void CriarTabelaHistorico(SQLiteDatabase db){
		
		StringBuilder codSQL = new StringBuilder();
    	codSQL.append("CREATE TABLE HistoricoIMC(");
    	codSQL.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
    	codSQL.append("data VARCHAR(10),");
    	codSQL.append("user VARCHAR(10),");
    	codSQL.append("sexo VARCHAR(1),");
    	codSQL.append("altura NUMERIC,");
    	codSQL.append("peso NUMERIC,");
    	codSQL.append("resultado VARCHAR(10),");
    	codSQL.append("resultTexto VARCHAR(80) )");

    	db.execSQL(codSQL.toString());
    	
	}
	
	public void CriarTabelaLogin(SQLiteDatabase db){
		
		StringBuilder codSQL = new StringBuilder();
    	codSQL.append("CREATE TABLE LoginIMC(");
    	codSQL.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
    	codSQL.append("user VARCHAR(10) UNIQUE,");
    	codSQL.append("pass VARCHAR(10) )");

    	db.execSQL(codSQL.toString());
    	
    	InserirUsuarioPrimario(db);
    	
	}
	
	public void InserirUsuarioPrimario(SQLiteDatabase db){
		
		ContentValues valores = new ContentValues();
    	valores.put("user", "admin");
    	valores.put("pass", "1234");

    	db.insert("LoginIMC", null, valores);

	}
	
	
	
}
