package bst;

import java.awt.print.Book;
import java.util.TreeSet;

public class BSTVisualizerDemo {

    public static class book implements Comparable<book> {



        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int compareTo(book o) {
            return 0;
        }
    }
    public static void main(String[] args){

        TreeSet<Book> treeSet = new TreeSet<>();
        treeSet.add(new Book());

        BinarySearchTree<Book> bst = new BinarySearchTree<Book>();
       /* bst.add(3);
        bst.add(4);
        bst.add(5);
        bst.add(6);
        bst.add(7);
        bst.add(14);
        bst.add(22);
        bst.add(24);
        bst.add(25);
        bst.add(26);
        bst.add(28);
        bst.add(30);
        bst.add(40);
        bst.add(50);
        bst.add(66);
        bst.printTree();
        bst.rebuild();
        BSTVisualizer bstVisualizer = new BSTVisualizer("BSTVisualizer",500,500);
        bstVisualizer.drawTree(bst);*/
    }

}
