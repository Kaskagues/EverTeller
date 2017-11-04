/**
 * 
 */
package logic;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Andoni Aranguren
 * @param <Inside>
 *
 */
class Side {
	//In a sided there are going to be only 2 vertex;
	private Vertex[] vertex;
	private Polygon2[] polygons;
	private Inside inside;

	public Side(Polygon2 pInnerPolygon) {
		polygons= new Polygon2[2];
		vertex= new Vertex[2];
		polygons[0]=pInnerPolygon;
	}
	/**
	 * @return the vertex
	 */
	public Vertex[] getVertex() {
		return vertex;
	}
	/**
	 * @param vertex the vertex to set
	 */
	public void setVertex(Vertex[] vertex) {
		this.vertex = vertex;
	}
	public void addPolygon(Polygon2 pPolygon, int i) {
		if(i<2&&i>=0) polygons[i]=pPolygon;
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
	public Polygon2 getOtherSide(Polygon2 pPolygon){
		System.out.println(this);
		if(pPolygon!=polygons[0]&&pPolygon!=polygons[1]) {
			try {
				throw new Exception("The polygon you are sending is not in this side");
			} catch (Exception e) {
				return null;
			}
		}
		else
			return (pPolygon==polygons[0]? polygons[1]:polygons[0]);
	}
	
	public String toString() {
		return (polygons[0]+" -> "+polygons[1]);
	}
}
