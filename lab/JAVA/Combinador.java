import java.util.*;


class Combinador {
	public static void main(String[] args) {
	  String str1 = new String();
	  String str2 = new String();
	  String str3 = new String();
	  Scanner sc = new Scanner(System.in);
	  int len = 0;
	  int k = 0; //indice str1
	  int j = 0; //indice str2
	  int l = 0; //indice str3

	  while(sc.hasNext()){
	    str1 = sc.next();
	    str2 = sc.next();
	    len = str1.length() + str2.length();
	    char[] arrChar = new char[len];

	    for(int i = 0; i <= len; i = i + 1){
	      if(i < str1.length()){
		arrChar[l] = str1.charAt(k);
		l = l + 1;
		k = k + 1;
	      }

	      if(i < str2.length()){
	       arrChar[l] = str2.charAt(j);
	       l = l + 1;
	       j = j + 1;
	      }

	    }

	    str3 = new String(arrChar);

	    //System.out.println(str1);
	    //System.out.println(str2);
	    System.out.println(str3);
	  }

	  sc.close();
	}
}
