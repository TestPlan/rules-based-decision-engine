package views;

import controllers.EntityController;
import services.EntityCollectionService;
import services.ObjectCollectionService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mike on 12/15/2016.
 */
public class EntitySelectForm
{
    private JComboBox entitySelectBox;
    private JButton cancelButton;
    private JButton okButton;

    public EntitySelectForm()
    {
        entitySelectBox.addItem("<Select Entity>");

        for (String s : EntityCollectionService.getInstance().getMap().keySet())
        {
            entitySelectBox.addItem(s);
        }

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

    public void onOk()
    {
        entitySelectBox.getSelectedItem();

    }

    public void onCancel()
    {
        //dispose();
    }

}
