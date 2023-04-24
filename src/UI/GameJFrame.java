package UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameJFrame extends JFrame implements KeyListener {

    private int[][] data = new int[4][4];
    private int blankX, blankY;
    private String path = "image/animal/animal3/";

    public GameJFrame(){
        initJFrame();

        initJMenuBar();

        initData();

        initImage();

        this.setVisible(true);
    }

    private void initData() {
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 1; i <= 16; i++)
            tmp.add(i);
        Collections.shuffle(tmp);

        for(int i = 1; i <= 16; i++){
            int num = tmp.get(i-1);
            if(num == 16){
                blankX = (i-1)/4;
                blankY = (i-1)%4;
            }
            data[(i-1)/4][(i-1)%4] = num;
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                ImageIcon icon = new ImageIcon(path + data[x][y]+".jpg");
                JLabel label = new JLabel(icon);
                label.setBounds(105*y + 83, 105*x + 134, 105, 105);
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(label);
            }
        }
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }


    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("Menu");
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
        this.setResizable(false);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // when "a" is pressed
        if(code == 65){
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            this.getContentPane().repaint();
        }
        // when "w" is pressed
        else if(code == 87){
            for(int i = 1; i <= 16; i++){
                data[(i-1)/4][(i-1)%4] = i;
            }
            blankX = 3;
            blankY = 3;
            initImage();
        }
        // arrow keys game play
        else{
            code -= 37;
            if(code < 0 || code > 3)
                return;

            int[] newX = {0, 1, 0, -1};
            int[] newY = {1, 0, -1, 0};

            int targetX = blankX + newX[code];
            int targetY = blankY + newY[code];

            if(targetX < 0 || targetX > 3 || targetY < 0 || targetY > 3)
                return;

            data[blankX][blankY] = data[targetX][targetY];
            data[targetX][targetY] = 0;
            blankX = targetX;
            blankY = targetY;
            initImage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // when key "a" is released
        if(code == 65)
            initImage();
    }
}