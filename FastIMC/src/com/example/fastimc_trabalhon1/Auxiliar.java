package com.example.fastimc_trabalhon1;

import java.text.DecimalFormat;

import android.widget.EditText;

public class Auxiliar {
	
	private String mensagemIMCErro,mensagemIMCBaixo,mensagemIMCNormal,mensagemIMCMagAcima,mensagemIMCAcima,mensagemIMCObeso,mensagemCamposVazios;
	protected String vResultadoPassando,vResultTextoPassando;

	public boolean verificarCampos(EditText vPeso, EditText vAltura){
		boolean retorno = true;
    	if (vPeso.getText().toString().equals("")){
    		retorno = false;
    	}
    	if (vAltura.getText().toString().equals("")){
    		retorno = false;
    	}
		return retorno;
    	
    }
	
	public boolean verificarCamposUser(EditText vUser, EditText vPass, EditText vPassR){
		boolean retorno = true;
    	if (vUser.getText().toString().equals("")){
    		retorno = false;
    	}
    	if (vPass.getText().toString().equals("")){
    		retorno = false;
    	}
    	if (vPassR.getText().toString().equals("")){
    		retorno = false;
    	}
		return retorno;
    	
    }
    
	public String GerarIMC(double vPeso, double vAltura,String vSexo,Auxiliar aux){
    	
    	double resultado = vPeso / (vAltura*vAltura);
    	String retorno =  aux.getMensagemIMCErro();
		String retornoTexto = null;
    	DecimalFormat decimal = new DecimalFormat("0.00"); 
    	if (vSexo == "F") {
	     	if (resultado < 19.1){
	    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCBaixo();
	    		retornoTexto = aux.getMensagemIMCBaixo();
	    	}else{
	    		if ((resultado > 19.1)&&(resultado < 25.8)){
		    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCNormal();
		    		retornoTexto = aux.getMensagemIMCNormal();
		    	}else{
		    		if ((resultado > 25.8)&&(resultado < 27.3)){
			    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCMagAcima();
			    		retornoTexto = aux.getMensagemIMCMagAcima();
			    	}else{
			    		if ((resultado > 27.3)&&(resultado < 32.3)){
				    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCAcima();
				    		retornoTexto = aux.getMensagemIMCAcima();
				    	}else{
				    		if (resultado > 32.3){
					    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCObeso();
					    		retornoTexto = aux.getMensagemIMCObeso();
					    	}	
				    	}
			    	}
		    	}
	    	}
    	}else{
    		if (resultado < 20.7){
	    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCBaixo();
	    		retornoTexto = aux.getMensagemIMCBaixo();
	    	}else{
	    		if ((resultado > 20.7)&&(resultado < 26.4)){
		    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCNormal();
		    		retornoTexto = aux.getMensagemIMCNormal();
		    	}else{
		    		if ((resultado > 26.4)&&(resultado < 27.8)){
			    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCMagAcima();
			    		retornoTexto = aux.getMensagemIMCMagAcima();
			    	}else{
			    		if ((resultado > 27.8)&&(resultado < 31.1)){
				    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCAcima();
				    		retornoTexto = aux.getMensagemIMCAcima();
				    	}else{
				    		if (resultado > 31.1){
					    		retorno = "ICM="+decimal.format(resultado)+" - "+aux.getMensagemIMCObeso();
					    		retornoTexto = aux.getMensagemIMCObeso();
					    	}	
				    	}
			    	}
		    	}
	    	}
    	}
    	setvResultTextoPassando(retornoTexto);
    	setvResultadoPassando(decimal.format(resultado));
    	return retorno;
    }
	
	public String getvResultadoPassando() {
		return vResultadoPassando;
	}

	public void setvResultadoPassando(String vResultadoPassando) {
		this.vResultadoPassando = vResultadoPassando;
	}
    
    public String getMensagemIMCErro() {
		return mensagemIMCErro;
	}

	public void setMensagemIMCErro(String mensagemIMCErro) {
		this.mensagemIMCErro = mensagemIMCErro;
	}

	public String getMensagemIMCBaixo() {
		return mensagemIMCBaixo;
	}

	public void setMensagemIMCBaixo(String mensagemIMCBaixo) {
		this.mensagemIMCBaixo = mensagemIMCBaixo;
	}

	public String getMensagemIMCNormal() {
		return mensagemIMCNormal;
	}

	public void setMensagemIMCNormal(String mensagemIMCNormal) {
		this.mensagemIMCNormal = mensagemIMCNormal;
	}

	public String getMensagemIMCMagAcima() {
		return mensagemIMCMagAcima;
	}

	public void setMensagemIMCMagAcima(String mensagemIMCMagAcima) {
		this.mensagemIMCMagAcima = mensagemIMCMagAcima;
	}

	public String getMensagemIMCAcima() {
		return mensagemIMCAcima;
	}

	public void setMensagemIMCAcima(String mensagemIMCAcima) {
		this.mensagemIMCAcima = mensagemIMCAcima;
	}

	public String getMensagemIMCObeso() {
		return mensagemIMCObeso;
	}

	public void setMensagemIMCObeso(String mensagemIMCObeso) {
		this.mensagemIMCObeso = mensagemIMCObeso;
	}
	
    public String getMensagemCamposVazios() {
		return mensagemCamposVazios;
	}

	public void setMensagemCamposVazios(String mensagemCamposVazios) {
		this.mensagemCamposVazios = mensagemCamposVazios;
	}

	public String getvResultTextoPassando() {
		return vResultTextoPassando;
	}

	public void setvResultTextoPassando(String vResultTextoPassando) {
		this.vResultTextoPassando = vResultTextoPassando;
	}
	
	
}
