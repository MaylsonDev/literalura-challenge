package br.com.alura.LiterAlura.principal;

import br.com.alura.LiterAlura.model.DadosLivro;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.repository.AutorRepository;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConsumoApi;
import br.com.alura.LiterAlura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private List<Livro> livrosRegistrados = new ArrayList<>();
    private List<Autor> autoresRegistrados = new ArrayList<>();

    private LivroRepository repositorio;
    private AutorRepository repository;

    public Principal(LivroRepository repositorio,AutorRepository repository) {
        this.repositorio = repositorio;
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                Escolha o número da sua opção
                1 - Buscar livro pelo Título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em determinado idioma
                0 - Sair                                 
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroTitulo() {
        System.out.println("Qual livro deseja pesquisar?");
        var nomeLivro = leitura.nextLine();

        try {
            String urlPesquisa = ENDERECO + nomeLivro.replace(" ", "%20");
            var json = consumo.obterDados(urlPesquisa);
            List<DadosLivro> livrosDados = conversor.obterListaDeLivros(json);

            if (livrosDados.isEmpty()) {
                System.out.println("Nenhum livro encontrado com o título: " + nomeLivro);
            } else {
                DadosLivro dadosLivro = livrosDados.get(0);

                System.out.println("\nTítulo: " + dadosLivro.title());
                System.out.println("Autores:");
                dadosLivro.authors().forEach(autor -> System.out.println(" - " + autor.get("name")));
                System.out.println("Resumos:");
                dadosLivro.summaries().forEach(System.out::println);
                System.out.println("Assuntos:");
                dadosLivro.subjects().forEach(assunto -> System.out.println(" - " + assunto));
                System.out.println("Idiomas:");
                dadosLivro.languages().forEach(idioma -> System.out.println(" - " + idioma));
                System.out.println("Downloads: " + dadosLivro.download_count());

                Livro livro = new Livro();
                livro.setTitle(dadosLivro.title());
                livro.setDownload_count(dadosLivro.download_count());
                livro.setSummaries(dadosLivro.summaries());
                livro.setSubjects(dadosLivro.subjects());
                livro.setLanguages(dadosLivro.languages());

                for (var autores : dadosLivro.authors()) {
                    Autor autor = new Autor();
                    autor.setName((String) autores.get("name"));
                    autor.setBirth_year((Integer)autores.get("birth_year"));
                    autor.setDeath_year((Integer)autores.get("death_year"));
                    livro.setAutor(autor);
                }

                repositorio.save(livro);

            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());

        }

    }

    private void listarLivrosRegistrados() {
        livrosRegistrados = repositorio.findAll();
        livrosRegistrados.stream()
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autoresRegistrados = repository.findAll();
        autoresRegistrados.stream()
                .forEach(autor -> System.out.println(autor.getName()));
    }

    private void listarAutoresVivos() {
        System.out.println("Qual Ano deseja pesquisar? ");
        var anoAutor = leitura.nextInt();
        List<Autor> autores = repository.findAutoresVivosNoAno(anoAutor);
        autores.forEach(autor -> System.out.println(autor.getName()));

    }

    private void listarLivrosIdioma() {
        System.out.print("Informe o idioma para listar os livros: ");
        String idioma = leitura.nextLine().trim();

        buscarLivrosPorIdioma(idioma);
    }


    public void buscarLivrosPorIdioma(String idioma) {

        List<Livro> livrosPorIdioma = repositorio.findByIdioma(idioma);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma: " + idioma);
        } else {

            for (Livro livro : livrosPorIdioma) {
                System.out.println("Título: " + livro.getTitle());
                System.out.println("Autores: " + livro.getAutor().getName());
                System.out.println("Idiomas: " + livro.getLanguages());
                System.out.println("Resumos: " + livro.getSummaries());
                System.out.println("Assuntos: " + livro.getSubjects());
                System.out.println("Downloads: " + livro.getDownload_count());
                System.out.println("-------------------------------");
            }
        }
    }
}
