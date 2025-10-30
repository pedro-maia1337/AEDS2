class Quick {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr, int esq, int dir){
        quickRec(arr, esq, dir);
    }

    public static void quickRec(int[] arr, int esq, int dir){
        int i = esq, j = dir;
        int pivo = arr[(dir + esq) / 2];
        while(i <= j){
            while(arr[i] < pivo) i++;
            while(arr[j] > pivo) j--;
            if(i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
            if(esq < j) quickRec(arr, esq, j);
            if(i < dir) quickRec(arr, i, dir);
        }
    }
    
    public static void main(String[] args) {
        int n = 0;
        int[] arr = {12, 4 , 8, 2, 14, 17, 6, 18, 10, 16, 15, 5, 13, 9, 1, 11, 7, 3};
        n = arr.length;

        sort(arr, 0 , n - 1);

        for(int i : arr){
            System.out.printf("%d ", i);
        }
    }
}