/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppGUI;

import AppServices.Streamer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author lukasgreiner
 */
public class AutoCompleteComboBox extends JComboBox {

    public int caretPos = 0;
    public JTextField inputField = null;

    public AutoCompleteComboBox(JComboBox box) {
        this.setModel(box.getModel());
        this.setBorder(box.getBorder());
        this.setEditor(box.getEditor());
        this.setEditable(true);
    }

    public void setSelectedIndex(int index) {
        super.setSelectedIndex(index);
        inputField.setText(getItemAt(index).toString());
        inputField.setSelectionEnd(caretPos + inputField.getText().length());
        inputField.moveCaretPosition(caretPos);
    }

    public void setEditor(ComboBoxEditor editor) {
        super.setEditor(editor);
        if (editor.getEditorComponent() instanceof JTextField) {
            inputField = (JTextField) editor.getEditorComponent();

            inputField.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent ev) {
                    char key = ev.getKeyChar();
                    if (!(Character.isLetterOrDigit(key) || Character.isSpaceChar(key))) {
                        return;
                    }

                    caretPos = inputField.getCaretPosition();
                    String text = "";
                    try {
                        text = inputField.getText(0, caretPos).toLowerCase();
                    } catch (javax.swing.text.BadLocationException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < getItemCount(); i++) {
                        Streamer streamer = (Streamer) getItemAt(i);
                        String element = streamer.getChannel().toLowerCase();
                        if (element.startsWith(text)) {
                            setSelectedIndex(i);
                            return;
                        }
                    }
                }
            });
        }
    }

}
