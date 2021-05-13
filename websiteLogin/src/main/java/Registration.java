import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Registration {
    JFrame frameR;
    Connector con = new Connector();
    private String[] entries = new String[6];

    public Registration() throws SQLException {
        setUp();
    }

    private void setUp() throws SQLException {
        frameR = new JFrame();
        JLabel tLabel = new JLabel("Registration      ");
        JPanel tBanner = new JPanel();
        JPanel background = new JPanel();
        JButton close = new JButton("X");
        JButton cancel = new JButton("Cancel");
        JButton submit = new JButton("Submit");


        JLabel firstLabel = new JLabel("First Name");
        JTextField first = new JTextField(15);
        JPanel firstPanel = new JPanel();

        JLabel lastLabel = new JLabel("Last Name");
        JTextField last = new JTextField(15);
        JPanel lastPanel = new JPanel();

        JLabel userLabel = new JLabel("Username");
        JTextField user = new JTextField(15);
        JPanel userPanel = new JPanel();

        JLabel passLabel = new JLabel("Password");
        JPasswordField pass = new JPasswordField(15);
        JPanel passPanel = new JPanel();

        JLabel emailLabel = new JLabel("Email");
        JTextField email = new JTextField(15);
        JPanel emailPanel = new JPanel();

        JLabel numberLabel = new JLabel("Favorite Number");
        JTextField number = new JTextField(15);
        JPanel numberPanel = new JPanel();

        JLabel bioLabel = new JLabel("Bio");
        JTextArea bio = new JTextArea(16,16);
        JPanel bioPanel = new JPanel();


        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int wSize = (int) size.getWidth() / 4;
        int hSize = (int) size.getHeight() / 2;
        frameR.setSize(wSize,hSize);
        int fwidth = frameR.getWidth();
        int fheight = frameR.getHeight()/7;

        tBanner.setBounds(0,0, fwidth, fheight);
        background.setBounds(0,fheight,fwidth,hSize-fheight);

        firstPanel.setBounds(0,100,fwidth,25);
        lastPanel.setBounds(0,125,fwidth,25);
        userPanel.setBounds(0,150,fwidth+4,25);
        passPanel.setBounds(0,175,fwidth+6,25);
        emailPanel.setBounds(0,200,fwidth+39,25);
        numberPanel.setBounds(0,225,fwidth-42,25);
        bioPanel.setBounds(0,253,fwidth-125,25);
        bio.setBounds(147,260,169,50);

        cancel.setBounds(111,330,85,35);
        submit.setBounds(231,330,85,35);



        close.setFont(new Font("ExitLogin", Font.BOLD, 35));
        tLabel.setFont(new Font("loginBanner", Font.PLAIN, 40));

        tBanner.setBackground(Color.decode("#f0932b"));
        background.setBackground(Color.decode("#130f40"));
        close.setBackground(Color.decode("#f0932b"));
        cancel.setBackground(Color.decode("#2980b9"));
        submit.setBackground(Color.decode("#2980b9"));

        firstPanel.setBackground(Color.decode("#130f40"));
        lastPanel.setBackground(Color.decode("#130f40"));
        userPanel.setBackground(Color.decode("#130f40"));
        passPanel.setBackground(Color.decode("#130f40"));
        emailPanel.setBackground(Color.decode("#130f40"));
        bioPanel.setBackground(Color.decode("#130f40"));
        numberPanel.setBackground(Color.decode("#130f40"));

        close.setForeground(Color.decode("#130f40"));
        firstLabel.setForeground(Color.decode("#f0932b"));
        lastLabel.setForeground(Color.decode("#f0932b"));
        userLabel.setForeground(Color.decode("#f0932b"));
        passLabel.setForeground(Color.decode("#f0932b"));
        emailLabel.setForeground(Color.decode("#f0932b"));
        bioLabel.setForeground(Color.decode("#f0932b"));
        numberLabel.setForeground(Color.decode("#f0932b"));

        Border emptyBorder = BorderFactory.createEmptyBorder();
        close.setBorder(emptyBorder);

        bio.setLineWrap(true);
        bio.setWrapStyleWord(true);

        tBanner.add(tLabel);
        tBanner.add(close);

        firstPanel.add(firstLabel);
        firstPanel.add(first);

        lastPanel.add(lastLabel);
        lastPanel.add(last);

        userPanel.add(userLabel);
        userPanel.add(user);

        passPanel.add(passLabel);
        passPanel.add(pass);

        emailPanel.add(emailLabel);
        emailPanel.add(email);

        numberPanel.add(numberLabel);
        numberPanel.add(number);

        bioPanel.add(bioLabel);


        frameR.setLayout(null);

        frameR.add(firstPanel);
        frameR.add(lastPanel);
        frameR.add(userPanel);
        frameR.add(passPanel);
        frameR.add(emailPanel);
        frameR.add(numberPanel);
        frameR.add(bio);
        frameR.add(bioPanel);
        frameR.add(tBanner);
        frameR.add(cancel);
        frameR.add(submit);
        frameR.add(background);

        frameR.setResizable(false);
        frameR.setVisible(true);

        close.addActionListener(e -> System.exit(0));
        cancel.addActionListener(e -> {
            frameR.dispatchEvent(new WindowEvent(frameR, WindowEvent.WINDOW_CLOSING));
            try {
                LoginPage login = new LoginPage();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
        submit.addActionListener(e -> {
            String firstentry = first.getText();
            entries[0] = firstentry;
            String lastentry = last.getText();
            entries[1] = lastentry;
            String userentry = user.getText();
            entries[2] = userentry;
            String emailentry = email.getText();
            entries[3] = emailentry;
            String numberentry = number.getText();
            entries[4] = numberentry;
            String bioentry = bio.getText();
            entries[5] = bioentry;

            char[] passentry = pass.getPassword();
            String password = String.valueOf(passentry);

            boolean filled = true;
            boolean check = false;
            try {
                check = check();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            for (String entry : entries) {
                if (entry.isBlank()) {
                    System.out.println("Every Field needs to be filled.");
                    filled = false;
                    break;
                }
            }
            if (!check&&filled) {
                try {
                    add(password);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                first.setText("");
                    last.setText("");
                    user.setText("");
                    pass.setText("");
                    email.setText("");
                    number.setText("");
                    bio.setText("");
                    frameR.dispatchEvent(new WindowEvent(frameR, WindowEvent.WINDOW_CLOSING));
                    try {
                        LoginPage login = new LoginPage();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            else if(check) {
                    System.out.println("Username Taken");
                }
        });
    }

    private void add(String password) throws SQLException {
        con.add(entries, password);
    }

    private boolean check() throws SQLException {
        boolean result = false;
        String compare;
        String username = entries[2];
        int total = con.highestID();
        for (int search = 1; search <= total; search++) {
            compare = con.searchUser(search);
            if (compare.equals(username)) {
                result = true;
            }
        }
        return result;
    }
}
