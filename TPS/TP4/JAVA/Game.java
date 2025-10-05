import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;

class Game {
    //Atributos 
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

    //Construtor (Sem parâmetros)
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

    //Construtor atribuindo valores
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

    //Função para imprimir os jogos
    public void imprimirGames(Game tabela) {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy"); //Iniciando data com formato dd/MM/yyyy
        String dataFormatada = (tabela.releaseDate != null) ? data.format(tabela.releaseDate) : "N/A";

        System.out.print("=> " + tabela.appID
            + " ## " + tabela.name
            + " ## " + dataFormatada
            + " ## " + tabela.estimatedOwners
            + " ## " + String.format("%.2f", tabela.price)
            + " ## [");

        //Imprimindo lista de linguagens
        for (int i = 0; i < tabela.supportedLanguages.length; i++) {
            if (i == tabela.supportedLanguages.length - 1)
                System.out.print(tabela.supportedLanguages[i]);
            else
                System.out.print(tabela.supportedLanguages[i] + ", ");
        }

        System.out.print("] ## " + tabela.metacritic
            + " ## " + String.format("%.1f", (double) tabela.userScore)
            + " ## " + tabela.achievements
            + " ## [" + tabela.publishers
            + "] ## [" + tabela.devolopers + "] ## [");

        //Imprimindo lista de categorias 
        for (int i = 0; i < tabela.categories.length; i++) {
            if (i == tabela.categories.length - 1)
                System.out.print(tabela.categories[i]);
            else
                System.out.print(tabela.categories[i] + ", ");
        }

        System.out.print("] ## [");

        //Imprimindo lista de gêneros
        for (int i = 0; i < tabela.genres.length; i++) {
            if (i == tabela.genres.length - 1)
                System.out.print(tabela.genres[i]);
            else
                System.out.print(tabela.genres[i] + ", ");
        }

        System.out.print("] ## [");

        //Imprimindo lista de tags
        for (int i = 0; i < tabela.tags.length; i++) {
            if (i == tabela.tags.length - 1)
                System.out.print(tabela.tags[i]);
            else
                System.out.print(tabela.tags[i] + ", ");
        }

        System.out.print("] ##");
        System.out.println();
    }

    //Função de leitura e preenchimento dos jogos
    //Retorna o número de jogos lidos
    public static int inicializarJogos(Game[] tabela) throws Exception {
        Scanner sc = new Scanner(System.in); // leitura da entrada padrão
        Scanner csv = new Scanner(new File("games.csv")); 
        SimpleDateFormat data = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

        int[] ids = new int[255]; //! - adaptar para lista depois
        int totalIds = 0;
        int count = 0;

        // Lê IDs até encontrar "FIM"
        boolean lendoIds = true;
        while (sc.hasNextLine() && lendoIds) {
            String linha = sc.nextLine().trim();
            if (linha.equals("FIM")) {
                lendoIds = false;
            } else if (!linha.isEmpty()) {
                ids[totalIds++] = Integer.parseInt(linha);
            }
        }

        // Array de jogos no qual os IDS foram lidos
        Game[] encontrados = new Game[totalIds];

        // Lendo CSV
        while (csv.hasNextLine()) {
            String linha = csv.nextLine();
            String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            try {
                int appID = Integer.parseInt(partes[0]);

                // Verifica se o appID está em ids
                int pos = -1;
                for (int i = 0; i < totalIds; i++) {
                    if (ids[i] == appID) {
                        pos = i;
                    }
                }

                // Caso encontre ID faz o parse 
                if (pos >= 0) {
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

                    encontrados[pos] = jogos; // guarda o jogo na posição do ID lido
                }
            } catch (Exception e) {
                // ignora linhas mal formatadas
            }
        }

        // copia apenas os jogos encontrados para a tabela final
        for (int i = 0; i < totalIds; i++) {
            if (encontrados[i] != null) {
                tabela[count++] = encontrados[i];
            }
        }

        csv.close();
        sc.close();
        return count;
    }



    public static void main(String[] args) throws Exception {
        Game[] tabela = new Game[1853]; //Inicializando array de games(max : 1853), adaptar para lista depois
        int count = inicializarJogos(tabela);

        //Percorrendo array de jogos e imprimindo;
        for (int i = 0; i < count; i++) {
            tabela[i].imprimirGames(tabela[i]);
        }
    }
}
