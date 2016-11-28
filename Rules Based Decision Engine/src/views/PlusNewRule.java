package views;

import controllers.ActionController;
import models.*;
import models.Action;

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
    private JComboBox actionBox;
    private JButton addActionButton;
    private JButton addConditionButton;

    private static JFrame frame;

    Action a; //todo: Use ActionList?
    private static ActionController ac = ActionController.getInstance();

    public PlusNewRule() {
        frame = new JFrame("Rule");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //actionBox = new JComboBox(ac.retrieveActions());
        //Populate JComboBox actionBox
        for(int i = 0; i < ac.retrieveActions().length; i++){
            actionBox.addItem(ac.retrieveActions()[i]);
        }//Todo: improve if possible


        addActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String s = (String) actionBox.getSelectedItem();
                a = ac.getAction(s);
                //System.out.println(a);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });




        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
