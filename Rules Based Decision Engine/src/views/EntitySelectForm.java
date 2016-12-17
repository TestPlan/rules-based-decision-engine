package views;

import controllers.EntityController;
import services.EntityCollectionService;
import services.ObjectCollectionService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A form which would be used in order to select which Entity should be retracted or updated should
 * those options be selected from the dropdown in PlusNewActionDialog
 *
 * NOTE: THIS CLASS IS CURRENTLY NOT A DIALOG. THIS CLASS SHOULD BE REPLACED WITH A DIALOG
 *       BECAUSE THE PROGRAM WILL NOT PAUSE ON CREATION OF A REGULAR WINDOW FORM.
 *       //TODO: Create a new Dialog for this class
 *       @see views.ConditionDialog
 *       @see views.ImportDataDialog
 *       @see views.EntityCreationForm
 *       @see views.PlusNewActionDialog
 *
 * @author Mike M
 * @version 1.0
 */
public class EntitySelectForm
{
    //Components
    private JComboBox entitySelectBox;
    private JButton cancelButton;
    private JButton okButton;

    //Fields

    /**
     * Creates an EntitySelectForm
     */
    public EntitySelectForm()
    {
        //Populate entitySelectBox
        entitySelectBox.addItem("<Select Entity>");
        for (String s : EntityCollectionService.getInstance().getMap().keySet())
        {
            entitySelectBox.addItem(s);
        }

        //Action Listeners
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOk();
            }
        });

        cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });
    }

    /**
     * To be executed when the OK button is clicked
     */
    public void onOk()
    {
        entitySelectBox.getSelectedItem();
    }

    /**
     * To be executed when the cancel button is clicked
     */
    public void onCancel()
    {
        //Currently does not work because the form is not a dialog
        //Should create a Dialog to replace this form so that it can function as such

        //dispose();
    }

}
