import java.util.*;

public class Grid {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = 0;
        int count = 0;


        while(sc.hasNext()) {
            n = sc.nextInt();

            int largada[] = new int[n];
            int chegada[] = new int[n];

            for(int i = 0; i < n; i = i + 1) {
                largada[i] = sc.nextInt();
            }

            for(int i = 0; i < n; i = i + 1) {
                chegada[i] = sc.nextInt();
            }

            for(int i = 0; i < n; i = i + 1){
                int tmp = chegada[i];
                int j = i - 1;

                while(j >= 0 && chegada[i] == tmp) {
                    largada[j + 1] = largada[j];
                    j--;
                }

                largada[j + 1] = tmp;
            }


            for(int i = 0; i < n; i = i + 1) {
                System.out.printf("%d", chegada[i]);
            }

        }


        sc.close();
    }
}
