class Lista {
    Celula primeiro, ultimo;

    Lista(){
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(int x) {
        Celula temp = new Celula(x);
        temp.prox = primeiro.prox;
        primeiro.prox = temp;
        //validar ao inserir
        if(primeiro == ultimo) {
            ultimo = temp;
        }
        temp = null;
    }

    public void inserirFim(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int removerInicio() {
        int elemento = 0;
        if(primeiro == ultimo) {
        } else {
            Celula i = primeiro.prox;
            elemento = i.elemento;
            primeiro.prox = i.prox; 
            i = null;
        }

        return elemento;
    }

    public int removerFim() {
        int elemento = 0;
        if(primeiro == ultimo) {
            System.out.println("Erro: Lista vazia");
        } else {
            Celula i;
            for(i = primeiro; i.prox != ultimo; i = i.prox); //atendar no i.prox != ultimo
            elemento = ultimo.elemento;
            ultimo = i;
            i = i.prox = null;
        }

        return elemento;
    }

    public void inserir(int x, int pos) {
        int tam = getTamanho();
        if(pos < 0 || pos > tam) {
            System.out.print("Erro: posição invalida");
        } else if(pos == 0) {
            inserirInicio(x);
        } else if(pos == tam) {
            inserirFim(x);
        } else {
            Celula temp = new Celula(x);
            int j = 0;
            Celula i;
            for(i = primeiro; j < pos; j++, i = i.prox);
            temp.prox = i.prox;
            i.prox = temp;
            temp = i = null;
        }
    }

    public int remover(int pos){
        int tam = getTamanho();
        int elemento = 0;
        if(pos < 0 || pos >= tam || primeiro == ultimo) {
            System.out.print("Erro ao remover");
        } else if(pos == 0) {
            elemento = removerInicio();
        } else if(pos == tam - 1) {
            elemento = removerFim();
        } else {
            int j = 0;
            Celula i;
            for(i = primeiro; j < pos; j++, i = i.prox);
            Celula temp = i.prox;
            elemento = temp.elemento;
            i.prox = temp.prox;
            temp.prox = null;
            i = temp = null;
        }

        return elemento;
    }

    public void mostrar() {
        System.out.print("[ ");
        for(Celula i = primeiro.prox; i != null; i = i.prox){ 
            System.out.print(i.elemento + " ");
        }
        System.out.print("]");
    }

    public int getTamanho(){
        int len = 0;
        for(Celula i = primeiro.prox; i != null; len++, i = i.prox);
        return len;
    }

    //meiose
    //percorrer / em cada iteração -> adiciona uma nova celula, divide o elemento, insere o elemento nas celulas, insere a nova celula 

    //cuidado na inserção de outros nós 
    public void meiose() { // O(n)
        Celula i = primeiro;

        while(i.prox != null) {
            int val = i.prox.elemento / 2;
            Celula temp = new Celula(val);
            temp.prox = i.prox.prox;
            i.prox.prox = temp;

            if(i.prox == ultimo) {
                ultimo = temp;
            }

            i = temp; 
        }
    }

   public void inverter() {
        if (primeiro.prox == null) return; // Lista vazia
        
        Celula anterior = null;
        Celula atual = primeiro.prox;
        Celula proximo = null;
        
        ultimo = atual; // O primeiro se torna o último
        
        while (atual != null) {
            proximo = atual.prox;   // Guarda próximo
            atual.prox = anterior;  // Inverte ponteiro
            anterior = atual;       // Avança anterior
            atual = proximo;        // Avança atual
        }
        
        primeiro.prox = anterior;   // Novo primeiro elemento
    }
    
}
