#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main(void) {
    int n = 0;
    int n1 = 0;
    int n2 = 0;
    int ac = 0;
    int op = 0;

    int len = 0;

    scanf("%d", &n);

    int values[n*2];
    int index = 0;

    for(int i = 0; i < n; i = i + 1) {
	scanf("%d %d", &n1, &n2);

	op = (n2 - n1) + 1;

	ac = ac + op;

	values[index] = n1;
	index = index + 1;
	values[index] = n2;
	index = index + 1;
    }

    int arr[ac];
    int indexArr = 0;
    int acIndex = 0;

    for(int i = 1; i < n * 2; i = i + 2) {
	int current = values[i];
	int previous = values[i-1];

	for(int k = previous; k < current; k = k + 1) {
	    arr[indexArr] = k;
	    indexArr = indexArr + 1;
	}	
    }

    for(int i = 0; i < ac; i = i + 1) {
	printf("%d ", arr[i]);
    }
 
    printf("\n%d ", indexArr);
    return 0;
}
