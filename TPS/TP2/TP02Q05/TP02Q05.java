import java.util.*;

class TP02Q05 {
    /*public static boolean is_anagrama(String str1, String str2) {
        char char_str1;
        char chat_str2;


        

        if(str1.length() != str2.length()) return false;


        if(count == str2.length()) return true;

        return false;
    } */


    //Implementar metódo para ordernar string com base no seleção
    public static String sort_string(String str) {
        int len = str.length();
        char[] newStr = new char[len];

        int menor = 0;
        int aux = 0;

        for(int i = 0; i < str.length() - 1; i = i + 1){
            menor = i;
            for(int j = i + 1; j < str.length(); j = j + 1) {
                if((int)str.charAt(menor) > (int)str.charAt(j)){
                    aux = (int) str.charAt(menor);
                    newStr[i] = str.charAt(j);
                    newStr[j] = (char) aux;
                }
            }

        }

        return new String(newStr);
    }

    public static String converte_min(String str){
        String minStr = "";
        int tmp = 0;
        char aux;

        for(int i = 0; i < str.length(); i = i + 1){
            tmp = (int) str.charAt(i);
            if(tmp >= 65 && tmp <= 90) {
                tmp += 32;
            }
            aux = (char) tmp;
            minStr += aux;
        }

        return minStr;
    }

    ///Criar método que converte em minuscula

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

            str1 = sc.next();
            hifen = sc.next(); //pega o char hifen do meio da frase
            str2 = sc.next();

            if(compare(str1, "FIM") || compare(str2, "FIM")) {
                sc.close();
                return;
            }

            //boolean test = false;

            str1 = converte_min(str1);
            str2 = converte_min(str2);

            str1 = sort_string(str1);
            str2 = sort_string(str2);

            System.out.println(str1);
            System.out.println(str2);

            //test = is_anagrama(str1, str2);

            /*if(test){
                System.out.println("SIM");
            } else {
                System.out.println("NÃO");
            }*/
        }

        sc.close();
    }

}