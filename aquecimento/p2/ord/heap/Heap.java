class Heap  {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr, int n) {
        int[] temp = new int[n+1];
        
        for(int i = 0; i < n; i = i + 1) {
            temp[i+1] = arr[i];
        }

        for(int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(temp, tamHeap);
        }

        int tamHeap = n;

        while(tamHeap > 1) {
            swap(temp, 1, tamHeap--);
            reconstruir(temp, tamHeap);
        }

        for(int i = 0; i < n; i = i + 1) {
            arr[i] = temp[i+1];
        }

    }

    public static void construir(int[] arr, int tamHeap) {
        for(int i = tamHeap; i > 1 && arr[i] > arr[i/2]; i /= 2) {
            swap(arr, i, i/2);
        } 
    }

    public static void reconstruir(int[] arr, int tamHeap) {
        int i = 1;
        while(i <= (tamHeap/2)){
            int filho = getMaiorFilho(arr, i, tamHeap);
            if(arr[i] < arr[filho]) {
                swap(arr, i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    public static int getMaiorFilho(int[] arr, int i, int tamHeap){
        int filho = 0;
        if(2*i == tamHeap || arr[2*i] > arr[2*i+1]){
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    public static void main(String[] args) {
        int[] arr = {12, 4 , 8, 2, 14, 17, 6, 18, 10, 16, 15, 5, 13, 9, 1, 11, 7, 3};
        int n = arr.length;

        sort(arr, n);

        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}
