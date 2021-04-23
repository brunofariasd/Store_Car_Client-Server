package Models;

import java.io.Serializable;


import json.JSONObject;

public class Carro implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nome;
	private String categoria;
	private int ano;
	private double preco;
	private String renavam;
	
	public Carro(JSONObject j) {
		this.nome = j.getString("nome");
		this.categoria = j.getString("categoria");
		this.ano = j.getInt("ano");
		this.preco = j.getDouble("preco");
		this.renavam = j.getString("renavam");
	}
	
	
	public Carro(String nome, String categoria, int ano, double preco, String renavam) {
		this.nome = nome;
		this.categoria = categoria;
		this.ano = ano;
		this.preco = preco;
		this.renavam = renavam;
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("nome", this.nome);
		json.put("categoria", this.categoria);
		json.put("ano", this.ano);
		json.put("preco", this.preco);
		json.put("renavam", this.renavam);
		return json;
	}

	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public double getPreco() {
		return preco;
	}	

	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	public String getRenavam() {
		return renavam;
	}


	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

}
