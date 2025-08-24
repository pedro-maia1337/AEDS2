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

        if(reverse_string.equals(str)){
            flag = true;
        }    

        return flag; 
    }

    public static void main(String[] args){
        String str = "";
        boolean test = false;
        Scanner sc = new Scanner(System.in); //Ler da entrada padrão (Teclado)

        while(sc.hasNext()) { // Ler em LOOP
            str = sc.nextLine(); // Solicita nova entrada

            if(str.equals("FIM")) { //Caso leia "FIM" para o LOOP
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