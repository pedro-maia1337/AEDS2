import java.util.*;

class TwoSun {

    public static int[] twoSum(int[] nums, int target) {
        int[] awnser = new int[2];
        for(int i = 0; i < nums.length; i = i + 1){
            for(int j = (i + 1); j < nums.length; j = j + 1){
                if(nums[i] + nums[j] == target) {
                    awnser[0] = i;
                    awnser[1] = j;
                }
            }
        }

        return awnser;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        int target = 0;

        n = sc.nextInt();
        target = sc.nextInt();

        int[] nums = new int[n];

        int[] awnser = new int[2];

        for(int i = 0; i < n; i = i + 1) {
            nums[i] = sc.nextInt();
        }

        awnser = twoSum(nums, target);

        for(int i : awnser) {
            System.out.println(i);
        }

        sc.close();
    }
}