package Zadaca3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {
    public static int BUS_TICKET = 100;
    public static int max(int n, int m){
        if(m==0){
            return n*BUS_TICKET;
        }
        return n*BUS_TICKET+(m-1)*BUS_TICKET;
    }
    public static int min(int n, int m){
        int sum = n * BUS_TICKET;
        if(m>n) {
            for (int i = 0; i < n; i++) {
                sum += (m / n - 1) * BUS_TICKET;
            }
            sum += (m % n) * BUS_TICKET;
        }else if(m<n){
            sum+=(m/n)*BUS_TICKET;
        }
        return sum;
    }
    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();
        System.out.println(min(N, M));
        System.out.println(max(N, M));
    }

}
