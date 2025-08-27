class Pesq {
    public static boolean pesquisa_sequencial(int vetor[], int n, int x) { //O(n)
        boolean flag = false;
        for(int i = 0; i < n; i = i + 1) {
            if(vetor[i] == x) {
                flag = true;
                i = n;
            }
        }

        return flag;
    }

    public static boolean pesquisa_bin(int vetor[], int n, int x) { //O(lg(n))
        boolean flag = false;
        int direita = n - 1, esquerda = 0, meio = 0;

        while(esquerda <= direita){
            meio = (esquerda + direita) / 2;
            if(x == vetor[meio]) {
                flag = true;
                esquerda = n;
            } else if(x > vetor[meio]) {
                esquerda = meio + 1;
            } else{
                direita = meio - 1;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        boolean test = false;
        int n = 10;
        int vetor[] = new int[n];
        
        for(int i = 0; i < n; i = i + 1) {
            vetor[i] = i;
        }

        //test = pesquisa_sequencial(vetor, n, 5);
        test = pesquisa_bin(vetor, n, 6);

        if(test) {
            System.out.println("ENCONTRADO");
        } else {
            System.out.println("NAO ENCONTRADO");
        }
    }
}