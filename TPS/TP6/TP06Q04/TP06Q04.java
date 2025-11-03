import java.util.*;
import java.io.*;
import java.io.File;
import java.text.SimpleDateFormat;

//Modifiquei para separar o arquivo em mais classes para melhor organização 

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
    public Date getRealeaseDate()           {return releaseDate;}
    public int getEstimatedOwners()         {return estimatedOwners;}
    public double getPrice()                {return price;}
    public String[] getSupportedLanguages() {return supportedLanguages;}
    public int getMetacritic()              {return metacritic;}
    public int getUserScore()               {return userScore;}
    public int getAchievements()            {return achievements;}
    public String getDevolopers()           {return devolopers;}
    public String[] getCategories()         {return categories;}
    public String[] getGenres()             {return genres;}
    public String[] getTags()               {return tags;}

    //Setters 
    public void setAppID(int appID)                                 {this.appID = appID;}
    public void setName(String name)                                {this.name = name;}
    public void setRealeaseDate(Date releaseDate)                   {this.releaseDate = releaseDate;}
    public void setEstimatedOwners(int estimatedOwners)             {this.estimatedOwners = estimatedOwners;}
    public void setPrice(double price)                              {this.price = price;}
    public void setSupportedLanguages(String[] supportedLanguages)  {this.supportedLanguages = supportedLanguages;}
    public void setMetacritic(int metacritic)                       {this.metacritic = metacritic;}
    public void setUserScore(int userScore)                         {this.userScore = userScore;}
    public void setAchievements(int achievements)                   {this.achievements = achievements;}
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
        for (int i = 0; i < supportedLanguages.length; i = i + 1) {
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
        for (int i = 0; i < categories.length; i= i + 1) {
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
                    encontrado = true; // Marca como encontrado, mas continua lendo até o final da linha atual
                }
            } catch (Exception e) {
                // ignora linhas não formatadas
            }
        }
        csv.close();
    } 
}

//Adaptando o código anterior para fila
class FilaGames {
    private Game[] array;
    private int n; //capacidade

    // Construtor
    public FilaGames(int tamanho) {
        array = new Game[tamanho];
        n = 0;
    }

    //Inserir no fim
    public void inserirFim(Game game) {
        if (n >= array.length) {
            System.out.println("Erro ao inserir. Lista cheia");
            return;
        }
        
        array[n] = game.clone();
        n = n + 1;
    }

    // Remover do início
    public Game removerInicio() {
        Game removido = array[0];
        if (n == 0) {
            System.out.println("Erro ao remover. Lista vazia");
            return null;
        }
        
        for (int i = 0; i < n - 1; i = i + 1) {
            array[i] = array[i + 1];
        }
        
        array[n - 1] = null;
        n = n - 1;
        
        return removido;
    }


    public void mostrar() {
        for (int i = 0; i < n; i = i + 1) {
            if (array[i] != null) {
                System.out.print("[" + i + "] ");
                array[i].imprimirGames();
            }
        }
    }
}


public class TP06Q04 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int maxIds = 255;
        int[] idsDesejados = new int[maxIds];
        int totalIds = 0;

        boolean flag = false;

        // Leitura dos IDs até encontrar FIM
        while (sc.hasNextLine() && totalIds < maxIds && !flag) {
            String linha = sc.nextLine();
            if (linha.equals("FIM")) 
                flag = true;
            else if (!linha.isEmpty()) 
                idsDesejados[totalIds++] = Integer.parseInt(linha);   
        }

        FilaGames lista = new FilaGames(totalIds + 50);

        for(int i = 0; i < totalIds; i = i + 1){
            Game game = new Game();
            game.inicializarGame(idsDesejados[i]);
            if (game.getAppID() != 0) { 
                lista.inserirFim(game);
            }
        }

        // Processa operações adicionais
        int numReg = Integer.parseInt(sc.nextLine());
        
        for(int i = 0; i < numReg; i = i + 1) {
            String str = sc.nextLine();
            String[] partes = str.split(" ");
            
            //Faz as operações
            switch(partes[0]) {
                case "I": 
                    int idII = Integer.parseInt(partes[1]);
                    Game gameII = new Game();
                    gameII.inicializarGame(idII);
                    lista.inserirFim(gameII);
                    break;
                    
                case "R": 
                    Game removidoRI = lista.removerInicio();
                    if (removidoRI != null) {
                        System.out.println("(R) " + removidoRI.getName());
                    }
                    break;
                    
                default:
                    System.out.println("Operação inválida: " + partes[0]);
            }
        }

        lista.mostrar();

        sc.close();
    }
}
