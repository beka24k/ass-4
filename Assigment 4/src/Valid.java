import javax.swing.*;
import java.awt.*;

public class Valid extends JDialog{
    private JPanel panel1;
    public Valid(JFrame parent){
        super(parent);
        setTitle("Log in");

        setContentPane(panel1);
        setMinimumSize(new Dimension(800, 474));
        setModal(true);
        setVisible(true);
    }
}
