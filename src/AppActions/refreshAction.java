/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppActions;

import AppGUI.AutoCompleteComboBox;
import AppGUI.ChoosePanel;
import AppGUI.StreamerModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author lukasgreiner
 */
public class refreshAction extends AbstractAction {

    private final ChoosePanel pan;
    
    public refreshAction(ChoosePanel pan) {
        this.pan = pan;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AutoCompleteComboBox box = pan.getInputBox();
        box.setModel(new StreamerModel());
        pan.setInputBox(box);
        pan.repaint();
    }
    
}
