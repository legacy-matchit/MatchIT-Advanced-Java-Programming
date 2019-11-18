package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {

	private QueueNode<E> last;

	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		/**새로운 노드를 만들고
		 * 새로운노드의 next를 first(last.next)로
		 * last.next를 새로운노드로
		 * last를 새로운노드로
		 * if last가 null일때 라스트를 먼저 초**/
		QueueNode<E> newNode = new QueueNode<>(e);
		if(last == null){
			last = newNode;
		}
		newNode.next = last.next;
		last.next = newNode;
		last = newNode;

		size++;

		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		//사이즈0보다 클때 라스트의 넥스트는 퍼스트가 되어 퍼스트의 element를 리턴 그렇지 않으면 널 리턴
		return size > 0 ? last.next.element : null;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {

		/**null 체크**/
		if(last == null){
			return null;
		}

		/** 첫 노드를 참조후
		 * 마지막 노드의 넥스트를 첫 노드의 넥스트로 바꿈
		 * 사이즈를 감소시킴
		 * 만약 사이즈가 0이면 last를 null로 변**/
		QueueNode<E> first = last.next;
		last.next = first.next;
		size--;
		if(size == 0){
			last = null;
		}
		return first.element;

	}

	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q){

		/** 동일객체허용안함**/
		if(this.equals(q)){
			throw new IllegalArgumentException();
		}
		/**인자가 널인지 체크**/
		if(q.size == 0){
			return;
		}

		/**현재 큐 와 인자로 넘어온 큐를 합침
		 * 만약 현재 큐가 엠티이면 라스트 변수만 변경
		 * 사이즈를 변경
		 * 인자로넘어온 큐의 사이즈를 0으로**/
		if(last != null){
			QueueNode<E> qFirst = q.last.next;
			q.last.next = last.next;
			last.next = qFirst;
		}else{
			last = q.last;
		}

		size += q.size;
		q.size = 0;

		/*
		while (q.iterator().hasNext()){
			E ele = q.poll();
			offer(ele);
		}*/
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {

		private QueueNode<E> pos;

		private int nextIndex;

		/* Konstruktor */
		private QueueIterator() {
			if(hasNext()){
				pos = last.next;
			}
			nextIndex = 0;
		}
		public boolean hasNext() {
			/** 현재 사이즈가 1 이상이면 true**/
			return nextIndex < size;
		}
		public E next() {

			/**현재큐에 1개이상 요소가 들어있으면
			 * 현재 요소를 빈 노드에 넣어두고
			 * 현재 노드는 다음 노드를 가르킴
			 * 비어 있는 노드에 접근하면 NoSuchElementException 발생시킴**/
			if(hasNext()){
				QueueNode<E> returnedElement = pos;
				pos = pos.next;
				nextIndex++;
				return returnedElement.element;
			}else{
				throw new NoSuchElementException();
			}

		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	public String toString(){
		StringBuilder str = new StringBuilder();
		QueueNode<E> next = last.next;
		str.append("[");
		Iterator<E> it = iterator();
		while (it.hasNext()){
			str.append(it.next()+",");
		}
		str.deleteCharAt(str.length()-1);
		str.append("]");
		return str.toString();
	}

}
