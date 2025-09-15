import java.util.*;


class Ranking {
  public static void main(String[] args){
    int len = 0;
    String str = new String();
    Scanner sc = new Scanner(System.in);

    while(sc.hasNext()) {
      str = sc.nextLine();

      String[] data = str.split(" ");
      int dataLen = data.length;
      int[] dataNum = new int[data.length];

      double avg = 0;
      int goals = 0;

      for(int i = 0; i < dataLen - 1; i = i + 1) {
	dataNum[i] = Integer.parseInt(data[i + 1]);
      }

      for(int i = 0; i < dataNum.length; i = i + 1) {
	avg += dataNum[i];	
      }

      for(int i = 0; i < dataNum.length; i = i + 2){
	goals = goals + dataNum[i];
      }

      avg = avg / (dataNum.length / 2);

      System.out.println(avg);
      System.out.println(goals);
    }

    sc.close();
  }

}
