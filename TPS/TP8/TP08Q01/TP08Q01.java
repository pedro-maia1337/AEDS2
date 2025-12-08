import java.util.*;
import java.io.*;
import java.io.File;
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
    public Date getRealeaseDate()           {return releaseDate;}
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
                    encontrado = true; // Marca como encontrado, mas continua lendo até o final da linha atual
                }
            } catch (Exception e) {
                // ignora linhas não formatadas
            }
        }
        csv.close();
    } 
}

//Implementação da tabela HASH
class Hash {
   Game tabela[];
   int m1, m2, m, reserva; //m1 = tam tabela, m2 = tam tabela reserva
   
   //Inicializa tabela com todos os elementos nulos
   public Hash(int m1, int m2) { 
      this.m = m1 + m2;
      this.tabela = new Game[this.m];
      for (int i = 0; i < m1; i++) {
         tabela[i] = null;
      }
      reserva = 0;
   }

   //Calcula pos
   public int h(String name) {
      int pos = 0;
      for(int i = 0; i < name.length(); i = i + 1) {
          pos = pos + (int) name.charAt(i); // Fazendo cast direto
      }
      return pos % 21;
   }

   public boolean inserir(Game elemento) {
      boolean resp = false;
      if (elemento != null) {
         int pos = h(elemento.getName());
         if (tabela[pos] == null) {
            tabela[pos] = elemento.clone();
            resp = true;
         } else if (reserva < m2) {
            tabela[m1 + reserva] = elemento.clone();
            reserva++;
            resp = true;
         }
      }
      return resp;
   }

   public boolean pesquisar(String elemento) {
      boolean resp = false;
      int pos = h(elemento);
      System.out.print(" (Posicao: " + pos + ") ");
      if (tabela[pos].getName().equals(elemento)) {
         resp = true;
      } else if (tabela[pos] != null) {
         for (int i = 0; i < reserva; i++) {
            if (tabela[m1 + i].getName().equals(elemento)) {
               resp = true;
               i = reserva;
            }
         }
      }
      return resp;
   }
}

public class TP08Q01 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int maxIds = 255;
        int[] idsDesejados = new int[maxIds];
        int totalIds = 0;
        boolean flag = false;
        
        double inicio = 0.0;
        double fim = 0.0;

        // Leitura dos IDs até encontrar FIM
        while (sc.hasNextLine() && totalIds < maxIds && !flag) {
            String linha = sc.nextLine();
            if (linha.equals("FIM")) 
                flag = true;
            else if (!linha.isEmpty()) 
                idsDesejados[totalIds++] = Integer.parseInt(linha);   
        }

        Hash tabela = new Hash(21, 9);

        //resetando a flag
        flag = false;

        //Insere os games tabela
        for(int i = 0; i < totalIds; i = i + 1){
            Game game = new Game();
            game.inicializarGame(idsDesejados[i]);
            if (game.getAppID() != 0) { 
                tabela.inserir(game);
            }
        }

        inicio = now();
        //Realiza a leitura das entradas, pesquisa e printa 
        while (sc.hasNextLine() && !flag) {
            String linha = sc.nextLine();
            if (linha.equals("FIM")) 
                flag = true;
            else if (!linha.isEmpty()) {
                System.out.print(linha + ": ");
                if(tabela.pesquisar(linha)) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
            }       
        }
        fim = now();
        
        escreverLog("874398_hash.txt", (fim-inicio)/1000.0);

        sc.close();
    }

    //Calcular tempo
    public static long now(){
		return new Date().getTime();
    }

    public static void escreverLog(String nomeArquivo, double tempo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("874398\t" + "\t" + tempo + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever log: " + e.getMessage());
        }
    }
}
