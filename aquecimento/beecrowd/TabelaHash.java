import java.util.*;

//Implementar a lista

class Celula {
    public Celula prox;
    public int elemento;

    Celula(int x) {
        prox = null;
        elemento = x;
    }
}

class Lista {
    private Celula primeiro;
    private Celula ultimo;

    Lista() {
        primeiro = new Celula(-1);
        ultimo = primeiro;
    }

    public void inserirFim(int elemento) {
		Celula tmp = new Celula(elemento);
		ultimo.prox = tmp;
		ultimo = ultimo.prox;
        tmp = null;
	}

    void mostrar() {
        Celula i = new Celula(-1);
        for(i = primeiro.prox; i != null; i = i.prox) {

            System.out.print("-> "+ i.elemento + " ");
        }
        System.out.print("-> /");
        System.out.println();
    }
}

class Hash {
    int len;
    Lista[] tabela;
    final int NULO = -1;


    public Hash(int len) {
        this.len = len;
        tabela = new Lista[len];
        for(int i = 0; i < len; i = i + 1) {
            tabela[i] = new Lista();
        }
    }

    int h(int x) {
        return x % len; //TEM QUE SER O TAMANHO DA TABELA
    }

    public void inserirFim(int elemento) {
        int pos = h(elemento);
        tabela[pos].inserirFim(elemento);
    }

    public void mostrar() {
        for(int i = 0; i < len; i = i + 1) {
            System.out.print(i + " ");
            tabela[i].mostrar();
        }
    }
}

class TabelaHash {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;

        n = Integer.parseInt(sc.nextLine().trim());

        for(int k = 0; k < n; k = k + 1) {
            int lenTable = 0;
            int lenElements = 0;
            String arrLen[] = sc.nextLine().split(" ");
            lenTable = Integer.parseInt(arrLen[0]);
            lenElements = Integer.parseInt(arrLen[1]);
            String elementos[] = sc.nextLine().split(" ");

            Hash hashTable = new Hash(lenTable);

            for(int i = 0; i < lenElements; i = i + 1) {
                int element = Integer.parseInt(elementos[i]);
                hashTable.inserirFim(element);
            } 
                
            hashTable.mostrar();

            System.out.println();
        }    
        
        sc.close();
    }
}