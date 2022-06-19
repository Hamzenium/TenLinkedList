
public  class TenLinkedList<E>  {

	private static final class Entry<E>{
		
		E element;
		Entry<E> next;
		Entry<E> previous;
		Entry<E> f_next; 
		Entry<E> b_next; 
		
	
	public Entry(E Element) {
			this.element = Element;}

	
	}
	private Entry<E> first;
	private Entry<E> last;
	private int size = 0;

	
    public TenLinkedList() {
		this.size = 0; // this is the constructor for the linked list list, it initiliazes the list with the size zero
	}

    public void addLast(Entry<E> e) {

    	// this is the helper method that we have created in order to help us with the add boolean method 
    	// it defines the element to the last entry of the list, and help us to differntiate between the entry 
    	// at the first postion or at the positon greater than the 0th position

    	if(size==0) {
 		   first =e;
 		   last=e;
 		  
    	}
 	   else if(size>0) {
 		   last.next=e;
 	    	e.previous=last;
 	    	last=e;
 	    	 Entry<E> back=last;
 	    	if(size>=10) {
  		   for(int i=0;i<10;i++) {
 			  back=back.previous;
 		   }
 		   e.b_next=back;
 		   back.f_next=e;
 	    	}
 	    	  }
    	size++;
 	   
    }
   public boolean add(E o) {  
	   // this method calls the helper function, and helps to input the entries into it 
	   addLast(new Entry<E>(o));
	   return true;
   }
   public void last(Entry<E> e) {
	   // this is another helper method to input the entires for the last entry in the linked list 
	  last.next=e;
   	  e.previous=last;
   	  last=e; 
   	  size++;  
   	Entry<E> back=last;
 	
   }
   
   public E get(int index) {
	   // in this method what we did was to search through thelinked list, 
//	   to get the entry at the specific index and to return that specific node element .
	   Entry<E> h ;
	   int a = index/10; int c = a*10; int e=index-c; int g=size/2;
	   int b = (size-1-index)/10; int d = b*10; int f=(size-index-d); 
	   if(g>=index) {
		   h=first;
	   for(int i=0;i<a;i++) {
		   h=h.f_next;
	   }
	   for(int j=0; j < e;j++) {
		   h = h.next;
	   }
	   }
	   else {
		   h=last;

		   for(int i=0;i<b;i++) {
			   h=h.b_next;
		   }
		   for(int j=0; j < f-1;j++) {
			   h= h.previous;
		   
	   }
	   }
	return h.element;
	   
   }
   
   public Entry<E> getObject(int index) {
	   
	   
	// in this helper method what we did was to search through the linked list, 
//	   to get the entry at the specific index and to return that specific node element .
	   Entry<E> h ;
	   int a = index/10; int c = a*10; int e=index-c; int g=size/2;
	   int b = (size-1-index)/10; int d = b*10; int f=(size-index-d); 
	   if(g>=index) {
		   h=first;
	   for(int i=0;i<a;i++) {
		   h=h.f_next;
	   }
	   for(int j=0; j < e;j++) {
		   h = h.next;
	   }
	   }
	   else {
		   h=last;

		   for(int i=0;i<b;i++) {
			   h=h.b_next;
		   }
		   for(int j=0; j < f-1;j++) {
			   h= h.previous;
		   
	   }
	   }
	return h;
	   
   }
   
   public E remove(int index) {
	   // in this method what we did was to to remove a specific node of the linked list, 
	   // and set it to null, moreover what we did was to set the previous node and the node after the one being removed to refer each other
	   
	  
	  Entry<E> m = getObject(index);
	 
	  if( size <= 1) {
		  first=last=null;
	  }
	  else {
		  
		  if(index>0 && index < size){
			  Entry<E> n = getObject(index+1);
			  Entry<E> h = getObject(index-1);
			  h.next=n;
			  n.previous=h;
		  }
		  
		  
		  else if(index == size) {
			  Entry<E> h = getObject(index-1);
			  last=h;
			  h.next=null;
			  
		  }
		  else if(m==first) {
			  first=m.next;
			  m.next.previous = null;
		  }
	  }
        if(index >10 && index < size) {
	  Entry<E> f1 = getObject(index);
	  Entry<E> f2 = getObject(index);
	  f2=f2.next;
	  for(int i=0;i<9;i++) {
		  f1=f1.previous;
	  }
	  for(int j=0;j<10;j++){
		 
		  if( f1 == null || f2 ==null) {
			  break;
		  }
		  f1.f_next=f2;
		  f2.b_next=f1;
		  f2=f2.next;
		  f1=f1.next; 
	  }
        }

	  
	  size --;
	  return m.element;
	   
   }

   public void add(int index, E element) {
	   // in this method what we did was to use a specific index and insert the node at the specifc place,
	   // and making sure the other nodes also refer to the right node 
	   Entry<E> e = new Entry<E>(element); 
	   Entry<E> pointer=first; Entry<E> p=first;
	   Entry<E> po=first; 
	   int number=0; int counter=0;
	   if(index != 0 && size - index > 0) {
	   while(number<index-1) {
		   pointer=pointer.next;
		   number++;
	   }
	   while(counter<index) {
		   p=p.next; counter++;
	   }
	   }
	   if(index != 0 && index != size) {
		   pointer.next=e;
		   e.previous=pointer;
		   e.next=p;
		   p.previous=e;
		   size++;
	   }
	   else if(index == 0 ) {
		   if(size == 0) {
			   first =e;
	 		   last=e;
	 		   size++;
		   }
		   else if(size > 0) {
			   	  first.previous=e;
			   	  e.next=first;
			   	  first=e;
			   	  size++; 
		   }
	   }
	   else if(index == size) {
		   last(e)  ;
	   }
	 
	   
	   
   }
   public void clear() {
	   // this method makes the list equal to zero, and so there is nothing there in the list
	   if(size > 0) {
		   first=null;
		   last=null;
		   size=0;
	   }
   }
   public int size() {
	   // this method returns the size of the list being referred
	return this.size;
	   
   }
   
   public void tester(int q) {
	   // this was test method created to check the f_next and b_next of the respective node
	   Entry<E> point = first;
	   Entry<E> pt ;
	   int printer =0;
	   while(printer != q) {
		   point=point.next;
		   printer++;
	   }	
	   System.out.println(point.element);
	   pt=(TenLinkedList.Entry<E>) point.b_next;
	   System.out.println(pt.element);
   }
   
   public String toString(){
	   // this uses the list, and converts them into a string 
//	   String str = "[";
//	   Entry<E> present = first;
//	   int counter=0;
//	   
//	   while(present != null){
//
//		   if(counter<size) {
//		   str += present.element + ",";
//		   counter++;
//		   }
//		   present= present.next;
//		  
//	   }
//	   return str + "]";
//   }
	   String result = "[";
	    if (first != null) {
	        result += first.toString();
	    }
	    return result+"]";
   }


}

   
   


