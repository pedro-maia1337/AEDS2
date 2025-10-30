#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_LARGE_STRING 2000

//Games 
typedef struct {
    int appID;
    char name[255];
    char releaseDate[255];
    int estimatedOwners;
    double price;
    char supportedLanguages[2000];
    int metacritic;
    double userScore;
    int achievements;
    char publishers[255];
    char devolopers[255];
    char categories[255];
    char genres[255];
    char tags[2000];
} Game;


void criarData(char *origem, char *destino) {
    char mesStr[10];
    int dia, ano, mes = 0;
    if (sscanf(origem, "%s %d, %d", mesStr, &dia, &ano) == 3) {
        if (strcmp(mesStr, "Jan") == 0) mes = 1;
        else if (strcmp(mesStr, "Feb") == 0) mes = 2;
        else if (strcmp(mesStr, "Mar") == 0) mes = 3;
        else if (strcmp(mesStr, "Apr") == 0) mes = 4;
        else if (strcmp(mesStr, "May") == 0) mes = 5;
        else if (strcmp(mesStr, "Jun") == 0) mes = 6;
        else if (strcmp(mesStr, "Jul") == 0) mes = 7;
        else if (strcmp(mesStr, "Aug") == 0) mes = 8;
        else if (strcmp(mesStr, "Sep") == 0) mes = 9;
        else if (strcmp(mesStr, "Oct") == 0) mes = 10;
        else if (strcmp(mesStr, "Nov") == 0) mes = 11;
        else if (strcmp(mesStr, "Dec") == 0) mes = 12;
        sprintf(destino, "%02d/%02d/%04d", dia, mes, ano);
    } else {
        strncpy(destino, origem, 254);
        destino[254] = '\0';
    }
}

void limpar(char *str) {
    if (!str) return;
    
    char temp[MAX_LARGE_STRING];
    int j = 0;
    
    for (int i = 0; str[i] && j < MAX_LARGE_STRING - 1; i++) {
        char c = str[i];
        
        // Remove caracteres 
        if (c == '[' || c == ']' || c == '\'' || c == '"' || c == '\n' || c == '\r') {
            continue;
        }
        
        temp[j++] = c;
        
        // adiciona espaço
        if (c == ',' && str[i+1] != ' ' && str[i+1] != '\0' && j < MAX_LARGE_STRING - 1) {
            temp[j++] = ' ';
        }
    }
    
    temp[j] = '\0';
    strcpy(str, temp);
}

//Fazer parse dos campos, strtok não funciona direito nesse caso 
int separarCampos(char *linha, char campos[][2000], int maxCampos) {
    int i = 0, j = 0, k = 0;
    int dentroAspas = 0;
    char campo[2000] = "";

    while (linha[i] != '\0') {
        char c = linha[i];
        if (c == '"') {
            dentroAspas = !dentroAspas;
        } else if (c == ',' && !dentroAspas) {
            campo[j] = '\0';
            limpar(campo);
            strncpy(campos[k], campo, 1999);
            campos[k][1999] = '\0';
            k++;
            j = 0;
            campo[0] = '\0';
            if (k >= maxCampos) break;
        } else if (j < 1998) {
            campo[j++] = c;
        }
        i++;
    }

    campo[j] = '\0';
    limpar(campo);
    strncpy(campos[k], campo, 1999);
    campos[k][1999] = '\0';
    k++;

    return k;
}

//Imprime os jogos
void imprimirGames(Game jogo) {
    printf("=> %d ## %s ## %s ## %d ## %.2lf ## [%s] ## %d ## %.1lf ## %d ## [%s] ## [%s] ## [%s] ## [%s] ## [%s] ##\n",
        jogo.appID,
        jogo.name,
        jogo.releaseDate,
        jogo.estimatedOwners,
        jogo.price,
        jogo.supportedLanguages,
        jogo.metacritic,
        jogo.userScore,
        jogo.achievements,
        jogo.devolopers,
        jogo.publishers,
        jogo.categories,
        jogo.genres,
        jogo.tags
    );
}

