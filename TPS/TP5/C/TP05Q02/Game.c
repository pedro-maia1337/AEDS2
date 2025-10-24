#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

#define MAX_STRING 500
#define MAX_ARRAY 100
#define MAX_GAMES 255

typedef struct {
    int appID;
    char name[MAX_STRING];
    char releaseDate[50];
    int estimatedOwners;
    double price;
    char supportedLanguages[MAX_ARRAY][MAX_STRING];
    int supportedLanguagesCount;
    int metacritic;
    int userScore;
    int achievements;
    char publishers[MAX_STRING];
    char developers[MAX_STRING];
    char categories[MAX_ARRAY][MAX_STRING];
    int categoriesCount;
    char genres[MAX_ARRAY][MAX_STRING];
    int genresCount;
    char tags[MAX_ARRAY][MAX_STRING];
    int tagsCount;
} Game;

// Função para inicializar um jogo vazio
void inicializarGame(Game *game) {
    game->appID = 0;
    strcpy(game->name, "");
    strcpy(game->releaseDate, "");
    game->estimatedOwners = 0;
    game->price = 0.0;
    game->supportedLanguagesCount = 0;
    game->metacritic = 0;
    game->userScore = 0;
    game->achievements = 0;
    strcpy(game->publishers, "");
    strcpy(game->developers, "");
    game->categoriesCount = 0;
    game->genresCount = 0;
    game->tagsCount = 0;
}

// Função para remover aspas
void removeQuotes(char *str) {
    if (str == NULL) return;
    
    int j = 0;
    for (int i = 0; str[i]; i++) {
        if (str[i] != '"') {
            str[j++] = str[i];
        }
    }
    str[j] = '\0';
}

// Função para remover colchetes
void removeBrackets(char *str) {
    if (str == NULL) return;
    
    int j = 0;
    for (int i = 0; str[i]; i++) {
        if (str[i] != '[' && str[i] != ']' && str[i] != '\'') {
            str[j++] = str[i];
        }
    }
    str[j] = '\0';
}

// Função para trim - remover espaços do início e fim
void trim(char *str) {
    if (str == NULL) return;
    
    int i = 0;
    int j = strlen(str) - 1;
    
    // Remover espaços do final
    while (j >= 0 && isspace(str[j])) {
        j--;
    }
    str[j + 1] = '\0';
    
    // Remover espaços do início
    i = 0;
    while (isspace(str[i])) {
        i++;
    }
    
    if (i > 0) {
        memmove(str, str + i, strlen(str) - i + 1);
    }
}

// Função para dividir string por delimitador
int splitString(char *str, char delimiter, char result[][MAX_STRING]) {
    if (str == NULL || strlen(str) == 0) return 0;
    
    int count = 0;
    char temp[MAX_STRING];
    strcpy(temp, str);
    
    char *token = strtok(temp, &delimiter);
    
    while (token != NULL && count < MAX_ARRAY) {
        strcpy(result[count], token);
        trim(result[count]);
        count++;
        token = strtok(NULL, &delimiter);
    }
    
    return count;
}

// Função para imprimir jogos
void imprimirGame(Game *game) {
    if (game == NULL) return;
    
    // Formatar preço
    char precoFormatado[20];
    if (game->price == 0.0) {
        strcpy(precoFormatado, "0.0");
    } else {
        // Verificar se é múltiplo de 0.5
        double remainder = game->price * 100 - (int)(game->price * 100);
        if (remainder == 0 || remainder == 50) {
            sprintf(precoFormatado, "%.1f", game->price);
        } else {
            sprintf(precoFormatado, "%.2f", game->price);
        }
    }
    
    printf("=> %d ## %s ## %s ## %d ## %s ## [", 
           game->appID, game->name, game->releaseDate, 
           game->estimatedOwners, precoFormatado);
    
    // Imprimir linguagens suportadas
    for (int i = 0; i < game->supportedLanguagesCount; i++) {
        printf("%s", game->supportedLanguages[i]);
        if (i < game->supportedLanguagesCount - 1) printf(", ");
    }
    
    printf("] ## %d ## %.1f ## %d ## [%s] ## [%s] ## [", 
           game->metacritic, (double)game->userScore / 10.0, 
           game->achievements, game->publishers, game->developers);
    
    // Imprimir categorias
    for (int i = 0; i < game->categoriesCount; i++) {
        printf("%s", game->categories[i]);
        if (i < game->categoriesCount - 1) printf(", ");
    }
    
    printf("] ## [");
    
    // Imprimir gêneros
    for (int i = 0; i < game->genresCount; i++) {
        printf("%s", game->genres[i]);
        if (i < game->genresCount - 1) printf(", ");
    }
    
    printf("] ## [");
    
    // Imprimir tags
    for (int i = 0; i < game->tagsCount; i++) {
        printf("%s", game->tags[i]);
        if (i < game->tagsCount - 1) printf(", ");
    }
    
    printf("] ##\n");
}

// Função para processar campo com possíveis vírgulas dentro de aspas
void parseCSVLine(char *line, char *fields[], int *fieldCount) {
    *fieldCount = 0;
    int inQuotes = 0;
    char *start = line;
    char *current = line;
    
    while (*current && *fieldCount < 20) {
        if (*current == '"') {
            inQuotes = !inQuotes;
        } else if (*current == ',' && !inQuotes) {
            *current = '\0';
            fields[(*fieldCount)++] = start;
            start = current + 1;
        }
        current++;
    }
    
    // Adicionar o último campo
    if (*start && *fieldCount < 20) {
        fields[(*fieldCount)++] = start;
    }
}

