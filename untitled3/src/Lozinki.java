import java.util.Scanner;
import java.util.Hashtable;

public class Lozinki {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        Hashtable<String, String> tabela = new Hashtable<>(N);
        scanner.nextLine();
        for (int i = 1; i <= N; i++) {
            String imelozinka = scanner.nextLine();
            String[] pom = imelozinka.split(" ");
            tabela.put(pom[0], pom[1]);
        }

        while (scanner.hasNextLine()) {
            String temp = scanner.nextLine();
            if(temp.equals("KRAJ")){
                break;
            }
            String[] pom = temp.split(" ");
            if(tabela.containsKey(pom[0])) {
                if (tabela.get(pom[0]).equals(pom[1])) {
                    System.out.println("Najaven");
                    break;
                }else {
                    System.out.println("Nenajaven");
                }
            }
            else {
                System.out.println("Nenajaven");
            }
        }
    }
}