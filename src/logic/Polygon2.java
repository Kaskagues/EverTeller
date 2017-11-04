/**
 * 
 */
package logic;

/**
 * @author Andoni Aranguren
 * @param <Inside>
 *
 */
abstract class Polygon2 {
	
	protected Vertex[] vertex;
	protected int sideAmount;
	protected Side[] sides;
	protected Inside inside;
	
	protected Polygon2(int pSides) {
		sideAmount=pSides;
		vertex= new Vertex[sideAmount];
		sides= new Side[sideAmount];
		inside= new Inside();
	}
	/**
	 * Pre: pWhichSide gives the location of the side this polygon needs the pLinker to to be added
	 * Post: It will overwrite the pWhichSide-th side of the polygon and the vertex
	 * 
	 * It's assumed that the polygon that is trying to get linked with this has calculated
	 * where in this polygon should the linker go. Example: P1 exits North -> P2 enters South
	 */
	abstract protected void addSide(int pWhichSide, Polygon2 pLinker);
	/**
	 * @return the inside
	 */
	public Inside getInside() {
		return inside;
	}
	/**
	 * @param inside the inside to set
	 */
	public void setInside(Inside inside) {
		this.inside = inside;
	}
	/**
	 * @param i
	 * @return
	 */
	public Side getSide(int i) {
		return sides[i];

}
	/**
	 * @param i
	 */
	public void movement(int pPower, int pTerrain) {
		inside.changeTerrain(pTerrain);
		if(pPower>0) {
			Polygon2 poly=null;
			pPower--;
			for(Side s: sides) {
				try {
					poly=s.getOtherSide(this);
				} catch (Exception e) {
					System.err.println("Ez dago");
				}
				poly.movement(pPower,pTerrain);
			}
		}
	}
	protected int mirrorSide(int pSide) {
		return correct(pSide+sideAmount/2);
	}
	private int correct(int pSide) {
		if(pSide>=sideAmount)
			pSide= pSide-sideAmount;
		else if(pSide<0)
			pSide=pSide+sideAmount;
		return pSide;
	}
	public void print() {
		System.out.println("Polygon name: "+ inside.getText()+" Goes to: ");
		int i=0;
		for(Side s: sides) {
			i++;
			System.out.println(i+"Polygon name: "+ s.getOtherSide(this).getInside().getText());
		}
	}
	public void setSide(int pWhichSide, Side pSide) {
		sides[pWhichSide]=pSide;		
	}
	public String toString() {
		return getInside().getText();
	}
}