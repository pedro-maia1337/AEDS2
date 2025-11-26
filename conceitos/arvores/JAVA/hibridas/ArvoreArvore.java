class ArvoreArvore {
    No raiz;

    ArvoreArvore() {
        raiz = null;
		inserir('M');
		inserir('T');
		inserir('F');
		inserir('A');
		inserir('B');
		inserir('C');
		inserir('D');
		inserir('E');
		inserir('G');
		inserir('H');
		inserir('I');
		inserir('J');
		inserir('K');
		inserir('L');
		inserir('N');
		inserir('O');
		inserir('P');
		inserir('Q');
		inserir('R');
		inserir('S');
		inserir('U');
		inserir('V');
		inserir('W');
		inserir('X');
		inserir('Y');
		inserir('Z');
    }

    public void inserirPalavras() {
        // Palavras para cada letra do alfabeto
        inserir("Amor");
        inserir("Abacaxi");
        inserir("Arvore");
        
        inserir("Bola");
        inserir("Banana");
        inserir("Bicicleta");
        
        inserir("Casa");
        inserir("Carro");
        inserir("Computador");
        
        inserir("Dado");
        inserir("Dedo");
        inserir("Dama");
        inserir("Diamante");
        inserir("Dinossauro");
        
        inserir("Elefante");
        inserir("Escola");
        inserir("Estrela");
        
        inserir("Faculdade");
        inserir("Futebol");
        inserir("Familia");
        
        inserir("Gato");
        inserir("Guitarra");
        inserir("Google");
        
        inserir("Hotel");
        inserir("Humor");
        inserir("Historia");
        
        inserir("Igreja");
        inserir("Internet");
        inserir("Ilha");
        
        inserir("Janela");
        inserir("Jogo");
        inserir("Jardim");
        
        inserir("Kiwi");
        inserir("Koala");
        inserir("Kilograma");
        
        inserir("Lua");
        inserir("Livro");
        inserir("Laranja");
        
        inserir("Macaco");
        inserir("Mesa");
        inserir("Montanha");
        
        inserir("Navio");
        inserir("Noite");
        inserir("Numero");
        
        inserir("Ovo");
        inserir("Ouro");
        inserir("Oceano");
        
        inserir("Pato");
        inserir("Praia");
        inserir("Professor");
        
        inserir("Queijo");
        inserir("Quadrado");
        inserir("Quimica");
        
        inserir("Rato");
        inserir("Rio");
        inserir("Relogio");
        
        inserir("Sol");
        inserir("Sapato");
        inserir("Sorvete");
        
        inserir("Tigre");
        inserir("Tempo");
        inserir("Tenis");
        
        inserir("Uva");
        inserir("Universo");
        inserir("Urso");
        
        inserir("Vaca");
        inserir("Vela");
        inserir("Vitoria");
        
        inserir("Windows");
        inserir("Waffle");
        inserir("Waterpolo");
        
        inserir("Xadrez");
        inserir("Xerox");
        inserir("Xilofone");
        
        inserir("Yoga");
        inserir("Youtube");
        inserir("Yakisoba");
        
        inserir("Zebra");
        inserir("Zangado");
        inserir("Zoo");
    }


    // Inserir letra primeira árvore
    public void inserir(char elemento) {
        raiz = inserir(raiz, elemento);
    }

    private No inserir(No i, char x) {
        if(i == null) {
            i = new No(x);
        } else if(x < i.elemento) {
            i.esq = inserir(i.esq, x);
        } else if(x > i.elemento) {
            i.dir = inserir(i.dir, x);
        } else {
            System.out.println("Erro ao inserir");
        }
        return i;
    }

    public void inserir(String s) {
        inserir(raiz, s);
    }

    // Inserir palavra na segunda árvore
    public void inserir(No i, String s) {
        if(i == null) {
            System.out.println("Erro ao inserir: " + s);
        } else if (s.charAt(0) < i.elemento) {
            inserir(i.esq, s);
        } else if(s.charAt(0) > i.elemento) {
            inserir(i.dir, s);
        } else {
            i.outro = inserir(s, i.outro);
        }
    }

    // Inserir palavra propriamente dita 
    private No2 inserir(String s, No2 i) {
        if (i == null) {
            i = new No2(s);
        } else if (s.compareTo(i.elemento) < 0) {
            i.esq = inserir(s, i.esq);
        } else if (s.compareTo(i.elemento) > 0) {
            i.dir = inserir(s, i.dir);
        } else {
            System.out.println("Erro ao inserir palavra duplicada: " + s);
        }
        return i;
    }

    public void mostrar(){
        mostrar(raiz);
   }

    public void mostrar(No i){
        if (i != null){
            mostrar(i.esq);
            System.out.println("Letra: " + i.elemento);
            mostrar(i.dir);
        }
    }

    public void mostrar(No2 i){
        if (i != null){
            mostrar(i.esq);
            System.out.println(i.elemento);
            mostrar(i.dir);
        }
   }

   public void mostrarPalavrasPorLetra(char letra) {
        mostrarPalavrasPorLetra(raiz, letra);
    }

    private void mostrarPalavrasPorLetra(No i, char letra) {
        if (i != null) {
            if (letra < i.elemento) {
                mostrarPalavrasPorLetra(i.esq, letra);
            } else if (letra > i.elemento) {
                mostrarPalavrasPorLetra(i.dir, letra);
            } else {
                System.out.println("Palavras com a letra " + letra + ":");
                if (i.outro != null) {
                    mostrar(i.outro);
                } else {
                    System.out.println("  Nenhuma palavra encontrada");
                }
            }
        }
    }

    public int contarPalavras(String palavra) {
        return contarPalavras(raiz, palavra);
    }

    public int contarPalavras(No i, String palavra) {
        int res = 0;
        if(i != null) {
            if(i.elemento == palavra.charAt(0)) {
                res = contarOcorrenciais(i.outro, palavra.length());
            } else if (i.elemento < palavra.charAt(0)){
                res = contarPalavras(i.esq, palavra);
            } else if (i.elemento > palavra.charAt(0)){
                res = contarPalavras(i.dir, palavra);
            } else {
                System.out.println("Erro");
            }
        }

        return res;
    }

    private int contarOcorrenciais(No2 i, int tamanho) {
        int count = 0;
        if(i != null) {
            if(i.elemento.length() == tamanho) count ++;
            count += contarOcorrenciais(i.esq, tamanho) + contarOcorrenciais(i.dir, tamanho);   
        }

        return count;
    }

}