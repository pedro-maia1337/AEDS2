/* 
Crie um método iterativo que recebe uma string, sorteia duas letras minúsculas aleatórias (código ASCII >= 'a' e <= 'z'), substitui todas as ocorrências da primeira letra na string pela segunda e retorna a string com as alterações efetuadas.

Na saída padrão, para cada linha de entrada, execute o método desenvolvido nesta questão e mostre a string retornada como uma linha de saída.

Abaixo, observamos um exemplo de entrada supondo que para a primeira linha as letras sorteadas foram o a e o q. Para a segunda linha, foram o e e o k.
*/


//javac TP01Q04.java
//java TP01Q04 < pub.in > saida.out
//diff pub.out saida.out


import java.util.*;
import java.util.Random;

class TP01Q04 {

    public static String alt(String str){
        char letra1;
        char letra2;
        int len = str.length();
        char[] newStr = new char[len];
        Random gerador = new Random();
        gerador.setSeed(4);

        letra1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
        letra2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

        for(int i = 0; i < str.length(); i = i + 1) {
            newStr[i] = str.charAt(i);
        }

        for(int i = 0; i < str.length(); i = i + 1) {
            if(newStr[i] == letra1){
                newStr[i] = letra2;   
            }
        }

        return new String(newStr);
    }

    public static boolean compare(String str1, String str2){ //Metódo criado para comparar duas strings, substituindo o metódo equals
        if(str1.length() != str2.length()) {
            return false;
        }

        for(int i = 0; i < str1.length() - 1; i = i + 1){
            if(str1.charAt(i) != str2.charAt(i)){ //Caso encontre diferença retorna false;
                return false;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        String newStr = new String();
        String str = new String();

        while(sc.hasNext()) { // Ler em LOOP
            str = sc.nextLine(); // Solicita nova entrada

            if(compare(str, "FIM")) { //Verifica se as duas strings são iguais, caso sejam para o loop pois encontrou FIM 
                sc.close();
                return;
            }

            newStr = alt(str);

            System.out.println(newStr); 
        }

        sc.close();
    }
}