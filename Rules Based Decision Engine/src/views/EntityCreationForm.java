package views;

import controllers.EntityController;
import services.EntityCollectionService;
import services.ObjectCollectionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.HashSet;

public class EntityCreationForm extends JDialog {
    //Components
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtEntityName;
    private JTable tblFields;
    private JButton addFieldButton;

    //Fields
    private DefaultTableModel fieldModel;

    public EntityCreationForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSave();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //Add Field Action Listener
        addFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tblFields.getModel();
                //model.setRowCount(model.getRowCount() + 1);
                String[] row = {"",""};
                model.addRow(row);
            }
        });

        pack();
        setVisible(true);
    }

    /**
     * Store keys and vals in their respective arrays, and pass those
     * and the name to an Entity creator
     *
     * Adds this Entity to a temporary collection so that the user can
     * select the Entity for comparison in a Condition of a Rule, but the object
     * will not be added to the collection.
     */
    private void onSave() {
        /*
        Create Entity object
        Add object to collection
         */
        String name = txtEntityName.getText();
        int rows = fieldModel.getRowCount();
        String[] tableKeys = new String[rows];
        String[] tableVals = new String[rows];
        HashSet<String> set = new HashSet<>();
        String key = "";
        for(int i = 0; i < rows; i++)
        {
            //TODO: Check for invalid input
            tableKeys[i] = (String) fieldModel.getValueAt(i, 0);
            tableVals[i] = (String) fieldModel.getValueAt(i, 1);
            key = "\"" + name + "." + fieldModel.getValueAt(i,0) + "\"";
            set.add(key);
            ObjectCollectionService.getInstance().put(key, fieldModel.getValueAt(i, 1));
        }

        EntityController.getINSTANCE().createEntity(name, tableKeys, tableVals);

        /*
        Add the actual java code to insert the entity into Drools memory

            HashSet<String> set;
            set.add(field.val);
            set.add(field2.val2);
            set.add(field3.val3);
            Entity e = new Entity("President", set);
            insert(e);
         */
//        String action = "";
//        action += "HashSet<String> set;\n    ";
//        for(String s : set)
//        {
//            action += "set.add(" + s + ");\n    ";
//        }
//        action += "Entity e = new Entity(\"" + name + "\", set);\n    insert(e)";

        String action = "";
        action += "Entity e = EntityCollectionService.getInstance().getFromActions(\"" + name + "\");\n";
        action += "    insert(e)";

        PlusNewActionDialog.actionString = action;

        //TODO: Fix stupid exception where you have to click out of the box first
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        //Properties for ruleTable
        fieldModel = new DefaultTableModel(0, 2)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return true;
            }
        };
        fieldModel.setColumnIdentifiers(new String[]{"Field", "Value"});

        tblFields = new JTable(fieldModel){
            public boolean isCellEditable(int row, int column)
            {
                return true;
            }
        };
    }
}
