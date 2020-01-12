package bst;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    E[] a;
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {

		BinaryNode<E>child = new BinaryNode<>(x);

		if(root == null){
			root = child;
			size++;
			return true;
		}else{
			return addNode(root, child);
		}


	}

	private boolean addNode(BinaryNode<E>root, BinaryNode<E> child){

		if (root.element.compareTo(child.element) == 0){
			return false;
		}

		if(child.element.compareTo(root.element) < 0){
			if(root.left == null){
				root.left = child;
				size++;
			}else{
				addNode(root.left,child);
			}
		}else if(child.element.compareTo(root.element) > 0) {
			if(root.right == null){
				root.right = child;
				size++;
			}else{
				addNode(root.right,child);
			}
		}
		return true;
	}



	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return calculateHeight(root);
	}
	private int calculateHeight(BinaryNode<E> node){
		if(node == null) {
			return 0;
		}else{
			int leftHeight = calculateHeight(node.left);
			int rightHeight = calculateHeight(node.right);
			return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1; // 큰쪽에 1씩 더해서 리턴
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	private void printTree(BinaryNode<E> node){
		if(node != null){
			printTree(node.left);
			System.out.println(node.element);
			printTree(node.right);
		}
	}



	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		//(root.element.class())[] a = new (root.element.getClass())[size];
		a = (E[])new Comparable[size];
		toArray(root,a,0);

	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n != null){
			toArray(n.left,a,index);
			a[index] = n.element;
			toArray(n.right,a,index);
		}
		return index;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		return null;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
			left = null;
			right = null;
		}	
	}
	
}
