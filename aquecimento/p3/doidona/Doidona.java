class Doidona {
    
    final int TAMT1 = 100;
    final int TAMT2 = 100;
    final int NULO = -0x7FFFFF;
    
    int[] t1;
    int[] t2;
    
    Celula primeiroListaT3, ultimoListaT3;
    No raizArvoreT2, raizArvoreT3;
    
    public Doidona (){
         t1 = new int[TAMT1];
         t2 = new int[TAMT2];
   
         for(int i = 0; i < TAMT1; i++){
            t1[i] = NULO;
         }
         for(int i = 0; i < TAMT2; i++){
            t2[i] = NULO;
         }
   
         primeiroListaT3 = ultimoListaT3 = new Celula();
   
         raizArvoreT2 = raizArvoreT3 = null;
    }
      
    public int hashT1(int elemento){
        int h = elemento % 13;
        return h;
    }
     
    public int hashT2(int elemento){
        int h = elemento % 13;
        return h;
    }
    
    public int rehashT2(int elemento){
        int h = elemento % 13;
        return h;
    }
     
    public int hashT3(int elemento){
        int h = elemento % 13;
        return h;
    }
     
    public int rehashT3(int elemento){
        int h = elemento % 13;
        return h;
    }
    
    
    public void inserir(int elemento) {
        int i = hashT1(elemento);
        if(t1[i] == NULO) {
            t1[i] = elemento;   
        } else {
            i = hashT2(elemento);
            if(t2[i] == NULO) {
                t2[i] = elemento;
            } else {
                i = rehashT2(elemento);
                if(t2[i] == NULO) {
                  t2[i] = elemento;  
                } else if(hashT3(elemento) == 0) {
                    raizArvoreT3 = inserirArvore(elemento, raizArvoreT3);
                } else if(hashT3(elemento) == 1) {
                    inserirLista(elemento);
                }
            }
        }
    }
    
    public No inserirArvore(int elemento, No raiz) {
        if(raiz == null) {
            raiz = new No(elemento);
        } else if(raiz.elemento < elemento) {
            raiz.esq = inserirArvore(elemento, raiz.esq);
        } else if(raiz.elemento > elemento) {
            raiz.dir = inserirArvore(elemento, raiz.dir);
        } else {
            System.out.println("ERRO");
        }
        return raiz;
    }
    
    public void inserirLista(int elemento) {
        ultimoListaT3.prox = new Celula(elemento);
        ultimoListaT3 = ultimoListaT3.prox;
    }
    
    public boolean pesquisar(int elemento) {
        int i = hashT1(elemento);
        boolean resp = false;
        if(t1[i] == NULO) {
            resp = false;
        } else if(t1[i] == elemento) {
            resp = true;
        } else {
            i = hashT2(elemento);
            if(t2[i] == NULO) {
                resp = false;
            } else if(t2[i] == elemento) {
                resp = true;
            } else if(hashT3(elemento) == 0) {
                resp = pesquisarArvore(elemento, raizArvoreT3);
            } else if(hashT3(elemento) == 1) {
                resp = pesquisarLista(elemento);
            }
        }
        
        return resp;
    }
    
    public boolean pesquisarArvore(int elemento, No raiz) {
        boolean resp = false;
        if(raiz == null) {
            resp = false;
        } else if(raiz.elemento < elemento) {
            resp = pesquisarArvore(elemento, raiz.esq);
        } else  {
            resp = pesquisarArvore(elemento, raiz.dir);
        }
        return resp;
    }
    
    public boolean pesquisarLista(int elemento) {
        boolean resp = false;
        for(Celula i = primeiroListaT3.prox; i != null; i = i.prox) {
            if(i.elemento == elemento) {
                resp = true;
                i = ultimoListaT3;
            }
        }
        
        return resp;
    }
    
}