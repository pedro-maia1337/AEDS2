package aquecimento01;
/*Crie um método iterativo em Java que receba como parâmetro uma string e retorne seu número de caracteres maiúsculos. Em seguida, teste o método anterior usando redirecionamento de entrada e saída.
A entrada padrão é composta por várias linhas sendo que a última contém a palavra FIM. A saída padrão contém um número inteiro para cada linha de entrada. */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class aquecimento01 {
    public static void main(String agrs[]) {
        String str = new String();
        int count = 0;
        Scanner sc = null;
        File file = new File("C:\\Users\\1543726\\Documents\\AEDS2\\aquecimento\\prat\\JAVA\\aquecimento01\\test.txt");

        try {
            sc = new Scanner(file);

            while(sc.hasNextLine()) {

                str = sc.nextLine();

                if(str.equals("FIM")) {
                    sc.close();
                    return;
                }
    
                count = count_mai(str);
                System.out.printf("%d\n", count);
            }

           
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if(sc != null) {
                sc.close();
            }
        }
    }

    public static int count_mai(String str) {
        int len = str.length();
        int count = 0;
        for(int i = 0; i < len; i = i + 1) {
            char aux = str.charAt(i);
            if((aux > 64) && (aux < 91)) count = count + 1;
        }

        return count;
    }
}