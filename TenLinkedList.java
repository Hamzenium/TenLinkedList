import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TenLinkedList <E> implements List <E>{
	private int size;
	private Node <E> head, tail;
	private static class Node<E> {
		Node<E> prev10;
		Node<E> prev;
		E item;
		Node<E> next;
		Node<E> next10;

		public Node(TenLinkedList.Node<E> prev10, TenLinkedList.Node<E> prev, E item, TenLinkedList.Node<E> next,
				TenLinkedList.Node<E> next10) {
			super();
			this.prev10 = prev10;
			this.prev = prev;
			this.item = item;
			this.next = next;
			this.next10 = next10;
		}
	}

	@Override
	public boolean add(E e) {
		add(size, e);
		return true;
	}
	@Override
	public void add(int index, E e) {
		Node <E> m10, p10;
		int i;
		if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
		if (size == 0) { //the list is empty
			head = new Node<E>(null, null, e, null, null); 
			tail = head;
		} else if (index == 0) { //@ head
			p10 = findPosition(9); 
			head = new Node <E> (null, null, e, head, p10);
			if (p10 != null) p10.prev10 = head;
			head.next.prev = head;
		} else if (index == size){ //@ tail
			m10 = findPosition(size - 10);
			tail = new Node<E> (m10, tail, e, null, null);
			if (m10 != null) m10.next10 = tail;
			tail.prev.next = tail;
		} else { //neither head nor tail
			Node <E> curNode = findPosition(index);
			m10 = findPosition(index - 10); 
			p10 = findPosition(index + 9); 
			Node <E> newNode = new Node <E> (m10, curNode.prev, e, curNode, p10);
			curNode.prev.next = newNode;
			curNode.prev = curNode.prev.next;
			//fix nodes after inserted node; curNode is @ index + 1
			for (i = index + 1; i <= index + 10 && curNode != null; i++, curNode = curNode.next) {
				if (curNode.prev10 != null) curNode.prev10 = curNode.prev10.next;
				else curNode.prev10 = findPosition(i - 10);
			}
			//fix nodes before inserted node
			for (curNode = newNode.prev, i = index - 1; i >= index - 10 && curNode != null; i--, curNode = curNode.prev) {
				if (curNode.next10 != null) curNode.next10 = curNode.next10.prev;
				else curNode.next10 = findPosition(i + 9);
			}
		}
		size ++;
	}
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		E elementToReturn;
		int i;
		if (index == 0) { //first position or if the list becomes empty
			elementToReturn = head.item;
			if (head.next10 != null)
				head.next10.prev10 = null;
			head = head.next;
			if (head == null) tail = null;
			else 
				head.prev = null;			
		} else if (index == size - 1) { //last position
			elementToReturn = tail.item;
			if (tail.prev10 != null)
				tail.prev10.next10 = null;
			tail = tail.prev;
			tail.next = null;
		} else { //neither first or the last; find the el. before
			Node <E> curNode, deletedNode;
			deletedNode = findPosition(index);
			elementToReturn = deletedNode.item;
			deletedNode.prev.next = deletedNode.next;
			deletedNode.next.prev = deletedNode.prev;
			//fix nodes after the deleted node
			for (curNode = deletedNode.next, i = index + 1; i <= index + 10 && curNode != null; i++, curNode = curNode.next) {
				if (curNode.prev10 != null) curNode.prev10 = curNode.prev10.prev;
			}
			//fix nodes before the deleted node
			for (curNode = deletedNode.prev, i = index - 1; i >= index - 10 && curNode != null; i--, curNode = curNode.prev) {
				if (curNode.next10 != null) curNode.next10 = curNode.next10.next;
			}
		}
		size --;
		return elementToReturn;
	}
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		return findPosition(index).item;
	}
	@Override
	public int size() {
		return this.size;
	}
	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	@Override
	public String toString() {
		if (size == 0) return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append('[');

		for (Node <E> node = head; node != null; node = node.next) {
			sb.append(node.item.toString());
//Uncomment the next 5 lines (and comment 1 line above) to see the list structure
//			sb.append(node.prev10 != null?node.prev10.item.toString():".")
//			.append("<-" + (node.prev != null?node.prev.item.toString():"."))
//			.append("<-(" + node.item.toString() + ")->")
//			.append(node.next != null?node.next.item.toString():".")
//			.append("->" + (node.next10 != null?node.next10.item.toString():"."));
			if (node.next == null)
				return sb.append(']').toString();
//				return sb.append(']').append(" Size: " + size).toString();
			sb.append(',').append(' ');
		}
		return null;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	private Node <E> findPosition (int index){
		if (index < 0 || index >= size()) return null;
		Node <E> curNode = index < size/2 ? head : tail;
		int initialIndex = curNode == head ? 0 : size - 1 ;
		int direction = curNode == head ? 1 : -1;
		int longJumps = Math.abs((initialIndex - index) / 10);
		int shortJumps = Math.abs((initialIndex - index) % 10);
		if (direction > 0) {
			for (int i = 0; i < longJumps; i++, curNode = curNode.next10);
			for (int i = 0; i < shortJumps; i++, curNode = curNode.next);
		}
		else {
			for (int i = 0; i < longJumps; i++, curNode = curNode.prev10);
			for (int i = 0; i < shortJumps; i++, curNode = curNode.prev);
		}
		return curNode;
	}
}
