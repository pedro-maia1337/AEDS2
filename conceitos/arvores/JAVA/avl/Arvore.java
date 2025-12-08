import java.util.*;

class Arvore {
    private No raiz;
    
    public Arvore() {
        raiz = null;
    }
    
    public void inserir(int elemento) {
        raiz = inserir(elemento, raiz);
    }
    
    private No inserir(int elemento, No raiz) {
        if(raiz == null) {
            raiz = new No(elemento);
        } else if(elemento < raiz.elemento) {
            raiz.esq = inserir(elemento, raiz.esq);
        } else if(elemento > raiz.elemento) {
            raiz.dir = inserir(elemento, raiz.dir);
        } else {
            System.out.println("Erro ao inserir.");
        }
        
        raiz.setNivel();
       
        return raiz;
    }
    
    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.print("]");
    }
    
    private void caminharCentral(No raiz){
        if(raiz != null) {
            caminharCentral(raiz.esq);
            System.out.print(raiz.elemento + "(" + raiz.nivel + ")" + " ");
            caminharCentral(raiz.dir);
        }      
    }
    
    //implementar a lógica do balanceamento (aquela tabela)
    
    private No rotacionarDir(No no) {
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;
    
		noEsq.dir = no;
		no.esq = noEsqDir;
		no.setNivel(); 
		noEsq.setNivel();
    
		return noEsq;
	}
    
	private No rotacionarEsq(No no) {
		No noDir = no.dir;
		No noDirEsq = noDir.esq;
    
		noDir.esq = no;
		no.dir = noDirEsq;
    
		no.setNivel(); 
		noDir.setNivel(); 
		return noDir;
	}
    
}