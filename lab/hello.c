#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int count_m (char *str) {
	int len = strlen(str);
	int count = 0;
	for(int i = 0; i < len; i = i + 1) {
		if(str[i] >= 'A' && str[i] <= 'Z') {
			count = count + 1;
		}

	} 

	return count;
}

int main(void) {
	char str[50];
	int result = 0;

	while(strstr(fgets(str, 50, stdin), "FIM") != NULL) {
			result = count_m(str);
			printf("%d\n", result);
	} 

	return 0;
}