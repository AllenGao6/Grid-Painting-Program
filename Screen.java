import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

public class Screen extends JPanel implements ActionListener, MouseListener {	
	private JButton clearGrid;
	private JButton saveImage;
	private JButton undo;
	private JButton redo;
	private int gridX = 65;
	private int gridY = 55;
	private boolean check = false;
	private boolean redocheck = false;
	
	Square sq = new Square();
	
	int selectColor = 0;
	int drawX = 0;
	int drawY = 0;
	
	private int pictureCount = 1;
		
	public Screen() {
	    
		addMouseListener(this);
		this.setLayout(null);
		
		clearGrid = new JButton("Clear Button");
		clearGrid.setBounds(1000,50,150,50);
		clearGrid.addActionListener(this);
		this.add(clearGrid);
		//clearGrid.setOpaque(false);
		//clearGrid.setContentAreaFilled(false);
		//clearGrid.setBorderPainted(false);
		
		saveImage = new JButton("Save Button");
		saveImage.setBounds(1000,130,150,50);
		saveImage.addActionListener(this);
		this.add(saveImage);
		
		undo = new JButton("undo");
		undo.setBounds(1000,210,150,50);
		undo.addActionListener(this);
		this.add(undo);
		
		redo = new JButton("redo");
		redo.setBounds(1000,290,150,50);
		redo.addActionListener(this);
		this.add(redo);
	}

	public Dimension getPreferredSize() {
        return new Dimension(1200,900);
    }
    
    public void paintComponent(Graphics g) {       
        super.paintComponent(g); 
       	//sq.drawMe(g, gridX, gridY);
		sq.drawPalette(g, 900, 50);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 80)); 
		g.drawString("Stack Lab", 880,800);
		if(check){
		    sq.Undo();
		    check = false;
		    System.out.println("Lala");
		    sq.drawMe(g, gridX, gridY);
		}
		else if(redocheck){
		    sq.Redo();
		    redocheck = false;
		    System.out.println("La");
		    sq.drawMe(g, gridX, gridY);
		}
		else{
		    sq.drawMe(g, gridX, gridY);
		    System.out.println("Hello");
		}
    }
    
    public void actionPerformed(ActionEvent e) {
		if( e.getSource() == clearGrid )
		{
			sq.clear();
		}
		else if( e.getSource() == saveImage)
		{
			sq.createImage(1200,900,pictureCount);
			pictureCount++;
		}
		else if(e.getSource() == undo){
		    check = true;
		}
		else if(e.getSource() == redo){
		    redocheck = true;
		}
		repaint();
	}	
	
	public void mouseClicked(MouseEvent e) {
		int mouseY = e.getY();
		int mouseX = e.getX();
		
		int col = (int)((e.getX() - (gridX + 2)) / (50));
		int row = (int)((e.getY() - (gridY + 2)) / (50));
		//System.out.println("mouseX: " + mouseX + ", mouseY " + mouseY);
		
		if(mouseX >= 895 && mouseX <= 950) {
			int colorPos = (mouseY - 50) / 50;
			if(colorPos >= 0 && colorPos <= 11) {
				selectColor = colorPos + 1;
				//System.out.println("Select Color: " + selectColor);
			}
		}
		
		else if(row >= 0 && row < 16 && col >= 0 && col < 16) {		
				sq.insertSquare(row,col,selectColor);
				//System.out.println("insertSq info: " + row + " " + col + " " + selectColor);
				//System.out.println("clicked in range");
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
    
}