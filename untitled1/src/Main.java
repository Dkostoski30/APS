import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student{
    private String ime, prezime;
    private int index;
    private int[] ocenki;
    private static int BR_STUDENTI = 0;

    public static int getBrStudenti() {
        return BR_STUDENTI;
    }
    public static Student vratiGoPodobriot(Student student1, Student student2){

    }
    public Student(String ime, String prezime, int index, int[] ocenki) {
        this.ime = ime;
        this.prezime = prezime;
        this.index = index;
        this.ocenki = ocenki;
        BR_STUDENTI++;
    }

}
public class Main {
    public static void main(String[] args) {
        Student student = new Student("Daniel", "Kostoski", 0, new int[]{0, 0});
        Student studen1t = new Student("Daniel", "Kostoski", 0, new int[]{0, 0});
        System.out.println();
    }
}