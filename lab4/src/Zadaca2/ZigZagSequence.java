package Zadaca2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        int counter = 1;
        int maxCounter = 1;
        int i;
        if(a[0]==0){
            i=2;
        }else{
            i=1;
        }
        for (; i < a.length; i++) {
            if((a[i-1]>0 && a[i]<0) || (a[i-1]<0 && a[i]>0)){
                counter++;
            }else{
                if(maxCounter<counter){
                    maxCounter = counter;
                }
                counter = 1;
            }
        }
        if(maxCounter<counter){
            maxCounter = counter;
        }
        return maxCounter;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}

