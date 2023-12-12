package Zadaca1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int charToInt(char c){
        return c-'0';
    }
    static int presmetaj(char c[], int l, int r) {
        Stack<Integer> brojki = new Stack<>();
        Stack<Character> operacii = new Stack<>();
        int sum = 0;
        for (char element: c) {
            if(Character.isDigit(element)){
                brojki.push(charToInt(element));
            } else if (element == '+' || element == '-') {
                operacii.push(element);
            } else if (element == ')') {
                if(operacii.peek() == '-'){
                    int a = brojki.pop();
                    int b = brojki.pop();
                    int zb = b - a;
                    brojki.push(zb);
                }else{
                    int a = brojki.pop();
                    int b = brojki.pop();
                    int zb = b + a;
                    brojki.push(zb);
                }
                operacii.pop();
            }
        }
        return brojki.peek();
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
