class Ord {

    public static void swap(int i, int j, int vetor[]) {
		int temp = vetor[i];
		vetor[i] = vetor[j];
		vetor[j] = temp;
	}

    public static void selecao(int vetor[], int n) { //O(n^2)
        int mov = 0;
        int comp = 0;
        int aux = 0;

        for(int i = 0; i < (n - 1); i = i + 1){
            int menor = i;

            for(int j = (i + 1); j < n; j = j + 1){
                comp++;
                if(vetor[menor] > vetor[j]) {
                    menor = j;
                }
            }

            if (menor != i) {
                swap(menor, i, vetor);
                mov += 3; 
            }
        }

        System.out.println("Numero de movimentacoes: " + mov);
        System.out.println("Numero de comparacoes: " + comp);
    }

    public static void main(String[] args) {
        int n = 10;
        int vetor[] = new int[n];

        for(int i = 0; i < n; i = i + 1) {
            vetor[i] = n - i;
        }

        System.out.println("Array Desordenado.");
        for(int i = 0; i < n; i = i + 1) {
            System.out.printf("%d ", vetor[i]);
        }

        System.out.println("");

        selecao(vetor, n);

        System.out.println("Array Ordenado.");
        for(int i = 0; i < n; i = i + 1) {
            System.out.printf("%d ", vetor[i]);
        }  

        System.out.println("");   
    }
}