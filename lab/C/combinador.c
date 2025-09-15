#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void){
  char str1[255];
  char str2[255];
  int len = 0;

  while(scanf("%s %s", str1, str2) != EOF){
    int l = 0; int j = 0; int k = 0;
    int len = strlen(str1) + strlen(str2);
    char str3[len + 1];
    for(int i = 0; i < len; i = i + 1){
      if(i < strlen(str1)){
	 str3[l++] = str1[k++];
      }

      if(i < strlen(str2)){
	str3[l++] = str2[j++];	
      }
    }

    for(int i = 0; i < len; i = i + 1){
      printf("%c", str3[i]);
    }

    printf("\n");
  }

  return 0;
}
