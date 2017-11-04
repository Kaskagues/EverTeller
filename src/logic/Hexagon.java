/**
 * 
 */
package logic;

/**
 * @author Andoni Aranguren
 *
 */
class Hexagon extends Polygon2 {

	public Hexagon() {
		super(6);
	}

	/* (non-Javadoc)
	 * @see logic.Polygon#addSide(int, logic.Side)
	 */
	@Override
	protected void addSide(int pWhichSide, Polygon2 pPolygon) {
		//Check if the given side is null to create one if so
		Vertex[] auxVertex= new Vertex[2];
		Side auxSide;
		
		//If this doesn't have that sides, it creates it
		auxSide= this.getSide(pWhichSide);
		if(auxSide==null) {
			auxSide=new Side(this);
		}
		auxSide.addPolygon(pPolygon, 1);

		//If this polygons has this side's vertex, it uses them, if not, new ones
		auxVertex[0]=vertex[pWhichSide];
		auxVertex[1]=vertex[(pWhichSide+1>=sideAmount)?
								0:pWhichSide+1];
		if(auxVertex[0]==null)
			auxVertex[0]=new Vertex(this,auxSide);
		if(auxVertex[1]==null)
			auxVertex[1]=new Vertex(this,auxSide);
		
		//Add the other polygon to the vertex
		auxVertex[0].addPolygon(pPolygon);
		auxVertex[1].addPolygon(pPolygon);
		
		//Make sure both have the new side
		this.setSide(pWhichSide, auxSide);
		pPolygon.setSide(mirrorSide(pWhichSide),auxSide);
	}
	protected void fillSides() {
		for(int i=0; i<sideAmount;i++) {
			if(sides[i]==null) addSide(i, null);
		}
	}

	/**
	 * @param sideN
	 * @return
	 */
	public boolean hasSide(int sideN) {
		return sides[sideN]!=null;
	}
	
}
