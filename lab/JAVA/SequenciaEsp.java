import java.util.*;

class SequenciaEsp {

    public static String reverseString(String str){
	String reverse_string = "";

	for(int i = str.length() - 1; i >= 0; i = i - 1){
	    reverse_string += str.charAt(i);
	}
	return reverse_string;
    }

    public static void main(String[] args) {
        int inicio = 0;
        int termino = 0;
	Scanner sc = new Scanner(System.in);

	while(sc.hasNext()){
	    String str = "";

	    inicio = sc.nextInt();
	    termino = sc.nextInt();

	    for(int i = inicio; i <= termino; i = i + 1){
		  str += Integer.toString(i);
	    }

	    str += reverseString(str);
	    System.out.println(str);	  	  
	}

	sc.close();
    }
}
