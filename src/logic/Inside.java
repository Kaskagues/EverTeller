/**
 * 
 */
package logic;

import java.awt.Color;
import java.util.Vector;

import javax.swing.ImageIcon;

/**
 * @author Andoni Aranguren
 *
 */
class Inside {
	private String text;
	private int terrain=0;

	java.util.Random rand= new java.util.Random();
	float r = 0.100f;//rand.nextFloat();
	float g = 0.100f;//rand.nextFloat();
	float b = 0.100f;//rand.nextFloat();

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
		if(pNumber==0) {
			r=0.1f;
			g=0.1f;
			b=0.1f;
		}else {
			r=0.5f;
			g=0.3f;
			b=0.1f;

			r = rand.nextFloat();
			g = rand.nextFloat();
			b = rand.nextFloat();
		}
		
		terrain=pNumber;
	}
	/**
	 * @return
	 */
	public Color getTerrain() {
//		changeTerrain(1);
		return new Color(r, g, b);
//		return colors[terrain];
	}

}
