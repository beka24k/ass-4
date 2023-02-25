import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JDialog {
    private JPasswordField txpassword;
    private JTextField txlogin;
    private JPanel authorisepanel;
    private JButton btnsubmit;
    private JButton btncancel;
    private boolean succeeded;




    public Interface(JFrame parent) {
        super(parent);
        setTitle("Log in");

        setContentPane(authorisepanel);
        setMinimumSize(new Dimension(420, 474));
        setModal(true);
        btnsubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txlogin.getText();
                String password = txpassword.getText();
                Autentification aut = new Autentification(username, password,username);
                // Then, you can perform some authentication logic
                // and close the dialog when the authentication is successful
                if (aut.authenticate()) {
                    System.out.println("Welcome");
                    succeeded = true;
                    new Valid(null);
                    dispose();
                } else {
                    System.out.println("GoodBuy");
                    succeeded=false;
                    new Invalid(null);
                    dispose();
                }
            }
        });
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                 }
        });setVisible(true);
    }
    public String getTxlogin() {
        String username = txlogin.getText();
        return username;
    }

    public String getTxpassword() {
        String pasword = txpassword.getText();
        return pasword;
    }
    public boolean isSucceeded() {
        return succeeded;
    }
}
