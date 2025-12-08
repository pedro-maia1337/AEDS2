import java.util.*;

class Principal {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        arvore.inserir(5);
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(25);
        arvore.inserir(30);
        arvore.inserir(40);
        arvore.inserir(50);
        
        arvore.caminharCentral();
    }
}