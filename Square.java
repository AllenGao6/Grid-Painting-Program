import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Stack;

public class Square
{
	private int[][] grid = new int[16][16];
	private int[][] copy = new int[16][16];
	private int[][] paste = new int[16][16];
	private int[][] start = new int[16][16];
	private int red;
	private int green;
	private int blue;
	private Color[] palette = new Color[12];
	Color white;
	Color black;
	BufferedImage picture;
	Graphics2D ig2;
	Color purple = new Color(204,0,102);
	Color lightgreen = new Color(178,255,102);
	Color bloodred = new Color(204,0,0);
	Color skyblue = new Color(153,255,255);
	Color darkgreen = new Color(51,102,0);
	Color darkpurple = new Color(102,0,102);
	Stack<int[][]> undolist = new Stack<int[][]>();
	Stack<int[][]> redolist = new Stack<int[][]>();
	public Square() {
		//grid = new int[16][16];
		
		white = Color.white;
		black = Color.black;
		palette[0] = Color.white;
		palette[1] = Color.blue;
		palette[2] = Color.green;
		palette[3] = Color.red;
		palette[4] = Color.black;
		palette[5] = Color.yellow;
		palette[6] = Color.orange;
		palette[7] = purple;
		palette[8] = lightgreen;
		palette[8] = bloodred;
		palette[9] = skyblue;
		palette[10] = darkgreen;
		palette[11] = darkpurple;
		
		for(int i = 0; i < start.length; i++)
            {
                for (int j = 0; j < start[i].length; j++)
                {
                    start[i][j] = 0;
                }
            }
		undolist.add(start);
		
		
		//undolist = new Stack<int[][]>();
	    //redolist = new Stack<int[][]>();
	}
	
	public void insertSquare(int row, int col, int color) {
		grid[row][col] = color;	
		
		/*for(int[] nums : grid)
		{
			for(int each : nums)
			{
				System.out.print(each + " ");
			}
			System.out.println();
		}*/
	        int[][] copy = new int[grid.length][];
            for(int i = 0; i < grid.length; i++)
            {
                copy[i] = new int[grid[i].length];
                for (int j = 0; j < grid[i].length; j++)
                {
                    copy[i][j] = grid[i][j];
                }
            }
		    /*for(int[] nums : copy)
		    {
			    for(int each : nums)
			    {
				    System.out.print(each + " ");
			    }
			    System.out.println();
		    }*/
			undolist.add(copy);
		System.out.println("----------------------------");
	}
	
	public void clear() {
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c] = 0;
			}
		}
		
		int[][] copy = new int[grid.length][];
            for(int i = 0; i < grid.length; i++)
            {
                copy[i] = new int[grid[i].length];
                for (int j = 0; j < grid[i].length; j++)
                {
                    copy[i][j] = grid[i][j];
                }
            }
            undolist.add(copy);
	}
	
	public void drawMe(Graphics g, int x, int y){
		g.setColor(Color.black);
		g.fillRect(x, y, (45 * 16) + (9 * 10), (45 * 16) + (9 * 10));
		//System.out.println("Get it");
        for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				g.setColor(palette[grid[i][j]]);
				g.fillRect(x + 5 + (50 * j),y + (50 * i) + 5,45,45);
			}
		}
		
	}
	
	public void Undo(){
	    //undolist.pop();
	    if(undolist.size()>1){
	        undolist.pop();
	        paste = undolist.peek();
	        redolist.add(paste);
	        //paste = undolist.peek();
	        
            for(int i = 0; i < paste.length; i++)
            {
                for (int j = 0; j < paste[i].length; j++)
                {
                    grid[i][j] = paste[i][j];
                }
            }
			/*for(int[] nums : grid)
		    {
			for(int each : nums)
			{
				System.out.print(each + " ");
			}
			System.out.println();
		}*/
	        //grid = undolist.peek();
	        System.out.println(redolist);
	        //System.out.println(grid);
	    }
	    else{
	        System.out.println("no more undo");
	    }
	    
	}
	
	public void Redo(){
	    if(redolist.size()>1){
	        redolist.pop();
	        paste = redolist.peek();
	        undolist.add(paste);
	        for(int i = 0; i < paste.length; i++)
            {
                for (int j = 0; j < paste[i].length; j++)
                {
                    grid[i][j] = paste[i][j];
                }
            }
            System.out.println("Redo !!!");
	    }
	}
	
	public void createImage(int width, int height, int pictureCount)
	{
		try {
			picture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			ig2 = picture.createGraphics();	
			ig2.setColor(Color.white);
   			ig2.fillRect(0,0,1200,1200);
   			drawMe(ig2, 51, 47);
			ImageIO.write(picture, "PNG", new File("images/picture" + pictureCount + ".PNG"));
		} catch(IOException ie) {
			ie.printStackTrace();
		}	
	}
	
	public void drawPalette(Graphics g, int x, int y) 
	{
		g.setColor(Color.gray);
		g.fillRect(x-10, y-10, 40 + (2 * 10), (40 * (palette.length - 1)) + (10 * (palette.length)));
		for(int i = 1; i < palette.length; i++) 
		{
				g.setColor(palette[i]);
				g.fillRect(x, y + (50 * (i-1)), 40, 40);
		}
	}
}