public class Ex01 {
    public static void main(String[] args) {
        Pedido[] pedidos = new Pedido[5];
        pedidos[0] = new Pedido(101, 3, 150.00);
        pedidos[1] = new Pedido(102, 1, 75.50);
        pedidos[2] = new Pedido(103, 5, 299.90);
        pedidos[3] = new Pedido(104, 2, 45.00);
        pedidos[4] = new Pedido(105, 4, 120.00);

        HeapSort heap = new HeapSort();

        heap.heapSort(pedidos, 5);

        for(int i = 0; i < 5; i = i + 1){
            System.out.println(pedidos[i].toString());
        }

    }
}

class Pedido {
    public int id;
    public int prioridade;
    public double valor;

    public Pedido(int id, int prioridade, double valor) {
        this.id = id;
        this.prioridade = prioridade;
        this.valor = valor;
    }

    @Override 
    public String toString() {
        return "ID: " + id + ", Prioridade: " + prioridade + ", Valor: " + valor; 
    }
}

class HeapSort {
    public static void swap(Pedido[] arr, int i, int j) {
        Pedido temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void heapSort(Pedido[] arr, int n) {
        Pedido[] temp = new Pedido[n+1];
        
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

    public void construir(Pedido[] arr, int tamHeap) {
        for(int i = tamHeap; i > 1 && arr[i].prioridade > arr[i/2].prioridade; i /= 2) {
            swap(arr, i, i/2);
        } 
    }

    public void reconstruir(Pedido[] arr, int tamHeap) {
        int i = 1;
        while(i <= (tamHeap/2)){
            int filho = getMaiorFilho(arr, i, tamHeap);
            if(arr[i].prioridade < arr[filho].prioridade) {
                swap(arr, i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    public  int getMaiorFilho(Pedido[] arr, int i, int tamHeap){
        int filho = 0;
        if(2*i == tamHeap || arr[2*i].prioridade > arr[2*i+1].prioridade){
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }
}


