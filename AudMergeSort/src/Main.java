import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int [] arr = new int[]{3, 2, 1, 0};
        System.out.println("BEFORE SORT: ");
        System.out.println(Arrays.toString(arr));
        MergeSort.sort(arr, 0, arr.length-1);
        System.out.println("AFTER SORT: ");
        System.out.println(Arrays.toString(arr));
    }
}