#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_LARGE_STRING 2000
#define MAX_IDS 255
#define MAX_GAMES 10000

// Games 
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
    char developers[255];
    char categories[255];
    char genres[255];
    char tags[2000];
} Game;

// Estrutura da lista
typedef struct ListaGames {
    struct ListaGames* prox;
    Game game;
} ListaGames;

// Variáveis globais
ListaGames* primeiro;
ListaGames* ultimo;
Game todosGames[MAX_GAMES];
int totalGamesCarregados = 0;

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

// Fazer parse dos campos
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

// Imprime os jogos NO FORMATO CORRETO
void imprimirGame(Game jogo) {
    // Formatação do preço conforme especificado
    char precoFormatado[20];
    if (jogo.price == 0.0) {
        strcpy(precoFormatado, "0.0");
    } else {
        // Verifica se é múltiplo de 0.5 para decidir formatação
        double decimal = jogo.price * 100;
        if ((int)decimal % 100 == 0 || (int)decimal % 100 == 50) {
            sprintf(precoFormatado, "%.1f", jogo.price);
        } else {
            sprintf(precoFormatado, "%.2f", jogo.price);
        }
    }

    // Formata userScore para uma casa decimal
    char userScoreFormatado[10];
    sprintf(userScoreFormatado, "%.1f", jogo.userScore);

    printf("=> %d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %s ## %d ## [%s] ## [%s] ## [%s] ## [%s] ## [%s] ##\n",
        jogo.appID,
        jogo.name,
        jogo.releaseDate,
        jogo.estimatedOwners,
        precoFormatado,
        jogo.supportedLanguages,
        jogo.metacritic,
        userScoreFormatado,
        jogo.achievements,
        jogo.publishers,
        jogo.developers,
        jogo.categories,
        jogo.genres,
        jogo.tags
    );
}

// Carregar todos os games do CSV 
int carregarTodosGames() {
    FILE *csv = fopen("/tmp/games.csv", "r");
    if (csv == NULL) csv = fopen("../games.csv", "r");
    if (csv == NULL) {
        printf("Erro: arquivo games.csv não encontrado.\n");
        return 0;
    }

    char linha[8192];
    fgets(linha, sizeof(linha), csv); // Pular cabeçalho

    int count = 0;

    while (fgets(linha, sizeof(linha), csv) != NULL && count < MAX_GAMES) {
        char campos[14][2000];
        int n = separarCampos(linha, campos, 14);
        if (n < 14) continue;

        todosGames[count].appID = atoi(campos[0]);

        strncpy(todosGames[count].name, campos[1], 254);
        todosGames[count].name[254] = '\0';
        criarData(campos[2], todosGames[count].releaseDate);
        todosGames[count].estimatedOwners = atoi(campos[3]);
        todosGames[count].price = atof(campos[4]);
        strncpy(todosGames[count].supportedLanguages, campos[5], 1999);
        todosGames[count].supportedLanguages[1999] = '\0';
        todosGames[count].metacritic = atoi(campos[6]);
        todosGames[count].userScore = atof(campos[7]);
        todosGames[count].achievements = atoi(campos[8]);
        strncpy(todosGames[count].publishers, campos[9], 254);
        todosGames[count].publishers[254] = '\0';

        strncpy(todosGames[count].developers, campos[10], 254);
        todosGames[count].developers[254] = '\0';

        strncpy(todosGames[count].categories, campos[11], 254);
        todosGames[count].categories[254] = '\0';

        strncpy(todosGames[count].genres, campos[12], 254);
        todosGames[count].genres[254] = '\0';

        strncpy(todosGames[count].tags, campos[13], 1999);
        todosGames[count].tags[1999] = '\0';

        count++;
    }

    fclose(csv);
    totalGamesCarregados = count;
    return count;
}

// Buscar game por ID nos games já carregados
Game* buscarGamePorID(int appID) {
    for (int i = 0; i < totalGamesCarregados; i++) {
        if (todosGames[i].appID == appID) {
            return &todosGames[i];
        }
    }
    return NULL;
}

// Nova célula
ListaGames* criarCelula(Game g) {
    ListaGames* nova = (ListaGames*) malloc(sizeof(ListaGames));
    nova->prox = NULL;
    nova->game = g;
    return nova;
}

// Iniciar lista
void start() {
    primeiro = criarCelula((Game){0}); // Nó cabeça
    ultimo = primeiro;
}

// Inserir no início
void inserirInicio(Game game) {
    ListaGames* tmp = criarCelula(game);
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    
    if (primeiro == ultimo) {
        ultimo = tmp;
    }
}

// Inserir no fim
void inserirFim(Game game) {
    ultimo->prox = criarCelula(game);
    ultimo = ultimo->prox;
}

// Remover do início
Game removerInicio() {
    Game removido = {0};
    
    if (primeiro == ultimo) {
        printf("Erro ao remover. Lista vazia!\n");
        return removido;
    }
    
    ListaGames* tmp = primeiro->prox;
    removido = tmp->game;
    primeiro->prox = tmp->prox;
    
    if (tmp == ultimo) {
        ultimo = primeiro;
    }
    
    free(tmp);
    return removido;
}

void mostrar() {
    int indice = 0;
    for (ListaGames* i = primeiro->prox; i != NULL; i = i->prox, indice++) {
        printf("[%d] ", indice);
        imprimirGame(i->game);
    }
}

int main() {
    int idsDesejados[MAX_IDS];
    int totalIds = 0;
    char linha[100];
    bool flag = false;

    int totalCarregados = carregarTodosGames();

    // Inicializar lista
    start();

    while (fgets(linha, sizeof(linha), stdin) != NULL && totalIds < MAX_IDS && !flag) {
        linha[strcspn(linha, "\n")] = 0;
        
        if (strcmp(linha, "FIM") == 0) {
            flag = true;
        } else if (strlen(linha) > 0) {
            idsDesejados[totalIds++] = atoi(linha);
        }
    }
    
    for (int i = 0; i < totalIds; i++) {
        Game* game = buscarGamePorID(idsDesejados[i]);
        if (game != NULL) {
            inserirFim(*game);
        } else {
            printf("Game com ID %d não encontrado\n", idsDesejados[i]);
        }
    }

    // Ler número de operações
    int numReg;
    if (fgets(linha, sizeof(linha), stdin) != NULL) {
        numReg = atoi(linha);
    }

    // Processar operações adicionais
    for (int i = 0; i < numReg; i++) {
        if (fgets(linha, sizeof(linha), stdin) == NULL) break;
        
        // Remover quebra de linha
        linha[strcspn(linha, "\n")] = 0;
        
        char comando[10];
        int param1 = 0;
        
        if (sscanf(linha, "%s %d", comando, &param1) >= 1) {
            if (strcmp(comando, "I") == 0) {
                Game* game = buscarGamePorID(param1);
                if (game != NULL) {
                    inserirInicio(*game);
                }
            }
            else if (strcmp(comando, "R") == 0) {
                Game removido = removerInicio();
                if (removido.appID != 0) { 
                    printf("(R) %s\n", removido.name);
                }
            }
            else {
                printf("Operacao invalida: %s\n", comando);
            }
        }
    }

    mostrar();
    
    return 0;
}