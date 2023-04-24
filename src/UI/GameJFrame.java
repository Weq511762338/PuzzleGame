package UI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameJFrame extends JFrame {

    private int[][] data = new int[4][4];
    public GameJFrame(){
        initJFrame();

        initJMenuBar();

        initData();

        initImage();

        this.setVisible(true);
    }

    private void initData() {
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 0; i < 16; i++)
            tmp.add(i);
        Collections.shuffle(tmp);

        for(int i = 0; i < 16; i++){
            int num = tmp.get(i);
            data[i/4][i%4] = num;
        }
    }

    private void initImage() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                ImageIcon icon = new ImageIcon("image/animal/animal3/"+data[x][y]+".jpg");
                JLabel label = new JLabel(icon);
                label.setBounds(105*x, 105*y, 105, 105);
                this.getContentPane().add(label);
            }
        }
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
        this.setLayout(null);
        this.setDefaultCloseOperation(3);
    }
}
