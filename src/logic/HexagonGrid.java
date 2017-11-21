/**
 * 
 */
package logic;

/**
 * @author Andoni Aranguren
 *
 */
class HexagonGrid {
	private Hexagon[][] grid;
	private int dimensionX,dimensionY;
	private int sideAmount;
//	private int topSide=0;
	
	public HexagonGrid(int width, int length) {
		dimensionX=width*2;
		dimensionY=length*2;
		if(dimensionX>1&&dimensionY>1) {
			init();
			createGrid();
		} else
			try {
				throw new Exception("At least one dimension of the grid is too small");
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.out.println("What dimensions are those? Do you even know physics?");
				System.out.println("I can't create a planet with only less than... wait I have a map for you");
				initException();				
			}
	}
	private void init() {
		System.out.println("Dimensions: X="+(dimensionX)+" Y="+(dimensionY));
		//The +2 will make the "warping horizons"
		//It's a "sphere" like grid with geodesics in all directions
		dimensionX= dimensionX+2;
		dimensionY= dimensionY+2;
		grid= new Hexagon[dimensionX][dimensionY];
		sideAmount=6;
	}
	private void initException() {
		dimensionX= 1;
		dimensionY= 1;
		init();
		Hexagon hex = new Hexagon();
		for(int y=0; y<dimensionY;y++)
			for(int x=0; x<dimensionX;x++)
				addHexToGrid(x, y, hex);
		addSides(1, 1);
	}
	public void createGrid() {
		Hexagon hex;
		
		//Filling grid
		for(int coordY=1; coordY<dimensionY-1; coordY++) {
			for(int coordX=1; coordX<dimensionX-1; coordX++) {
				hex= new Hexagon();
				addHexToGrid(coordX,coordY,hex);
				addWarpingHorizon(coordX,coordY,hex);
			}
		}
			
		//Link the hexagons
		for(int coordY=1; coordY<dimensionY-1; coordY++) {
			for(int coordX=1; coordX<dimensionX-1; coordX++) {
				addSides(coordX,coordY);
			}
		}
	}
	
	private void addHexToGrid(int coordX, int coordY, Hexagon hex) {
		if(hex.inside.getText()==null)hex.inside.setText("("+(coordX-1)+","+(coordY-1)+")");
		grid[coordX][coordY]=hex;
	}
	private void addWarpingHorizon(int coordX, int coordY, Hexagon hex) {
		//First and last column
		if(coordX==1)
			addHexToGrid(dimensionX-1,coordY,hex);
		else if(coordX==dimensionX-2)
			addHexToGrid(0, coordY, hex);
		
		//Top and bottom rows
		if(coordY==1)
			addHexToGrid(fixcoordenates(coordX+(dimensionX-2)/2), dimensionY-1, hex);
		else if(coordY==dimensionY-2)
			addHexToGrid(fixcoordenates(coordX+(dimensionX-2)/2), 0, hex);
		
		//Four corners
		if(coordX==(dimensionX-2)/2 && coordY==1) {
			addHexToGrid(0, dimensionY-1, hex);
		}else if(coordX-1==(dimensionX-2)/2 && coordY==1) {
			addHexToGrid(dimensionX-1, dimensionY-1, hex);
		}else if(coordX==(dimensionX-2)/2 && coordY==dimensionY-2) {
			addHexToGrid(0, 0, hex);
		}else if(coordX-1==(dimensionX-2)/2 && coordY==dimensionY-2) {
			addHexToGrid(dimensionX-1, 0, hex);
		}
		
	}
	private int fixcoordenates(int i) {
		return (i>=dimensionX-1 ? i-dimensionX+2 : i);
	}
	private void addSides(int coordX, int coordY) {
		Hexagon hex= grid[coordX][coordY];
		
		for(int sideN=0; sideN<6; sideN++) {
			if(!hex.hasSide(sideN)) {
				int[] facingCoords=sidePointigCoords(sideN,new int[]{coordX,coordY});
				hex.addSide(sideN, grid[facingCoords[0]][facingCoords[1]]);
			}
		}
	}
	private int[] sidePointigCoords(int sideN, int[] coords) {
		int[] facing= new int[]{coords[0],coords[1]};
		
		//Facing up or down is always the same
		if(sideN==0) {
			facing=new int[]{coords[0],coords[1]-1};
		} else if(sideN==3) {
			facing=new int[]{coords[0],coords[1]+1};
		} 
		//Diagonals are different in odds or evens
		else if(coords[0]%2==0) {
			if(sideN==1) {
				facing=new int[]{coords[0]+1,coords[1]-1};
			} else if(sideN==5) {
				facing=new int[]{coords[0]-1,coords[1]-1};
			} else if(sideN==2) {
				facing=new int[]{coords[0]+1,coords[1]};
			} else if(sideN==4) {
				facing=new int[]{coords[0]-1,coords[1]};
			}
		}else {
			if(sideN==1) {
				facing=new int[]{coords[0]+1,coords[1]};
			} else if(sideN==5) {
				facing=new int[]{coords[0]-1,coords[1]};
			} else if(sideN==2) {
				facing=new int[]{coords[0]+1,coords[1]+1};
			} else if(sideN==4) {
				facing=new int[]{coords[0]-1,coords[1]+1};
			}	
		}
		return facing;
	}
	public Polygon2 get(int x, int y){
//		x++;
//		y++;
		if(x<0||x>dimensionX-1||y<0||y>dimensionY-1) {
			return null;
		}else
			return grid[x][y];
	}
	public void toggleMovement(int pKoordX, int pKoordY, int pPower, int pTerrain) {
		grid[pKoordX][pKoordY].movement(pPower,pTerrain);
	}
	public int getHorizontal() {
		return dimensionX;
	}
	public int getVertical() {
		return dimensionY;
	}
}