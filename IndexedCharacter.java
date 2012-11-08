
public class IndexedCharacter implements Comparable<IndexedCharacter>
{
	
	private Character dataChar;
	private int freq;
	private IndexedCharacter left;
	private IndexedCharacter right;
	private String huffmanCode;
	
	/**
	 * Creates an indexed character object.
	 * @param inputChar the character.
	 * @param initialFreq the frequency of the character in the text.
	 * @param left the left child.
	 * @param right the right child.
	 */
	public IndexedCharacter(Character inputChar, int initialFreq, IndexedCharacter left, IndexedCharacter right)
	{
		if(inputChar!=null)
		{
			dataChar = inputChar.charValue();
		}
		freq = initialFreq;
		this.right = right;
		this.left=left;
		huffmanCode="";
	}
	/**
	 * Increases the frequency of the character.
	 */
	public void incrementFreq()
	{
		freq++;
	}
	
	/**
	 * returns the frequency of the object in the text.
	 * @return the frequency as an integer.
	 */
	public int getFrequency()
	{
		return freq;
	}
	
	/**
	 * Returns the character stored in the object.
	 * @return the character as Character object.
	 */
	public Character getCharacter()
	{
		if(dataChar!=null)
		return dataChar;
		
		return null;
	}
	
	/**
	 * Returns the left child.
	 * @return the left child.
	 */
	public IndexedCharacter getLeft()
	{
		return left;
	}
	
	/**
	 * Returns the right child.
	 * @return the right child.
	 */
	public IndexedCharacter getRight()
	{
		return right;
	}
	/**
	 * The huffman code for the character.
	 * @return the huffman code as a string.
	 */
	public String getHuffmanCode()
	{
		return huffmanCode;
	}
	
	/**
	 * Changes the huffman code.
	 * @param s A string that should be either 0 or 1.
	 */
	public void editHuffmanCode(String s)
	{
		huffmanCode = huffmanCode.concat(s);
	}

	/**
	 * Compares two IndexedCharacter objects based on frequency.
	 */
	public int compareTo(IndexedCharacter other) 
	{
		if(this.freq>((IndexedCharacter)(other)).getFrequency())
			return 1;
		if(this.freq<((IndexedCharacter)(other)).getFrequency())
			return -1;
		
		return 0;
	}

}
