import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Menu {
    JFrame backg = new JFrame();

    public Menu() {
        setUp();
    }

    private void setUp() {
        JButton startBut = new JButton("Login");
        JLabel tLabel = new JLabel("Hello                ");
        JPanel tBanner = new JPanel();
        JPanel background = new JPanel();
        JButton close = new JButton("X");

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int wSize = (int) size.getWidth() / 4;
        int hSize = (int) size.getHeight() / 2;
        backg.setSize(wSize, hSize);
        int fwidth = backg.getWidth();
        int fheight = backg.getHeight()/8;

        startBut.setBounds(95, 200, 200, 75);
        tBanner.setBounds(0,0, fwidth, fheight);
        background.setBounds(0,fheight,fwidth,hSize-fheight);

        close.setFont(new Font("ExitMenu", Font.BOLD, 35));
        startBut.setFont(new Font("loginButton", Font.PLAIN, 35));
        tLabel.setFont(new Font("loginBanner", Font.PLAIN, 40));


        tBanner.setBackground(Color.decode("#f0932b"));
        close.setBackground(Color.decode("#f0932b"));
        background.setBackground(Color.decode("#130f40"));
        startBut.setBackground(Color.decode("#262626"));
        tLabel.setBackground(Color.decode("#130f40"));

        startBut.setForeground(Color.decode("#bdc3c7"));
        close.setForeground(Color.decode("#130f40"));

        Border emptyBorder = BorderFactory.createEmptyBorder();
        close.setBorder(emptyBorder);

        tBanner.add(tLabel);
        tBanner.add(close);
        backg.setLayout(null);
        backg.add(tBanner);
        backg.add(startBut);
        backg.add(background);
        backg.setResizable(false);
        backg.setVisible(true);

        startBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backg.dispatchEvent(new WindowEvent(backg, WindowEvent.WINDOW_CLOSING));
                try {
                    LoginPage login = new LoginPage();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}

