import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;
    public MapEntry (K key, E val) {
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

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }


    @Override
    public String toString() {
        return element.toString();
    }
}



class CBHT<K extends Comparable<K>, E> {
    private SLLNode<MapEntry2<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry2<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry2<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry2<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry2<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry2<K, E> newEntry = new MapEntry2<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry2<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry2<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry2<K,E>>(newEntry, buckets[b]);
    }



    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry2<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry2<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry2<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }
}



public class RoutingHashJava {
    public static void main (String[] args)  {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, List<String>> routingTable = new CBHT<>(n);
        for (int i = 0; i < n; i++) {
            String vleznaIP = scanner.nextLine();
            String[] staticRoutesArray = scanner.nextLine().split(",");
            List<String> staticRoutes = new ArrayList<>(Arrays.stream(staticRoutesArray).collect(Collectors.toList()));
            routingTable.insert(vleznaIP, staticRoutes);
        }
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String vleznaIP = scanner.nextLine();
            String destIP = scanner.nextLine();
            if( routingTable.search(vleznaIP)!=null) {
                boolean flag = true;
                for (String staticRoute : routingTable.search(vleznaIP).element.value) {
                    if (staticRoute.substring(0, staticRoute.length() - 1).equals(destIP.substring(0, destIP.length() - 1)) || staticRoute.substring(0, staticRoute.length() - 1).equals(destIP.substring(0, destIP.length() - 2)) || staticRoute.substring(0, staticRoute.length() - 1).equals(destIP.substring(0, destIP.length() - 3))) {
                        System.out.println("postoi");
                        flag = false;
                    }
                }
                if(flag){
                    System.out.println("ne postoi");
                }
            }else{
                System.out.println("ne postoi");
            }
        }
    }
}