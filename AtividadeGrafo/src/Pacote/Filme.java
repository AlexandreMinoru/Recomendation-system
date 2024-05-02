package Pacote;

import java.util.Objects;

public class Filme {
	private String nome, diretor, genero, ft;
	private int nota, ano;
	
	
	
	public Filme(String nome, String diretor, String genero, String ft, int nota, int ano) {
		super();
		this.nome = nome;
		this.diretor = diretor;
		this.genero = genero;
		this.ft = ft;
		this.nota = nota;
		this.setAno(ano);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFt() {
		return ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}


	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(ano, diretor, ft, genero, nome, nota);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return ano == other.ano && Objects.equals(diretor, other.diretor) && Objects.equals(ft, other.ft)
				&& Objects.equals(genero, other.genero) && Objects.equals(nome, other.nome) && nota == other.nota;
	}

	@Override
	public String toString() {
		return "Filme: " + this.nome + "\n Gênero: " + this.genero + "\n Diretor: " + this.diretor + "Ano lançamento: " + this.ano+ "\n Nota: " + this.nota + "/5\n";
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	
}
