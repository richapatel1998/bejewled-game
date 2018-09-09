package hw3;
import java.util.Scanner;
import java.util.ArrayList;
import api.GridPosition;
import api.Jewel;

import java.util.Arrays;

public class Game {

	private int rows, columns;
    private Jewel[][] jewels; //jewels is basically the grid but i just call it jewels

 
 
    
   
   
    public static void main(String[] args) {
    // descriptor for initial grid of a 3 x 4 game 
   	String[] desc =
    		{
    				"2 0 1 3", 
    				"1 0 1 3", 
    				"2 1 2 1"
    		};
    	JewelFactory generator = new JewelFactory(4);
    	Game g = new Game(desc, generator);
    	System.out.println(g.toString());
   	System.out.println(g.getJewel(2, 1)); // expected 1
   	g.setJewel(2, 1, new Jewel(3));
   	System.out.println(g.getJewel(2, 1)); // expected 3
    	g.setJewel(2, 1, null);
    	System.out.println(g.toString());
   	
   	Jewel[] arr = g.getCol(2);
   	System.out.println(Arrays.toString(arr));
   	Jewel[] testCol = Util.createFromString("5 6 7");
    	
    	
//    	2013 
//    	1013 
//    	2121
//   1
//   3
//    	2013 
//    	1013 
//    	2*21
    	
    	
    	
    	
//    	String[] desc =
//    		{
//    		"4 2 2 2 4 4 3 3 3 3 2",
//    		"4 2 2 2 4 4 3 3 3 3 2",
//    		"1 2 2 2 2 1 3 3 4 5 1",
//    		"1 2 2 2 2 1 3 3 4 5 1"
//    		};
//    		JewelFactory generator = new JewelFactory(6);
//    		Game g = new Game(desc, generator);
//    		ArrayList<GridPosition> result1 = g.findHorizontalRuns(6);
//    		ArrayList<GridPosition> result2 = g.findVerticalRuns(6);
//    		System.out.println(result1);
//    		System.out.println(result2);


    		//expected output
    		//[[(0,1) 2], [(0,2) 2], [(0,3) 2], [(0,6) 3], [(0,7) 3], [(0,8) 3], [(0,9) 3]]
    		//[[(0,6) 3], [(1,6) 3], [(2,6) 3], [(3,6) 3]]

    }
    
    
    
		//Constructs a game with the given number of columns and
    //rows that will use the given JewelFactory instance to create new jewels.
    		public Game(int width, int height, JewelFactory generator) 
    		{
    			rows = width;
    			columns = height;
    			generator.createGrid(rows, columns);
    			
    			
    		}
 
private JewelFactory factory;
//Constructs a game with size and initial configuration are determined by the given string array and
 //that will use the given Jewel Factory instance to create new jewels   		
    		public Game(java.lang.String[] descriptor, JewelFactory generator) {
    			jewels = Util.createFromStringArray(descriptor);
    		}
    		
    		
    		
    		//returns the jewel at the given row and columns
    		public Jewel getJewel(int row, int col) {
    			return jewels[row][col];
    			
    		}
    		//sets the Jewel at the given row and column.
    		public void setJewel(int row,int col, Jewel jewel) {
    			jewels[row][col] = jewel;
    			
    	    } 
    		
    
    //returns the width of the grid for this game which are the number of columns.
    		public int getWidth() {
    			return jewels[0].length;
    			//return columns which is width in this case
    		}
    
    		
    		//returns the height of the grid for this game which are number of rows.
    		public int getHeight() {
    		//return rows; which means length in this case
    			return jewels.length;

    }
    
	//returns the current score for this game    
	   public int getScore() {
	   	return 0;
	    	
	    }
	    
	    //returns a copy of the given row of the grid
	    public Jewel[] getRow(int row) {
	    	int col = this.getWidth();
	    	Jewel[] Row = new Jewel[col];
	    	for(int j = 0; j< col; j++) {
	    		Row[j] = jewels[row][j];
	    		
	    	}
	   return Row;
	    	
	    	
	    }
	    
