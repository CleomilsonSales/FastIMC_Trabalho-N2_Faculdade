package com.example.fastimc_trabalhon1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class Dicas extends Activity {
	
	String direcaoLinkRecebido ;
	String baixoPeso,normalPeso,magAcimaPeso,acimaPeso,obesoPeso;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dicas);

        //recebendo usuario
        Intent i = getIntent();
        direcaoLinkRecebido = i.getStringExtra("direcaoLinkPassado");
		//--
        carrendandoVar();
        carregarPages(direcaoLinkRecebido);
            
    }
    
    public void carregarPages(String direcaoLink){
    	WebView myWebView = (WebView) findViewById(R.id.webview);
        
    	if(direcaoLink.equals(obesoPeso)){
    		//perder
            myWebView.loadUrl("http://www.tuasaude.com/5-dicas-simples-para-emagrecer-e-perder-barriga/");
	
    	}else{
	    	if(direcaoLink.equals(acimaPeso)){
	    		//perder
	            myWebView.loadUrl("http://www.tuasaude.com/5-dicas-simples-para-emagrecer-e-perder-barriga/");
		
	    	}else{
		    	if(direcaoLink.equals(magAcimaPeso)){
		    		//perder
		            myWebView.loadUrl("http://www.tuasaude.com/5-dicas-simples-para-emagrecer-e-perder-barriga/");
			
		    	}else{
		    		if(direcaoLink.equals(normalPeso)){
		    			//manter
		    	         myWebView.loadUrl("http://www.dicasparaperderpeso.com.br/como-manter-o-peso-depois-de-emagrecer/");
		    	         
		        	}else{
		        		if(direcaoLink.equals(baixoPeso)){
		        			//ganhar
		        			myWebView.loadUrl("http://www.bancodesaude.com.br/dieta/para-engordar");
		        		}
		        	}
		    	}
	    	}	
    	}
    	
         
         
         
         /*/ se usar javascript
         WebSettings webSettings = myWebView.getSettings();
         webSettings.setJavaScriptEnabled(true);
         //--*/
    }
    
	public void carrendandoVar(){
    	baixoPeso = getString(R.string.mensagemIMCBaixo);
    	normalPeso = getString(R.string.mensagemIMCNormal);
    	magAcimaPeso = getString(R.string.mensagemIMCMagAcima);
    	acimaPeso = getString(R.string.mensagemIMCAcima);
    	obesoPeso = getString(R.string.mensagemIMCObeso);
	}
    
}
