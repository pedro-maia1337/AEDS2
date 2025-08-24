/*
O Imperador Júlio César foi um dos principais nomes do Império Romano. Entre suas contribuições, temos um algoritmo de criptografia chamado Ciframento de César. Segundo os historiadores, César utilizava esse algoritmo para criptografar as mensagens que enviava aos seus generais durante as batalhas. A ideia básica é um simples deslocamento de caracteres.


Assim, por exemplo, se a chave utilizada para criptografar as mensagens for 3, todas as ocorrências do caractere 'a' são substituídas pelo caractere 'd', as do 'b' por 'e', e assim sucessivamente.


Crie um método iterativo que recebe uma string como parâmetro e retorna outra contendo a entrada de forma cifrada. Neste exercício, suponha a chave de ciframento três. Na saída padrão, para cada linha de entrada, escreva uma linha com a mensagem criptografada.

*/

//javac TP01Q03.java
//java TP01Q03 < pub.in > saida.out
//diff pub.out saida.out

import java.util.*;

class TP01Q03 {

    public static String criptografia(String str) {
        String strCrip = new String();
        int len = str.length();
        int numChar = 0;
        char aux = ' ';

        //Percorre a string, realiza o casting, soma + 3, obtém o novo char a partir do inteiro baseado na tabela ascii.
        //Optei pelo casting pela compatibilidade com C

        for(int i = 0; i < len; i = i + 1) {
            char tmp = str.charAt(i);

            if(tmp > 31 && tmp < 123){ // Garante que somente caracteres da tabela ASCII serão alterados
                numChar = ((int) tmp) + 3; // Casting de char para inteiro e somando + 3 
                aux = (char) numChar; //Casting de inteiro para char obtendo novo char
            } else {
                aux = tmp; 
            }

            strCrip = strCrip + aux; //Concatenando os caracteres e formando a string
        }

        return strCrip;
    }

    public static void main(String[] args) {
        String str = new String();
        Scanner sc = new Scanner(System.in); //Ler da entrada padrão (Teclado)
        String strCrip = new String();

        while(sc.hasNext()) { // Ler em LOOP
            str = sc.nextLine(); // Solicita nova entrada

            if(str.equals("FIM")) { //Caso leia "fim" para o LOOP
                sc.close();
                return;
            }

            strCrip = criptografia(str);

            System.out.println(strCrip); //Chama função para criptografia
        }

        sc.close(); 
    }    
}
