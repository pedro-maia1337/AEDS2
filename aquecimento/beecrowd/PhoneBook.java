import java.util.*;


class PhoneBook {

    public static int economy(String[] phones) {
        int index = 0;

        Arrays.sort(phones);

        for(int i = 1; i < phones.length; i = i + 1) {
            String current = phones[i];
            String previous = phones[i - 1];

            while(current.charAt(index) == previous.charAt(index)) {
                index = index + 1;
            }
            
        }

        return index;
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        int n = 0;

        while(sc.hasNext()) {
            n = Integer.parseInt(sc.nextLine().trim());

            String phones[] = new String[n];

            for(int i = 0; i < n; i = i + 1) {
                phones[i] = sc.nextLine().trim();
            }

            result = economy(phones);

            System.out.println(result);
        }

        sc.close();
    }
}