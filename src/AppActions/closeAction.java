/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppActions;

import AppGUI.ChoosePanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author lukasgreiner
 */
public class closeAction extends AbstractAction{
    
    
    public closeAction() {
        this.putValue(NAME,"Close");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    
}
