import java.util.Random;

class Principal  {

    //Metódo estático que recebe duas árvores e retorna se são iguais 
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
        Random gerador = new Random();
        Arvore arvoreBin = new Arvore();
       
        for(int i = 1; i < 5; i++) {
            int random = gerador.nextInt(1000);
            arvoreBin.inserir(random);
            double log = (Math.log(random) / Math.log(2));
            System.out.println("Números de elementos = " + i + " log = " + Math.floor(log) + " altura = " + arvoreBin.getAltura());
        }


    }
}
