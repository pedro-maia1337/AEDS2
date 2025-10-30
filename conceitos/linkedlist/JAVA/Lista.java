class Lista {
    private Celula primeiro;                    // ref "primeiro" que irá apontar para o primeiro elemento
    private Celula ultimo;                      // ref "ultimo" que irá apontar para o ultimo elemento

    public Lista() {
        primeiro = ultimo = new Celula();       // Criação da célula 
    }

    public void inserirFim(int x) {
        ultimo.prox = new Celula(x);         // cria nova celula 
        ultimo = ultimo.prox;               //  faz a ref ultimo apontar para celula criada
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);       // cria nova celula 
        tmp.prox = primeiro.prox;        //  faz a celula tmp apontar para prox celula
        primeiro.prox = tmp;            //   faz o nó cabeça apontar para tmp
        if(primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public int removerFim() {
        if(primeiro == ultimo) {        //Verifica se a lista está vazia
            System.out.println("Erro: Lista vazia");
            return 0;
        }
        Celula i = new Celula();        //Cria uma celula tmp
        for(i = primeiro.prox; i.prox != ultimo; i = i.prox); //Percorre até o penultimo elemento
        int elemento = ultimo.elemento; //Atribui elemento da ultima celula 
        ultimo = i; // faz ref ultimo apontar para o penultimo
        i = ultimo.prox = null; // elimina o ultimo elemento e a celula tmp 

        return elemento; //retorna elemento removido
    }

    public int removerInicio() {
        if(primeiro == ultimo) { //Verifica se a lista está vazia
            System.out.println("Erro: Lista vazia");
            return 0;
        }
        Celula tmp = primeiro; //Cria celula temporaria que já aponta pra primeira
        primeiro = primeiro.prox; // faz ref primeira apontar para proxima celula
        int elemento = primeiro.elemento; // Atribui elemento
        tmp.prox = null; //Remove ligação do primeiro elemento com o resto da lista
        tmp = null; // elimina primeira celula 

        return elemento;
    }

    public void inserir(int x, int pos) {
        int tamanho = tamanho();
        if(pos == 0) inserirInicio(x);
        if(pos == tamanho) inserirFim(x);
        if(pos < 0 || pos > tamanho) {
            System.out.println("Erro ao inserir");
        } else {
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula();
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public int remover(int x, int pos) {
        int tamanho = tamanho();
        int elemento = 0;
        if(pos == 0) removerInicio(x);
        if(pos == tamanho - 1) removerFim(x);
        if(primeiro == ultimo || pos < 0 || pos >= tamanho) {
            System.out.println("Erro ao inserir");
        } else {
            Celula i = primeiro;
            for(int j = 0; j < tamanho; j++, i = i.prox);
            Celula tmp = i.prox;
            elemento = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

        return elemento;
    }

    public void mostrar() {
		System.out.print("[ ");
		
		for(Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		
		System.out.println("] ");
	}

    public int tamanho() {
        int len = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
        return len;
    }
}

