class Principal  {

    public static boolean equals(No i, No j) {
        boolean resp = false;

        if(i != null && j != null) {
            resp = (i.elemento == j.elemento) && equals(i.esq, j.esq) && equals(i.dir, j.dir);
        } else if(i == null && j == null) {
            resp = true;
        } else {
            resp = false;
        }
        
        return resp;
    }

    public static void main(String[] args) {
        Arvore arvoreBin1 = new Arvore();
        Arvore arvoreBin2 = new Arvore();

        for(int i = 1; i < 30; i++) {
            arvoreBin1.inserir(i);
        }

        for(int i = 1; i < 10; i+=2){
            arvoreBin2.inserir(i);
        }

        if(equals(arvoreBin1.raiz, arvoreBin2.raiz)) {
            System.out.println("Árvores iguais");
        } else {
            System.out.println("Árvores diferentes");
        }

        if(arvoreBin1.div11()) {
            System.out.println("possui um numero divisivel por 11");
        } else {
            System.out.println("nao possui um numero divisivel por 11");
        }
    }
}
