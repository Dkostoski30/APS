import java.util.Scanner;

class QuarterlySales {

    private int numOfSales;
    private int [] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }
    public int getSumOfRevenuse(){
        int sum = 0;
        for (int c:
             revenues) {
            sum+=c;
        }
        return sum;
    }
    @Override
    public String toString() {
        return String.valueOf(getSumOfRevenuse());
    }
}

class SalesPerson {

    private String name;
    private QuarterlySales [] quarters;
    public SalesPerson(String name){
        this.name = name;
        this.quarters = new QuarterlySales[4];
    }
    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }
    @Override
    public String toString() {
        String rez = getName();
        for (int i = 0; i < 4; i++) {
            rez+=String.format("   %d", quarters[i].getSumOfRevenuse());
        }
        rez+=String.format("   %d", sumSales(this));
        return rez;
    }

    public String getName() {
        return this.name;
    }

    public static int sumSales(SalesPerson sp){
        int sum = 0;
        for (QuarterlySales sales:
                sp.quarters) {
            sum += sales.getSumOfRevenuse();
        }
        return sum;
    }
}



public class Main {

    public static SalesPerson salesChampion(SalesPerson [] arr)
    {
        SalesPerson champ = arr[0];
        for (SalesPerson person:
             arr) {
            if(SalesPerson.sumSales(champ)<SalesPerson.sumSales(person)){
                champ = person;
            }
        }
        return champ;
    }
    public static void table(SalesPerson [] arr)
    {
        String table = "SP   1   2   3   4   Total\n";
        for (SalesPerson person:
             arr) {
            table+=(person.toString()+'\n');
        }
        System.out.println(table);
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        SalesPerson [] arr = new SalesPerson[n];
        for(int i=0;i<n;i++)
        {
            String name = input.next();
            QuarterlySales[] sales = new QuarterlySales[4];
            for (int j = 0; j < 4; j++) {
                int x = input.nextInt();
                int []revenues = new int[x];
                for (int k = 0; k < x; k++) {
                    revenues[k] = input.nextInt();
                }
                sales[j] = new QuarterlySales(x, revenues, j);
            }
            arr[i] = new SalesPerson(name, sales);
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}