package bst;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**E extends Comparable<? super E> compareTo 구현 안되있으면 컴파일 에러 추천방법 **/
//<E extends Comparable<? super E>> : 수퍼클래스 또는 서브 클래스에서 구현되어있어야함 컴파일 에러
//((Comparable)e1).compareTo(e2) : Comparable 인터페이스 타입으로 캐스팅 해주어야함 그냥 <E>로 선언했을때
//E[] a = (E[])new Object[size] : 그냥 <E>로만 선언 되었을땐 괜찮음.
//E[] a = (E[])new Comparable[size] : <E extends Comparable<? super E>>로 선언되었을땐 Comparable 로 구현해야함.
//Comparable 인터페이스는 엘레멘트 클래스<커스텀클래스> 안에서 구현 예)String, Integer..등 클래스는 이미구현되어있음
//Comparator 인터페이스는 ADT 타입으로 인스턴스 만들어서 compare 메소드를 구현 해야함
//public class BinarySearchTree<E extends Comparable<? super E>> {

public class BinarySearchTree<E>{

	BinaryNode<E> root;
    Comparator<E> comp;
	int size;


	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
	}

	/**
	 * Constructs with Comparator Interface
	 * @param comp
	 */
	public BinarySearchTree(Comparator<E> comp){ //오버로딩
		this.comp = comp;
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

		int compareResult = compareElement(child.element,root.element);

		if(compareResult < 0){
			if(root.left == null){
				root.left = child;
				size++;
			}else{
				addNode(root.left,child);
			}
		}else if(compareResult > 0) {
			if(root.right == null){
				root.right = child;
				size++;
			}else{
				addNode(root.right,child);
			}
		}else{
			return false;
		}
		return true;
	}
	private int compareElement(E e1,E e2){
		if(comp == null){
			return ((Comparable)e1).compareTo(e2);
			//return e1.compareTo(e2);

		}else{
			return comp.compare(e1,e2);
		}
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
		//E[] a = (E[])new Comparable[size];
		E[] a = (E[])new Object[size];

		System.out.println(toArray(root,a,0));
		System.out.println(Arrays.toString(a));
		root = buildTree(a,0,size-1);
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
			return toArray(n.right,a,index+1);
		}
		return index;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {

		if(first > last){
			return null;
		}
		int mid = (first + last) / 2;
		BinaryNode<E> node = new BinaryNode<>(a[mid]);
		node.left = buildTree(a,first,mid-1);
		node.right = buildTree(a,mid+1,last);
		return node;
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
