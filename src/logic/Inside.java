/**
 * 
 */
package logic;

import javax.swing.ImageIcon;

/**
 * @author Andoni Aranguren
 *
 */
class Inside {
	private String text;
	private int terrain=0;

    ImageIcon icons[][] = new ImageIcon[2][2];
    
	public Inside() {
		icons[0][0] = new ImageIcon("src/logic/hexagonGreen0.png");
		icons[0][1] = new ImageIcon("src/logic/hexagonGreenB0.png");
		
		icons[1][0] = new ImageIcon("src/logic/hexagonPink0.png");
		icons[1][1] = new ImageIcon("src/logic/hexagonPinkB0.png");
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
		terrain=pNumber;
	}
	/**
	 * @return
	 */
	public ImageIcon[] getTerrain() {
		return icons[terrain];
	}

}
