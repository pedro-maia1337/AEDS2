class Principal  {
    public static void main(String[] args) {
        Random gerador = new Random();
        Arvore arvoreBin = new Arvore();
       
        for(int i = 1; i < 30; i++) {
            arvoreBin.inserir(i);
        }


    }
}
