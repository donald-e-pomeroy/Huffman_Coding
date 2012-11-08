import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.util.*;
import javax.swing.*;


public class HuffmanCodingGUI
{
	public static void main(String[] args)
	{
	final JFrame frame= new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	 final JPanel myTreePanel = new JPanel(new FlowLayout());
	 final HuffmanTreeComponent myTree = new HuffmanTreeComponent("The quick brown fox jumps over the lazy dog. THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG. !@#$%%^&*()_+",2500);
	 myTree.setPreferredSize(new Dimension(10000,8000));
	 myTree.setVisible(true);	
	 myTreePanel.add(myTree);
	 
	 final JTextArea myTextArea = new JTextArea("The quick brown fox jumps over the lazy dog. THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG. !@#$%%^&*()_+",50,50);
	 myTextArea.setLineWrap(true);
	 myTextArea.setWrapStyleWord(true);

	 JScrollPane myScroll = new JScrollPane(myTextArea);
	 myScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	 myScroll.setPreferredSize(new Dimension(100,100));
	 myScroll.setViewportView(myTextArea);
	 
	 final JScrollPane myTreeScroll = new JScrollPane(myTreePanel);
	 myTreeScroll.setPreferredSize(new Dimension(10000,8000));
	 myTreeScroll.setViewportView(myTreePanel);
	 
	 frame.add(myTreeScroll,BorderLayout.CENTER);
	 
	 final JButton myButton = new JButton("Press to Display Tree");
	 
	
	 myButton.addActionListener(new ActionListener()
	 {
		public void actionPerformed(ActionEvent e)
		{			
			myTree.setString(myTextArea.getText());
			myTreePanel.revalidate();					
			myTree.setVisible(true);												
			myTree.repaint();
			
			
			IndexedCharacter[] arr=myTree.asciiArray();
			JTextArea windowText = new JTextArea();
			windowText.setLineWrap(true);
			windowText.setWrapStyleWord(true);
			
			for(int i=0;i<128; i++)
			{
				if(arr[i].getFrequency()!=0)
				{
					windowText.setText(windowText.getText()+" ASCII: "+i+ " Character: "+ arr[i].getCharacter() + " Frequency "+arr[i].getFrequency()+"\n");
				}
			}
			JScrollPane myWindowScroll = new JScrollPane(windowText);			
			myWindowScroll.setPreferredSize(new Dimension(500,100));
			myWindowScroll.setViewportView(windowText);
			JOptionPane.showMessageDialog(null,myWindowScroll);
			System.out.println(myTree.getTotalHuffmanCode());
			
							
		}		
	 });
	 
	 
		
	 
	 myTreeScroll.getVerticalScrollBar().addAdjustmentListener( new AdjustmentListener()
	 {		
		public void adjustmentValueChanged(AdjustmentEvent e)
		{			
			myTree.setString(myTextArea.getText());
 			myTreePanel.revalidate();															
			myTree.repaint();
		}		 
	 });
	 
	 myTreeScroll.getHorizontalScrollBar().addAdjustmentListener( new AdjustmentListener()
	 {		
		public void adjustmentValueChanged(AdjustmentEvent e)
		{			
			myTree.setString(myTextArea.getText());
 			myTreePanel.revalidate(); 														
			myTree.repaint();
		}		 
	 });
	 
	 frame.addComponentListener(new ComponentAdapter()
	 {
		public void componentResized(ComponentEvent ev)
		{			
			myTree.setString(myTextArea.getText());											
			myTree.repaint();	
		}
		 
	 });
	 
	
	
	 frame.add(myScroll,BorderLayout.NORTH);
	 frame.add(myButton, BorderLayout.SOUTH);
	 frame.setSize(1000,800);
	 frame.setVisible(true);
	 

	}

}
