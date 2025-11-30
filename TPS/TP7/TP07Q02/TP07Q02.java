import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Game {
    // Atributos
    private int appID;
    private String name;
    private Date releaseDate;
    private int estimatedOwners;
    private double price;
    private String[] supportedLanguages;
    private int metacritic;
    private int userScore;
    private int achievements;
    private String publishers;
    private String devolopers;
    private String[] categories; 
    private String[] genres;
    private String[] tags;

    // Construtor vazio
    public Game() {
        appID = 0;
        name = "";
        releaseDate = new Date();
        estimatedOwners = 0;
        price = 0.0;
        supportedLanguages = new String[0];
        metacritic = 0;
        userScore = 0;
        achievements = 0;
        publishers = "";
        devolopers = "";
        categories = new String[0];
        genres = new String[0];
        tags = new String[0];
    }

    // Construtor atribuindo valores
    public Game(int appID, String name, Date releaseDate, int estimatedOwners, double price, 
        String[] supportedLanguages, int metacritic, int userScore, int achievements, String publishers,
        String devolopers, String[] categories, String[] genres, String[] tags) {
        this.appID = appID; 
        this.name = name;
        this.releaseDate = releaseDate;
        this.estimatedOwners = estimatedOwners;
        this.price = price;
        this.supportedLanguages = supportedLanguages;
        this.metacritic = metacritic;
        this.userScore = userScore;
        this.achievements = achievements;
        this.publishers = publishers;
        this.devolopers = devolopers;
        this.categories = categories;
        this.genres = genres;
        this.tags = tags;
    }

    //Getters
    public int getAppID()                   {return appID;}
    public String getName()                 {return name;}
    public Date getReleaseDate()            {return releaseDate;}
    public int getEstimatedOwners()         {return estimatedOwners;}
    public double getPrice()                {return price;}
    public String[] getSupportedLanguages() {return supportedLanguages;}
    public int getMetacritic()              {return metacritic;}
    public int getUserScore()               {return userScore;}
    public int getAchievements()            {return achievements;}
    public String getPublishers()           {return publishers;}
    public String getDevolopers()           {return devolopers;}
    public String[] getCategories()         {return categories;}
    public String[] getGenres()             {return genres;}
    public String[] getTags()               {return tags;}

    //Setters 
    public void setAppID(int appID)                                 {this.appID = appID;}
    public void setName(String name)                                {this.name = name;}
    public void setReleaseDate(Date releaseDate)                    {this.releaseDate = releaseDate;}
    public void setEstimatedOwners(int estimatedOwners)             {this.estimatedOwners = estimatedOwners;}
    public void setPrice(double price)                              {this.price = price;}
    public void setSupportedLanguages(String[] supportedLanguages)  {this.supportedLanguages = supportedLanguages;}
    public void setMetacritic(int metacritic)                       {this.metacritic = metacritic;}
    public void setUserScore(int userScore)                         {this.userScore = userScore;}
    public void setAchievements(int achievements)                   {this.achievements = achievements;}
    public void setPublishers(String publishers)                    {this.publishers = publishers;}
    public void setDevolopers(String devolopers)                    {this.devolopers = devolopers;}
    public void setCategories(String[] categories)                  {this.categories = categories;}
    public void setGenres(String[] genres)                          {this.genres = genres;}
    public void setTags(String[] tags)                              {this.tags = tags;}

    // Método clone pra copiar um jogo
    public Game clone() {
        Game clone = new Game();
        clone.appID = this.appID;
        clone.name = this.name;
        clone.releaseDate = this.releaseDate; 
        clone.estimatedOwners = this.estimatedOwners;
        clone.price = this.price;
        clone.supportedLanguages = this.supportedLanguages.clone();
        clone.metacritic = this.metacritic;
        clone.userScore = this.userScore;
        clone.achievements = this.achievements;
        clone.publishers = this.publishers;
        clone.devolopers = this.devolopers;
        clone.categories = this.categories.clone();
        clone.genres = this.genres.clone();
        clone.tags = this.tags.clone();
        return clone;
    }

    // Função para imprimir os jogos
    public void imprimirGames() {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = (releaseDate != null) ? data.format(releaseDate) : "N/A";

        //Formantando preço para caso seja free to play considerar somente uma casa e ignorar o ultimo zero 
        String precoFormatado;
        if (price == 0.0) {
            precoFormatado = "0.0"; 
        } else {
            double decimalParte = price * 10 % 10; 
            if (price * 100 % 100 == 0 || price * 100 % 100 == 50) {
                precoFormatado = String.format("%.1f", price);
            } else {
                precoFormatado = String.format("%.2f", price);
            }
        }

        System.out.print("=> " + appID
            + " ## " + name
            + " ## " + dataFormatada
            + " ## " + estimatedOwners
            + " ## " + precoFormatado
            + " ## [");

        // Imprimindo lista de linguagens
        for (int i = 0; i < supportedLanguages.length; i++) {
            System.out.print(supportedLanguages[i]);
            if (i < supportedLanguages.length - 1) System.out.print(", ");
        }

        System.out.print("] ## " + metacritic
            + " ## " + String.format("%.1f", (double) userScore)
            + " ## " + achievements
            + " ## [");

        String publishersFormatado = publishers.replaceAll("\\s*,\\s*", ", ");
        System.out.print(publishersFormatado);

        System.out.print("] ## [");

        String devsFormatado = devolopers.replaceAll("\\s*,\\s*", ", ");
        System.out.print(devsFormatado);

        System.out.print("] ## [");

        // Imrpimindo categorias
        for (int i = 0; i < categories.length; i = i + 1) {
            System.out.print(categories[i]);
            if (i < categories.length - 1) System.out.print(", ");
        }

        System.out.print("] ## [");

        // Imprimindo gêneros
        for (int i = 0; i < genres.length; i = i + 1) {
            System.out.print(genres[i]);
            if (i < genres.length - 1) System.out.print(", ");
        }

        System.out.print("] ## [");

        //Imprimindo tags
        for (int i = 0; i < tags.length; i = i + 1) {
            System.out.print(tags[i]);
            if (i < tags.length - 1) System.out.print(", ");
        }

        System.out.println("] ##");
    }

    public void inicializarGame(int appIDDesejado) throws Exception {
        Scanner csv = null;

        try {
            csv = new Scanner(new File("/tmp/games.csv"));
        } catch (Exception e) {
            csv = new Scanner(new File("../games.csv"));
        }

        SimpleDateFormat data = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

        boolean encontrado = false;

        // Lendo CSV
        while (csv.hasNextLine() && !encontrado) {
            String linha = csv.nextLine();
            String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            try {
                int appID = Integer.parseInt(partes[0]);

                // Se encontrou o ID desejado, preenche este objeto
                if (appID == appIDDesejado) {
                    this.appID = appID;
                    this.name = partes[1].replace("\"", "");
                    this.releaseDate = data.parse(partes[2].replace("\"", ""));
                    this.estimatedOwners = Integer.parseInt(partes[3]);
                    this.price = Double.parseDouble(partes[4]);
                    this.supportedLanguages = partes[5].replaceAll("[\\[\\]'\"]", "").split("\\s*,\\s*");
                    this.metacritic = Integer.parseInt(partes[6]);
                    this.userScore = Integer.parseInt(partes[7]);
                    this.achievements = Integer.parseInt(partes[8]);
                    this.devolopers = partes[9].replace("\"", "");
                    this.publishers = partes[10].replace("\"", "");
                    this.categories = partes[11].replace("\"", "").split("\\s*,\\s*");
                    this.genres = partes[12].replace("\"", "").split("\\s*,\\s*");
                    this.tags = partes[13].replace("\"", "").split("\\s*,\\s*");
                    encontrado = true;
                }
            } catch (Exception e) {
                // ignora linhas não formatadas
            }
        }
        csv.close();
    } 
}

