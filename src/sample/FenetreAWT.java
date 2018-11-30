package sample;

import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class FenetreAWT extends JFrame implements ActionListener {

    static JButton b = new JButton("test");
    static JPanel panel= new JPanel();
    JLabel l = new JLabel("Saisissez la température voulu");
    JLabel txtCombo = new JLabel("Ce que contient le frigo");
    static JComboBox combo = new JComboBox();
    private JTextField jtf = new JTextField(20);



    public static void main(String[] args) {
        b.addActionListener(new FenetreAWT());


    }

    public FenetreAWT(){
        setSize(600,300);
        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        JPanel mid = new JPanel();
        JPanel bot = new JPanel ();
        Font police = new Font("Arial", Font.BOLD, 14);
        combo.setPreferredSize(new Dimension(150,20));
        combo.addItem("Cannette de Coca");
        combo.addItem("Cannette d'Oasis");

        jtf.setFont(police);
        jtf.setForeground(Color.DARK_GRAY);
        jtf.setPreferredSize(new Dimension(50,50));

        top.add(txtCombo);
        top.add(combo);

        mid.add(l);
        mid.add(jtf);

        bot.add(b);
        panel.add(top, BorderLayout.NORTH);
        panel.add(mid, BorderLayout.CENTER);
        panel.add(bot, BorderLayout.SOUTH);

        setContentPane(panel);


        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if( e.getSource() == b){
            this.dispose();


        }
    }

    public String getJtf() {
        String rslt = jtf.getText();
        return rslt;
    }


    public void setJtf(JTextField t) {
        this.jtf = t;
    }

}
