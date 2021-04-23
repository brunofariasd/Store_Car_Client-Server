package Models;

import java.io.Serializable;

import json.JSONObject;

public class notaFiscal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String endereco;
	private String nomeCarro;
	private int anoCarro;
	private double preco;
	private String renavam;
	
	public notaFiscal(String nome, String cpf, String dataNascimento, String endereco, String nomeCarro, int anoCarro, double preco, String renavam ){	
		this.setNome(nome);
		this.setCpf(cpf);
		this.setDataNascimento(dataNascimento);
		this.setEndereco(endereco);
		this.setNomeCarro(nomeCarro);
		this.setAnoCarro(anoCarro);
		this.setAnoCarro(anoCarro);
		this.setPreco(preco);
		this.setRenavam(renavam);
	}
	
	public notaFiscal(JSONObject j) {
		this.nome = j.getString("nome");
		this.cpf = j.getString("cpf");
		this.dataNascimento = j.getString("dataNascimento");	
		this.endereco = j.getString("endereco");
		this.nomeCarro = j.getString("nomeCarro");
		this.anoCarro = j.getInt("anoCarro");
		this.preco = j.getDouble("preco");
		this.renavam = j.getString("renavam");
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("nome", this.nome);
		json.put("cpf", this.cpf);
		json.put("dataNascimento", this.dataNascimento);		
		json.put("endereco", this.endereco);
		json.put("nomeCarro", this.nomeCarro);
		json.put("anoCarro", this.anoCarro);		
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
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeCarro() {
		return nomeCarro;
	}

	public void setNomeCarro(String nomeCarro) {
		this.nomeCarro = nomeCarro;
	}

	public int getAnoCarro() {
		return anoCarro;
	}

	public void setAnoCarro(int anoCarro) {
		this.anoCarro = anoCarro;
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
