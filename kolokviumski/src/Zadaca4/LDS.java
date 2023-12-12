package Zadaca4;

import java.util.Arrays;
import java.util.Scanner;


public class LDS {

    private static int findMax(int[] arr){
        int maxPos=0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[maxPos] < arr[i]){
                System.out.println("MAX E "+i);
                maxPos = i;
            }
        }
        return maxPos;
    }
    private static int najdolgaOpagackaSekvenca(int[] arr) {
        if(arr.length<2){
            return 1;
        }
        int[] lsd = new int[arr.length+1];
        int counter = 0;
        while (arr.length>counter){
            lsd[counter++] = arr[findMax(arr)];
            arr = removeElementAtIndex(arr, lsd[counter-1]);
        }
        return counter;
    }
    public static int[] removeElementAtIndex(int[] array, int index) {
        // Check if the index is valid
        if (index >= 0 && index < array.length) {
            // Create a new array with size one less than the original array
            int[] newArray = new int[array.length - 1];

            // Copy the elements before the specified index
            System.arraycopy(array, 0, newArray, 0, index);

            // Copy the elements after the specified index
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);

            //System.out.println("Element at index " + index + " removed.");

            return newArray;
        } else {
            //System.out.println("Invalid index. Element not removed.");
            return array;
        }
    }
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}

