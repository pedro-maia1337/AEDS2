class LP {
    CelulaLista primeiro;
    CelulaLista ultimo;

    //Nó cabeça
    LP() {
        primeiro = new CelulaLista();
        ultimo = primeiro;
    }
    
    //Inserção de uma lista 
    public void inserirLista() {
        //Lista
        CelulaLista temp = new CelulaLista();
        temp.prox = primeiro.prox;
        primeiro.prox = temp;
        if(primeiro == ultimo) {
            ultimo = temp;
        }
        temp = null;
    }

    /*
     * @param pilha - valor int indicando em qual lista será inserida
     * @param x - valor int que será inserido na pilha
     */
    public void inserirPilha(int pilha, int x){
        CelulaLista i = primeiro;
        CelulaPilha temp = new CelulaPilha(x);
        for(int j = 0; j < pilha; j++, i = i.prox); //faz chegar na lista em questão
        temp.prox = i.topo;
        i.topo = temp;
        i = null;
        temp = null;
    }

    public void mostrar(){
        CelulaLista i = primeiro;
        CelulaPilha j;
        for(i = primeiro.prox; i != null; i = i.prox){
            System.out.print("[ ");
            for(j = i.topo; j != null; j = j.prox){
                System.out.print(j.elemento + " ");
            }
            System.out.print(" ]");
            System.out.println("");
        }

        i = null;
        j = null;
    }

    public CelulaPilha maiorPilha(){
        CelulaLista i = primeiro;
        CelulaPilha j;
        CelulaPilha celula = primeiro.topo;
        int maior = 0;
        for(i = primeiro.prox; i != null; i = i.prox){
            int count = 0;
            for(j = i.topo; j != null; j = j.prox){
                count = count + 1;
            }
            if(count > maior) {
                celula = i.topo;
                maior++;
            }
        }

        i = null;
        j = null;

        return celula;
    }

    public void mostrarMaiorPilha() {
        CelulaPilha j;
        System.out.print("[ ");
        for(j = maiorPilha(); j != null; j = j.prox){
            System.out.print(j.elemento + " ");
        }
        System.out.print(" ]");
        System.out.println("");
        
        j = null;
    }

}
