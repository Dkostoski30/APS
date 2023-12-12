import java.io.*;
public class MergeSort {
     static void merge(int[] numbers, int left, int mid, int right){
        int[] leva_niza = new int[mid - left + 1];
        int[] desna_niza = new int[right-mid];

         for (int i = 0; i < mid - left + 1; i++) {
             leva_niza[i] = numbers[left+i];
         }
         for (int j = 0; j < right - mid; j++){
             desna_niza[j] = numbers[mid + 1 + j];
         }
        int k = left;
        int i, j;
        for (i = 0, j=0; j < right-mid && i < mid - left + 1; k++) {
            if(leva_niza[i] <= desna_niza[j]){
                numbers[k] = leva_niza[i++];
            }else{
                numbers[k] = desna_niza[j++];
            }
        }
         for(; i < mid - left + 1; k++, i++){
             numbers[k] = leva_niza[i];
         }
        for (; j < right-mid; j++, k++) {
            numbers[k] = desna_niza[j];
        }
    }
    public static void sort(int[] arr, int left, int right){
        if(left < right){
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid+1, right);
            merge(arr, left, mid, right);
        }
    }
}
