import java.util.*;

//Organizar cada registro
//Output -> Cor (Branco > Vermelho), Tamanho(Ordem decrescente P > M > G), nome em ordem Crescente

//Cria a classe da abstração 
class Shirt {
    String color;
    Char size;
    String name;

    public Shirt(String color, Char size, String name) {
        this.color = color;
        this.size = size;
        this.name = name;
    }
}

public class Tshirts {

    //Função de ordenação
    public static void selectionSort(List<Shirt> list) {
        for(int i = 0; i < list.size() - 1; i = i + 1) {
            int menor = i;
            for(int j = i + 1; j < list.size(); i = i + 1) {
                if(CompareShirt(list.get(j), list.get(menor)) < 0) {
                    menor = j;
                }
            } 

            if(menor != i) {
                Shirt temp = list.get(i);
                list.set(i, list.get(menor));
                list.set(menor, temp);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNext()) {
            int n = Integer.parseInt(sc.nextLine().trim());

            //Usa list para não se preocupar com tamanho de array
            List<Shirt> shirts = new ArrayList<>();

            for(int i = 0; i < n; i = i + 1) {
                String name = sc.nextLine();
                String[] details = sc.nextLine().split(" ");
                String color = details[0];
                String size  = details[1];
                shirts.add(new Shirt(color, size, name));
            }

            
        }

        sc.close();
    }
}