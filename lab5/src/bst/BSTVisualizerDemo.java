package bst;

import java.awt.print.Book;
import java.util.TreeSet;

public class BSTVisualizerDemo {


    public static class Book implements Comparable<Book>  {
        private String name;
        public Book(String name){
            this.name = name;
        }

        @Override
        public int compareTo(Book o) {
            return name.compareTo(o.name);
        }
        public String toString(){
            return name;
        }
    }
    public static void main(String[] args){

        /*TreeSet<Book> treeSet = new TreeSet<>();
        treeSet.add(new Book("A"));*/
        TreeSet<String> tSet = new TreeSet<>();
        tSet.add("A");

        BinarySearchTree<Book> bst = new BinarySearchTree<>();


        bst.add(new Book("A"));
        bst.add(new Book("B"));
        bst.add(new Book("C"));
        bst.add(new Book("D"));
        bst.add(new Book("E"));
        bst.add(new Book("F"));
        bst.add(new Book("H"));
        bst.add(new Book("I"));
        bst.add(new Book("J"));

        /*
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(9);
        bst.add(11);
        bst.add(13);*/
/*
        bst.add("a");
        bst.add("c");
        bst.add("d");
        bst.add("f");
        bst.add("h");
        bst.add("i");
        bst.add("k");*/

        BSTVisualizer bstVisualizer = new BSTVisualizer("Before Rebuild",300,300);
        bstVisualizer.drawTree(bst);


        System.out.println("Before Rebuild Height : " + bst.height());
        //bst.printTree();
        bst.rebuild();
        System.out.println("After Rebuild Height : " + bst.height());

        BSTVisualizer bstVisualizer2 = new BSTVisualizer("After Rebuild",300,300);
        bstVisualizer2.drawTree(bst);
    }

}
