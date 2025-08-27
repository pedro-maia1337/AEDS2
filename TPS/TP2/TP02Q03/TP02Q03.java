/*
Crie um método iterativo que recebe uma string como parâmetro e retorna a string invertida. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com a string invertida. Por exemplo, se a entrada for abcde, a saída deve ser edcba.
*/


//javac TP02Q03.java
//java TP02Q03.java < pub.in > saida.out
//diff saida.out pub.in


import java.util.*;

class TP02Q03 {

    public static String reverse(String str) { //Metódo para inverter string
        String reverse_str = new String(); 

        //Percorre do final ao inicio salvando os caracteres na nova string
        for(int i = str.length() - 1; i >= 0; i = i - 1) { 
            reverse_str += str.charAt(i);
        }

        return reverse_str; //Retorna string invertida
    }

    //Metódo criado para comparar duas strings, substituindo o metódo equals
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
        Scanner sc = new Scanner(System.in);
        String str = new String();


        while(sc.hasNext()){
            str = sc.nextLine();

            if(compare(str, "FIM")) { //Verifica se as duas strings são iguais, caso sejam para o loop pois 
                sc.close();
                return;
            }

            System.out.println(reverse(str)); //Printa String invertida
        }


        sc.close();
    }
}