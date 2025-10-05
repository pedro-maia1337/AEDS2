import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;

class Game {
    private int appID;
    private String name;
    private Date releaseDate;
    private int estimatedOwners;
    private double price;
    private String[] supportedLanguages;
    private int metacritic;
    private int userScore;
    private int achievements;
    private String devolopers;
    private String[] categories; 
    private String[] genres;
    private String[] tags;

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
        devolopers = "";
        categories = new String[0];
        genres = new String[0];
        tags = new String[0];
    }

    public Game(int appID, String name, Date releaseDate, int estimatedOwners, double price, 
        String[] supportedLanguages, int metacritic, int userScore, int achievements, 
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
        this.devolopers = devolopers;
        this.categories = categories;
        this.genres = genres;
        this.tags = tags;
    }

    public void imprimir(Game tabela) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = (tabela.releaseDate != null) ? sdf.format(tabela.releaseDate) : "N/A";

        System.out.print("=> " + tabela.appID
            + " ## " + tabela.name
            + " ## " + dataFormatada
            + " ## " + tabela.estimatedOwners
            + " ## " + String.format("%.2f", tabela.price)
            + " ## [");

        for (int i = 0; i < tabela.supportedLanguages.length; i++) {
            if (i == tabela.supportedLanguages.length - 1)
                System.out.print(tabela.supportedLanguages[i]);
            else
                System.out.print(tabela.supportedLanguages[i] + ", ");
        }

        System.out.print("] ## " + tabela.metacritic
            + " ## " + String.format("%.1f", (double) tabela.userScore)
            + " ## " + tabela.achievements
            + " ## [" + tabela.devolopers + "] ## [");

        for (int i = 0; i < tabela.categories.length; i++) {
            if (i == tabela.categories.length - 1)
                System.out.print(tabela.categories[i]);
            else
                System.out.print(tabela.categories[i] + ", ");
        }

        System.out.print("] ## [");

        for (int i = 0; i < tabela.genres.length; i++) {
            if (i == tabela.genres.length - 1)
                System.out.print(tabela.genres[i]);
            else
                System.out.print(tabela.genres[i] + ", ");
        }

        System.out.print("] ## [");

        for (int i = 0; i < tabela.tags.length; i++) {
            if (i == tabela.tags.length - 1)
                System.out.print(tabela.tags[i]);
            else
                System.out.print(tabela.tags[i] + ", ");
        }

        System.out.print("] ##");
        System.out.println();
    }

    public static int carregarJogos(Game[] tabela) throws Exception {
        Scanner pubScanner = new Scanner(System.in);
        Scanner csvScanner = new Scanner(new File("games.csv"));
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

        csvScanner.nextLine(); // pular cabeçalho

        int[] idsDesejados = new int[200]; // até 200 IDs
        int totalIds = 0;
        int count = 0;

        // lê IDs do pub.in até encontrar "FIM"
        while (pubScanner.hasNextLine()) {
            String linha = pubScanner.nextLine().trim();
            if (linha.equals("FIM")) break;
            if (!linha.isEmpty()) {
                idsDesejados[totalIds++] = Integer.parseInt(linha);
            }
        }

        // percorre o CSV
        while (csvScanner.hasNextLine() && count < tabela.length) {
            String linha = csvScanner.nextLine();

            // divide considerando aspas e vírgulas (regex robusto)
            String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            try {
                int appID = Integer.parseInt(partes[0]);

                // verifica se o ID está em pub.in
                boolean existe = false;
                for (int i = 0; i < totalIds; i++) {
                    if (idsDesejados[i] == appID) {
                        existe = true;
                        break;
                    }
                }

                if (!existe) continue; // se não está, ignora a linha

                // cria o objeto Game
                String name = partes[1].replace("\"", "");
                Date releaseDate = sdf.parse(partes[2].replace("\"", ""));
                int estimatedOwners = Integer.parseInt(partes[3]);
                double price = Double.parseDouble(partes[4]);
                String[] supportedLanguages = partes[5].replaceAll("[\\[\\]'\"]", "").split("\\s*,\\s*");
                int metacritic = Integer.parseInt(partes[6]);
                int userScore = Integer.parseInt(partes[7]);
                int achievements = Integer.parseInt(partes[8]);
                String devolopers = partes[9].replace("\"", "");
                String[] categories = partes[10].replace("\"", "").split("\\s*,\\s*");
                String[] genres = partes[11].replace("\"", "").split("\\s*,\\s*");
                String[] tags = partes[12].replace("\"", "").split("\\s*,\\s*");

                tabela[count++] = new Game(appID, name, releaseDate, estimatedOwners, price,
                        supportedLanguages, metacritic, userScore, achievements,
                        devolopers, categories, genres, tags);

            } catch (Exception e) {
                // ignora linhas inválidas
            }
        }

        csvScanner.close();
        pubScanner.close();

        return count;
    }


    public static void main(String[] args) throws Exception {
        Game[] tabela = new Game[1853];
        int count = carregarJogos(tabela);

        for (int i = 0; i < count; i++) {
            tabela[i].imprimir(tabela[i]);
        }
    }
}
