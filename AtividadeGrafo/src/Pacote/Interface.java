package Pacote;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Interface<T> extends JFrame implements ActionListener{
	//Instanciando o grafo e a ArrayList que vai guardar os filmes
	Grafo<T> grafo = new Grafo<T>();
	ArrayList<Filme> filmes = new ArrayList<Filme>();
	//Instanciando variáveis globais que precisam ser tratadas durante o código
	  JMenuBar barraMenu;
	  JMenu principal, atualizar;
	  JMenuItem main, adicionar, editar,remover;
	  JPanel inicial, painelAdicionar, painelRemover, menu, listaFilme, painelEditar;
	  JLabel recomendacao;
	  
	  //Método construtor que já implementa o JFrame
	public Interface() {
		
		//Adicionando alguns filmes
		Filme velozes = new Filme("Velozes e furiosos", "Vin Diesel","Ação","https://pbs.twimg.com/media/FluIoz2XoAYkeYn.jpg:large", 5,2001);
		Filme indiana = new Filme("Cyberpunk Edgerunners", "Hiroyuki Imaishi","Animação","https://static.wikia.nocookie.net/cyberpunk/images/a/ac/David_Infobox_CPEDGE.png/revision/latest?cb=20220915120459",5,2022);
		Filme killbill = new Filme("JoJo Bizare Adventures", "Hirohiko Araki", "Animação", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7ArSMzow7HT-N7OZWwBZMNqUDE4R-L8BghA&usqp=CAU", 5,2004 );
		Filme pokemon = new Filme("Kill bill", "Quentin Tarantino", "Ação", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmI5EfjTXcIa5yLVXe3Odk_hvwMwQ2A-OctZ75hc0nhuGhkuYVSASI_N3RI1NFYal4Qlc&usqp=CAU", 3,2003 );
		filmes.add(killbill);
		filmes.add(velozes);
		filmes.add(indiana);
		filmes.add(pokemon);
		grafo.adicionarVertice(pokemon);
		grafo.adicionarVertice(velozes);
		grafo.adicionarVertice(indiana);
		grafo.adicionarVertice(killbill);
		//Adicionando relações entre eles
		grafo.adicionarAresta(killbill, pokemon);
		grafo.adicionarAresta(killbill, velozes);
		grafo.adicionarAresta(killbill, indiana);
		grafo.adicionarAresta(pokemon, killbill);
		grafo.adicionarAresta(pokemon, velozes);
		grafo.adicionarAresta(pokemon, indiana);
		grafo.adicionarAresta(velozes, pokemon);
		grafo.adicionarAresta(velozes, velozes);
		grafo.adicionarAresta(velozes, indiana);
		inicial =  new JPanel();
		
		//Configurações básicas do JFrame
		inicial.setLayout(new BorderLayout());
		setTitle("Atividade aula reversa com grafos");
		setSize(2000,2000);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.lightGray);
		
		//Criação do painel que vai guardar o menu
		menu = new JPanel();
		menu.setLayout(new GridLayout(0,1));
		
		//Criando painel que ficará no meio
		listaFilme = new JPanel();
		recomendacao = criarLabel("");
		recomendacao.setForeground(Color.blue);
		listaFilme.setLayout(new FlowLayout());
		add(menu, BorderLayout.NORTH);
		//Adicionando eles no Frame
		inicial.add(listaFilme, BorderLayout.CENTER);
		renderizarLista(filmes);
		

		menu.setBackground(new Color(208, 211, 212));
		criarMenu();
		menu.add(recomendacao);
		add(inicial);
		this.revalidate();
	}
	//Main apenas para chamar o construtor
	public static void main(String[] args) {
		new Interface();
		
		

	}
	//NEsta função será criado cada painel de filmes passando como parâmetro o filme
	public JPanel criarPanelFilme(Filme filme) {
		//Criado o painel
		JPanel painel = new JPanel();
		painel.setBorder(new LineBorder(Color.black));
		painel.setLayout(new GridLayout(0,1));
		painel.setAlignmentX(SwingConstants.CENTER);;
		painel.setBackground(Color.lightGray);
		
		//Criado paineis que ficarão dentro do painel principal
		JPanel um =criarPainelInfo();
		JPanel dois = criarPainelInfo();
		JPanel tres = criarPainelInfo();
		JPanel quatro = criarPainelInfo();
		JPanel cinco = criarPainelInfo();
		JPanel seis = criarPainelInfo();
		JPanel sete = criarPainelInfo();
		//Lendo a imagem da url
		try {
			BufferedImage bufferedImage = ImageIO.read(new URL(filme.getFt()));
			Image image = bufferedImage.getScaledInstance(70, 80, Image.SCALE_DEFAULT);
			JLabel img = new JLabel(new ImageIcon(image));


			img.setSize(1,1);
			um.add(img);
		} catch (MalformedURLException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		//Criando os labels e botão
		JLabel nome = criarLabel("Nome: " +filme.getNome());
		JLabel genero = criarLabel("Genero: "+ filme.getGenero());
		JLabel diretor = criarLabel("Diretor: "+filme.getDiretor());
		JLabel ano = criarLabel("Ano: " +filme.getAno());
		JLabel nota = criarLabel("Nota: "+Integer.toString(filme.getNota())+"/5");
		JButton like = new JButton("Gostei");
		like.setBackground(Color.green);
		
		//Adicionando listener de evento ao clicar no botão
		like.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				novaPágina(filme);
				
			}

			private void novaPágina(Filme filme) {
				//Criar uma nova ArrayList para armazenar os filmes que vai receber da função buscaEmLarguraArray
				ArrayList<Filme> filmesRecomendados = grafo.buscaEmLarguraArray(filme);
				recomendacao.setText("Recomendações");
				recomendacao.setForeground(Color.red);
				//Limpa o Painel para não concatenar com os outros
				listaFilme.removeAll();
				//Tira o filme que foi selecionado
				filmesRecomendados.remove(0);
				//Checa se a lista de filmes recomendados está vazia
				if(filmesRecomendados.size() ==0) {
					recomendacao.setText("Nenhuma recomendação");
					recomendacao.setForeground(Color.red);
				}
				//Renderiza a tela com os filmes recomendadods
				renderizarLista(filmesRecomendados);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		
		//Adicionando os lables aos seus paineis
		dois.add(nome);
		tres.add(genero);
		quatro.add(diretor);
		cinco.add(ano);
		seis.add(nota);
		sete.add(like);
		
		//Adiciona os paineis ao painel principal
		painel.add(um);
		painel.add(dois);
		painel.add(tres);
		painel.add(quatro);
		painel.add(cinco);
		painel.add(seis);
		painel.add(sete);
		
		return painel;
	}
	
	public void criarMenu() {
		//Adiciona as opções no menu
		barraMenu = new JMenuBar();
		principal = new JMenu("Principal");
		atualizar = new JMenu("Atualizar");
		main = new JMenuItem("Main");
		adicionar = new JMenuItem("Adicionar");
		remover = new JMenuItem("Remover");
		editar  = new JMenuItem("Editar");
		//Adiciona os tratamento de eventos
		adicionar.addActionListener(this);
		main.addActionListener(this);
		remover.addActionListener(this);
		editar.addActionListener(this);
		//Adiciona no menu as opções
		barraMenu.add(principal);
		barraMenu.add(atualizar);
		principal.add(main);
		atualizar.add(adicionar);
		atualizar.add(editar);
		atualizar.add(remover);
		//Adiciona no frame
		menu.add(barraMenu);
	}
	
	public JLabel criarLabel(String texto) {
		
		//Função criada para criar labels de maneira padronizada
		JLabel label = new JLabel(texto);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		return label;
	}
	
	public void renderizarLista(ArrayList<Filme> filmeNovo) {
		//remove todos os outros filmes previamente renderizados
		listaFilme.removeAll();
		//Laço de repetição feito para renderizar cada filme na tela
		for (int i = 0; i < filmeNovo.size(); i++) {
			Filme filme = filmeNovo.get(i);
			listaFilme.add(criarPanelFilme(filme));
		}
	}
	//Tratamneto de eventos
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adicionar) {
			paginaAdicionar();
		}
		if(e.getSource() == main) {
			paginaInicial();
		}
		if(e.getSource() == remover) {
			paginaRemover();
		}
		if(e.getSource() == editar) {
			paginaEditar();
		}
	}
	
	private void paginaEditar() {
		//Remove outras telas
		this.limparTela();
		//Adiciona e renderiza a página de editar
		painelEditar = new JPanel();
		painelEditar.setLayout(new BorderLayout());
		renderizarPaginaEditar(filmes);
		recomendacao.setText("Editar");
		recomendacao.setForeground(Color.blue);
		add(painelEditar, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
		
	}

	private void renderizarPaginaEditar(ArrayList<Filme> filmeNovo) {
		painelEditar.removeAll();
		JPanel novoPanel = new JPanel();
		novoPanel.removeAll();
		for (int i = 0; i < filmeNovo.size(); i++) {
			Filme filme = filmeNovo.get(i);
			novoPanel.add(criarPanelFilmeEditar(filme));
		}
		painelEditar.add(novoPanel);
		
	}

	private Component criarPanelFilmeEditar(Filme filme) {
		JPanel painel = new JPanel();
		painel.setBorder(new LineBorder(Color.black));
		painel.setLayout(new GridLayout(0,1));
		painel.setAlignmentX(SwingConstants.CENTER);;
		painel.setBackground(Color.lightGray);
		
		JPanel um =criarPainelInfo();
		JPanel dois = criarPainelInfo();
		JPanel tres = criarPainelInfo();
		JPanel quatro = criarPainelInfo();
		JPanel cinco = criarPainelInfo();
		JPanel seis = criarPainelInfo();
		JPanel sete = criarPainelInfo();
		try {
			BufferedImage bufferedImage = ImageIO.read(new URL(filme.getFt()));
			Image image = bufferedImage.getScaledInstance(70, 80, Image.SCALE_DEFAULT);
			JLabel img = new JLabel(new ImageIcon(image));


			img.setSize(1,1);
			um.add(img);
		} catch (MalformedURLException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		JLabel nome = criarLabel("Nome: " +filme.getNome());
		JLabel genero = criarLabel("Genero: "+ filme.getGenero());
		JLabel diretor = criarLabel("Diretor: "+filme.getDiretor());
		JLabel ano = criarLabel("Ano: " +filme.getAno());
		JLabel nota = criarLabel("Nota: "+Integer.toString(filme.getNota())+"/5");
		JButton like = new JButton("Editar");
		like.setBackground(Color.blue);
		like.setForeground(Color.white);
		like.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Confirma se quer editar
				int reply = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar?:\n " + filme.toString(),"?", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					//Pergunta o que quer alterar
				    int opcao = Integer.parseInt(JOptionPane.showInputDialog("O que deseja fazer? \n"
				    		+ "[1] Alterar nome \n"
				    		+ "[2] Alterar gênero \n"
				    		+ "[3] Alterar diretor \n"
				    		+ "[4] Alterar ano \n"
				    		+ "[5] Alterar nota \n"
				    		+ "[6] Alterar url da Imagem \n"
				    		+ "[7] Alterar tudo"));
				    switch(opcao) {
				    //Caso for 7, é preciso alterar tudo
				    case 7:
				    	try {
				    		int i = filmes.indexOf(filme);
				    		
							Filme novoFilme = grafo.editarVertice(filme);
							filmes.set(i, novoFilme);
						} catch (Exception e1) {
							recomendacao.setText("Filme inválido");
						}
				    	break;
				    	//Caso contrário, passa a opção e altera apenas o selecionado
				    default:
				    	try {
				    		int i = filmes.indexOf(filme);
				    		Filme novoFilme = grafo.editarVertice(filme, opcao);
				    		filmes.set(i, novoFilme);
						} catch (Exception e1) {
							recomendacao.setText("Valor inválido");
						}
				    }
				    
				    limparTela();
				    renderizarPaginaEditar(filmes);
				    getContentPane().add(painelEditar, BorderLayout.CENTER);
				    getContentPane().revalidate();
				    getContentPane().repaint();
				}
			}

			
		});
		dois.add(nome);
		tres.add(genero);
		quatro.add(diretor);
		cinco.add(ano);
		seis.add(nota);
		sete.add(like);
		painel.add(um);
		painel.add(dois);
		painel.add(tres);
		painel.add(quatro);
		painel.add(cinco);
		painel.add(seis);
		painel.add(sete);
		
		return painel;
	}

	private void paginaRemover() {
		this.limparTela();
		painelRemover = new JPanel();
		painelRemover.setLayout(new BorderLayout());
		renderizarPaginaRemover(filmes);
		recomendacao.setText("Remover");
		recomendacao.setForeground(Color.red);
		add(painelRemover, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	private void renderizarPaginaRemover(ArrayList<Filme> filmeNovo) {
		painelRemover.removeAll();
		JPanel novoPanel = new JPanel();
		novoPanel.removeAll();
		for (int i = 0; i < filmeNovo.size(); i++) {
			Filme filme = filmeNovo.get(i);
			novoPanel.add(criarPanelFilmeRemover(filme));
		}
		painelRemover.add(novoPanel);
	}

	private Component criarPanelFilmeRemover(Filme filme) {
		JPanel painel = new JPanel();
		painel.setBorder(new LineBorder(Color.black));
		painel.setLayout(new GridLayout(0,1));
		painel.setAlignmentX(SwingConstants.CENTER);;
		painel.setBackground(Color.lightGray);
		
		JPanel um =criarPainelInfo();
		JPanel dois = criarPainelInfo();
		JPanel tres = criarPainelInfo();
		JPanel quatro = criarPainelInfo();
		JPanel cinco = criarPainelInfo();
		JPanel seis = criarPainelInfo();
		JPanel sete = criarPainelInfo();
		try {
			BufferedImage bufferedImage = ImageIO.read(new URL(filme.getFt()));
			Image image = bufferedImage.getScaledInstance(70, 80, Image.SCALE_DEFAULT);
			JLabel img = new JLabel(new ImageIcon(image));


			img.setSize(1,1);
			um.add(img);
		} catch (MalformedURLException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		JLabel nome = criarLabel("Nome: " +filme.getNome());
		JLabel genero = criarLabel("Genero: "+ filme.getGenero());
		JLabel diretor = criarLabel("Diretor: "+filme.getDiretor());
		JLabel ano = criarLabel("Ano: " +filme.getAno());
		JLabel nota = criarLabel("Nota: "+Integer.toString(filme.getNota())+"/5");
		JButton like = new JButton("Remover");
		like.setBackground(Color.red);
		like.setForeground(Color.white);
		like.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Confirma se quer remover
				int reply = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover:\n " + filme.toString(),"?", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					//remove da arraylist e do grafo
				    filmes.remove(filme);
				    //remove todas as arestas relacionadas a este filme
				    grafo.removeAresta(filme);
				    grafo.removerVertice(filme);
				    limparTela();
				    renderizarPaginaRemover(filmes);
				    getContentPane().add(painelRemover, BorderLayout.CENTER);
				    getContentPane().revalidate();
				    getContentPane().repaint();
				}
			}

			
		});
		dois.add(nome);
		tres.add(genero);
		quatro.add(diretor);
		cinco.add(ano);
		seis.add(nota);
		sete.add(like);
		painel.add(um);
		painel.add(dois);
		painel.add(tres);
		painel.add(quatro);
		painel.add(cinco);
		painel.add(seis);
		painel.add(sete);
		
		return painel;
	}

	private void limparTela() {
		//Limpa a parte central da tela
		try {
			this.remove(painelAdicionar);
		}catch(Exception e) {
			
		}try {
			this.remove(inicial);
		}catch(Exception e) {
			
		}try {
			this.remove(painelRemover);
		}catch(Exception e) {
			
		}try {
			this.remove(painelEditar);
		}catch(Exception e) {
			
		}
		this.revalidate();
		this.repaint();
	}
	
	
	private void paginaInicial() {
		this.limparTela();
		this.renderizarLista(filmes);
		recomendacao.setText("");
		this.add(inicial);
		this.revalidate();
		this.repaint();
		
	}

	private void paginaAdicionar() {
		this.limparTela();
		renderizarPaginaAdicionar();
		this.revalidate();
		this.repaint();
		
	}

	private void renderizarPaginaAdicionar() {
		painelAdicionar = new JPanel();
		JPanel botao = new JPanel();
		JPanel inputs = new JPanel();
		painelAdicionar.setLayout(new GridLayout(0,1));
		inputs.setBackground(Color.LIGHT_GRAY);
		recomendacao.setText("Adicionar novo filme");
		recomendacao.setForeground(Color.BLUE);
		inputs.setLayout(new GridLayout(0,2));
		JLabel nomeLabel = criarLabel("Nome");
		JLabel diretorLabel = criarLabel("Diretor");
		JLabel generoLabel = criarLabel("Gênero");
		JLabel anoLabel = criarLabel("Ano lançamento");
		JLabel notaLabel = criarLabel("Nota: ");
		JLabel urlLabel = criarLabel("URL da imagem");
		
		JTextField nomeInput = criarInput();
		JTextField diretorInput = criarInput();
		JTextField generoInput = criarInput();
		JTextField anoInput = criarInput();
		JTextField notaInput = criarInput();
		JTextField urlInput = criarInput();
		
		inputs.add(criarPainelLables(painelAdicionar,nomeLabel,nomeInput));
		inputs.add(criarPainelLables(painelAdicionar,diretorLabel,diretorInput));
		inputs.add(criarPainelLables(painelAdicionar,generoLabel,generoInput));
		inputs.add(criarPainelLables(painelAdicionar,anoLabel,anoInput));
		inputs.add(criarPainelLables(painelAdicionar,notaLabel,notaInput));
		inputs.add(criarPainelLables(painelAdicionar,urlLabel,urlInput));
		
		JButton adicionar = new JButton("Adicionar");
		
		adicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == adicionar) {
					
					Filme novo = new Filme(nomeInput.getText(), diretorInput.getText(), generoInput.getText(),urlInput.getText(),Integer.parseInt(notaInput.getText()),Integer.parseInt(anoInput.getText()));
					
					int decisao =0;
					do {
						decisao = Integer.parseInt(JOptionPane.showInputDialog(listaFilmesIndex()));
						decisao -= 1;
						if(decisao == -1) {
							break;
						}else {
							
							grafo.adicionarVertice(novo);
							grafo.adicionarAresta(novo, filmes.get(decisao));
							
							
						}
					}while(decisao != -1);
					filmes.add(novo);
				}
				
			}
		});
		
		botao.add(adicionar);
		
		painelAdicionar.add(inputs);
		painelAdicionar.add(botao);
		add(painelAdicionar);
		this.revalidate();
		this.repaint();
	}
	
	private JPanel criarPainelLables(JPanel painel, JLabel label, JTextField Input) {
		JPanel novoPainel = new JPanel();
		novoPainel.setLayout(null);
		label.setBounds(40, 50, 100, 40);
		Input.setBounds(140, 50, 100, 30);

		novoPainel.add(label);
		novoPainel.add(Input);
		return novoPainel;
	}

	private JTextField criarInput() {
		JTextField texto = new JTextField();
		texto.setSize(1, 1);
		return texto;
	}
	
	
	private String listaFilmesIndex() {
		//Fazer uma string para usar na hora das relações
		StringBuilder builder = new StringBuilder();
		
		builder.append("Lista de filmes registrados: \n");
		
		for (int i = 0; i < filmes.size(); i++) {
			builder.append("[" + (i +1) +"] " + filmes.get(i).getNome()  +"\n");
		}
		
		builder.append("[0] Caso esse filme não tenha relação com nenhum outro");
		String texto = builder.toString();
		return texto;
	}
	
	private JPanel criarPainelInfo() {
		JPanel novo = new JPanel();
		novo.setBackground(Color.LIGHT_GRAY);

		return novo;
		
	}
}
