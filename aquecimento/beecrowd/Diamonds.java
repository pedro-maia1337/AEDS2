import java.util.*;

class Diamonds{

    public static int mineracao (List<String> abertura, List<String> fechamento) {
        if(abertura.size() == fechamento.size())  return abertura.size();
        
        if(abertura.size() > fechamento.size())  return fechamento.size();
        
        if(abertura.size() < fechamento.size())  return abertura.size();

        return 0;
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;

        n = Integer.parseInt(sc.nextLine().trim()); //Precisa disso

        for(int k = 0; k < n; k = k + 1) {
            String str = new String();
            int diamonds = 0;

            str = sc.nextLine();

            int len = str.length();

            List<String> abertura = new ArrayList<>();
            List<String> fechamento = new ArrayList<>();

            for(int i = 0; i < len; i = i + 1) {
                if(str.charAt(i) == '<') {
                    abertura.add(String.valueOf(str.charAt(i)));
                }

                if(str.charAt(i) == '>') {
                    fechamento.add(String.valueOf(str.charAt(i)));
                }
            }

            diamonds = mineracao(abertura, fechamento);

            System.out.println(diamonds);
        } 
        
        sc.close();
    }
}