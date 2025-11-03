public class Lista {
    No primeiro;
    No ultimo;

    Lista() {
        primeiro = new No();
        ultimo = primeiro;
    }

    public void inserir(int x){
        No temp = new No(x);
        ultimo.prox = temp;
        ultimo = temp;
    }

    public void mostrar() {
        System.out.print("[ ");
        for(No i = primeiro.prox; i != null; i = i.prox) System.out.print(i.elemento + " ");
        System.out.print("]");
    }


    public void addTwoNumbers(Lista l1, Lista l2) {
        int nl1 = 0;
        int nl2 = 0;

        int numl1 = 0;
        int numl2 = 0;
        int sum = 0;

        String strl1 = new String();
        String strl2 = new String();

        for(No i = l1.primeiro.prox; i != null; nl1++, i = i.prox);
        for(No i = l2.primeiro.prox; i != null; nl2++, i = i.prox);

        int[] arrl1 = new int[nl1];
        int[] arrl2 = new int[nl2];

        int index1 = 0;
        int index2 = 0;

        for(No i = l1.primeiro.prox; i != null; index1++, i = i.prox) {
            arrl1[index1] = i.elemento;
        }

        for(No i = l2.primeiro.prox; i != null; index2++, i = i.prox) {
            arrl2[index2] = i.elemento;
        }

        for(int i = arrl1.length - 1; i > 0; i = i - 1) strl1 += Integer.toString(arrl1[i]);
        for(int i = arrl2.length - 1; i > 0; i = i - 1) strl2 += Integer.toString(arrl2[i]);
        strl1 += arrl1[0];
        strl2 += arrl2[0];

        sum = Integer.parseInt(strl1) + Integer.parseInt(strl2);
        int numadd = 0;
        for(int i = sum; sum > 0; sum = sum / 10){
            numadd = sum % 10;
            inserir(numadd);
        }
    }
}
