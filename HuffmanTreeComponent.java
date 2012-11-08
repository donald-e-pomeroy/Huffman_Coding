import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class HuffmanTreeComponent extends JComponent
{
	private HashSet<Character> charSet;
	private ArrayList<IndexedCharacter> indexedChars;
	private MyMinHeap<IndexedCharacter> heap;	
	private int width;
	private static final int YOFFSET =100;
	private int maxDepth;
	private String totalHuffmanCode;
	String input;
	
	
	/**
	 * Creates a Huffman code tree component.
	 * @param s String to encode.
	 * @param width 
	 */
	public HuffmanTreeComponent(String s, int width)
	{
		totalHuffmanCode="";
		input =s;
		charSet = new HashSet<Character>();
		indexedChars = new ArrayList<IndexedCharacter>();
		this.width =width;
		for(int i=0;i<s.length();i++)
		{
			charSet.add(s.charAt(i));			
		}
		
		for(Character ch:charSet)
		{
			indexedChars.add(new IndexedCharacter(ch.charValue(),0,null,null));
		}
		
		for(IndexedCharacter iChar:indexedChars)
		{
			for(int i=0;i<s.length();i++)
			{
				if(iChar.getCharacter()==s.charAt(i))
				{
					iChar.incrementFreq();
				}
			}
		}
		
		IndexedCharacter[] charArray = new IndexedCharacter[indexedChars.size()];
		for(int i=0;i<charArray.length;i++)
		{
			charArray[i]=indexedChars.get(i);
		}
		
		heap = new MyMinHeap<IndexedCharacter>(charArray);
		
		while(heap.getSize()!=1)
		{			
			IndexedCharacter del1 = heap.deleteMin();			
			IndexedCharacter del2 = heap.deleteMin();
			
			heap.insert(new IndexedCharacter(null,del1.getFrequency()+del2.getFrequency(),del1,del2));
		}
		
	}
	
	/**
	 * Returns an array of the characters.
	 * @return
	 */
	public IndexedCharacter[] asciiArray()
	{
		IndexedCharacter[] array = new IndexedCharacter[128];
		
		for(int i =0;i<128;i++)
		{
			array[i]= new IndexedCharacter(null,0,null,null);
		}
		
		for(IndexedCharacter i:indexedChars)
		{
			array[((int)i.getCharacter())] = i;
		}
		return array;		
	}
	
	/**
	 * Sets the string that will be turned into the huffman coding tree
	 * @param s
	 */
	public void setString(String s)
	{
		input =s;
		totalHuffmanCode="";		
		charSet = new HashSet<Character>();
		indexedChars = new ArrayList<IndexedCharacter>();
		for(int i=0;i<s.length();i++)
		{
			charSet.add(s.charAt(i));			
		}
		
		for(Character ch:charSet)
		{
			indexedChars.add(new IndexedCharacter(ch.charValue(),0,null,null));
		}
		
		for(IndexedCharacter iChar:indexedChars)
		{
			for(int i=0;i<s.length();i++)
			{
				if(iChar.getCharacter()==s.charAt(i))
				{
					iChar.incrementFreq();
				}
			}
		}
		
		IndexedCharacter[] charArray = new IndexedCharacter[indexedChars.size()];
		for(int i=0;i<charArray.length;i++)
		{
			charArray[i]=indexedChars.get(i);
		}
		
		heap = new MyMinHeap<IndexedCharacter>(charArray);
		
		while(heap.getSize()!=1)
		{
			
			IndexedCharacter del1 = heap.deleteMin();			
			IndexedCharacter del2 = heap.deleteMin();		
			heap.insert(new IndexedCharacter(null,del1.getFrequency()+del2.getFrequency(),del1,del2));
		}
	}
	/**
	 * Paints the component.
	 */
	public void paintComponent(Graphics g)
	{
		
		Graphics2D g2 = (Graphics2D) g;			
		traverse(heap.findMin(),g2,1,0);
		
	}
	
	/**
	 * Goes through the tree and draws it.
	 * @param current the current node of the tree.
	 * @param g2 graphics object.
	 * @param mWidth width factor.
	 * @param depth the depth of the tree at the start always 0.
	 */
	private void traverse(IndexedCharacter current,Graphics2D g2, double mWidth, int depth)
	{		
		if(current!=null)
		{			
			g2.setFont(new Font(null,Font.BOLD,12));			
			if(current.getLeft()!=null)
			{
				current.getLeft().editHuffmanCode(current.getHuffmanCode().concat("0"));
				g2.drawLine((int)(width*mWidth),YOFFSET+(YOFFSET*depth),(int)(width*(mWidth-(1/Math.pow(2,depth+1)))),(int)(YOFFSET+(YOFFSET*(depth+1))));
				traverse(current.getLeft(),g2,mWidth-(1/Math.pow(2,depth+1)),depth+1);
			}
			if(current.getRight()!=null)
			{
				current.getRight().editHuffmanCode(current.getHuffmanCode().concat("1"));
				g2.drawLine((int)(width*mWidth),YOFFSET+(YOFFSET*depth),(int)((width)*(mWidth +(1/Math.pow(2,depth+1)))),(int)(YOFFSET+(YOFFSET*(depth+1))));
				traverse(current.getRight(),g2,mWidth +(1/Math.pow(2,depth+1)),depth+1);
			}
			Ellipse2D el = 	new Ellipse2D.Double((double)(int)(width*mWidth),YOFFSET+(YOFFSET*depth),20/(.2*depth+1),20/(.2*depth+1));
			g2.draw(el);
			g2.drawString(""+current.getCharacter()+" "+current.getFrequency(), (int)(width*mWidth),YOFFSET+(YOFFSET*depth));
			if(current.getLeft()==null&&current.getRight()==null)
			{				
				
				g2.setFont(new Font(null,Font.BOLD,10));
				g2.drawString(current.getHuffmanCode(), (int)(width*mWidth), YOFFSET+(YOFFSET*depth)+20);
				
				
				
			}
			if(depth>maxDepth)
			{
				maxDepth = depth;
			}
		}	
	}
	
	private void traverseTree(IndexedCharacter current, Character wanted)
	{
		if(current!=null)
		{
			if(current.getLeft()==null&&current.getRight()==null)
			{
				if(current.getCharacter().equals(wanted))
				{
					totalHuffmanCode+=current.getHuffmanCode();
				}
			}
			if(current.getLeft()!=null)
			{
				traverseTree(current.getLeft(),wanted);
			}
			if(current.getRight()!=null)
			{
				traverseTree(current.getRight(),wanted);
			}
		}
	}
	
	/**
	 * returns the full huffman code for the message.
	 * @return
	 */
	public String getTotalHuffmanCode()
	{
		for(int i=0;i<input.length();i++)
		{
			traverseTree(heap.findMin(),input.charAt(i));
		}
		return totalHuffmanCode;
	}

}
