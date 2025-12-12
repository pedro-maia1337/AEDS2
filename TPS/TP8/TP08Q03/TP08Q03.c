#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_LARGE_STRING 2000
#define MAX_IDS 255
#define MAX_GAMES 10000

// Estrutura Game
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

// Variáveis globais
Game todosGames[MAX_GAMES];
int totalGamesCarregados = 0;
int comparacoes = 0;

// Funções auxiliares
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
        
        // Remove caracteres indesejados
        if (c == '[' || c == ']' || c == '\'' || c == '"' || c == '\n' || c == '\r') {
            continue;
        }
        
        temp[j++] = c;
        
        // Adiciona espaço após vírgula quando necessário
        if (c == ',' && str[i+1] != ' ' && str[i+1] != '\0' && j < MAX_LARGE_STRING - 1) {
            temp[j++] = ' ';
        }
    }
    
    temp[j] = '\0';
    strcpy(str, temp);
}

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

void imprimirGame(Game jogo) {
    // Formatação do preço 
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

// Carregar todos os games do CSV uma única vez
int carregarTodosGames() {
    FILE *csv = fopen("/tmp/games.csv", "r");
    if (csv == NULL) csv = fopen("../games.csv", "r");
    if (csv == NULL) {
        printf("Erro: arquivo games.csv não encontrado.\n");
        return 0;
    }

    char linha[8192];
    fgets(linha, sizeof(linha), csv); 

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

//Struct Celula
typedef struct Celula {
    struct Celula *prox;
    Game elemento;
} Celula;

// Struct lista 
typedef struct Lista {
    Celula *primeiro;
    Celula *ultimo;
} Lista;

//Struct Hash
typedef struct Hash {
    int len;
    Lista **tabela;
} Hash;

//Inicia Celula
Celula* novaCelula(Game x) {
    Celula *nova = (Celula*)malloc(sizeof(Celula));
    nova->prox = NULL;
    nova->elemento = x;
    return nova;
}

//Inicia lista
Lista* novaLista(Lista* lista) {
    lista->primeiro = (Celula*) malloc(sizeof(Celula));
    lista->primeiro->prox = NULL;
    lista->ultimo = lista->primeiro;
}

// Insere no fim da lista
void inserirLista(Lista* l, Game g) {
    l->ultimo->prox = novaCelula(g);
    l->ultimo = l->ultimo->prox;
}

bool pesquisarLista(Lista* l, char* nome) {
    for (Celula* i = l->primeiro->prox; i != NULL; i = i->prox) {
        comparacoes++;
        if (strcmp(i->elemento.name, nome) == 0) {
            return true;
        }
    }
    return false;
}

//Tabela HASH
Lista tabela[21];

void novoHash() {
    for (int i = 0; i < 21; i++)
        novaLista(&tabela[i]);
}

// Função hash (melhorada)
int h(char* name) {
    int soma = 0;
        for (int i = 0; name[i] != '\0'; i++) {
            soma += (unsigned char)name[i];
        }
        return soma % 21;
}

// Insere elemento na tabela hash
void inserirHash(Game g) {
    int pos = h(g.name);
    inserirLista(&tabela[pos], g);
}

//Pesquisa na HASH
bool pesquisarHash(char* nome) {
    int pos = h(nome);
    printf("%s:  (Posicao: %d) ", nome, pos);
    return pesquisarLista(&tabela[pos], nome);
}

// Log - evidencia que C é bem mais rápido mesmo 
void escreverLog(const char* nomeArquivo, int comparacoes, double tempo) {
    FILE* writer = fopen(nomeArquivo, "w");
    if (writer == NULL) {
        printf("Erro ao escrever log: %s\n", nomeArquivo);
        return;
    }
    fprintf(writer, "874398\t%d\t%.5f\n", comparacoes, tempo);
    fclose(writer);
}

//Fiz identico ao HashIndireto nos fontes do github porém por algum motivo a primeira posição está sendo iniciada como 3 e não 0
int main() {
    int idsDesejados[MAX_IDS];
    int totalIds = 0;
    char linha[100];
    bool flag = false;

    int totalCarregados = carregarTodosGames();
    if (totalCarregados == 0) {
        printf("Nenhum jogo carregado. Encerrando.\n");
        return 1;
    }

    novoHash();

    // Leitura dos IDs até encontrar FIM
    while (fgets(linha, sizeof(linha), stdin) != NULL && totalIds < MAX_IDS && !flag) {
        // Remover quebra de linha
        linha[strcspn(linha, "\n")] = 0;
        
        if (strcmp(linha, "FIM") == 0) {
            flag = true;
        } else if (strlen(linha) > 0) {
            idsDesejados[totalIds++] = atoi(linha);
        }
    }

    // Inserir jogos na HASH
    for (int i = 0; i < totalIds; i++) {
        Game* game = buscarGamePorID(idsDesejados[i]);
        if (game != NULL) {
            inserirHash(*game);
        } else {
            printf("Game com ID %d não encontrado\n", idsDesejados[i]);
        }
    }

    // Resetar flag 
    flag = false;
    
    clock_t inicio, fim;
    double tempo;

    inicio = clock();
    
    printf("\n");
    
    // Realiza a leitura das entradas, pesquisa e imprime
    while (fgets(linha, sizeof(linha), stdin) != NULL && !flag) {
        // Remover quebra de linha
        linha[strcspn(linha, "\n")] = 0;
        
        if (strcmp(linha, "FIM") == 0) {
            flag = true;
        } else if (strlen(linha) > 0) {
            if(pesquisarHash(linha)) {
                printf("SIM\n");
            } else{
                printf("NAO\n");
            }
        }
    }
    
    fim = clock();
    tempo = ((double)(fim - inicio)) / CLOCKS_PER_SEC;

    escreverLog("874398_hashIndireta.txt", comparacoes, tempo);
    
    return 0;
}