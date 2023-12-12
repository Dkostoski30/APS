// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    static void printArray(int a[], int n){
        for (int element: a) {
            System.out.print(element+" ");
        }
        System.out.println();
    }
    static void oddEvenSort(int a[], int n)
    {
        int min = 0;
        int max = a.length-1;
        while (min!=max){
            for (int i = 0; i < n; i++) {
                if(a[min]>a[i]){
                    int temp = a[i];
                    a[i] = a[min];
                    a[min++] = temp;
                }
            }

            for (int i = 0; i < n; i++) {
                if(a[max]<a[i]){
                    int temp = a[i];
                    a[i] = a[min];
                    a[max--] = temp;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}