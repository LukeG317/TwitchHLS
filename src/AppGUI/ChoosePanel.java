/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppGUI;

import AppActions.*;
import AppServices.StreamerListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author lukasgreiner
 */
public class ChoosePanel extends JPanel {

    private final String[] quality = {"Source", "High", "Medium", "Low", "Mobile", "Audio Only"};
    private final JLabel inputLabel = new JLabel("Streamer:");
    private final JLabel qualityLabel = new JLabel("Quality:");
    private final JComboBox inputBox = new JComboBox();
    private final JComboBox qualityBox = new JComboBox(quality);

    private final Action startAction = new startAction(this);
    private final JButton startButton = new JButton(startAction);
    private final Action closeAction = new closeAction();
    private final JButton closeButton = new JButton(closeAction);

    private final JLabel chatLabel = new JLabel("Chat:");
    private final JCheckBox chatBox = new JCheckBox();
    private final JLabel autoQualityLabel = new JLabel("AQ:");
    private final JCheckBox autoQualityBox = new JCheckBox();

    public ChoosePanel() {
        this.setLayout(new BorderLayout());
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(padding);

        ImageIcon image = new ImageIcon(getClass().getResource("/Resources/TwitchKlein.png"));
        JLabel label = new JLabel("", image, JLabel.CENTER);
        this.add(label, BorderLayout.WEST);

        this.add(this.createCenterPanel(), BorderLayout.CENTER);
        this.add(this.createSouthPanel(), BorderLayout.SOUTH);
        this.setFocusable(true);
        this.setBackground(new Color(68, 68, 72));

    }

    private JPanel createCenterPanel() {
        JPanel help = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbConstr;
        help.setLayout(layout);

        //Input Label
        gbConstr = this.makegbc(0, 0, 1, 1);
        inputLabel.setToolTipText("Select the streamer you would like to watch");
        gbConstr.anchor = GridBagConstraints.WEST;
        layout.setConstraints(inputLabel, gbConstr);

        //Quality Label
        gbConstr = this.makegbc(0, 1, 1, 1);
        qualityLabel.setToolTipText("Select the quality of the stream");
        gbConstr.anchor = GridBagConstraints.WEST;
        layout.setConstraints(qualityLabel, gbConstr);

        inputLabel.setForeground(Color.white);
        qualityLabel.setForeground(Color.white);
        help.add(inputLabel);
        help.add(qualityLabel);

        //Input Box
        inputBox.setModel(new StreamerModel());
        inputBox.setEditable(true);
        AutoCompleteComboBox input = new AutoCompleteComboBox(inputBox);
        ImageIcon greenButton = new ImageIcon(getClass().getResource("/Resources/green-button.png"));
        ImageIcon redButton = new ImageIcon(getClass().getResource("/Resources/red-button.png"));
        input.setRenderer(new StreamerRenderer(greenButton,redButton));
        input.getEditor().getEditorComponent().addKeyListener(new StreamerListener(this,input));
        help.add(input);

        gbConstr = this.makegbc(1, 0, 2, 1);
        gbConstr.anchor = GridBagConstraints.WEST;
        gbConstr.fill = GridBagConstraints.HORIZONTAL;
        input.setToolTipText("Select the streamer you would like to watch");
                
        layout.setConstraints(input, gbConstr);

        //Quality Box
        qualityBox.setEditable(false);
        qualityBox.setToolTipText("Select the quality of the stream");
        help.add(qualityBox);
        gbConstr = this.makegbc(1, 1, 2, 1);
        gbConstr.anchor = GridBagConstraints.WEST;
        gbConstr.fill = GridBagConstraints.HORIZONTAL;

        layout.setConstraints(qualityBox, gbConstr);

        //Buttons
        JPanel buttons = this.createButtonPanel();
        help.add(buttons);
        gbConstr = this.makegbc(1, 3, 2, 1);
        gbConstr.anchor = GridBagConstraints.WEST;
        gbConstr.fill = GridBagConstraints.HORIZONTAL;

        layout.setConstraints(buttons, gbConstr);

        help.setBackground(new Color(68, 68, 72));
        return help;

    }

    private JPanel createButtonPanel() {
        GridLayout layout = new GridLayout();
        JPanel help = new JPanel(layout);
        help.setForeground(Color.white);
        help.setBackground(new Color(68, 68, 72));

        //Chat Label
        help.add(chatLabel);
        chatLabel.setToolTipText("Activates the live chat of the stream");
        chatLabel.setForeground(Color.white);

        //Chat Checkbox
        help.add(chatBox);
        chatBox.setToolTipText("Activates the live chat of the stream");
        chatBox.setSelected(true);

        //Auto Quality Label
        help.add(autoQualityLabel);
        autoQualityLabel.setToolTipText("Activates the automatic quality selection");
        autoQualityLabel.setForeground(Color.white);

        //Auto Quality Checkbox
        help.add(autoQualityBox);
        autoQualityBox.setSelected(false);
        autoQualityBox.setToolTipText("Activates the automatic quality selection");
        autoQualityBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoQualityBox.isSelected()) {
                    qualityBox.setEnabled(false);
                } else {
                    qualityBox.setEnabled(true);
                }
            }

        });
        return help;
    }

    private JPanel createSouthPanel() {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        JPanel help = new JPanel(layout);

        startButton.setSelected(true);
        startButton.setDefaultCapable(true);

        help.add(closeButton);
        help.add(startButton);
        help.setBackground(new Color(68, 68, 72));
        return help;
    }

    private GridBagConstraints makegbc(int x, int y, int width, int height) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.insets = new Insets(1, 1, 1, 1);
        return gbc;
    }

    public JComboBox getInputBox() {
        return inputBox;
    }

    public JComboBox getQualityBox() {
        return qualityBox;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JCheckBox getChatBox() {
        return chatBox;
    }

    public JCheckBox getAutoQualityBox() {
        return this.autoQualityBox;
    }

}
