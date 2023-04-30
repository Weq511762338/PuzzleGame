package ui;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import domain.User;
import util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class RegisterJFrame extends JFrame implements MouseListener {

    ArrayList<User> allUsers;

    JButton submit = new JButton();
    JButton reset = new JButton();

    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField rePassword = new JTextField();

    public RegisterJFrame(ArrayList<User> allUsers){
        this.allUsers = allUsers;
        initJFrame();
        initView();
        setVisible(true);
    }

    private void initView() {
        JLabel usernameText = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        usernameText.setBounds(85, 135, 80, 20);

        username.setBounds(195, 134, 200, 30);

        JLabel passwordText = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordText.setBounds(97, 193, 70, 20);

        password.setBounds(195, 195, 200, 30);

        JLabel rePasswordText = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        rePasswordText.setBounds(64, 255, 95, 20);

        rePassword.setBounds(195, 255, 200, 30);

        submit.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        submit.setBounds(123, 310, 128, 47);
        submit.setBorderPainted(false);
        submit.setContentAreaFilled(false);
        submit.addMouseListener(this);

        reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        reset.setBounds(256, 310, 128, 47);
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        reset.addMouseListener(this);

        JLabel background = new JLabel(new ImageIcon("image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);

        this.getContentPane().add(usernameText);
        this.getContentPane().add(passwordText);
        this.getContentPane().add(rePasswordText);
        this.getContentPane().add(username);
        this.getContentPane().add(password);
        this.getContentPane().add(rePassword);
        this.getContentPane().add(submit);
        this.getContentPane().add(reset);
        this.getContentPane().add(background);
    }

    public void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("Puzzle Game Login");
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setLayout(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == submit){
            if(username.getText().length() == 0 || password.getText().length() == 0 || rePassword.getText().length() == 0){
                showJDialog("Username and password can't be empty");
                return;
            }

            if(!password.getText().equals(rePassword.getText())){
                showJDialog("The two passwords are not the same");
                return;
            }

            if(!username.getText().matches("[a-zA-Z0-9]{4,16}")){
                showJDialog("Your username does not meet the format requirements");
                return;
            }
            if(!password.getText().matches("\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[a-z])\\S*")){
                showJDialog("Your password does not meet the format requirements");
                return;
            }

            if(containsUsername(username.getText())){
                showJDialog("Username already exists");
                return;
            }

            allUsers.add(new User(username.getText(), password.getText()));

            FileUtil.writeLines(allUsers, "../../../userinfo.txt", CharsetUtil.UTF_8);
            showJDialog("Registration Complete");
            this.setVisible(false);
            new LoginJFrame();

        }else if(e.getSource() == reset){
            username.setText("");
            password.setText("");
            rePassword.setText("");
        }
    }

    public boolean containsUsername(String username){
        for(User user : allUsers){
            if(user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }

}
