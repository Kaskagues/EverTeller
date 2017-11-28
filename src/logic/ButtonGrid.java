/**

 * 
 */
package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

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
	private Container borderPanel= frame.getContentPane();
	private JPanel grid= new JPanel(); //names the grid of buttons
	private HexagonGrid hexaGrid;
    private Hexagon hex,hex2;
    private int windowH=500,windowL=500,escale;
    private Timer timer;
	private ImageIcon icon;
	 
	public ButtonGrid(int height, int width){ //constructor with 2 parameters
		int horizontal, vertical;
		hexaGrid= new HexagonGrid(height,width);
		
		horizontal=hexaGrid.getHorizontal();
        vertical=hexaGrid.getVertical();

		float sizeOfGrid=1.0f;
		escale=(int) ((windowH*sizeOfGrid)/(vertical+0.5f));
		
    	icon = new ImageIcon(new ImageIcon("src/logic/hexagonRegular.png").getImage().getScaledInstance(escale, escale,  java.awt.Image.SCALE_SMOOTH));
    	
        hex=(Hexagon) hexaGrid.get(vertical/2, horizontal/2);
        hex2=(Hexagon) hexaGrid.get(vertical/2,1);
        createGrid(vertical,horizontal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(windowH,windowL);
		frame.invalidate();
		frame.validate();
		frame.repaint();
        frame.setVisible(true);
       
//        timer.start();
	}
	private void createGrid(int vertical, int horizontal) {
        JPanel boxH = new JPanel();
        JPanel boxV;
        
        boxH.setBackground(Color.GRAY);
        boxH.setLayout(new BoxLayout(boxH,BoxLayout.X_AXIS));
        for(int x=0; x<horizontal; x++){
            boxV= new JPanel();
            boxV.setLayout(new BoxLayout(boxV,BoxLayout.Y_AXIS));
            boxV.setBackground(Color.BLACK);
            for(int y=0; y<vertical; y++){
            	JButton j=filledHex(x, y);
            	if(x%2!=0&&y==0)
            			boxV.add(Box.createRigidArea(new Dimension(0,j.getHeight()/2)));
            		
            	boxV.add(j);
            	
            	if(x%2==0&&y==vertical-1)
        			boxV.add(Box.createRigidArea(new Dimension(0,j.getHeight()/2)));
            }
            boxH.add(boxV);
        }
        grid.add(boxH);
        borderPanel.add(BorderLayout.CENTER,grid);
        timer = new Timer(100, new ActionListener() {
          int num = 0;
          int tmp=500;
          int power=2;
          public void actionPerformed(ActionEvent e) {
              if (num > tmp+16) {
                  ((Timer) e.getSource()).stop();
              } else {
//              	if(num>tmp)
//              		frame.setSize((255-((num-tmp)*(num-tmp)))*3, (255-(num-tmp)*(num-tmp))*3);
//              	else 
//              		frame.setSize(windowH,windowH);
              	hex.movement(power, 0);
//              	hex2.movement(1, 0);
                  num++;
//          		hex=(Hexagon) hex.getSide(new Random().nextInt(6)).getOtherSide(hex);
                  hex=(Hexagon) hex.getSide(1).getOtherSide(hex);
//          		hex2=(Hexagon) hex2.getSide(1).getOtherSide(hex2);
          		grid.removeAll();
          		hex.movement(power, 1);
//              	hex2.movement(1, 1);
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
//		StretchIcon iconS = new StretchIcon(icon.getImage());
//		Image img = icon.getImage();
//		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//		Graphics g = bi.createGraphics();
//		g.drawImage(img,escale,escale,img.getHeight(null),img.getWidth(null), null,null);
//		icon = new ImageIcon(bi);
		

		Polygon2 in=hexaGrid.get(x, y);
		JButton button= new JButton(icon);
//		iconS.setImageObserver(button);
//		button.setIcon(iconS);
		
    	button.setSize(new Dimension(escale, escale));
    	button.setPreferredSize(new Dimension(escale, escale));

		button.setBackground(in.getInside().getTerrain());
    	button.setToolTipText((in.getInside().getText())+" "+in.getInside().getHeight());
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
