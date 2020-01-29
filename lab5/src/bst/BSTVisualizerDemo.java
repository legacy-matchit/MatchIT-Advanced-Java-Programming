package bst;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;

public class BSTVisualizerDemo {


    //extends Comparable<? super E>
    public static class Fiction extends Book {

        public Fiction(String name, int isbn) {
            super(name, isbn);
        }
        /*
        @Override
        public int compareTo(Fiction o) {
            return super.name.compareTo(o.name);
        }*/
    }
    //public static class Book implements Comparable<Book>  {
    public static class Book implements Comparable<Book>{
        protected String name;
        protected int isbn;
        public Book(String name,int isbn){
            this.name = name;
            this.isbn = isbn;
        }

        @Override
        public int compareTo(Book o) {
            return name.compareTo(o.name);
        }

        public String toString(){
            return name + ":" + isbn;
        }
    }

    public static void main(String[] args){

        BinarySearchTree<Fiction> bst = new BinarySearchTree<>(
                (o1, o2) -> Integer.compare(o1.isbn,o2.isbn)
        );
        //BinarySearchTree<Fiction> bst = new BinarySearchTree<>();


        bst.add(new Fiction("A",1));
        bst.add(new Fiction("B",3));
        bst.add(new Fiction("C",5));
        bst.add(new Fiction("D",7));
        bst.add(new Fiction("E",9));
        bst.add(new Fiction("F",11));
        bst.add(new Fiction("H",13));



        BSTVisualizer bstVisualizer = new BSTVisualizer("Before Rebuild",300,300);
        bstVisualizer.drawTree(bst);


        System.out.println("Before Rebuild Height : " + bst.height());
        bst.printTree();
        bst.rebuild();
        System.out.println("After Rebuild Height : " + bst.height());

        BSTVisualizer bstVisualizer2 = new BSTVisualizer("After Rebuild",300,300);
        bstVisualizer2.drawTree(bst);

        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.get("A") + " " + map.size());
        map.put("A", 43);
        System.out.println(map.get("A") + " " + map.size());
        map.put("A", 42);
        System.out.println(map.get("A") + " " + map.size());
    }

}
