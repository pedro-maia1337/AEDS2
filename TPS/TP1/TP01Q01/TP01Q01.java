/*Crie um método iterativo que recebe uma string como parâmetro e retorna true se essa é um ``Palíndromo''. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada pode ter caracteres não letras.
*/

//javac TP01Q01.java
//java TP01Q01 < pub.in > saida.out
//diff pub.out saida.out

import java.util.*;

class TP01Q01 {

    public static boolean is_palindromo(String str) {
        boolean flag = false;
        String reverse_string = "";

        //Inverte a string recebida por parametro e salva a string invertida em outra variavel
        for(int i = str.length() - 1; i >= 0; i = i - 1) {     
            reverse_string = reverse_string + str.charAt(i);
        }

        if(compare(reverse_string, str)){ //Utilizando método de comparação para verificar se a string inversa é igual a original 
            flag = true;
        }    

        return flag; 
    }

    public static boolean compare(String str1, String str2){ //Metódo criado para comparar duas strings, substituindo o metódo equals
        if(str1.length() != str2.length()) {
            return false;
        }

        for(int i = 0; i < str1.length() - 1; i = i + 1){
            if(str1.charAt(i) != str2.charAt(i)){ //Casp encontre diferença retorna false;
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        String str = "";
        boolean test = false;
        Scanner sc = new Scanner(System.in); //Ler da entrada padrão (Teclado)

        while(sc.hasNext()) { // Ler em LOOP
            str = sc.nextLine(); // Solicita nova entrada

            if(compare(str, "FIM")) { //Caso leia "FIM" para o LOOP
                sc.close();
                return;
            }

            test = is_palindromo(str); //Chama função para verificação da string 

            if(test) { // Testa
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }

        sc.close();
    }
}