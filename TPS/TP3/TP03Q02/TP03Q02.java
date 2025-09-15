/*
Crie um método recursivo que recebe uma string como parâmetro e retorna a string invertida. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com a string invertida. Por exemplo, se a entrada for abcde, a saída deve ser edcba.
*/

import java.util.*;

class TP03Q02 {

    public static String reverseString(String str, int len) {
        if(len <= 0) { //Caso o tamanho da string atinja zero retorna "vazio"
            return "";
        }
        return str.charAt(len - 1) + reverseString(str, len - 1); //Concatena a string de forma invertida e faz a rechamada da função diminuindo o tamanho
    }

    public static boolean compare(String str1, String str2){ //Metódo para comparar string 
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
    public static void main(String[] args) {
        String str = new String();
        String strReverse = new String(); //Armazenar string invertida 
        int len = 0;
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            str = sc.nextLine();

            if(compare(str, "FIM")) {
                sc.close();
                return;
            }

            len = str.length();
            strReverse = reverseString(str, len); //Chamada da função recursiva
            System.out.println(strReverse);
        }

        sc.close();
    }
}
