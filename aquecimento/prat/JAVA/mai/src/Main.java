import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = new String();
        File file = new File ("C:\\Users\\loona\\Documents\\AEDS2\\aquecimento\\prat\\JAVA\\mai\\src\\in.txt");
        Scanner sc = null;
        int len = 0;
        int count = 0;

        try {

            sc = new Scanner(file);
            while(sc.hasNext()) { // valida se tem proxima linha
                str = sc.nextLine(); // pula proxima linnha

                if(str.equals("FIM")) {
                    sc.close();
                    return;
                }

                len = str.length();

                countM(str, len, count);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            if(sc != null) {
                sc.close();
            }
        }
    }

    public static void countM(String str, int len, int count) {
            for(int i = 0; i < len; i = i + 1){
                char aux = str.charAt(i);
                if((aux > 64) && (aux < 91)){
                 count = count + 1;
                }
            }
        System.out.println(count);
    }
}