// Nó da segunda árvore (organizada por nome)
class No2 {
    public No2 esq, dir;
    public Game game;

    public No2(Game game) {
        this.game = game;
        this.esq = null;
        this.dir = null;
    }
}

// Nó da primeira árvore (organizada por mod 15)
class No1 {
    public No1 esq, dir;
    public int elemento;  // valor do mod 15
    public No2 outro;     // raiz da segunda árvore

    public No1(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
        this.outro = null;
    }
}

// Árvore de Árvores
class ArvoreArvore {
    private No1 raiz;
    private boolean encontrado;

    public ArvoreArvore() {
        raiz = null;
        encontrado = false;
        construirPrimeiraArvore();
    }

    // Constrói a primeira árvore 
    private void construirPrimeiraArvore() {
        int[] ordem = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int valor : ordem) {
            inserirPrimeiraArvore(valor);
        }
    }
    private void inserirPrimeiraArvore(int valor) {
        raiz = inserirPrimeiraArvore(raiz, valor);
    }

    private No1 inserirPrimeiraArvore(No1 i, int valor) {
        if (i == null) {
            i = new No1(valor);
        } else if (valor < i.elemento) {
            i.esq = inserirPrimeiraArvore(i.esq, valor);
        } else if (valor > i.elemento) {
            i.dir = inserirPrimeiraArvore(i.dir, valor);
        }
        return i;
    }

    public void inserir(Game game) {
        int mod = game.getEstimatedOwners() % 15;
        raiz = inserirSegundaArvore(raiz, game, mod);
    }

    // Percorre até encontrar o nó com o mod correto
    private No1 inserirSegundaArvore(No1 i, Game game, int mod) {
        if (i != null) {
            if (mod == i.elemento) {
                i.outro = inserirPorNome(i.outro, game);
            } else if (mod < i.elemento) {
                i.esq = inserirSegundaArvore(i.esq, game, mod);
            } else {
                i.dir = inserirSegundaArvore(i.dir, game, mod);
            }
        }
        return i;
    }

    // Insere na segunda árvore
    private No2 inserirPorNome(No2 i, Game game) {
        if (i == null) {
            i = new No2(game);
        } else if (game.getName().compareTo(i.game.getName()) < 0) {
            i.esq = inserirPorNome(i.esq, game);
        } else if (game.getName().compareTo(i.game.getName()) > 0) {
            i.dir = inserirPorNome(i.dir, game);
        }
        return i;
    }

    // Pesquisa pelo nome 
    public int pesquisar(String nome) {
        encontrado = false;
        System.out.print("raiz ");
        int comp = pesquisarPrimeiraArvore(nome, raiz);
        
        if (!encontrado) {
            System.out.print(" NAO ");
        }
        System.out.println();
        
        return comp;
    }

    // Percorre pré ordem
    private int pesquisarPrimeiraArvore(String nome, No1 i) {
        int comp = 0;
        
        if (i != null) {
            comp += pesquisarSegundaArvore(nome, i.outro);
            
            if (encontrado) {
                return comp;
            }
            
            System.out.print(" ESQ ");
            comp += pesquisarPrimeiraArvore(nome, i.esq);
            
            if (encontrado) {
                return comp;
            }
            
            System.out.print(" DIR ");
            comp += pesquisarPrimeiraArvore(nome, i.dir);
        }
        
        return comp;
    }

    // Pesquisa na segunda árvore (binária por nome)
    private int pesquisarSegundaArvore(String nome, No2 i) {
        int comp = 0;
        
        if (i == null) {
            comp++;
            return comp;
        } else if (nome.equals(i.game.getName())) {
            comp++;
            System.out.print(" SIM ");
            encontrado = true;
            return comp;
        } else if (nome.compareTo(i.game.getName()) < 0) {
            comp++;
            System.out.print("esq ");
            comp += pesquisarSegundaArvore(nome, i.esq);
        } else {
            comp++;
            System.out.print("dir ");
            comp += pesquisarSegundaArvore(nome, i.dir);
        }
        
        return comp;
    }
}

