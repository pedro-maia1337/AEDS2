#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_LARGE_STRING 2000
#define MAX_IDS 255

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

//Ponteiros primeiro e ultimo da lista
ListaGames* primeiro;
ListaGames* ultimo;

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

// Imprime os jogos
void imprimirGame(Game jogo) {
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
        jogo.developers,
        jogo.publishers,
        jogo.categories,
        jogo.genres,
        jogo.tags
    );
}

// Função para inicializar um único jogo
int inicializarGame(Game *jogo, int appIDDesejado) {
    FILE *csv = fopen("/tmp/games.csv", "r");
    if (csv == NULL) csv = fopen("../games.csv", "r");
    if (csv == NULL) {
        printf("Erro: arquivo games.csv não encontrado.\n");
        return 0;
    }

    char linha[8192];
    fgets(linha, sizeof(linha), csv); // Pular cabeçalho

    int encontrado = 0;

    while (fgets(linha, sizeof(linha), csv) != NULL && !encontrado) {
        char campos[14][2000];
        int n = separarCampos(linha, campos, 14);
        if (n < 14) continue;

        int appID = atoi(campos[0]);

        if (appID == appIDDesejado) {
            jogo->appID = appID;

            strncpy(jogo->name, campos[1], 254);
            jogo->name[254] = '\0';
            criarData(campos[2], jogo->releaseDate);
            jogo->estimatedOwners = atoi(campos[3]);
            jogo->price = atof(campos[4]);
            strncpy(jogo->supportedLanguages, campos[5], 1999);
            jogo->supportedLanguages[1999] = '\0';
            jogo->metacritic = atoi(campos[6]);
            jogo->userScore = atof(campos[7]);
            jogo->achievements = atoi(campos[8]);
            strncpy(jogo->developers, campos[9], 254); // Corrigido
            jogo->developers[254] = '\0';
            strncpy(jogo->publishers, campos[10], 254);
            jogo->publishers[254] = '\0';
            strncpy(jogo->categories, campos[11], 254);
            jogo->categories[254] = '\0';
            strncpy(jogo->genres, campos[12], 254);
            jogo->genres[254] = '\0';
            strncpy(jogo->tags, campos[13], 1999);
            jogo->tags[1999] = '\0';

            encontrado = 1;
        }
    }

    fclose(csv);
    return encontrado;
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

// Inserir no inicio
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

// Inserir em posição específica
void inserirPos(Game game, int pos) {
    int tamanho = 0;
    ListaGames* i;
    for (i = primeiro->prox; i != NULL; i = i->prox) tamanho++;
    
    if (pos < 0 || pos > tamanho) {
        printf("Erro ao inserir. Posicao invalida!\n");
        return;
    } else if (pos == 0) {
        inserirInicio(game);
    } else if (pos == tamanho) {
        inserirFim(game);
    } else {
        ListaGames* anterior = primeiro;
        for (int j = 0; j < pos; j++) {
            anterior = anterior->prox;
        }
        
        ListaGames* tmp = criarCelula(game);
        tmp->prox = anterior->prox;
        anterior->prox = tmp;
    }
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

// Remover do fim
Game removerFim() {
    Game removido = {0};
    
    if (primeiro == ultimo) {
        printf("Erro ao remover. Lista vazia!\n");
        return removido;
    }

    ListaGames* i;
    for (i = primeiro; i->prox != ultimo; i = i->prox);
    
    removido = ultimo->game;
    ListaGames* tmp = ultimo;
    ultimo = i;
    ultimo->prox = NULL;
    free(tmp);
    
    return removido;
}

// Remover de qualquer posição
Game removerPos(int pos) {
    Game removido = {0};
    int tamanho = 0;
    ListaGames* i;
    
    if (primeiro == ultimo) {
        printf("Erro ao remover. Lista vazia!\n");
        return removido;
    }
    
    for (i = primeiro->prox; i != NULL; i = i->prox) {
        tamanho++;
    }
    
    if (pos < 0 || pos >= tamanho) {
        printf("Erro ao remover. Posicao invalida!\n");
        return removido;
    } else if (pos == 0) {
        removido = removerInicio();
    } else if (pos == tamanho - 1) {
        removido = removerFim();
    } else {
        ListaGames* anterior = primeiro;
        for (int j = 0; j < pos; j++) {
            anterior = anterior->prox;
        }
        
        ListaGames* tmp = anterior->prox;
        removido = tmp->game;
        anterior->prox = tmp->prox;
        free(tmp);
    }
    
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

    // Inicializar lista
    start();

    // Leitura dos IDs até encontrar FIM
    while (fgets(linha, sizeof(linha), stdin) != NULL && totalIds < MAX_IDS && !flag) {
        linha[strcspn(linha, "\n")] = 0;
        
        if (strcmp(linha, "FIM") == 0) {
            flag = true;
        } else if (strlen(linha) > 0) {
            idsDesejados[totalIds++] = atoi(linha);
        }
    }

    // Inserir jogos iniciais
    for (int i = 0; i < totalIds; i++) {
        Game game;
        if (inicializarGame(&game, idsDesejados[i])) {
            inserirFim(game);
        }
    }

    // Ler número de operações
    int numReg;
    if (fgets(linha, sizeof(linha), stdin) != NULL) {
        numReg = atoi(linha);
    }

    //Leitura das operações 
    for (int i = 0; i < numReg; i++) {
        if (fgets(linha, sizeof(linha), stdin) == NULL) break;
        
        // Remover quebra de linha
        linha[strcspn(linha, "\n")] = 0;
        
        char comando[10];
        int param1 = 0, param2 = 0;
        
        // Parse
        if (sscanf(linha, "%s %d %d", comando, &param1, &param2) >= 1) {
            if (strcmp(comando, "II") == 0) {
                // Inserir no início
                Game game;
                if (inicializarGame(&game, param1)) {
                    inserirInicio(game);
                }
            }
            else if (strcmp(comando, "I*") == 0) {
                // Inserir em qualquer posição
                Game game;
                if (inicializarGame(&game, param2)) {
                    inserirPos(game, param1);
                }
            }
            else if (strcmp(comando, "IF") == 0) {
                // Inserir no fim
                Game game;
                if (inicializarGame(&game, param1)) {
                    inserirFim(game);
                }
            }
            else if (strcmp(comando, "RI") == 0) {
                // Remover do início
                Game removido = removerInicio();
                if (removido.appID != 0) {
                    printf("(R) %s\n", removido.name);
                }
            }
            else if (strcmp(comando, "R*") == 0) {
                // Remover de qualquer posição
                Game removido = removerPos(param1);
                if (removido.appID != 0) {
                    printf("(R) %s\n", removido.name);
                }
            }
            else if (strcmp(comando, "RF") == 0) {
                // Remover do fim
                Game removido = removerFim();
                if (removido.appID != 0) {
                    printf("(R) %s\n", removido.name);
                }
            }
            else {
                printf("Operacao invalida: %s\n", comando);
            }
        }
    }

    // Imprimir lista final
    mostrar();
    
    return 0;
}