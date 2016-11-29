package views;

import controllers.ActionController;
import controllers.RuleController;
import models.*;
import models.Action;

import javax.swing.*;
import java.awt.event.*;

/**
 * Creates a PlusNewRule Dialog in order to assist the user in creating
 * their own Rule object (which will be used to create a DRL of the Rule.
 *
 * @author Michael Crinite and Ian Markind
 * @version 0.1 11/27/16
 */
public class PlusNewRuleDialog extends JDialog {
    //Components
    private JPanel contentPane;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField enterRuleTitleTextField;
    private JButton addConditionButton;
    private JComboBox actionBox;

    //Fields
    private Rule rule;
    public static ConditionalElementList cel = new ConditionalElementList();
    private models.Action a; //todo: Use ActionList?

    private RuleController rc = RuleController.getInstance();
    private ActionController ac = ActionController.getInstance();

    /**
     * Creates a new PlusNewRule instance.
     */
    public PlusNewRuleDialog() {
        this.setTitle("Rule");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(saveButton);

        //Create new Rule object
        rule = rc.createDefaultRule();

        //Populate JComboBox actionBox
        for (int i = 0; i < ac.retrieveActions().length; i++)
        {
            actionBox.addItem(ac.retrieveActions()[i]);
        }//Todo: improve if possible

        // ActionListeners
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                dispose();
            }
        });

        addConditionButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                new ConditionDialog();
            }
        });

        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                //Get Action from actionBox
                String s = (String) actionBox.getSelectedItem();
                a = ac.getAction(s);
                onSave(enterRuleTitleTextField.getText().trim().replaceAll("\\s+", "_"), cel, a);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Gathers rule info and calls rc to create a new Rule object and add it to the collection
     * @param title
     * @param cel
     * @param action
     */
    public void onSave(String title, ConditionalElementList cel, Action action)
    {
        try
        {
            //Create Rule object
            rule = rc.setRuleFields(title, cel, action);

            //Add Rule to collection
            rc.addRuleToCollection(rule);

            System.out.println(rule);

            //Create Drools File
            rc.createDroolsFileFromRule(rule);

            dispose();
        }
        catch (NullPointerException n)
        {
            JOptionPane.showMessageDialog(null, "Please make sure you have created an Action\n and filled in your Condition " +
                "before attempting to create a rule.", "Incomplete Rule Fields", JOptionPane.ERROR_MESSAGE);
        }
    }
}
