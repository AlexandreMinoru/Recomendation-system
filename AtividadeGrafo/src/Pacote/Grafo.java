package Pacote;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Grafo <T>{
	private ArrayList<Vertice<T>> vertices;
	private ArrayList<Aresta<T>> arestas;
	
	public Grafo() {
		this.arestas = new ArrayList<Aresta<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}
	
	public void adicionarVertice(Filme dado) {
		Vertice<T> novoVertice = new Vertice<T>(dado);
		this.vertices.add(novoVertice);
	}
	
	public Vertice<T> getVertice(Filme dado){
		Vertice<T> vertice = null;
		//Percorre as vertices no grafo para encontrar se alguma é igual ao dado passado
		for (int i = 0; i < this.vertices.size(); i++) {
			if(this.vertices.get(i).getDado().equals(dado)) {
				vertice = this.vertices.get(i);
				break;
			}
		}
		//Retorna a vertice encontrada ou nulo caso não encontre nenhuma
		return vertice;
	}
	
	public void adicionarAresta(Filme dadoInicio, Filme dadoFim) {
		Vertice<T> fim = this.getVertice(dadoFim);
		Vertice<T> inicio = this.getVertice(dadoInicio);
		int peso = calcularPeso(dadoInicio, dadoFim);
		Aresta<T> aresta = new Aresta<T>(peso, inicio, fim);
		inicio.adicionarArestaSaida(aresta);
		fim.adicionarArestaEntrada(aresta);
		
		//Faz uma aresta para volta
		Aresta<T> arestaVolta = new Aresta<T>(peso, inicio, fim);
		inicio.adicionarArestaEntrada(aresta);
		fim.adicionarArestaSaida(aresta);
		this.arestas.add(aresta);
		this.arestas.add(arestaVolta);
		
	}
	
	public int calcularPeso(Filme inicio, Filme fim) {
		int peso = 0;
		Filme verticeInicio = this.getVertice(inicio).getDado();
		Filme verticeFim = this.getVertice(fim).getDado();
		//Faz o peso ser adicionado dinâmicanete a partir das semelhanças dos filmes
		if(verticeInicio.getNome().equalsIgnoreCase(verticeFim.getNome())) {
			peso ++;
		}
		if(verticeInicio.getDiretor().equalsIgnoreCase(verticeFim.getDiretor())) {
			peso ++;
		}
		if(verticeInicio.getGenero().equalsIgnoreCase(verticeFim.getGenero())) {
			peso ++;
		}
		if(verticeInicio.getAno() == verticeFim.getAno()) {
			peso++;
		}
		if(verticeInicio.getNota() <= verticeFim.getNota()) {
			peso++;
		}
		return peso;
	}
	
	public void buscaEmLargura(){
		//Busca em largura, apenas de teste
		ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();
        Vertice<T> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<T> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestaSaida().size(); i++){
                Vertice<T> proximo = visitado.getArestaSaida().get(i).getFim();
                if (!marcados.contains(proximo)){ //se o vértice ainda não foi marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }
    
	public Filme editarVertice(Filme filme) throws Exception {
		Vertice<T> vertice = this.getVertice(filme);
		if(vertices.contains(vertice)) {
			int i = vertices.indexOf(vertice);
			String nome = JOptionPane.showInputDialog("Informe o nome");
			String genero = JOptionPane.showInputDialog("Informe o gênero");
			String diretor = JOptionPane.showInputDialog("Informe o diretor");
			int ano = Integer.parseInt(JOptionPane.showInputDialog("Informe o ano de lançamento"));
			int nota = Integer.parseInt(JOptionPane.showInputDialog("Informe a nota"));
			String url = JOptionPane.showInputDialog("Informe a url da imagem");
			Filme novoFilme = new Filme(nome, diretor, genero, url, nota, ano);
			vertices.get(i).setDado(novoFilme);
			return novoFilme;
		}else {
			throw new Exception("Não encontrado");
		}
	}
	
	public Filme editarVertice(Filme filme, int opcao) throws Exception {
		Vertice<T> vertice = this.getVertice(filme);
		int i = vertices.indexOf(vertice);
		if(vertices.contains(vertice)) {
			switch(opcao) {
			case 1:
				vertices.get(i).getDado().setNome(JOptionPane.showInputDialog("Informe o nome"));;
				break;
			case 2:
				vertices.get(i).getDado().setGenero(JOptionPane.showInputDialog("Informe o gênero"));
				break;
			case 3:
				vertices.get(i).getDado().setDiretor(JOptionPane.showInputDialog("Informe o diretor"));
				break;
			case 4:
				vertices.get(i).getDado().setAno(Integer.parseInt(JOptionPane.showInputDialog("Informe o ano de lançamento")));
				break;
			case 5:
				vertices.get(i).getDado().setNota(Integer.parseInt(JOptionPane.showInputDialog("Informe a nota")));
				break;
			case 6:
				vertices.get(i).getDado().setFt(JOptionPane.showInputDialog("Informe a url da imagem"));
				break;
			default:
				throw new Exception("Valor inválido");

			}
		}else {
			throw new Exception("Filme encontrado");
		}
		Filme filmeEditado = vertices.get(i).getDado();
		return filmeEditado;
	}
	
	public ArrayList<Filme> buscaEmLarguraArray(Filme dado){
		//Busca dem largura que retorna uma array
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();
        ArrayList<Filme> filmes = new ArrayList<Filme>();
        filmes.clear();
        Vertice<T> atual = this.vertices.get(this.vertices.indexOf(this.getVertice(dado)));
        marcados.add(atual);
        //Adiciona na arraylist o filme
        filmes.add(atual.getDado());
        fila.add(atual);
        while(fila.size() >  0){
            Vertice<T> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestaSaida().size(); i++){
                Vertice<T> proximo = visitado.getArestaSaida().get(i).getFim();
                if (!marcados.contains(proximo) && proximo.isAtivo()){ 
                    marcados.add(proximo);
                    //Adiciona na arraylist o filme
                    filmes.add(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
        return filmes;
    }
	
	public void removerVertice(Filme filme) {
		Vertice<T> vertice = this.getVertice(filme);
		//Checa se existe a vertice
		if (this.vertices.contains(vertice)) {
			//Checa o index
			int i = this.vertices.indexOf(vertice);
			//Desativa ela
			this.vertices.get(i).setAtivo(false);
			//Remove
            this.vertices.remove(vertice);
		}
	}
	
	public void removeAresta(Filme source) {
		Vertice<T> vertice = this.getVertice(source);
		//Uma array para armazenar todas as arestas que precisam ser removidas
		ArrayList<Aresta<T>> arestasRemover = new ArrayList<Aresta<T>>();
		//Percorre arestas
        for (int i = 0; i < this.arestas.size(); i++) {
        	//Checa se o final ou inicio é igual ao filme passado
            if (arestas.get(i).getInicio().equals(vertice) || arestas.get(i).getFim().equals(vertice) ){
            	//Adiciona na array de arestas pra remover
                arestasRemover.add(arestas.get(i));
                
            }
        }
        //Checa se a array esta vazia
        if (arestasRemover != null) {
        	//PErcorre removendo todos as arestas
        	for (int i = 0; i < arestasRemover.size(); i++) {
        		arestas.remove(arestasRemover.get(i));
        		
			}
            
        }
    

	}
}
