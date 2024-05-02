package Pacote;

import java.util.ArrayList;
import java.util.Objects;

public class Vertice<T> {
	private Filme dado;
	private ArrayList<Aresta<T>> arestaEntrada;
    private ArrayList<Aresta<T>> arestaSaida;
    
    //Precisamos adicionar um boolean para checar se ele foi removido, pois a busca em largura ainda o recomndava, mesmo apos removido
    private boolean ativo = true;
	
	public Vertice(Filme dado) {
		this.dado = dado;
		this.arestaEntrada = new ArrayList<Aresta<T>>();
		this.arestaSaida = new ArrayList<Aresta<T>>();
	}

	public Vertice(Filme dado, ArrayList<Aresta<T>> arestaEntrada, ArrayList<Aresta<T>> arestaSaida) {
		super();
		this.dado = dado;
		this.arestaEntrada = arestaEntrada;
		this.arestaSaida = arestaSaida;
	}

	public Filme getDado() {
		return dado;
	}

	public void setDado(Filme dado) {
		this.dado = dado;
	}

	public ArrayList<Aresta<T>> getArestaEntrada() {
		return arestaEntrada;
	}

	public void setArestaEntrada(ArrayList<Aresta<T>> arestaEntrada) {
		this.arestaEntrada = arestaEntrada;
	}

	public ArrayList<Aresta<T>> getArestaSaida() {
		return arestaSaida;
	}

	public void setArestaSaida(ArrayList<Aresta<T>> arestaSaida) {
		this.arestaSaida = arestaSaida;
	}
	
	public void adicionarArestaEntrada(Aresta<T> arestaEntrada) {
		this.arestaEntrada.add(arestaEntrada);
	}
	
	public void adicionarArestaSaida(Aresta<T> arestaSaida) {
		this.arestaSaida.add(arestaSaida);
	}

	@Override
	public int hashCode() {
		return Objects.hash(arestaEntrada, arestaSaida, dado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		return Objects.equals(arestaEntrada, other.arestaEntrada) && Objects.equals(arestaSaida, other.arestaSaida)
				&& Objects.equals(dado, other.dado);
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
	
	
}
