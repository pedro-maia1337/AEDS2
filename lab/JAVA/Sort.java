import java.util.*;

class Sort {

  public static void swap(int i, int j, int arr[]){
    int aux = arr[i];
    arr[i] = arr[j];
    arr[j] = aux;
  }

  public static void main(String[] args){
    int len = -1;
    int mod = -1;
    int num = 0;
    Scanner sc = new Scanner(System.in);

    while(sc.hasNext()){
      len = sc.nextInt();
      mod = sc.nextInt();

      System.out.printf("%d %d", len, mod); 

      if(len == 0 && mod == 0){
	sc.close();
	return;
      }

      int[] arr = new int[len];

      for(int i = 0; i < len; i = i + 1) {
	arr[i] = sc.nextInt();
      }

      for(int i = 0; i < len - 1; i = i + 1){
	for(int j = i + 1; j < len; j = j + 1){

	  if(arr[i] % mod > arr[j] % mod){
	    swap(j, i, arr);
	  }

	  if(arr[i] % mod == arr[j] % mod){
	    if(arr[i] % 2 != 0) {
	      swap(j, i, arr);
	    }
	    if(arr[i] % 2 != 0 && arr[i] > arr[j]){
	      swap(j, i, arr);
	    }

	  }
	}
      }

      for(int i = 0; i < len; i = i + 1){
	System.out.println(arr[i]);
      }

    }
    sc.close();
  }
}
