class ListaDupla {
    Celula primeiro, ultimo;

    ListaDupla() {
        primeiro =  new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo) 
            ultimo = tmp;
        else 
            tmp.prox.ant = tmp;
        
        tmp = null;
    }

    public void inserirFim(int x) {
       ultimo.prox = new Celula(x);
       ultimo.prox.ant = ultimo;
       ultimo = ultimo.prox;
    }

    public int removerInicio() {
        int elemento = 0;
        if(primeiro == ultimo){
            System.out.println("Erro ao inserir: lista vazia");
        } else {
            Celula tmp = primeiro.prox;
            elemento = tmp.elemento;
            primeiro.prox = tmp.prox;
            tmp.prox = tmp.ant = null;
            tmp = null;
        }
        return elemento;
    }

    public int removerFim() {
        int elemento = 0;
        if(primeiro == ultimo) {
            System.out.println("Erro ao inserir: lista vazia");
        } else {
            elemento = ultimo.elemento;
            ultimo = ultimo.ant;
            ultimo.prox.ant = null;
            ultimo.prox = null;
        }
        return elemento;
    }

    public void inserir(int pos, int x) {
        int tamanho = tamanho();
        if(pos < 0 || pos > tamanho)
            System.out.println("Erro ao inserir.");
        else if(pos == 0) 
            inserirInicio(x);
        else if(pos == tamanho)
            inserirFim(x);
        else {
            Celula tmp = new Celula(x);
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    public int remover(int pos) {
        int tamanho = tamanho();
        int elemento = 0;
        if(primeiro == ultimo) 
            System.out.println("Erro: lista vazia");
        else if(pos < 0 || pos >= tamanho) 
            System.out.println("Erro");
        else if(pos == 0) 
            elemento = removerInicio();
        else if(pos == tamanho - 1)
            elemento = removerFim();
        else {
            Celula i = primeiro.prox;
            for(int j = 0; j < pos; j++, i = i.prox);
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            elemento = i.elemento;
            i.prox = i.ant = null;
            i = null;
        }

        return elemento;
    }

    public void mostrar() {
		System.out.print("[ "); 
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); 
	}

    public int tamanho() {
        int len = 0;
		for (Celula i = primeiro.prox; i != null; len++, i = i.prox);
		return len;
	}

    public void inverte(){
        Celula i = primeiro.prox; Celula j = ultimo;
        while (i != j && j.prox != i){
            int tmp = i.elemento;
            i.elemento = j.elemento;
            j.elemento = tmp;
            i = i.prox;
            j = j.ant;
        }
    }
}
