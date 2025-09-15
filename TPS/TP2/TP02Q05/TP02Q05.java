/* 
 Crie um método iterativo que recebe duas strings como parâmetros e retorna true se as strings são anagramas uma da outra, ou false caso contrário.
Na saída padrão, para cada par de strings de entrada, escreva uma linha de saída com SIM/NÃO indicando se as strings são anagramas. Por exemplo, se as entradas forem listen e silent, a saída deve ser SIM.
*/

//javac TP02Q05.java
//java TP02Q05 < pub.in > saida.out
//diff saida.out pub.out

import java.util.*;

class TP02Q05 {

    //Metódo para ordernar String alfabeticamente
     public static String sort_string(String str) {
        int len = str.length();
        char[] arr = new char[len]; //Array de caracteres para receber os caracteres ordernados
    
        //Copiando a string para o array
        for (int i = 0; i < len; i++) {
            arr[i] = str.charAt(i);
        }
    
        // Ordernando utilizando o algortimo de seleção visto nas aulas 
        for (int i = 0; i < len - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[menor]) {
                    menor = j;
                }
            }
            //Swap
            char aux = arr[i];
            arr[i] = arr[menor];
            arr[menor] = aux;
        }
    
        //Converte o array de char para string e retorna 
        return new String(arr);
    }

    //Método para transformar string em lowercase
    public static String converte_min(String str){
        String minStr = "";
        int tmp = 0;
        char aux;

        for(int i = 0; i < str.length(); i = i + 1){
            tmp = (int) str.charAt(i);
            if(tmp >= 65 && tmp <= 90) { //Valida se o caractere é maisculo
                tmp += 32; //Caso seja, soma + 32 no código da tabela ascii garantindo a conversão
            }
            aux = (char) tmp; //Casting do código inteiro para char
            minStr += aux; //Concatenando caracteres com a string vazia
        }

        return minStr;
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

    public static void main(String[] args) {
        String str1, str2, hifen;
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){

            //Lê cada parte da string de forma separada
            str1 = sc.next();

            
            if(compare(str1, "FIM")) { //Valida se string = FIM, caso seja para o loop
                sc.close();
                return;
            }


            hifen = sc.next(); // pega o caractere '-'
            str2 = sc.next();


            //Convertendo todos os caracteres para minusculo e ordenando para depois realizar a validação
            str1 = converte_min(str1);
            str2 = converte_min(str2);

            str1 = sort_string(str1);
            str2 = sort_string(str2);

            if(compare(str1, str2)){ //Validando se a primeira string ordenada é igual a segunda, caso seja é um anagrama
                System.out.println("SIM");
            } else {
                System.out.println("NÃO");
            }
        }

        sc.close();
    }

}