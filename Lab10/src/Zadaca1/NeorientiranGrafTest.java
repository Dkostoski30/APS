package Zadaca1;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.Stack;
import java.util.NoSuchElementException;
/*class SLLNode<E> {
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
interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

}
class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}

class Graph<E> {

    int num_nodes; // broj na jazli
    E nodes[];    // informacija vo jazlite - moze i ne mora?
    int adjMat[][];  // matrica na sosednost

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        nodes = (E[]) new Object[num_nodes];
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }



    public Graph(int num_nodes, E[] nodes) {
        this.num_nodes = num_nodes;
        this.nodes = nodes;
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }



    int adjacent(int x,int y)
    {  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
        return (adjMat[x][y]!=0)?1:0;
    }

    void addEdge(int x,int y)
    {  // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y]=1;
        adjMat[y][x]=1;
    }

    void deleteEdge(int x,int y)
    {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y]=0;
        adjMat[y][x]=0;
    }

    // Moze i ne mora?
    E get_node_value(int x)
    {  // ja vraka informacijata vo jazelot so indeks x
        return nodes[x];
    }

    // Moze i ne mora?
    void set_node_value(int x, E a)
    {  // ja postavuva informacijata vo jazelot so indeks na a
        nodes[x]=a;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }

    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);

        for (int i = 0; i < this.num_nodes; i++) {
            if(adjacent(node, i)==1){
                if (!visited[i])
                    dfsRecursive(i, visited);
            }
        }
    }

    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);

        int pom;

        while (!s.isEmpty()) {
            pom = s.peek();
            int pom1 = pom;
            for (int i = 0; i < num_nodes; i++) {
                if(adjacent(pom,i)==1){
                    pom1 = i;
                    if(!visited[i])
                        break;
                }
            }
            if(!visited[pom1]){
                visited[pom1] = true;
                System.out.println(pom1 + ": " + nodes[pom1]);
                s.push(pom1);
            }
            else
                s.pop();
        }

    }

    void bfs(int node){
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node+": " + nodes[node]);
        Queue<Integer> q = new LinkedQueue<Integer>();
        q.enqueue(node);

        int pom;

        while(!q.isEmpty()){
            pom = q.dequeue();
            for (int i = 0; i < num_nodes; i++) {
                if(adjacent(pom, i)==1){
                    if (!visited[i]){
                        visited[i] = true;
                        System.out.println(i+": " + nodes[i]);
                        q.enqueue(i);
                    }

                }
            }


        }

    }



    @Override
    public String toString() {
        String ret="  ";
        for(int i=0;i<num_nodes;i++)
            ret+=nodes[i]+" ";
        ret+="\n";
        for(int i=0;i<num_nodes;i++){
            ret+=nodes[i]+" ";
            for(int j=0;j<num_nodes;j++)
                ret+=adjMat[i][j]+" ";
            ret+="\n";
        }
        return ret;
    }
}*/

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void findPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> invertedPath = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while(!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            T currentVertex = invertedPath.peek();
            T tmp = currentVertex;

            for(T vertex : getNeighbors(currentVertex)) {
                tmp = vertex;
                if(!visited.contains(vertex)) {
                    break;
                }
            }

            if(!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            }
            else {
                invertedPath.pop();
            }
        }

        Stack<T> path = new Stack<>();
        while(!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        while(!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }



}
public class NeorientiranGrafTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        int br = 0;
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            if(line.contains("CREATE")){
                graph = new AdjacencyListGraph<>();
            }else if(line.contains("ADDEDGE")){
                String[] parts = line.split("\\s+");
                Integer broj1 = Integer.parseInt(parts[1]);
                Integer broj2 = Integer.parseInt(parts[2]);
                graph.addEdge(broj1, broj2);
            }else if(line.contains("PRINTGRAPH")){
                System.out.println(graph.toString());
            }else if(line.contains("ADJACENT")){
                String[] parts = line.split("\\s+");
                Integer broj1 = Integer.parseInt(parts[1]);
                Integer broj2 = Integer.parseInt(parts[2]);
                System.out.println(graph.getNeighbors(broj1).contains(broj2));
            }else if(line.contains("DELETEEDGE")){
                String[] parts = line.split("\\s+");
                Integer broj1 = Integer.parseInt(parts[1]);
                Integer broj2 = Integer.parseInt(parts[2]);
                graph.removeEdge(broj1, broj2);
            }
        }
    }
}
