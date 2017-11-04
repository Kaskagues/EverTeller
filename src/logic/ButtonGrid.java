/**
 * 
 */
package logic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	JFrame frame=new JFrame(); //creates frame
	JPanel grid; //names the grid of buttons
    HexagonGrid hexaGrid;
    Hexagon hex;
    JPanel i=new JPanel();
    Timer timer;
	 
	public ButtonGrid(int width, int length){ //constructor with 2 parameters
		int horizontal, vertical;
		hexaGrid= new HexagonGrid(width,length);
		
		horizontal=hexaGrid.getHorizontal();
        vertical=hexaGrid.getVertical();
                
        hex=(Hexagon) hexaGrid.get(vertical/2, vertical/2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(750, 750);
        frame.setVisible(true);
        createGrid(horizontal,vertical);
       
        timer.start();
	}
	/**
	 * 
	 */
	private void createGrid(int vertical, int horizontal) {
        JPanel j = new JPanel(new GridLayout(vertical,horizontal));
        JButton [][] gridAux=new JButton[vertical][horizontal]; //allocate the size of grid
        
        for(int y=0; y<vertical; y++){ 
                for(int x=0; x<horizontal; x++){
                	Polygon2 in=hexaGrid.get(x, y);
                	
                	gridAux[y][x]= new JButton(in.getInside().getTerrain()[(x % 2==0? 0:1)]);
                	gridAux[y][x].setToolTipText((in.getInside().getText()));
                	j.add(gridAux[y][x]);
                }
        }
        grid=j;
        frame.add(grid); 
        timer = new Timer(100, new ActionListener() {
            int num = 0;
            int tmp=500;
            public void actionPerformed(ActionEvent e) {
                if (num > tmp+16) {
                    ((Timer) e.getSource()).stop();
                } else {
//                	if(num>tmp)
//                		frame.setSize((255-((num-tmp)*(num-tmp)))*3, (255-(num-tmp)*(num-tmp))*3);
//                	else 
                		frame.setSize(255*3, 255*3);
                	hex.movement(1, 0);
                    num++;
            		hex=(Hexagon) hex.getSide(1).getOtherSide(hex);
            		frame.remove(grid);
            		hex.movement(1, 1);
            		createGrid(vertical, horizontal);
            		frame.invalidate();
            		frame.validate();
            		frame.repaint();
                }
            }
        });
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				new ButtonGrid(13,13);
				}
				});
	}
}