	    //copies the given array values into the specified row of the grid
	    public void setRow(int row, Jewel[] arr) {
	    	for(int m = 0; m < jewels.length; m++) {
	    		jewels[m][row] = arr[m];
	    	}
	    
	    }
	    //returns a copy of the given column of the grid
	    public Jewel[] getCol(int col) {
	    	int row = this.getHeight();
	    	Jewel[] Col = new Jewel[row];
	    	for(int j = 0; j< col; j++) {
	    		Col[j] = jewels[col][j];
	    	}
	  return Col;
	    }
	    
	    
	    //copies the given array values into the specified column of the grid given array
	    public void setCol(int col, Jewel[] arr) {
	    	//I AM NOT SURE IF THIS IS RIGHT!! COME BACK TO IT
	    	for(int n = 0; n < jewels.length; n++) {
	    		jewels[n][col] = arr[n];
	    	}
	    	}

	
	    	
	    
	  
	    //exchanges the Jewels described by the given GridPositions, if possible.
	    //the caller is responsible for ensuring that the given positions are horizontally or vertically adjacent.
	    //the exchange is allowed only if one of the affected cells forms part of a run after the jewels
	    //are swapped. If so, the jewels for the cells are exchanged and the method returns true;
	    //otherwise, the method returns false. No other aspects of the game state are modified. 
	public boolean select(GridPosition c0, GridPosition c1) {

    Jewel j0 = c0.getJewel();
    Jewel j1 = c1.getJewel();
	    jewels[c0.row()][c0.col()] = j1;
    jewels[c1.row()][c1.col()] = j0;
	 	        
  	 boolean colEqual = c1.col() == c0.col();
	        // Checks if the row is equal to the previous one.
    	 boolean rowEqual = c1.row() == c0.row();

    // Checks if the c1row and c0 row is only one element away.
    	 boolean rowAdj = Math.abs(c1.row() - c0.row()) == 1;
    	 // Checks if the c1 col and c0 row is only one away.
    	 boolean colAdj = Math.abs(c1.col() - c0.col()) == 1;

    //Determines whether not the jewel is adjacent to another.
    if (rowAdj && colEqual) return true;
      if (colAdj && rowEqual) return true;

       jewels[c0.row()][c0.col()] = j0;
	    jewels[c1.row()][c1.col()] = j1;
       return false;
	    	// comparing the rows and columns to the previous ones

	    	
	    }
//	    Finds runs in the given row of the grid using the method Util.findRuns 
//	    The return value is a list containing a GridPosition object for each cell 
//	    	that is part of a run, in left-to-right order. This method does not modify the
//	    	grid or update the score.  
	    public java.util.ArrayList<GridPosition> findHorizontalRuns(int row){
	    	 ArrayList<GridPosition>list1 = new ArrayList<GridPosition>();
	    	 Jewel[] HorizontalArr = this.getRow(row);
	    	 ArrayList<Integer> location = new ArrayList<Integer>();
	    	 location = Util.findRuns(HorizontalArr);
	    	 
	    	 for(int n = 0; n < location.size(); n++) {
				GridPosition arrlist = new GridPosition(row,location.get(n), jewels[row][location.get(n)]);
	    		 list1.add(arrlist);
	    		 
	    		 
	    	 }
	    return list1;
				 
			 
	    }
	    
			 
	    public java.util.ArrayList<GridPosition> findVerticalRuns(int col){
////	    Finds runs in the given column of the grid using the method
	    	ArrayList<GridPosition> list = new ArrayList<>();
	    	ArrayList<Integer> listOFMatches = new ArrayList<>();
	    	Jewel[] lArray = this.getCol(col);
	    	listOFMatches = Util.findRuns(lArray); //local array
	    	Integer Arr_Matches[] = listOFMatches.toArray(new Integer[listOFMatches.size()]);
	    	///	    Util.findRuns The return value is a list containing a GridPosition object 
////    	for each cell that is part of a run, in top-to-bottom order. 
//    	This method does not modify the grid or update the score.
	    	
	    	for(int i = 0; i < listOFMatches.size(); i++) {
	    		GridPosition y = new GridPosition(Arr_Matches[i],col, getJewel(Arr_Matches[i], col));
	    		list.add(y);
	    	}
	    	return list;
	    	}
 
//	    Finds all horizontal and vertical runs, and returns a list containing 
//	    a GridPosition object for all cells that are part of a run. 
//	    The list is in no particular order and may contain duplicates.
//	    All grid positions that are part of a run are set to null 
//	    in the grid, and the score is updated.
//	   
	    public java.util.ArrayList<GridPosition> findAndMarkAllRuns(){
	    	 ArrayList<GridPosition> run = new ArrayList<GridPosition>();
	    	 
	    	 int r, s;
	    	 for(r = 0; r < this.getHeight(); r++) { //uses horizontal runs
	    		 run.addAll(this.findHorizontalRuns(r));
	    	 }
	    	 for(s = 0; s < this.getWidth(); s++) {
	    		 run.addAll(this.findVerticalRuns(s)); //uses vertical runs
	    		 
	    	 }
	    	 for(int k = 0; k < run.size(); k++)
	    	 {
	    		 //this uses all of the runs
	    		 this.setJewel(run.get(k).row(), run.get(k).col(), null);
	    	 }
	    	 System.out.println(Util.convertToString(jewels));
	    	return run;
	    }
	    //returns the run
	    
	    
	    
	    
	    
