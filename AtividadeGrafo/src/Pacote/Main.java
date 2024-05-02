package Pacote;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		
		
		Filme velozes = new Filme("Velozes e furiosos", "tom cruise","acao","asdajsdhash", 5,123123);
		Filme indiana = new Filme("Indiana Jones", "luke","acao","asdasd",4,123123);
		Filme killbill = new Filme("Kill bill", "tom cruise", "acao", "sadasd", 3,123123);
		Filme pokemon = new Filme("Kill bill", "tom cruise", "acao", "sadasd", 3,2302);
		Filme teste = new Filme("TEste", "magia", "acao", "asdad",3,2323);
		grafo.adicionarVertice(velozes);
		grafo.adicionarVertice(killbill);
		grafo.adicionarVertice(indiana);
		grafo.adicionarVertice(pokemon);
		grafo.adicionarVertice(teste);
		
		grafo.adicionarAresta(velozes, killbill);
		grafo.adicionarAresta(killbill, pokemon);
		grafo.adicionarAresta(pokemon, indiana);
		grafo.adicionarAresta(indiana, teste);
		grafo.adicionarAresta(velozes, pokemon);
		grafo.adicionarAresta(teste, killbill);
		grafo.adicionarAresta(teste, velozes);
		
		grafo.removeAresta(teste);
		grafo.removerVertice(teste);
		grafo.buscaEmLargura();
	}

}