//Ler os arquivos do csv
int inicializarGames(Game tabela[], int idsDesejados[], int totalIds) {
    FILE *csv = fopen("/tmp/games.csv", "r");
    if (csv == NULL) csv = fopen("../../games.csv", "r");
    if (csv == NULL) {
        printf("Erro: arquivo games.csv não encontrado.\n");
        return 0;
    }

    char linha[8192];
    fgets(linha, sizeof(linha), csv); 

    Game encontrados[255];
    for (int i = 0; i < 255; i = i + 1) encontrados[i].appID = -1;

    //Lê e formata com as funções acima
    while (fgets(linha, sizeof(linha), csv) != NULL) {
        char campos[14][2000];
        int n = separarCampos(linha, campos, 14);
        if (n < 14) continue;

        int appID = atoi(campos[0]);
        int pos = -1;
        for (int i = 0; i < totalIds; i = i + 1) {
            if (idsDesejados[i] == appID) {
                pos = i;
                break;
            }
        }

        //Atribuição dos campos
        if (pos != -1) {
            Game jogo;
            jogo.appID = appID;

            strncpy(jogo.name, campos[1], 254);
            jogo.name[254] = '\0';
            criarData(campos[2], jogo.releaseDate);
            jogo.estimatedOwners = atoi(campos[3]);
            jogo.price = atof(campos[4]);
            strncpy(jogo.supportedLanguages, campos[5], 1999);
            jogo.supportedLanguages[1999] = '\0';
            jogo.metacritic = atoi(campos[6]);
            jogo.userScore = atof(campos[7]);
            jogo.achievements = atoi(campos[8]);
            strncpy(jogo.devolopers, campos[9], 254);
            strncpy(jogo.publishers, campos[10], 254);
            strncpy(jogo.categories, campos[11], 254);
            strncpy(jogo.genres, campos[12], 254);
            strncpy(jogo.tags, campos[13], 1999);
            jogo.tags[1999] = '\0';

            encontrados[pos] = jogo;
        }
    }

    fclose(csv);

    int count = 0;
    for (int i = 0; i < totalIds; i = i + 1) {
        if (encontrados[i].appID != -1) {
            tabela[count++] = encontrados[i];
        }
    }

    return count;
}

//Função para realizar a troca de jogos 
void swap(Game tabela[], int i, int j) {
    Game temp = tabela[i];
    tabela[i] = tabela[j];
    tabela[j] = temp;
}

//Utilizando código do seleção visto nas aulas 
// utilizando strcmp para compração de string 
int selecao(Game *array, int n){
    int cmp = 0;
    for (int i = 0; i < (n - 1); i = i + 1) {
        int menor = i;
        for (int j = (i + 1); j < n; j = j + 1){
            cmp++;
            if (strcmp(array[menor].name, array[j].name) > 0){
                menor = j;
            }
        }
        swap(array, menor, i);
    }

    return cmp;
}

//Escrever LOG
void escreverLog(char *nomeArquivo, int comparacoes, double tempo) {
    FILE *arquivo = fopen(nomeArquivo, "a"); 
    if (arquivo == NULL) {
        fprintf(stderr, "Erro ao escrever no log: %s\n", nomeArquivo);
        return;
    }
    fprintf(arquivo, "874398\t%.6f\t%d\n", tempo, comparacoes);
    fclose(arquivo);
}

int main() {
    int idsDesejados[255];
    int totalIds = 0;
    char entrada[32];

    //esse tipo de dado vem da biblioteca time.h
    //na doc diz que retorna um long int
    clock_t inicio;
    clock_t fim;

    double total = 0.0;
    int cmp = 0;

    //Lendo até encontrar FIM 
    while (scanf("%s", entrada) == 1) {
        if (strcmp(entrada, "FIM") == 0) break;
        idsDesejados[totalIds++] = atoi(entrada);
    }

    Game tabela[255];
    int count = inicializarGames(tabela, idsDesejados, totalIds);

    //Ordenação
    inicio = clock();
    cmp = selecao(tabela, count);
    fim = clock();
    total = ((fim - inicio) / (double)CLOCKS_PER_SEC);

    for (int i = 0; i < count; i = i + 1) {
        imprimirGames(tabela[i]);
    }

    escreverLog("log", cmp, total);

    return 0;
}
