import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GUI extends JPanel {

    public static JFrame windows = new JFrame("Computer Store");
    private final JButton b1;
    private final JButton b2;
    
    private final JRadioButton r1;
    private final JRadioButton r2;
    private final JRadioButton r3;
    private final JRadioButton r4;

        public static void main(String[] args) {

            showGUI();

    }

        public GUI() {

            this.setLayout(new GridLayout(2, 2));

            //HEADER
            JPanel header = new JPanel();
            header.setLayout(new GridLayout(1, 2));
            header.setPreferredSize(new Dimension(10, 10));

            //MENU
            JPanel menu = new JPanel();
            menu.setLayout(new GridLayout(1, 2));

            //radio button
            ButtonGroup group = new ButtonGroup();

            r1 = new JRadioButton("name");
            header.add(r1);

            r2 = new JRadioButton("type");
            header.add(r2);

            r3 = new JRadioButton("memory size");
            header.add(r3);

            r4 = new JRadioButton("price");

            header.add(r4);
            group.add(r1);
            group.add(r2);
            group.add(r3);
            group.add(r4);

            //knap
            b1 = new JButton("Add item");
           

            b2 = new JButton("Delete item");
            header.add(b1);
            header.add(b2);


            this.add(header);
            this.add(menu);

    }

    public static void showGUI() {

            // create window
            GUI content = new GUI();
            windows.setContentPane(content);
            windows.pack();
            windows.setSize(800, 600);
            windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windows.setVisible(true);



    }


}
