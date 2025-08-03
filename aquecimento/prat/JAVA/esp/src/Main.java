import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        int num_inicial = 0;
        int num_final = 0;

        System.out.println("Digite o numero inicial: ");
        num_inicial = ler.nextInt();

        System.out.println("Digite o numero final: ");
        num_final = ler.nextInt();

        for(int i = num_inicial; i <= num_final; i = i + 1) {
            System.out.printf("%d", i);
        }

        for(int i = num_final; i >= num_inicial; i = i - 1) {
            System.out.printf("%d", i);
        }

        ler.close();
    }
}