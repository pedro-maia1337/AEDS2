class Ex01 {
    public static void quicksort(int[] arr, int low, int high, int[] mov, int[] cmp) {
        partition(arr, low, high, mov, cmp);
    }

    public static void partition(int[] arr, int low, int high, int[] mov, int[] cmp) {
        int i = low; int j = high; int pivo = arr[(low + high) / 2];
        while(i <= j) {
            cmp[0]++;
            while(arr[i] < pivo) i++;
            cmp[0]++;
            while(arr[j] > pivo) j--;
            cmp[0]++;
            if(i <= j) {
                mov[0]+=3;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
                
            }
            cmp[0]++;
            if(low < j)  partition(arr, low , j, mov, cmp);
            cmp[0]++;
            if(i < high) partition(arr, i, high, mov, cmp);
        }
    }
    public static void main(String[] args) {
        int arr[] = {89, 45, 120, 23, 67, 199, 34};
        int n = arr.length;
        int mov[] = new int[1];
        int cmp[] = new int[1];

        quicksort(arr, 0, n - 1, mov, cmp);

        for(int i : arr){
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("Numero de comparações:" + cmp[0]);
        System.out.println("Numero de movimentações:" + mov[0]);
    }
}
