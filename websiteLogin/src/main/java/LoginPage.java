import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class LoginPage {
    private JFrame frameL;
    private Connector con = new Connector();;

    public LoginPage() throws SQLException {
        setUp();



    }

    private void setUp() {
        JLabel tLabel = new JLabel("Login Page      ");
        JPanel tBanner = new JPanel();
        JPanel background = new JPanel();
        JTextField user = new JTextField(15);
        JLabel userLabel = new JLabel("Username");
        JPanel userPanel = new JPanel();
        JPasswordField pass = new JPasswordField(15);
        JLabel passLabel = new JLabel("Password");
        JPanel passPanel = new JPanel();
        JButton login = new JButton("Login");
        JButton newUser = new JButton("Click Here to Create a New Account");
        JButton close = new JButton("X");
        frameL = new JFrame();

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int wSize = (int) size.getWidth() / 4;
        int hSize = (int) size.getHeight() / 2;
        frameL.setSize(wSize,hSize);
        int fwidth = frameL.getWidth();
        int fheight = frameL.getHeight()/7;


        tBanner.setBounds(0,0, fwidth, fheight);
        background.setBounds(0,fheight,fwidth,hSize-fheight);
        userPanel.setBounds(0,175,fwidth,50);
        passPanel.setBounds(0,225,fwidth,50);
        login.setBounds(155,275,75,30);
        newUser.setBounds(65, 325, 250,15);

        close.setFont(new Font("ExitLogin", Font.BOLD, 35));
        newUser.setFont(new Font("loginButton", Font.PLAIN, 11));
        tLabel.setFont(new Font("loginBanner", Font.PLAIN, 40));

        tBanner.setBackground(Color.decode("#f0932b"));
        background.setBackground(Color.decode("#130f40"));
        close.setBackground(Color.decode("#f0932b"));
        userPanel.setBackground(Color.decode("#130f40"));
        passPanel.setBackground(Color.decode("#130f40"));
        login.setBackground(Color.decode("#2980b9"));
        newUser.setBackground(Color.decode("#130f40"));

        close.setForeground(Color.decode("#130f40"));
        userLabel.setForeground(Color.decode("#f0932b"));
        passLabel.setForeground(Color.decode("#f0932b"));
        newUser.setForeground(Color.decode("#7f8c8d"));

        Border emptyBorder = BorderFactory.createEmptyBorder();
        newUser.setBorder(emptyBorder);
        close.setBorder(emptyBorder);

        tBanner.add(tLabel);
        tBanner.add(close);

        userPanel.add(userLabel);
        userPanel.add(user);

        passPanel.add(passLabel);
        passPanel.add(pass);

        frameL.setLayout(null);
        frameL.add(login);
        frameL.add(userPanel);
        frameL.add(passPanel);
        frameL.add(login);
        frameL.add(newUser);
        frameL.add(tBanner);
        frameL.add(background);

        frameL.setResizable(false);
        frameL.setVisible(true);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = user.getText();
                char[] passwordentry = pass.getPassword();
                String password = String.valueOf(passwordentry);
                try {
                    if (!check(username, password)) {
                        System.out.println("Did not recognize username or password.");
                    } else {
                        frameL.dispatchEvent(new WindowEvent(frameL, WindowEvent.WINDOW_CLOSING));
                        Welcome welcomepage = new Welcome();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                user.setText("");
                pass.setText("");
            }
        });
        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameL.dispatchEvent(new WindowEvent(frameL, WindowEvent.WINDOW_CLOSING));
                try {
                    Registration registration = new Registration();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private boolean check(String username, String password) throws SQLException {
        boolean result = false;
        String compare;
        int total = con.highestID();
        for(int search = 1; search <= total; search++ ){
            compare = con.searchUser(search);
            if(compare.equals(username)){
                if(checkPass(username,password)){
                    result = true;
                }
                break;
            }
        }
        return result;
    }

    private boolean checkPass(String username, String password) throws SQLException {
        boolean result = false;
        String compare = con.searchPass(username);
        if(compare.equals(password)){
            result = true;
        }
        return result;
    }

}