	    public java.util.ArrayList<GridPosition> shiftColumnDown(int col){
  	 ArrayList<GridPosition> shift = new ArrayList<GridPosition>();
//	    Shifts the Jewels in a given column downward using the method Util.shiftNonNullElements. 
	    Jewel [] down = new Jewel[this.getHeight()];
	    int count = 0; 
	    for(int t = 0; t < this.getHeight(); t++) {
	    	down[t]= jewels[t][col];			//
	    }
//	    After executing this method the null cells, if any, are at the top of the column. 
//	    The order of the Jewels is not changed. 

	    int num_null = down.length - Util.shiftNonNullElements(down).size();
	    Jewel [] list1 = new Jewel[num_null];
	    Jewel [] list2 = new Jewel [this.getHeight()-num_null];
	    //incrementing
	    for (int w = 0; w < num_null; w++) {
	    	list1[w] = null;
	    	System.out.println(list1[w]);
//	   The return value is a list containing a GridPosition object for each Jewel that was moved by this operation. The GridPosition's row and column should be the 
//	    	position of the moved Jewel when the operation completes.
	    }
	    	for(int f = 0; f < list2.length; f++) {
	    		list2[f] = list1[Util.shiftNonNullElements(list1).get(f)];
	    		System.out.println(list1[f]);
	    		
	    	}
	    	Jewel [] list3 = new Jewel[this.getHeight()];
	    	
	    	for(int f = 0; f < num_null; f++) {
	    		jewels[f][col] = list2[f];
	    		
	    	}
	    	for(int f = 0; f < list2.length; f++) {
	    		jewels[f+num_null][col]=list2[f];
	    	}
	    			
	    		
	    		
	    
	    
	    	
	    	
	    	return null;
}
	  
//	    Replaces each null cell in the grid with a new Jewel created by a call to this Game's JewelFactory. 
//	    The return value is a list containing a GridPosition object for each newly assigned cell.
	    
	    public java.util.ArrayList<GridPosition> fillAll(){
	    	 ArrayList<GridPosition> fillgame = new ArrayList<GridPosition>();
	    	 for(int b = 0; b < getHeight(); b++)
	    	 {
	    		 for(int c = 0; c < getWidth(); c++) {
	    			 if(jewels[b][c] == null) {
	    				jewels[b][c] = factory.generate();
	    				GridPosition list = new GridPosition(b,c,jewels[b][c]);
	    				fillgame.add(list);
	    			 }
	    		 }
	    	 }
	    	 
	    	return fillgame;
	    }
	    

	    //Returns a String representation of the grid for this game, with rows delimited by newlines.

	    public java.lang.String toString(){
	   return Util.convertToString(jewels);
	   
	    	
	 	
	    	
	  
	    }
}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    