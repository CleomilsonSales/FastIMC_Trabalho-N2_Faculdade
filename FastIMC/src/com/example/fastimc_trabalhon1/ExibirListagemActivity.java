package com.example.fastimc_trabalhon1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class ExibirListagemActivity extends Activity{
	private RadioGroup rgFiltro;
	private ListView lvDados;
	private String usuarioLogado;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_litagem);

        //recebendo usuario
        Intent i = getIntent();
		usuarioLogado = i.getStringExtra("usuarioLogado").toUpperCase();
		//--
        
        rgFiltro = (RadioGroup) findViewById(R.id.rgFiltro);
        filtroSemana();
        
        rgFiltro.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				filtros();
				
			}
		});
        
	}
	
	public void filtros(){
		String valor = null;
    	switch (rgFiltro.getCheckedRadioButtonId()) {
    	    case R.id.rbSemana:
    	    	valor = "semana";
    	        break;
    	    case R.id.rbMes:
    	    	valor = "mes";
    	        break;
    	    case R.id.rbAno:
    	    	valor = "ano";
    	        break;
    	}
    	
    	if (valor.equals("semana")){
    		filtroSemana();
    	}else{
    		if (valor.equals("mes")){
    			filtroMes();
        	}else{
        		filtroAno();
        	}
    	}
	}
	public void filtroSemana(){
		
		String[] colunas = new String[]{"_id","user","data"};
		Historico_DAO h = new Historico_DAO(this);
		String where = "user='"+usuarioLogado+"'";
		Cursor cursor = h.con.query("HistoricoIMC", colunas, where, null, null, null, "_id DESC");
		
		String primeiraDataSql = null;
		String ultimaDataSql = null;
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			String ultimaDataDB = cursor.getString(2);
			int ultimoDia = Integer.parseInt(ultimaDataDB.substring(0, 2));
			int primeiroDia =  ultimoDia - 7;
						
			String primeiroMes =  ultimaDataDB.substring(3, 5);
			String primeiroAno =  ultimaDataDB.substring(6, 10);
			primeiraDataSql = primeiroAno+primeiroMes+primeiroDia;
			ultimaDataSql = primeiroAno+primeiroMes+ultimoDia;
		}
		
		Historico_DAO hist = new Historico_DAO(this);
		
    	StringBuilder sqlBuscar= new StringBuilder();
    	sqlBuscar.append("SELECT * FROM HistoricoIMC where "+where+" and substr(data,7)||substr(data,4,2)||substr(data,1,2) between '"+primeiraDataSql+"' and '"+ultimaDataSql+"' order by _id desc");
    	Cursor dados = hist.con.rawQuery(sqlBuscar.toString(), null);
    	
    	int[] to= {R.id.tvId,R.id.tvData, R.id.tvUser,R.id.tvSexo,R.id.tvAltura,R.id.tvPeso,R.id.tvResultado,R.id.tvResultadoTexto};
    	String[] from= {"_id","data","user","sexo","altura","peso","resultado","resultTexto"};
    	
    	try{
    		
    		SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.listagem, dados, from, to,0);
	    	lvDados = (ListView) findViewById(R.id.lvDados);
	    	lvDados.setAdapter(ad);

    	}
    	catch(Exception ex)
    	{
    		Toast.makeText(getBaseContext(), sqlBuscar.toString() + "Erro = "+ ex.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}
	
	public void filtroMes(){
		
		String[] colunas = new String[]{"_id","user","data"};
		Historico_DAO h = new Historico_DAO(this);
		String where = "user='"+usuarioLogado+"'";
		Cursor cursor = h.con.query("HistoricoIMC", colunas, where, null, null, null, "_id DESC");
		
		String primeiraDataSql = null;
		String ultimaDataSql = null;
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			String ultimaDataDB = cursor.getString(2);
		
			String primeiroMes =  ultimaDataDB.substring(3, 5);
			String primeiroAno =  ultimaDataDB.substring(6, 10);
			primeiraDataSql = primeiroAno+primeiroMes+"01";
			ultimaDataSql = primeiroAno+primeiroMes+"31";
		}
		
		Historico_DAO hist = new Historico_DAO(this);
		
    	StringBuilder sqlBuscar= new StringBuilder();
    	sqlBuscar.append("SELECT * FROM HistoricoIMC where "+where+" and substr(data,7)||substr(data,4,2)||substr(data,1,2) between '"+primeiraDataSql+"' and '"+ultimaDataSql+"' order by _id desc");
    	Cursor dados = hist.con.rawQuery(sqlBuscar.toString(), null);
    	
    	int[] to= {R.id.tvId,R.id.tvData, R.id.tvUser,R.id.tvSexo,R.id.tvAltura,R.id.tvPeso,R.id.tvResultado,R.id.tvResultadoTexto};
    	String[] from= {"_id","data","user","sexo","altura","peso","resultado","resultTexto"};
    	
    	try
    	{
	    	SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.listagem, dados, from, to,0);
	    	ListView lvDados = (ListView) findViewById(R.id.lvDados);
	    	lvDados.setAdapter(ad);
    	}
    	catch(Exception ex)
    	{
    		Toast.makeText(getBaseContext(), sqlBuscar.toString() + "Erro = "+ ex.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}
	
	public void filtroAno(){
		
		String[] colunas = new String[]{"_id","user","data"};
		Historico_DAO h = new Historico_DAO(this);
		String where = "user='"+usuarioLogado+"'";
		Cursor cursor = h.con.query("HistoricoIMC", colunas, where, null, null, null, "_id DESC");
		
		String primeiraDataSql = null;
		String ultimaDataSql = null;
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			String ultimaDataDB = cursor.getString(2);
		
			String primeiroAno =  ultimaDataDB.substring(6, 10);
			primeiraDataSql = primeiroAno+"01"+"01";
			ultimaDataSql = primeiroAno+"12"+"31";
		}
		
		Historico_DAO hist = new Historico_DAO(this);
		
    	StringBuilder sqlBuscar= new StringBuilder();
    	sqlBuscar.append("SELECT * FROM HistoricoIMC where "+where+" and substr(data,7)||substr(data,4,2)||substr(data,1,2) between '"+primeiraDataSql+"' and '"+ultimaDataSql+"' order by _id desc");
    	Cursor dados = hist.con.rawQuery(sqlBuscar.toString(), null);
    	
    	int[] to= {R.id.tvId,R.id.tvData, R.id.tvUser,R.id.tvSexo,R.id.tvAltura,R.id.tvPeso,R.id.tvResultado,R.id.tvResultadoTexto};
    	String[] from= {"_id","data","user","sexo","altura","peso","resultado","resultTexto"};
    	
    	try
    	{
	    	SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.listagem, dados, from, to,0);
	    	ListView lvDados = (ListView) findViewById(R.id.lvDados);
	    	lvDados.setAdapter(ad);
    	}
    	catch(Exception ex)
    	{
    		Toast.makeText(getBaseContext(), sqlBuscar.toString() + "Erro = "+ ex.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}




	/*
	public void listagem(){
		
		Historico_DAO hist = new Historico_DAO(this);
		
    	StringBuilder sqlBuscar= new StringBuilder();
    	sqlBuscar.append("SELECT * FROM HistoricoIMC order by _id desc");
    	Cursor dados = hist.con.rawQuery(sqlBuscar.toString(), null);
    	
    	//Arraycom os ID dos campos do layout dados
    	int[] to= {R.id.tvId,R.id.tvData,R.id.tvUser,R.id.tvSexo,R.id.tvAltura,R.id.tvPeso,R.id.tvResultado};
    	//Arraycom o nome dos campos da tabela que serao mostrados
    	String[] from= {"_id","data","user","sexo","altura","peso","resultado"};
    	
    	try
    	{
	    	SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.listagem, dados, from, to,0);
	    	ListView lvDados;
	    	lvDados = (ListView) findViewById(R.id.lvDados);
	    	lvDados.setAdapter(ad);
    	}
    	catch(Exception ex)
    	{
    		Toast.makeText(getBaseContext(), sqlBuscar.toString() + "Erro = "+ ex.getMessage(), Toast.LENGTH_LONG).show();
    	}
    }
	
*/	
}
