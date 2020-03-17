import javax.swing.JFrame;
public class Runner
{
    public static void main(String args[])
    {
        Screen screen = new Screen();
        JFrame frame = new JFrame("Stack Lab");
         
        frame.add(screen);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}