package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mike on 11/27/2016.
 */
public class PlusNewRule {
    private JPanel panel1;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField enterRuleTitleTextField;
    private JComboBox comboBox1;
    private JButton addActionButton;
    private JButton addConditionButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rule");
        frame.setContentPane(new PlusNewRule().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PlusNewRule() {
        addActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}
