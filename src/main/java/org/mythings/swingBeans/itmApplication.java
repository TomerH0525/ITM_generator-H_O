package org.mythings.swingBeans;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


public class itmApplication extends JFrame {


    //layout manager
    MigLayout mainMigPanel;

    public static JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontSizeSpinner;
    Font defaultFont = new Font("Arial",Font.BOLD,20);

    public static JRadioButton itm_6091;
    public static JRadioButton itm_6090;
    public static JRadioButton itm_6089;
    JLabel itmChooserLabel;

    public static JButton createITMs;
    ButtonGroup bgItm;

    JPanel leftPanel;
    JPanel rightPanel;
    ActionListener buttonActionListener;


    public itmApplication(ActionListener buttonActionListener) throws HeadlessException {
        this.buttonActionListener = buttonActionListener;
        //using MigLayout as my layout manager
        mainMigPanel = new MigLayout("wrap, inset 20 , flowy, fill","[center]10[center]");

        JPanel mainPanel = new JPanel(mainMigPanel);

        leftPanel = new JPanel(new MigLayout("wrap, fill, insets 5 ","","[]10[200,500]"));
        rightPanel = new JPanel(new MigLayout("wrap,fill, insets 5,","","[]25[]"));

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

        JTextPane textAreaLabel = new JTextPane();
        textAreaLabel.setText(
                "יש להזין מספרי תעודות ומחסן שולח בסדר הבא:\n" +
                        "מספר תעודת משלוח ואז טאב (TAB) ומחסן שולח\n\n" +
                        "ניתן להעתיק מקובץ אקסל 2 עמודות ולהדביק במכה אחת\n" +
                        "עמודה ראשונה תהיה תעודה משלוח \nועמודה שניה מחסן שולח"
        );
        textAreaLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textAreaLabel.setEditable(false);


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




        leftPanel.add(textAreaLabel,"center,wrap,span 2");
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
