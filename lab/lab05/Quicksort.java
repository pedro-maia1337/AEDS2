import java.util.concurrent.ThreadLocalRandom;

class Quicksort extends Geracao {

	/**
	 * Construtor.
	 */
   public Quicksort(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public Quicksort(int tamanho){
      super(tamanho);
   }


	/**
	 * Algoritmo de ordenacao Quicksort.
	 */
   @Override
   public void sort(int i) {
      if(i == 1)  QuickSortFirstPivot(0, n-1);
      if(i == 2)  QuickSortLastPivot(0, n-1);
      if(i == 3)  QuickSortRandomPivot(0, n-1);
      if(i == 4)  QuickSortMedianOfThree(0, n-1);
   }

	/**
	 * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
	 */

    //Pior caso?
    private void QuickSortFirstPivot(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[i];
        while (i <= j) {
            while (array[i] < pivo) {
                cmp++;
                i++;
            }
            while (array[j] > pivo) {
                cmp++;
                j--;
            }
            if (i <= j) {
                swap(i, j);
                mov+=3;
                i++;
                j--;

            }
        }
        if (esq < j)  QuickSortFirstPivot(esq, j);
        if (i < dir)  QuickSortFirstPivot(i, dir);
        
    }

    //Melhor pior caso?
    private void QuickSortLastPivot(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[dir];
        while (i <= j) {
            while (array[i] < pivo) {
                cmp++;
                i++;
            }
            while (array[j] > pivo) {
                cmp++;
                j--;
            }
            if (i <= j) {
                swap(i, j);
                mov+=3;
                i++;
                j--;

            }
        }
        if (esq < j)  QuickSortLastPivot(esq, j);
        if (i < dir)  QuickSortLastPivot(i, dir);
    }

    //Caso médio ?
    private void QuickSortRandomPivot(int esq, int dir) {
        int randomPivot = ThreadLocalRandom.current().nextInt(esq, dir + 1);
        int i = esq, j = dir;
        int pivo = array[randomPivot];
        while (i <= j) {
            while (array[i] < pivo) {
                cmp++;
                i++;
            }
            while (array[j] > pivo) {
                cmp++;
                j--;
            }
            if (i <= j) {
                swap(i, j);
                mov+=3;
                i++;
                j--;

            }
        }
        if (esq < j)  QuickSortRandomPivot(esq, j);
        if (i < dir)  QuickSortRandomPivot(i, dir);
    }

    //Melhor caso
    private void QuickSortMedianOfThree(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[(dir+esq)/2];
        while (i <= j) {
            while (array[i] < pivo) {
                cmp++;
                i++;
            }
            while (array[j] > pivo) {
                cmp++;
                j--;
            }
            if (i <= j) {
                swap(i, j);
                mov+=3;
                i++;
                j--;

            }
        }
        if (esq < j)  QuickSortMedianOfThree(esq, j);
        if (i < dir)  QuickSortMedianOfThree(i, dir);
    }
}