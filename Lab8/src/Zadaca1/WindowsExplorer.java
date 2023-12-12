package Zadaca1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}


class SLLTree<E> implements Tree<E> {

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        //curr.firstChild = tmp;
        tmp.parent = curr;

        if(curr.firstChild == null) {
            curr.firstChild = tmp;
        } else if(tmp.element.toString().compareTo(curr.firstChild.element.toString()) < 0) {
            tmp.sibling = curr.firstChild;
            curr.firstChild = tmp;
        } else {
            SLLNode<E> prev = new SLLNode<>(curr.firstChild.element);
            prev.sibling = curr.firstChild;
            while(prev.sibling != null && tmp.element.toString().compareTo(prev.sibling.element.toString()) > 0) {
                prev = prev.sibling;
            }
            tmp.sibling = prev.sibling;
            prev.sibling = tmp;
        }
        return tmp;
    }

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i=0;i<level;i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>)node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level+1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

}

class SLLNode<P> implements Node<P> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
    public String getPath(){
        String path = "";
        SLLNode<P> current = this;
        while (current!=null){
            path = current.element+ "\\" + path;
            current = current.parent;
        }
        return path;
    }
}

public class WindowsExplorer {

    public static void main(String[] args) throws Exception {
        int j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];

        for (int i=0;i<N;i++)
            commands[i] = br.readLine();

        br.close();
        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");
        SLLNode<String> current = tree.root;
        // vasiot kod ovde

        for (int i = 0; i < N; i++) {
            String[] commandNamePair = commands[i].split("\\s+");
            if (commands[i].contains("CREATE")){
                tree.addChild(current, commandNamePair[1]);
            } else if (commands[i].contains("OPEN")) {
                SLLNode<String> tmp = current.firstChild;
                while (tmp!=null){
                    if(tmp.element.equals(commandNamePair[1])){
                        current = tmp;
                        break;
                    }
                    tmp = tmp.sibling;
                }
            }else if(commands[i].contains(("BACK"))){
                current = current.parent;
            }else if(commands[i].contains("PATH")){
                System.out.println(current.getPath());
            }else if(commands[i].contains("PRINT")){
                tree.printTree();
            }else if(commands[i].contains("DELETE")){
                SLLNode<String> tmp = current.firstChild;
                while (tmp!=null){
                    if(tmp.element.equals(commandNamePair[1])){
                        tree.remove(tmp);
                        break;
                    }
                    tmp = tmp.sibling;
                }
            }
        }
    }

}
