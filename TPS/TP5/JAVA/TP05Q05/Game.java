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


    //Função de leitura e preenchimento dos jogos
    //Retorna o número de jogos lidos
    public static int inicializarGames(Game[] tabela, int[] idsDesejados, int totalIds) throws Exception {
        Scanner csv = null;

        try {
            csv = new Scanner(new File("/tmp/games.csv"));
        } catch (Exception e) {
            csv = new Scanner(new File("../../games.csv"));
        }

        SimpleDateFormat data = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

        int count = 0;
        Game[] encontrados = new Game[totalIds];

        //Lendo CSV
        while (csv.hasNextLine()) {
            String linha = csv.nextLine();
            String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            try {
                int appID = Integer.parseInt(partes[0]);

                // Valida se o appID está em ids
                int pos = -1;
                for (int i = 0; i < totalIds; i = i + 1) {
                    if (idsDesejados[i] == appID) {
                        pos = i;
                    }
                }

                if (pos >= 0) { //caso encontra faz o parse
                    String name = partes[1].replace("\"", "");
                    Date releaseDate = data.parse(partes[2].replace("\"", ""));
                    int estimatedOwners = Integer.parseInt(partes[3]);
                    double price = Double.parseDouble(partes[4]);
                    String[] supportedLanguages = partes[5].replaceAll("[\\[\\]'\"]", "").split("\\s*,\\s*");
                    int metacritic = Integer.parseInt(partes[6]);
                    int userScore = Integer.parseInt(partes[7]);
                    int achievements = Integer.parseInt(partes[8]);
                    String devolopers = partes[9].replace("\"", "");
                    String publishers = partes[10].replace("\"", "");
                    String[] categories = partes[11].replace("\"", "").split("\\s*,\\s*");
                    String[] genres = partes[12].replace("\"", "").split("\\s*,\\s*");
                    String[] tags = partes[13].replace("\"", "").split("\\s*,\\s*");

                    Game jogos = new Game(appID, name, releaseDate, estimatedOwners, price,
                            supportedLanguages, metacritic, userScore, achievements,
                            devolopers, publishers, categories, genres, tags);

                    encontrados[pos] = jogos;
                }
            } catch (Exception e) {
                // ignora linhas não formatadas
            }
        }

        // copia apenas os jogos encontrados para a tabela final
        for (int i = 0; i < totalIds; i = i + 1) {
            if (encontrados[i] != null) {
                tabela[count++] = encontrados[i];
            }
        }

        csv.close();
        return count;
    }

    //Função de swap para ordernar os games 
    public static void swap(Game[] tabela, int i, int j) {
        Game temp = tabela[i];
        tabela[i] = tabela[j];
        tabela[j] = temp;
    }

    //Método de desempate utilizando appID como chave secundária 
    //retorna menor appID
    //Método de comparação com desempate por appID
    public static boolean desempate(Game a, Game b) {
    //Adequando metódo por conta da sentinela do algoritmo do merge sort
        if (a == null) return false;
        if (b == null) return true;
        if (a.price != b.price) return a.price > b.price; 
        return a.appID > b.appID; 
    }

    public static void sort(Game[] tabela) {
        int n = tabela.length;
        mergesort(tabela, 0, n-1);
    }

   /**
    * Algoritmo de ordenacao Mergesort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
    */
    private static void mergesort(Game[] tabela, int esq, int dir) {
        if (esq < dir){
            int meio = (esq + dir) / 2;
            mergesort(tabela, esq, meio);
            mergesort(tabela, meio + 1, dir);
            intercalar(tabela, esq, meio, dir);
        }
    }

   /**
    * Algoritmo que intercala os elementos entre as posicoes esq e dir
    * @param int esq inicio do array a ser ordenado
    * @param int meio posicao do meio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
    */ 
   public static void intercalar(Game[] tabela, int esq, int meio, int dir){
        int n1, n2, i, j, k;

        //Definir tamanho dos dois subarrays
        n1 = meio-esq+1;
        n2 = dir - meio;

        Game[] a1 = new Game[n1+1];
        Game[] a2 = new Game[n2+1];

      //Inicializar primeiro subarray
        for(i = 0; i < n1; i = i + 1){
            a1[i] = tabela[esq+i];
        }

      //Inicializar segundo subarray
        for(j = 0; j < n2; j = j + 1){
            a2[j] = tabela[meio+j+1];
        }

      //Sentinela no final dos dois arrays
      //Definindo como null para não criar mais objetos
        a1[n1] = null;
        a2[n2] = null;

      //Intercalacao propriamente dita
        for(i = j = 0, k = esq; k <= dir; k = k + 1){
            if(a2[j] == null || (a1[i] != null && desempate(a1[i], a2[j]))) {
                tabela[k] = a1[i++];
            } else {
                tabela[k] = a2[j++];
            }
        } 
    }


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int maxIds = 255; // tamanho máximo de IDs (mudar isso depois para crescer de forma dinamica)
        int[] idsDesejados = new int[maxIds];
        int totalIds = 0;

        boolean flag = false;


        // Leitura dos IDs até encotrar FIM
        while (sc.hasNextLine() && totalIds < maxIds) {
            String linha = sc.nextLine();
            if (linha.equals("FIM")) flag = true;
            if (!linha.isEmpty() && !flag) {
                idsDesejados[totalIds++] = Integer.parseInt(linha);
            }
        }

        // Inicializa tabela de jogos
        Game[] tabela = new Game[totalIds];
        int count = inicializarGames(tabela, idsDesejados, totalIds);

        //Ordena
        sort(tabela);

        //Corrigindo problema de encode do verde :)
        String texto1 = "| 5 preços mais caros |";
        texto1.replace("Ã§", "ç");

        String texto2 = "| 5 preços mais baratos |";
        texto1.replace("Ã§", "ç");

        //Exibindo os jogos mais caros e mais baratos
        System.out.println(texto1);
        for(int i = 0; i < 5; i = i + 1) {
            tabela[i].imprimirGames();
        }

        System.out.println();

        System.out.println(texto2);
        for(int i = count - 1; i >= count - 5; i = i - 1) {
            tabela[i].imprimirGames();
        }

        escreverLog("teste", totalIds, count);

        sc.close();
    }

    public static void escreverLog(String nomeArquivo, int comparacoes, int tempo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("8743985\t" + comparacoes + "\t" + tempo + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever log: " + e.getMessage());
        }
    }

}

