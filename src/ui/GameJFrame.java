package ui;

import domain.GameInfo;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    private int[][] data = new int[4][4];
    private int blankX, blankY;
    private String path = "image/animal/animal3/";

    private int step = 0;

    JMenuItem girlItem = new JMenuItem("Girls");
    JMenuItem animalItem = new JMenuItem("Animals");
    JMenuItem sportItem = new JMenuItem("Sports");

    JMenuItem replayItem = new JMenuItem("Replay");
    JMenuItem reLoginItem = new JMenuItem("Login Again");
    JMenuItem closeItem = new JMenuItem("Quit");

    JMenuItem accountItem = new JMenuItem("Public Account");

    JMenu saveJMenu = new JMenu("Save");
    JMenu loadJMenu = new JMenu("Load Saves");

    JMenuItem saveItem0 = new JMenuItem("Save0(empty)");
    JMenuItem saveItem1 = new JMenuItem("Save1(empty)");
    JMenuItem saveItem2 = new JMenuItem("Save2(empty)");
    JMenuItem saveItem3 = new JMenuItem("Save3(empty)");
    JMenuItem saveItem4 = new JMenuItem("Save4(empty)");

    JMenuItem loadItem0 = new JMenuItem("Load Save0(empty)");
    JMenuItem loadItem1 = new JMenuItem("Load Save1(empty)");
    JMenuItem loadItem2 = new JMenuItem("Load Save2(empty)");
    JMenuItem loadItem3 = new JMenuItem("Load Save3(empty)");
    JMenuItem loadItem4 = new JMenuItem("Load Save4(empty)");



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

        if(victory()){
            JLabel winLabel = new JLabel(new ImageIcon("image/win.png"));
            winLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winLabel);
        }

        JLabel stepCount = new JLabel("step: " + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                ImageIcon icon = new ImageIcon(path + ((data[x][y] == 16) ? -1 : data[x][y]) +".jpg");
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
        JMenu replaceImageMenu = new JMenu("Replace Image");

        saveJMenu.add(saveItem0);
        saveJMenu.add(saveItem1);
        saveJMenu.add(saveItem2);
        saveJMenu.add(saveItem3);
        saveJMenu.add(saveItem4);

        loadJMenu.add(loadItem0);
        loadJMenu.add(loadItem1);
        loadJMenu.add(loadItem2);
        loadJMenu.add(loadItem3);
        loadJMenu.add(loadItem4);

        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);

        accountItem.addActionListener(this);

        saveItem0.addActionListener(this);
        saveItem1.addActionListener(this);
        saveItem2.addActionListener(this);
        saveItem3.addActionListener(this);
        saveItem4.addActionListener(this);
        loadItem0.addActionListener(this);
        loadItem1.addActionListener(this);
        loadItem2.addActionListener(this);
        loadItem3.addActionListener(this);
        loadItem4.addActionListener(this);

        replaceImageMenu.add(girlItem);
        replaceImageMenu.add(animalItem);
        replaceImageMenu.add(sportItem);

        functionJMenu.add(replaceImageMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        functionJMenu.add(saveJMenu);
        functionJMenu.add(loadJMenu);

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
        if(victory())
            return ;

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
            step++;
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

    public boolean victory(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                if (data[i][j] != i * 4 + j + 1)
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == replayItem){
            initData();
            step = 0;
            initImage();
        }else if(obj == closeItem){
            System.exit(0);
        }else if(obj == reLoginItem){
            this.setVisible(false);
            new LoginJFrame();
        }else if(obj == accountItem){
            JDialog dialog = new JDialog();
            JLabel label = new JLabel(new ImageIcon("image/about.jpg"));
            label.setBounds(0, 0, 258, 258);
            dialog.getContentPane().add(label);

            dialog.setSize(344, 344);
            dialog.setAlwaysOnTop(true);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
        }else if(obj == girlItem){
            randomImage("girl");
        }else if(obj == animalItem){
            randomImage("animal");
        }else if(obj == sportItem){
            randomImage("sport");
        }else if(obj == saveItem0 || obj == saveItem1 || obj == saveItem2 || obj == saveItem3){
            JMenuItem item = (JMenuItem) obj;
            String str = item.getText();
            int index = str.charAt(4) - '0';

            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save\\save" + index + ".data"));
                GameInfo gi = new GameInfo(data, blankX, blankY, path, step);
                oos.writeObject(gi);
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            item.setText("Save" + index + "(" + step + " Moves)");
            loadJMenu.getItem(index).setText("Load Save" + index + "(" + step + " Moves)");

        }else if(obj == loadItem0 || obj == loadItem1 || obj == loadItem2 || obj == loadItem3){
            JMenuItem item = (JMenuItem) obj;
            String str = item.getText();
            int index = str.charAt(9) - '0';
            GameInfo gi = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save\\save" + index + ".data"));
                gi = (GameInfo) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            data = gi.getData();
            path = gi.getPath();
            blankX = gi.getX();
            blankY = gi.getY();
            step = gi.getStep();

            initImage();
        }
    }

    private void randomImage(String category){
        File dir = new File("image/" + category);
        int total = dir.list().length;
        int index = new Random().nextInt(total)+1;
        path = "image/" + category + "/" + category + index + "/";
        step = 0;
        initData();
        initImage();
    }
}