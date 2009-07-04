package com.rollingwithcode.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Começando");
		List<String> lista = new ArrayList<String>();
		lista.add("Uva");
		lista.add("Maça");
		lista.add("Melancia");
		lista.add("Melão");
		lista.add("Maracujá");
		Collection<String> busca = (Collection<String>) new Collect().in(lista).when().eql("Mel", "Ma");
		for(String s : busca){
			System.out.println(s);
		}
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.add(new Pessoa("Rafael", 21));
		pessoas.add(new Pessoa("Suélen", 19));
		pessoas.add(new Pessoa("Samuel", 25));
		pessoas.add(new Pessoa("Pedro", 23));
		pessoas.add(new Pessoa("Rafael", 23));
		Collection<Pessoa> buscaP = (Collection<Pessoa>) new Collect().in(pessoas).when("nome").eql("Rafael");
		for(Pessoa p : buscaP){
			System.out.println(p);
		}
		
		buscaP = (Collection<Pessoa>) new Collect().in(pessoas).when("idade").eql(23);
		for(Pessoa p : buscaP){
			System.out.println(p);
		}
		System.out.println();
		System.out.println();
		buscaP = (Collection<Pessoa>) new Collect().in(pessoas).when("nome", "idade").eql("Rafael", 23);
		for(Pessoa p : buscaP){
			System.out.println(p);
		}
		System.out.println();
		busca = (Collection<String>) new Collect().in(lista).when().like("M");
		for(String s : busca){
			System.out.println(s);
		}
		
		System.out.println();
		buscaP = (Collection<Pessoa>) new Collect().in(pessoas).when("nome").like("S");
		for(Pessoa p : buscaP){
			System.out.println(p);
		}
		
		System.out.println();
		buscaP = (Collection<Pessoa>) new Collect().in(pessoas).when("idade").like("1");
		for(Pessoa p : buscaP){
			System.out.println(p);
		}
		
	}
	private static class Pessoa{
		String nome;
		int idade;
		
		public Pessoa() {
			super();
		}
		
		public Pessoa(String nome, int idade) {
			super();
			this.nome = nome;
			this.idade = idade;
		}
		/**
		 * @return the nome
		 */
		public String getNome() {
			return nome;
		}
		/**
		 * @param nome the nome to set
		 */
		public void setNome(String nome) {
			this.nome = nome;
		}
		/**
		 * @return the idade
		 */
		public int getIdade() {
			return idade;
		}
		/**
		 * @param idade the idade to set
		 */
		public void setIdade(int idade) {
			this.idade = idade;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Pessoa["+nome+", "+idade+" ]";
		}
		
	}
}
