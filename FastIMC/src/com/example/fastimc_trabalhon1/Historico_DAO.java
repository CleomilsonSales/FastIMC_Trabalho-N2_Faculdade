package com.example.fastimc_trabalhon1;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

@SuppressLint("SimpleDateFormat") 
public class Historico_DAO{
	
	protected SQLiteDatabase con;
	
	public Historico_DAO(Context c){
		Conexao auxCon = new Conexao(c);
		con = auxCon.getWritableDatabase();
		
	}


	public void inserirIMC(Historico hist){

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
    	Date vDataHoje = new Date(System.currentTimeMillis());
    	
    	ContentValues valores = new ContentValues();
    	valores.put("data", df.format(vDataHoje));
    	valores.put("user", hist.getvUser());
    	valores.put("sexo", hist.getvSexo());
    	valores.put("altura", hist.getvAltura());
    	valores.put("peso", hist.getvPeso());
    	valores.put("resultado", hist.getvResultado());
    	valores.put("resultTexto", hist.getvResultTexto());
    	
    	con.insert("HistoricoIMC", null, valores);
    	

    }
	/*
	public List<Historico> pesquisarIMC(){
    	
		List<Historico> list = new ArrayList<Historico>();
		String[] colunas = new String[]{"_id","data","user","sexo","resultado"};
		
		Cursor cursor = con.query("HistoricoIMC", colunas, null, null, null, null, "_id DESC");
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			do{
				Historico h = new Historico();
				h.setvId(cursor.getInt(0));
				h.setvData(cursor.getString(1));
				h.setvUser(cursor.getString(2));
				h.setvSexo(cursor.getString(3));
				h.setvResultado(cursor.getDouble(6));
				list.add(h);
			}while(cursor.moveToNext());
		}
		return(list);
    }*/
}
