/**
 * 
 */
package logic;

import java.util.ArrayList;

/**
 * @author Andoni Aranguren
 * 
 */
class Vertex {
	
	private ArrayList<Side> sides;
	private ArrayList<Polygon2> polygons;
	protected int polygonsAmount;
	private Inside inside;
	
	public Vertex(Polygon2 pPolygon, Side pSide) {
		sides = new ArrayList<Side>();
		polygons = new ArrayList<Polygon2>();
		polygons.add(pPolygon);
		sides.add(pSide);
	}
	/**
	 * @return the inside
	 */
	public Inside getInside() {
		return inside;
	}
	/**
	 * @param inside the inside to set
	 */
	public void setInside(Inside pInside) {
		this.inside = pInside;
	}
	/**
	 * @param side
	 * @param side2
	 */
	public void addSide(Side pSide) {
		sides.add(pSide);
	}
	public void addPolygon(Polygon2 pPolygon) {
		polygons.add(pPolygon);
	}
	
}
