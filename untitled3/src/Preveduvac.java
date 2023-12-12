import java.io.IOException;
import java.util.Scanner;

class MapEntry2<K extends Comparable<K>,E> implements Comparable<K> {
    K key;
    E value;

    public MapEntry2(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry2<K,E> other = (MapEntry2<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}


class OBHT<K extends Comparable<K>,E> {

    private MapEntry2<K,E>[] buckets;

    static final int NONE = -1;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final MapEntry2 former = new MapEntry2(null, null);

    private int occupancy = 0;

    @SuppressWarnings("unchecked")
    public OBHT (int m) {
        buckets = (MapEntry2<K,E>[]) new MapEntry2[m];
    }

    private int hash (K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public MapEntry2<K,E> getBucket(int i){
        return buckets[i];
    }


    public int search (K targetKey) {
        int b = hash(targetKey);
        for (;;) {
            MapEntry2<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else
                b = (b + 1) % buckets.length;
        }
    }


    public void insert (K key, E val) {
        MapEntry2<K,E> newEntry = new MapEntry2<K,E>(key, val);
        int b = hash(key);
        for (;;) {
            MapEntry2<K,E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            } else
                b = (b + 1) % buckets.length;
        }
    }


    @SuppressWarnings("unchecked")
    public void delete (K key) {
        int b = hash(key);
        for (;;) {
            MapEntry2<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;
                return;
            } else{
                b = (b + 1) % buckets.length;
            }
        }
    }


    public String toString () {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K,E> clone () {
        OBHT<K,E> copy = new OBHT<K,E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry2<K,E> e = buckets[i];
            if (e != null&&e != former)
                copy.buckets[i] = new MapEntry2<K,E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }
}



public class Preveduvac {
    public static void main (String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        OBHT<String, String> translator = new OBHT<>(n+1);
        for (int i = 0; i < n; i++) {
            String pair = scanner.nextLine();
            String [] parts = pair.split(" ");
            translator.insert(parts[1], parts[0]);
        }
        /*for (int i = 0; i < n; i++) {
            System.out.println(translator.getBucket(i).key+" "+translator.getBucket(i).value);
        }*/
        while (scanner.hasNextLine()){
            String temp = scanner.nextLine();
            if(temp.equals("KRAJ")){
                break;
            }
            if(translator.search(temp)>=0){
                System.out.println(translator.getBucket(translator.search(temp)).value);
            }else{
                System.out.println("/");
            }
        }
    }
}
