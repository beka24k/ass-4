import javax.swing.*;
import java.awt.*;

public class Invalid extends JDialog {
    private JPanel invalidPanelio;
public Invalid(JFrame parent){
        super(parent);
        setTitle("Log in");

        setContentPane(invalidPanelio);
        setMinimumSize(new Dimension(420, 474));
        setModal(true);
        setVisible(true);
}
}
