public class Complexidade {

    public static boolean exemplo01 (int vetor[], int x) {
	int len = vetor.length;
	for(int i = 0; i < len; i = i + 1) {
	  if(vetor[i] == x) return true;
	}

	return false;
    }

    public static boolean exemplo01(int vetor[]) {
	int len = vetor.length; //O(1)
	for(int i = 0; i < len; i++) { //O(N)
	    for(int j = 0; j < len; j++) { // O(N)
		if(i != j && vetor[i] == vetor[j]) { //O(1)
		    return true;
		}
	    }
  
	}

	return false;
    }



    public static void main(String[] args) {
        int vetor[]; // inicializar vetor
	vetor = new int [10]; //alocar vetor
	boolean resp = false;

	for(int i = 0; i < 10; i = i + 1) {
	  vetor[i] = i;
	}

	resp = exemplo01(vetor, 9);

	if(resp) {
	  System.out.print("Encontrado\n");
	} else  {
	  System.out.printf("Nao encontrado\n");
	}
    }
}

