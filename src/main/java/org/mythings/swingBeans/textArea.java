package org.mythings.swingBeans;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class textArea extends JFrame {


    //layout manager
    MigLayout mainMigPanel;

    public static JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontSizeSpinner;
    Font defaultFont = new Font("Arial",Font.BOLD,14);

    public static JRadioButton itm_6091;
    public static JRadioButton itm_6090;
    public static JRadioButton itm_6089;
    JLabel itmChooserLabel;

    public static JButton createITMs;
    ButtonGroup bgItm;

    JPanel leftPanel;
    JPanel rightPanel;
    ActionListener buttonActionListener;


    public textArea(ActionListener buttonActionListener) throws HeadlessException {
        this.buttonActionListener = buttonActionListener;
        //using MigLayout as my layout manager
        mainMigPanel = new MigLayout("wrap, inset 35 45 30 45, flowy, fill","[center]10[center]");

        JPanel mainPanel = new JPanel(mainMigPanel);
        leftPanel = new JPanel(new MigLayout("wrap, fillx, insets 10","","[]10[500,500]"));
        rightPanel = new JPanel(new MigLayout("wrap,fill, insets 10","","[]25[]"));

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        this.add(mainPanel);


        this.setMinimumSize(new Dimension(450,450));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("יצירת ממשקי פריט - אייץ אנד או");
        this.setSize(450,350);
        this.setLocationRelativeTo(null);

        textArea = new JTextArea("אזור טקסט");
        textArea.setAutoscrolls(true);
        textArea.setLineWrap(true);
        textArea.setFont(defaultFont);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500,250));
        scrollPane.setWheelScrollingEnabled(true);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50,25));
        fontSizeSpinner.setValue(14);
        fontSizeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(),Font.BOLD, (int) fontSizeSpinner.getValue()));

            }

        });


        itm_6091 = new JRadioButton("6091");
        itm_6090 = new JRadioButton("6090");
        itm_6089 = new JRadioButton("6089");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(itm_6091);
        buttonGroup.add(itm_6090);
        buttonGroup.add(itm_6089);

        createITMs = new JButton("Create ITM's");

        createITMs.addActionListener(getButtonActionListener());
        itmChooserLabel = new JLabel("Choose ITM format");




        leftPanel.add(fontSizeSpinner,"center");
        leftPanel.add(scrollPane,"grow");

        rightPanel.add(itmChooserLabel);
        rightPanel.add(itm_6091,"center");
        rightPanel.add(itm_6090,"center");
        rightPanel.add(itm_6089,"center");
        rightPanel.add(createITMs);

        this.setVisible(true);


    }


    public ActionListener getButtonActionListener(){
        return this.buttonActionListener;

    }

    public void setButtonActionListener(ActionListener al){
        this.buttonActionListener = al;
    }
}
