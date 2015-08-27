import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGameFrame extends JFrame
{
    public MainGameFrame(String title)
    {
        super(title);

        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new MainGamePanel();
        this.getContentPane().add(panel);
    }
}
