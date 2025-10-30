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
        for (int i = 0; i < categories.length; i++) {
            System.out.print(categories[i]);
            if (i < categories.length - 1) System.out.print(", ");
        }

        System.out.print("] ## [");

        // Imprimindo gêneros
        for (int i = 0; i < genres.length; i++) {
            System.out.print(genres[i]);
            if (i < genres.length - 1) System.out.print(", ");
        }

        System.out.print("] ## [");

        //Imprimindo tags
        for (int i = 0; i < tags.length; i++) {
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
                for (int i = 0; i < totalIds; i++) {
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
        for (int i = 0; i < totalIds; i++) {
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
    public static int desempate(Game a, Game b) {
    // Primeiro compara por estimatedOwners 
        if (a.estimatedOwners > b.estimatedOwners) {
            return 1;
        } else if (a.estimatedOwners < b.estimatedOwners) {
            return -1;
        } else {
        // Se estimatedOwners for igual, desempata por appID 
            if (a.appID > b.appID) {
                return 1;
            } else if (a.appID < b.appID) {
                return -1;
            } else {
                return 0;
            }
        }
}

    //Ordenação dos elementos utilizando heapsort e utilizando estimatedOwners como chave primária
    //utilizando o código heapsort visto nas aulas 
    public static void sort(Game[] tabela, int [] cmp) {
        cmp[0] = 0;
        int n = tabela.length;
        //Alterar o vetor ignorando a posicao zero
        Game[] tmp = new Game[n+1];
        for(int i = 0; i < n; i++){
            tmp[i+1] = tabela[i];
        }
        
        // Contrucao do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++){
            construir(tmp, tamHeap, cmp);
        }

        // Ordenacao propriamente dita
        int tamHeap = n;
        while(tamHeap > 1){
            swap(tmp, 1, tamHeap);
            tamHeap--;
            reconstruir(tmp, tamHeap, cmp);
        }

        // Alterar o vetor para voltar a posicao zero
        for(int i = 0; i < n; i++){
            tabela[i] = tmp[i+1];
        }
    }

    public static void construir(Game[] tabela, int tamHeap, int [] cmp){
        for(int i = tamHeap; i > 1 && desempate(tabela[i], tabela[i/2]) > 0; i /=2) {
            cmp[0]++; // comparação for
            swap(tabela, i, i/2);
        }
    }

    public static void reconstruir(Game[] tabela, int tamHeap, int [] cmp){
        int i = 1;
        while(i <= (tamHeap/2)) {
            cmp[0]++; // comparação while
            int filho = getMaiorFilho(tabela, i, tamHeap, cmp);
            cmp[0]++; // comparação if
            if(desempate(tabela[i], tabela[filho]) < 0) {
                swap(tabela, i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

   
    public static int getMaiorFilho(Game[] tabela, int i, int tamHeap, int [] cmp){
        int filho;
        cmp[0]++;
        if (2*i + 1 > tamHeap) {
            filho = 2*i;
        } else {
            if (desempate(tabela[2 * i], tabela[2 * i + 1]) > 0) {
                filho = 2*i;
            } else {
                filho = 2*i + 1;
            }
        }

        return filho;
    }

    public static long now(){
		return new Date().getTime();
	}

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int maxIds = 255; // tamanho máximo de IDs (mudar isso depois para crescer de forma dinamica)
        int[] idsDesejados = new int[maxIds];
        int totalIds = 0;

        double inicio = 0;
        double fim = 0;
        int[] cmp = new int[1]; //fazendo por ref por com int iria ter que mudar os metodos do heap 

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

        // Removendo elementos nulos - estava constando erro no verde
        Game[] tabelaSemNulos = new Game[count];
        for (int i = 0; i < count; i = i + 1) {
            if (tabela[i] != null) {
                tabelaSemNulos[i] = tabela[i];
            }
        }

        //Ordena
        inicio = now();
        sort(tabelaSemNulos, cmp);
        fim = now();

        for(int i = 0; i < count; i = i + 1) {
            tabelaSemNulos[i].imprimirGames();
        }

        escreverLog("log", cmp[0], (fim-inicio)/1000.0);

        sc.close();
    }

    public static void escreverLog(String nomeArquivo, int comparacoes, double tempo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("874398\t" + tempo + "\t" + comparacoes + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no log: " + e.getMessage());
        }
    }

}

