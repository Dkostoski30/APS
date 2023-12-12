package Zadaca3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {
    static int sumOfArr(int [] arr){
        return Arrays.stream(arr).sum();
    }
    static int solve(int numbers[], int N, int K) {
       int[] razliki = new int[N-1];
        for (int i = 1; i < N; i++) {
            razliki[i-1] = Math.abs(numbers[i]-numbers[i-1]);
        }

        int max = numbers[0];
        for (int i = 0; i < razliki.length; i++) {
            int[] subArr = new int[K];
            if(K+i<N){
                System.arraycopy(razliki, i, subArr, 0, K);
                if (sumOfArr(subArr) > max) {
                    max = sumOfArr(subArr);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}