public class TP07Q02 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int maxIds = 255;
        int[] idsDesejados = new int[maxIds];
        int totalIds = 0;
        boolean flag = false;

        int comp = 0;
        double inicio = 0;
        double fim = 0;

        // Leitura dos IDs até encontrar FIM
        while (sc.hasNextLine() && totalIds < maxIds && !flag) {
            String linha = sc.nextLine();
            if (linha.equals("FIM")) 
                flag = true;
            else if (!linha.isEmpty()) 
                idsDesejados[totalIds++] = Integer.parseInt(linha);   
        }

        ArvoreArvore arvore = new ArvoreArvore();

        // Insere os games na árvore
        for (int i = 0; i < totalIds; i++) {
            Game game = new Game();
            game.inicializarGame(idsDesejados[i]);
            if (game.getAppID() != 0) { 
                arvore.inserir(game);
            }
        }

        // Resetando a flag
        flag = false;

        inicio = now();
        
        // Realiza a leitura das entradas, pesquisa e printa 
        while (sc.hasNextLine() && !flag) {
            String linha = sc.nextLine();
            if (linha.equals("FIM")) 
                flag = true;
            else if (!linha.isEmpty()) {
                System.out.print("=> " + linha + " => ");
                comp += arvore.pesquisar(linha);
            }       
        }
        
        fim = now();

        escreverLog("874398_arvoreArvore.txt", comp, (fim - inicio) / 1000.0);

        sc.close();
    }

    // Calcular tempo
    public static long now() {
        return new Date().getTime();
    }

    public static void escreverLog(String nomeArquivo, int comparacoes, double tempo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("874398\t" + comparacoes + "\t" + tempo + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever log: " + e.getMessage());
        }
    }
}