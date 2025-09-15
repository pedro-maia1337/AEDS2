/*
Crie um método recursivo que recebe um número inteiro como parâmetro e retorna a soma de seus dígitos. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com o resultado da soma dos dígitos. Por exemplo, se a entrada for 12345, a saída deve ser 15
*/

import java.util.*;

class TP03Q03 {

    public static int somaRecursiva(int num, int dig, int soma){
        if(num <= 0){ //Caso o número atinja zero retorna a soma(condição de parada)
            return soma; 
        } else {
            dig = num % 10; //Separa os digitos pegando o módulo da divisão por 10
            soma = soma + dig; //Realiza a soma dos dígitos
        }

        return somaRecursiva(num / 10, dig, soma); //Chama a função novamente repassando o número inicial divido por 10 para remover os digitos que já foram operados
    } 

    public static boolean compare(String str1, String str2){ 
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
        Scanner sc = new Scanner(System.in);
        int num = 0;
        int soma = 0;

        while(sc.hasNext()){
            str = sc.nextLine();

            if(compare(str, "FIM")){
                sc.close();
                return;
            }

            num = Integer.parseInt(str); //Tranforma string para inteiro
            soma = somaRecursiva(num, 0, 0); //Chama o metódo recursivo

            System.out.println(soma);    
        }

        sc.close();   
    }
}