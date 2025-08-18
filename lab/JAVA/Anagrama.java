/* Crie um método iterativo que recebe duas strings como parâmetros e retorna true se as strings são anagramas uma da outra, ou false caso contrário. Na saída padrão, para cada par de strings de entrada, escreva uma linha de saída com SIM/NÃO indicando se as strings são anagramas. Por exemplo, se as entradas forem listen e silent, a saída deve ser SIM.
*/

//amor
//roma

class Anagrama {
    public static boolean is_anagrama(String str1, String str2) {

        //transformar frases em lower case
        boolean flag = false;
        int count = 0;

        for(int i = 0; i < str1.length(); i = i + 1) {
            char tmp = str1.charAt(i);
            for(int j = 0; j < str2.length(); j = j + 1){
                char aux = str2.charAt(j);
                if(tmp == aux) count = count + 1;
            }
        }

        if(count == str1.length()) flag = true;

        return flag;
    }

    public static void main(String[] args) {
        String str1 = "listen";
        String str2 = "silent";

        boolean test = false;

        test = is_anagrama(str1, str2);

        if(test){
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }

    }
}