class Arvore {
    private No raiz;
    
    public Arvore() {
        raiz = null;
    }

    public void inserir(int x) {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) {
        if(i == null) {
            i = new No(x);
        } else if(x < i.elemento){
            i.esq = inserir(x, i.esq);
        } else if(x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            System.out.println("Erro ao inserir");
        }

        return i;
    }

    public void inserirPai(int x) {
        if(raiz == null){
            raiz = new No(x);
        } else if(x < raiz.elemento){
		    inserirPai(x, raiz.esq, raiz);
        } else if(x > raiz.elemento){
		   inserirPai(x, raiz.dir, raiz);
        } else {
            System.out.println("Erro");
        }
	}

    private void inserirPai(int x, No i, No pai) {
		if (i == null) {
            if(x < pai.elemento){
                pai.esq = new No(x);
            } else {
                pai.dir = new No(x);
            }
        } else if (x < i.elemento) {
            inserirPai(x, i.esq, i);
        } else if (x > i.elemento) {
            inserirPai(x, i.dir, i);
        } else {
            System.out.println("Erro");
        }
	}

    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(int x, No i) {
        boolean resp = false;
        if(i == null) {
            resp = false;
        } else if(x == i.elemento) {
            resp = true;
        } else if(x < i.elemento) {
            resp = pesquisar(x, i.esq);
        } else {
            resp = pesquisar(x, i.dir);
        }
        
        return resp;
    }

    public void caminharCentral() {
        System.out.println("[");
        caminharCentral(raiz);
        System.out.println("]");
        
    }

    private void caminharCentral(No i) {
        if(i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento + " ");
            caminharCentral(i.dir);
        } 
    }

    public void caminharPre() {
        System.out.println("[");
        caminharPre(raiz);
        System.out.println("]");
    }

    private void caminharPre(No i) {
        if(i != null) {
            System.out.println(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
        
    }

    public void caminharPos(){
        System.out.println("[");
        caminharPos(raiz);
        System.out.println("]");
    }

    
    private void caminharPos(No i) {
        if(i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.println(i.elemento + " ");
        }
    }

    void remover(int x) {

    }
}
