package views;

import controllers.EntityController;
import models.Operator;
import models.*;
import services.EntityCollectionService;

import javax.swing.*;
import java.awt.event.*;

public class ConditionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox entityBox;
    private JComboBox keyBox;
    private JComboBox operatorBox;
    private JTextField valueTxt;

    EntityController svc = EntityController.getINSTANCE();

    public ConditionDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //Populate entityBox
        entityBox.addItem("<Select Entity>");
        for(String s : EntityCollectionService.getInstance().getMap().keySet())
        {
            entityBox.addItem(s);
        }

        //Populate keyBox
        keyBox.addItem("No Data Imported");

        //Populate OperatorBox
        //operatorBox = new JComboBox(Operator.values());
        for(Operator o : Operator.values())
        {
            operatorBox.addItem(o);
        } //Todo: improve if possible

        //valueBox will be user-input

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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

        entityBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                 if(!entityBox.getSelectedItem().equals("<Select Entity>")) {
                     keyBox.removeAllItems();

                     String s = (String) entityBox.getSelectedItem();
                     for (String str : svc.retrieveFields(s)) {
                         keyBox.addItem(str);
                     }
                 }
            }
        });

        // call onCancel() on ESCAPE
//        contentPane.registerKeyboardAction(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
        String entity = (String) entityBox.getSelectedItem();
        String field = (String) keyBox.getSelectedItem();
        Operator o = (Operator) operatorBox.getSelectedItem();
        String value = (String) valueTxt.getText();

        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
