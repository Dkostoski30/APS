package Zadaca3;
import java.util.Scanner;
public class MiceHoles {

    //TODO: implement function
    public static int minTime(int mice[], int holes[]) {
        sort(mice);
        sort(holes);
        int max = Math.abs(mice[0] - holes[0]);
        for (int i = 0; i < mice.length; i++) {
            if (max < Math.abs(mice[i] - holes[i])){
                max = Math.abs(mice[i] - holes[i]);
            }
        }
        return max;
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]<arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String line = input.nextLine();
        String parts[] = line.split(" ");
        int mice[] = new int[parts.length];
        for(int i=0;i<parts.length;i++) {
            mice[i] = Integer.parseInt(parts[i]);
        }

        line = input.nextLine();
        parts = line.split(" ");
        int holes[] = new int[parts.length];
        for(int i=0;i<parts.length;i++) {
            holes[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(minTime(mice, holes));
    }
}
