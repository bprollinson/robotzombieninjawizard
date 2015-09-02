package rznw.ui;

import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGameFrame extends JFrame
{
    public MainGameFrame(String title)
    {
        super(title);

        this.setSize(400, 440);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new MainGamePanel();
        this.getContentPane().add(panel);
    }

    public void display(KeyListener keyListener)
    {
        this.setVisible(true);
        this.getContentPane().setFocusable(true);
        this.getContentPane().requestFocus();
        this.getContentPane().addKeyListener(keyListener);
    }

    public void renderDisplayString(int i, int j, String displayString)
    {
        MainGamePanel panel = (MainGamePanel)this.getContentPane().getComponent(0);
        panel.renderDisplayString(i, j, displayString);
    }

    public void renderDisplayCharacter(int i, int j, char displayCharacter)
    {
        MainGamePanel panel = (MainGamePanel)this.getContentPane().getComponent(0);
        panel.renderDisplayCharacter(i, j, displayCharacter);
    }
}
