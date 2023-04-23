package UI;

import javax.swing.*;

public class GameJFrame extends JFrame {

    public GameJFrame(){
        initJFrame();

        initJMenuBar();

        this.setVisible(true);
    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("Function");
        JMenu aboutJMenu = new JMenu("About Us");

        JMenuItem replayItem = new JMenuItem("Replay");
        JMenuItem reLoginItem = new JMenuItem("Login Again");
        JMenuItem closeItem = new JMenuItem("Quit");

        JMenuItem accountItem = new JMenuItem("Public Account");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("Puzzle Game");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
    }
}
