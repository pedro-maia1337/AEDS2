package mai;

import java.util.*;

public class mai {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String frase = new String();
        int tam, resp, aux;

        while(sc.hasNext()){
            frase = sc.nextLine();
            tam = frase.length();
            resp = 0;

            if(frase.equals("FIM")){
                sc.close();
                return;
            }

            for (int i = 0; i < tam; i++){
                aux = frase.charAt(i);
                if((aux > 64) && (aux < 91)){
                    resp++;
                }
            }
            System.out.println(resp);
        }
    sc.close();
    }
}
