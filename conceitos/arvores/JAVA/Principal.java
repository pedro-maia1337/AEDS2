class Principal  {

    public static boolean equals(Arvore tree1, Arvore tree2) {
        
    }
    public static void main(String[] args) {
        Arvore arvoreBin1 = new Arvore();
        Arvore arvoreBin2 = new Arvore();

        for(int i = 1; i < 10; i++) {
            arvoreBin1.inserir(i);
        }

        for(int i = 1; i < 10; i++){
            arvoreBin2.inserir(i);
        }

        equals(arvoreBin1, arvoreBin2);
    }
}
