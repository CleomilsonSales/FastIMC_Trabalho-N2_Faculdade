package com.example.fastimc_trabalhon1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Principal extends Activity {
	private Button btGerar,btListagem,btListaUser;
	private EditText etPeso,etAltura;
	private TextView tvBemVindo;
	private String usuarioLogado;
	private RadioGroup rgSexo;
	
	private String nomeAdmin; 

    @SuppressLint("DefaultLocale")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        
        nomeAdmin = getString(R.string.nomeAdmin);
        
    	carregandoTelaPrincipal();
    	
    	String textBemVindo = getString(R.string.tvBemVindo);
    	tvBemVindo = (TextView) findViewById(R.id.tVBemVindo);
    	
		Intent i = getIntent();
		usuarioLogado =i.getStringExtra("usuarioLogado").toUpperCase();
    	
        tvBemVindo.setText(textBemVindo+" "+usuarioLogado+" "); 
        
        if (!usuarioLogado.equals(nomeAdmin)){
        	Button btListaUser = (Button) findViewById(R.id.btListaUser);
        	btListaUser.setVisibility(View.GONE);
        	
        }
            
    }
    
    @SuppressLint("DefaultLocale")
	public void carregandoTelaPrincipal(){
    	btGerar = (Button) findViewById(R.id.btGerar);
        
        btGerar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    	etPeso = (EditText) findViewById(R.id.etPeso);
		    	etAltura = (EditText) findViewById(R.id.etAltura);
		    
		    	Auxiliar aux = new Auxiliar(); 
		    	carrendandoVarMsg(aux);
		    	if (aux.verificarCampos(etPeso, etAltura)){
		    	
			    	double vPeso = Double.parseDouble(etPeso.getText().toString());
			    	double vAltura = Double.parseDouble(etAltura.getText().toString());
			    	String vSexo = "F";
			    	
			    	rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
			    	switch (rgSexo.getCheckedRadioButtonId()) {
			    	    case R.id.rbSexo1:
			    	    	vSexo = "F";
			    	        break;
			    	    case R.id.rbSexo2:
			    	    	vSexo = "M";
			    	        break;
			    	}
			    	
			    	String msg = aux.GerarIMC(vPeso,vAltura,vSexo,aux);
			    	//exibirMensagens(msg);

			    	//Insirindo historico
			    	Historico h = new Historico();
			        
			        h.setvUser(usuarioLogado);
			        h.setvSexo(vSexo);
			        h.setvPeso(vPeso);
			        h.setvAltura(vAltura);
			        h.setvResultado(aux.vResultadoPassando);
			        h.setvResultTexto(aux.vResultTextoPassando.toUpperCase());
			    	
			    	inserirIMC_P(h);
			    	
			    	String passandoTexto = aux.vResultTextoPassando;
			    	String msgSimNaoAbrirSite = getString(R.string.mensagemSimNaoAbrirSite);
			    	String msgBtSim = getString(R.string.sim);
					String msgBtNao = getString(R.string.nao);
			    	
			    	mensagemSimNao(passandoTexto,msg+" "+msgSimNaoAbrirSite, msgBtSim, msgBtNao);
			    	
			    	
			    	
		    	}else{
		    		exibirMensagens(aux.getMensagemCamposVazios());
		    	}
			}
        });
        
        btListagem = (Button) findViewById(R.id.btListagem);
        
        btListagem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Principal.this,ExibirListagemActivity.class);
				i.putExtra("usuarioLogado", usuarioLogado);
				
				Principal.this.startActivity(i);
				
				
			}
        });
        
        btListaUser = (Button) findViewById(R.id.btListaUser);
        
        btListaUser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Principal.this,ExibirListagemUserActivity.class);
				Principal.this.startActivity(i);
			}
        });
    }
    
    public void mensagemSimNao(final String textoPassando,String msgAbrirSite,String msgBtSim,String msgBtNao){
		//Alert com Sim ou Nao
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
	           switch (which){
	           case DialogInterface.BUTTON_POSITIVE:
	        	   abrirDicas(textoPassando);
	               break;

	           case DialogInterface.BUTTON_NEGATIVE:
	               //Sem acao
	               break;
	           }
	       }
		};

	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
	   builder.setMessage(msgAbrirSite).setPositiveButton(msgBtSim, dialogClickListener)
	       .setNegativeButton(msgBtNao, dialogClickListener).show();
		
		//--
	}
    
    public void abrirDicas(String direcaoLink){
    	Intent i = new Intent(Principal.this,Dicas.class);
    	i.putExtra("direcaoLinkPassado", direcaoLink);
		Principal.this.startActivity(i);
    }
    
    public void inserirIMC_P(Historico h){

    	Historico_DAO hist = new Historico_DAO(this);

    	try
    	{
    		hist.inserirIMC(h);
    		//Toast.makeText(getBaseContext(), "Inserido", Toast.LENGTH_SHORT).show();
    	}
    	catch(SQLException ex)
    	{
    		String textMensagemErroInserir = getString(R.string.mensagemErroInserir);	
    		exibirMensagens(textMensagemErroInserir + ex.getMessage());
    	}
    }
    
    
            
	public void exibirMensagens(String msgRetorno){
        String textBotaoEnviar = getString(R.string.textBotaoEnviar);
        String textTitulo = getString(R.string.textTitulo);
        
    	AlertDialog.Builder msg = new AlertDialog.Builder(this);
    	msg.setTitle(textTitulo);
    	msg.setNeutralButton(textBotaoEnviar, null);
		msg.setMessage(msgRetorno);
		msg.show();
    }
    
	public void carrendandoVarMsg(Auxiliar aux){
    	aux.setMensagemIMCErro(getString(R.string.mensagemIMCErro));
    	aux.setMensagemIMCBaixo(getString(R.string.mensagemIMCBaixo));
    	aux.setMensagemIMCNormal(getString(R.string.mensagemIMCNormal));
    	aux.setMensagemIMCMagAcima(getString(R.string.mensagemIMCMagAcima));
    	aux.setMensagemIMCAcima(getString(R.string.mensagemIMCAcima));
    	aux.setMensagemIMCObeso(getString(R.string.mensagemIMCObeso));
    	aux.setMensagemCamposVazios(getString(R.string.mensagemCamposVazios));
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
