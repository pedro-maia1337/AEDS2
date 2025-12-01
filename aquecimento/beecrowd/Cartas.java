import java.util.*;

class Celula {
    Celula prox;
    int elemento;

    Celula(){
        this(0);
    }

    Celula(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class Pilha {
    Celula topo;

    Pilha(){
        topo = null;
    }

    public void inserir(int x){
        Celula temp = new Celula(x);
        temp.prox = topo;
        topo = temp;
        temp = null;
    }

    public int remover() {
        int resp = 0;
        if(topo == null) {
            
        } else {
            //Remove
            Celula temp = topo;
            resp = topo.elemento;
            topo = topo.prox;
            temp.prox = null;
            temp = null;


        }

        return resp;

    }

    public void op() {
        int resp = remover();
        Celula i;
        int index = getLen();
        int j = 0;
        for(i = topo; j < index - 1; j++, i = i.prox);
        i.prox = new Celula(resp);
    }

    public int getLen() {
        int len = 0;
        Celula i = new Celula(-1);
        for(i = topo; i != null; len++, i = i.prox);
        return len;
    }

    boolean hasTwo() {
        if(getLen() < 2) return false;
        return true;
    }


    void mostrar() {
        Celula i = new Celula(-1);
        for(i = topo; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
    }

}
    
public class Cartas {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int card = 0;
       boolean flag = false;

        while(sc.hasNext() && !flag) {

            card = Integer.parseInt(sc.nextLine().trim());

            if(card == 0) {
                flag = true;
            } else {
                Pilha pilha = new Pilha();

                int removidos[] = new int[card-1];
                int index = 0;

                for(int i = card; i >= 1; i = i - 1) {
                    pilha.inserir(i);
                }

                while(pilha.getLen() > 2) {
                    removidos[index] = pilha.remover();
                    pilha.op();
                    index = index + 1;
                }

                removidos[card-2] = pilha.remover();

                System.out.print("Discarded cards: ");
                for(int i = 0; i < removidos.length - 1; i = i + 1) {
                    System.out.print(removidos[i] + "," + " ");
                }

                System.out.print(removidos[card-2]);

                System.out.println();

                System.out.print("Remaining card: ");
                pilha.mostrar();

                System.out.println();
            }            
        }
      
       sc.close();
    }
}
