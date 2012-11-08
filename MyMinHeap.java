/**
 * A Binary Min Heap
 * @author Based on Weiss 6.4
 *
 */
public class MyMinHeap <T extends Comparable<?super T>>
{
	private int currentSize;
	private T[] array;
	private static int DEFAULT_CAPACITY=10;
	
	/**
	 * Constructs a MyMinHeap object that can hold size items.
	 */
	public MyMinHeap()
	{
		currentSize =0;
		array = (T[])(new Comparable[DEFAULT_CAPACITY]);
	}
	/**
	 * Constructs a MyMinHeap object that can hold size items.
	 * @param size the number of items that heap can hold.
	 */
	public MyMinHeap(int size)
	{
		currentSize =0;
		array = (T[])(new Comparable[size]);
	}
	
	/**
	 * Constructs a MyMinHeap object from the array of items.
	 * @param items an array of items. 
	 */
	public MyMinHeap(T[]items)
	{
		currentSize = items.length;
		array = (T[])(new Comparable[(currentSize+2)*(11/10)]);
		
		int i = 1;
		for(T item:items)
		{
			array[i++] = item;
		}
		buildHeap();		
	}
	/**
	 * Builds a heap
	 */
	private void buildHeap()
	{
		for(int i = currentSize/2;i>0;i--)
			percolateDown(i);
	}
	/**
	 * Percolates an element down to its proper position.
	 * @param hole the starting location of the element.
	 */
	private void percolateDown(int hole)
	{
		int child;
		T temp = array[hole];
		
		for(;hole*2<=currentSize;hole = child)
		{
			child = hole*2;
			if(child!=currentSize&&array[child+1].compareTo(array[child])<0)
			{
				child++;
			}
			
			if(array[child].compareTo(temp)<0)
			{
				array[hole]=array[child];
			}
			else
			{
				break;
			}
		}		
		array[hole]=temp;		
	}
	
	/**
	 * Deletes the minimum item in the heap.
	 * @return the minimum item in the heap.
	 * @throws Exception Thrown if heap is empty.
	 */
	public T deleteMin() 
	{
		if(isEmpty())
			return null;
		
		T minItem = findMin();
		array[1]=array[currentSize--];
		percolateDown(1);
		
		return minItem;
	}
	/**
	 * Determines if the heap is empty.
	 * @return true if empty, false if otherwise.
	 */
	public boolean isEmpty()
	{
		return currentSize==0;
	}
	
	/**
	 * Finds the smallest element in the heap.
	 * @return the smalles element in the heap.
	 */
	public T findMin()
	{
		if(!isEmpty())
			return array[1];
		
		return null;
	}
	
	private void enlargeArray(int newSize)
	{
		T[] temp = array;
		
		array = (T[])(new Object[newSize]);
		for(int i=0;i<temp.length;i++)
		{
			array[i]=temp[i];
		}		
	}
	/**
	 * Inserts an element into the heap.
	 * @param element
	 */
	public void insert(T element)
	{
		if(currentSize==array.length-1)
		{
			enlargeArray(array.length*2 +1);
		}
		
		int hole =++currentSize;
		for(;hole>1&&element.compareTo(array[hole/2])<0;hole/=2)
		{
			array[hole]=array[hole/2];
		}
		array[hole]=element;
	}
	
	/**
	 * returns the size of heap.
	 * @return
	 */
	public int getSize()
	{
		return currentSize;
	}
	
	

}
