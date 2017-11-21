/**
 * 
 */
package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * @author Andoni Aranguren
 *
 */
public class ButtonGrid {
	private JFrame frame=new JFrame(); //creates frame
	private JPanel borderPanel= new JPanel(new BorderLayout());
	private JPanel grid= new JPanel(); //names the grid of buttons
	private HexagonGrid hexaGrid;
    private Hexagon hex;
    private int windowH=750,windowL=750;
    private JPanel i=new JPanel();
    private Timer timer;
	 
	public ButtonGrid(int width, int length){ //constructor with 2 parameters
		int horizontal, vertical;
		hexaGrid= new HexagonGrid(width,length);
		
		horizontal=hexaGrid.getHorizontal();
        vertical=hexaGrid.getVertical();
        windowH=vertical*64;
        windowL=horizontal*64;
        
        hex=(Hexagon) hexaGrid.get(vertical/2, horizontal/2);
        hex=(Hexagon) hexaGrid.get(4, 1);
        hex.movement(2, 1);
        createGrid(horizontal,vertical);
        frame.add(borderPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(windowH,windowH);
		frame.invalidate();
		frame.validate();
		frame.repaint();
        frame.setVisible(true);
       
        timer.start();
	}
	private void createGrid(int vertical, int horizontal) {
        JPanel boxH = new JPanel(new GridLayout(1,horizontal));
        JPanel boxV;
        
        for(int x=0; x<horizontal; x++){
            boxV= new JPanel();
            boxV.setLayout(new BoxLayout(boxV,BoxLayout.Y_AXIS));
            for(int y=0; y<vertical; y++){
            	JButton j=filledHex(x, y);
            	if(x%2!=0&&y==0)
            		boxV.add(Box.createVerticalStrut(j.getHeight()/2));
            	boxV.add(j);
            }
        	boxV.setBackground(Color.BLACK);
            boxH.add(boxV);
        }
        grid.add(BorderLayout.WEST,boxH);
        borderPanel.add(grid);
        timer = new Timer(100, new ActionListener() {
          int num = 0;
          int tmp=500;
          public void actionPerformed(ActionEvent e) {
              if (num > tmp+16) {
                  ((Timer) e.getSource()).stop();
              } else {
//              	if(num>tmp)
//              		frame.setSize((255-((num-tmp)*(num-tmp)))*3, (255-(num-tmp)*(num-tmp))*3);
//              	else 
              		frame.setSize(windowH,windowH);
              	hex.movement(2, 0);
                  num++;
          		hex=(Hexagon) hex.getSide(1).getOtherSide(hex);
          		grid.removeAll();
          		hex.movement(2, 1);
          		createGrid(vertical, horizontal);
          		frame.invalidate();
          		frame.validate();
          		frame.repaint();

                  frame.setVisible(true);
              }
          }
      });
	}
	/**
	 * @param x
	 * @param y
	 */
	private JButton filledHex(int x, int y) {
		Polygon2 in=hexaGrid.get(x, y);
		
		ImageIcon icon= new ImageIcon("src/logic/hexagonRegular.png");
		JButton button= new JButton(icon);
		button.setBackground(in.getInside().getTerrain());
    	button.setToolTipText((in.getInside().getText()));
    	button.setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    	button.setPreferredSize(new Dimension(icon.getIconWidth()-2, icon.getIconHeight()-2));
    	button.setBorderPainted(false);
		return button;
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				new ButtonGrid(6,6);
				}
				});
	}
}
