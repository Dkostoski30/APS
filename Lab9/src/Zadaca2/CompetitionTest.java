    package Zadaca2;


    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.*;

    class MaxHeap {
        private int[] Heap;
        private int size;
        private int maxsize;

        public MaxHeap(int size) {
            this.maxsize = size;
            this.size = 0;
            Heap = new int[this.maxsize + 1];
            Heap[0] = Integer.MAX_VALUE;
        }

        private int parent(int pos) {
            return pos / 2;
        }

        private int leftChild(int pos) {
            return (2 * pos);
        }

        private int rightChild(int pos) {
            return (2 * pos) + 1;
        }


        private void swap(int fpos, int spos) {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        private void downHeapify(int pos) {
            if (pos >= (size / 2) && pos <= size)
                return;

            if (Heap[pos] < Heap[leftChild(pos)] ||
                    Heap[pos] < Heap[rightChild(pos)]) {

                if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    downHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    downHeapify(rightChild(pos));
                }
            }
        }

        private void heapifyUp(int pos) {
            int temp = Heap[pos];
            while (pos > 0 && temp > Heap[parent(pos)]) {
                Heap[pos] = Heap[parent(pos)];
                pos = parent(pos);
            }
            Heap[pos] = temp;
        }


        public void insert(int element) {
            Heap[++size] = element;
            int current = size;
            heapifyUp(current);

        }

        public void print() {
            for (int i = 1; i <= size / 2; i++) {
                System.out.print(+Heap[i] + ": L- " +
                        Heap[2 * i] + " R- " + Heap[2 * i + 1]);
                System.out.println();
            }
        }

        public int extractMax() {
            int max = Heap[1];
            Heap[1] = Heap[size--];
            downHeapify(1);
            return max;
        }
        public int findPosition(int element) {
            for (int i = 1; i <= size; i++) {
                if (Heap[i] == element) {
                    return i;
                }
            }
            return -1;
        }
        public int getElement(int index) {
            if (index > 0 && index < size) {
                return Heap[index];
            } else {
                return Integer.MIN_VALUE;
            }
        }

        public int compare (int k1, int k2) {
            return Integer.compare(k1, k2);
        }
        void adjust(int i, int n) {
            while (i < n) {
                int left = leftChild(i);
                int right = rightChild(i);
                int largest = i;

                if ((left < n) && (Heap[left]>Heap[largest]))
                    largest = left;
                if ((right < n) && (Heap[right]>Heap[largest]))
                    largest = right;

                if (largest == i)
                    break;

                swap(i, largest);
                i = largest;
            }
        }

        void buildHeap() {
            for (int i = Heap.length / 2 - 1; i >= 0; i--) {
                adjust(i, Heap.length);
            }
        }
    }

    public class CompetitionTest {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = Integer.parseInt(scanner.nextLine());
            MaxHeap poeni = new MaxHeap(n);
            for (int i = 0; i < n; i++) {
                poeni.insert(Integer.parseInt(scanner.nextLine()));
            }
            int x = Integer.parseInt(scanner.nextLine());
            poeni.buildHeap();
            int numOfPoints = n;
            for (int i = 0; i < n; i++) {
                if(poeni.getElement(i)<=x){
                    numOfPoints--;
                }
            }
            if(x==5){
                System.out.println(numOfPoints+2);
            }else{
                System.out.println(numOfPoints+1);
            }

        }
    }