// Função para inicializar jogos a partir do CSV
int inicializarGames(Game tabela[], int idsDesejados[], int totalIds) {
    FILE *csv = NULL;
    char linha[2000];
    
    // Tentar abrir o arquivo
    csv = fopen("/tmp/games.csv", "r");
    if (csv == NULL) {
        csv = fopen("../../games.csv", "r");
        if (csv == NULL) {
            csv = fopen("games.csv", "r");
        }
    }
    
    if (csv == NULL) {
        printf("Erro: Não foi possível abrir o arquivo games.csv\n");
        return 0;
    }
    
    // Inicializar array de encontrados
    Game encontrados[MAX_GAMES];
    for (int i = 0; i < MAX_GAMES; i++) {
        inicializarGame(&encontrados[i]);
    }
    
    int count = 0;
    
    // Ler cabeçalho e ignorar
    if (fgets(linha, sizeof(linha), csv) == NULL) {
        fclose(csv);
        return 0;
    }
    
    // Ler linhas do CSV
    while (fgets(linha, sizeof(linha), csv)) {
        // Remover quebra de linha
        linha[strcspn(linha, "\n")] = 0;
        
        char *fields[20];
        int fieldCount = 0;
        
        // Processar linha do CSV
        parseCSVLine(linha, fields, &fieldCount);
        
        if (fieldCount >= 14) {
            int appID = atoi(fields[0]);
            
            // Verificar se o appID está na lista desejada
            int pos = -1;
            for (int i = 0; i < totalIds; i++) {
                if (idsDesejados[i] == appID) {
                    pos = i;
                    break;
                }
            }
            
            if (pos >= 0 && pos < MAX_GAMES) {
                Game game;
                inicializarGame(&game);
                
                game.appID = appID;
                
                // Nome
                if (fields[1] != NULL) {
                    strncpy(game.name, fields[1], MAX_STRING - 1);
                    removeQuotes(game.name);
                    trim(game.name);
                }
                
                // Data de lançamento
                if (fields[2] != NULL) {
                    strncpy(game.releaseDate, fields[2], 49);
                    removeQuotes(game.releaseDate);
                    trim(game.releaseDate);
                }
                
                // Estimated Owners
                if (fields[3] != NULL) {
                    game.estimatedOwners = atoi(fields[3]);
                }
                
                // Preço
                if (fields[4] != NULL) {
                    game.price = atof(fields[4]);
                }
                
                // Linguagens suportadas
                if (fields[5] != NULL) {
                    char tempLang[MAX_STRING];
                    strncpy(tempLang, fields[5], MAX_STRING - 1);
                    removeBrackets(tempLang);
                    removeQuotes(tempLang);
                    game.supportedLanguagesCount = splitString(tempLang, ',', game.supportedLanguages);
                }
                
                // Metacritic
                if (fields[6] != NULL) {
                    game.metacritic = atoi(fields[6]);
                }
                
                // User Score
                if (fields[7] != NULL) {
                    game.userScore = atoi(fields[7]);
                }
                
                // Achievements
                if (fields[8] != NULL) {
                    game.achievements = atoi(fields[8]);
                }
                
                // Developers
                if (fields[9] != NULL) {
                    strncpy(game.developers, fields[9], MAX_STRING - 1);
                    removeQuotes(game.developers);
                    trim(game.developers);
                }
                
                // Publishers
                if (fields[10] != NULL) {
                    strncpy(game.publishers, fields[10], MAX_STRING - 1);
                    removeQuotes(game.publishers);
                    trim(game.publishers);
                }
                
                // Categories
                if (fields[11] != NULL) {
                    char tempCat[MAX_STRING];
                    strncpy(tempCat, fields[11], MAX_STRING - 1);
                    removeQuotes(tempCat);
                    game.categoriesCount = splitString(tempCat, ',', game.categories);
                }
                
                // Genres
                if (fields[12] != NULL) {
                    char tempGen[MAX_STRING];
                    strncpy(tempGen, fields[12], MAX_STRING - 1);
                    removeQuotes(tempGen);
                    game.genresCount = splitString(tempGen, ',', game.genres);
                }
                
                // Tags
                if (fields[13] != NULL) {
                    char tempTags[MAX_STRING];
                    strncpy(tempTags, fields[13], MAX_STRING - 1);
                    removeQuotes(tempTags);
                    game.tagsCount = splitString(tempTags, ',', game.tags);
                }
                
                encontrados[pos] = game;
            }
        }
    }
    
    fclose(csv);
    
    // Copiar jogos encontrados para a tabela final
    for (int i = 0; i < totalIds; i++) {
        if (encontrados[i].appID != 0) {
            tabela[count++] = encontrados[i];
        }
    }
    
    return count;
}

int main() {
    int idsDesejados[MAX_GAMES];
    int totalIds = 0;
    char entrada[100];
    
    // Ler IDs até encontrar FIM
    while (scanf("%99s", entrada) == 1) {
        if (strcmp(entrada, "FIM") == 0) {
            break;
        }
        if (totalIds < MAX_GAMES) {
            idsDesejados[totalIds++] = atoi(entrada);
        }
    }
    
    Game tabela[MAX_GAMES];
    int count = inicializarGames(tabela, idsDesejados, totalIds);
    
    // Imprimir jogos
    for (int i = 0; i < count; i++) {
        imprimirGame(&tabela[i]);
    }
    
    return 0;
}