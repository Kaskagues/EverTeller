/**
 * 
 */
package logic;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * @author Andoni Aranguren
 *
 */
class Inside {
	private String text;
	private int terrain=0;

	java.util.Random rand= new java.util.Random();
	float r = rand.nextFloat();
	float g = rand.nextFloat();
	float b = rand.nextFloat();

    Color colors[]={Color.GREEN,Color.RED};
    
	public Inside() {
		}
	/**
	 * @param string
	 */
	public void setText(String string) {
		text=string;
	}
	public String getText() {
		return text;
	}
	/**
	 * 
	 */
	public void changeTerrain(int pNumber) {

		r = rand.nextFloat();
		g = rand.nextFloat();
		b = rand.nextFloat();
		
		terrain=pNumber;
	}
	/**
	 * @return
	 */
	public Color getTerrain() {
		
		return new Color(r, g, b);
//		return colors[terrain];
	}

}
