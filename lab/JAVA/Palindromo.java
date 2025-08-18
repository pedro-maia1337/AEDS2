/*Crie um método iterativo que recebe uma string como parâmetro e retorna true se essa é um ``Palíndromo''. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada pode ter caracteres não letras.

*/
class Palindromo {

    public static boolean is_palindromo(String str) {
        boolean flag = false;
        String reverse_string = "";

        for(int i = str.length() - 1; i >= 0; i = i - 1) {
            reverse_string = reverse_string + str.charAt(i);
        }

        if(reverse_string.equals(str)){
            flag = true;
        }    

        return flag; 
    }



    public static void main(String[] args){
        String str = "reviver";

        boolean test = false;

        test = is_palindromo(str);

        if(test) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }
    }
}