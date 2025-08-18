class Aplicacoes {

  public static void a(int n) {
    for(int i = 0; i < n; i++) {
	    System.out.println(" " + i);
    }

    for(int i = 0; i < n; i++){
      for(int k = 0; k < n; k++) {
	      System.out.println(" " + i);
      }
    }
  }

  public static void sel(int vetor[], int n) {
    int temp = 0;
    for(int i = 0; i < (n - 1); i++){
      for(int j = (i + 1); j < n; j++){
        System.out.println()
        if(vetor[i] > vetor[j]){
            temp = vetor[j];
            vetor[j] = vetor[i];
            vetor[i] = temp;
        }
      }
    }
  }


  public static void main(String[] args) {
    // a(5);
    int[] vetor = {43, 42, 4, 8, 55, 21, 2, 45, 32, 61};

    sel(vetor, 10);

    for(int i = 0; i < 10; i = i + 1){
	    System.out.println(vetor[i]);
    }

  }

}
