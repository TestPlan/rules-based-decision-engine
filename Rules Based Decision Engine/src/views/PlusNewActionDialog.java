package views;

import controllers.ActionController;
import services.ActionCollectionService;

import javax.swing.*;
import java.awt.event.*;

/**
 * Dialog for creating new Action from MainView screen
 *
 * @author Michael Crinite
 * @version 11.17.16
 */
public class PlusNewActionDialog extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtActionName;
    private JTextField txtActionDescription;
    private JCheckBox automaticallyTriggerCheckBox;

    public static void main(String[] args)
    {
        PlusNewActionDialog dialog = new PlusNewActionDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public PlusNewActionDialog()
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK()
    {
        ActionController.getInstance().newAction(txtActionName.getText(), txtActionDescription.getText());
        //if(automaticallyTriggerCheckBox.isSelected()){
        //TODO: Set Action to auto trigger if above condition is met
        dispose();
    }

    private void onCancel()
    {
        dispose();
    }
}
