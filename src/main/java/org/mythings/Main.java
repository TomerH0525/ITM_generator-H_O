package org.mythings;

import org.mythings.swingBeans.itmApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static String osUser = System.getProperty("user.name");
    public static File userSelectedFile;
    public static FileInputStream propertiesStream;
    public static String currentDatePattern = "yyyyMMdd";


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("Failed to set systems native FeelAndLook UI , Error stacktrace above ^^");
        }

        ActionListener buttonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creating current date for file name using currently set pattern
                SimpleDateFormat sdf = new SimpleDateFormat(currentDatePattern);
                String currentDate = sdf.format(new Date());
                if (itmApplication.itm_6089.isSelected()){
                    Calendar calendar = new GregorianCalendar();
                    System.out.println(calendar);
//                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyPattern("yyyyMMdd");
                    System.out.println(sdf.format(new Date()));

                } else if (itmApplication.itm_6090.isSelected()) {
                    System.out.println("yada yada");
                }else {

                    HashMap<String,String> teodotSH = new HashMap<>();
                    itmApplication.textArea.getText().lines().forEach(string -> {
                        if (string.isBlank() || string.isEmpty()){
                            System.out.println("no text was found in textArea !!!");
                        }else {
                            String[] stringList = string.split("\t");
                            System.out.println(Arrays.toString(stringList));
                            if (stringList.length < 2){
                                System.out.println("אין מחסן שולח!");
                            }else teodotSH.put(stringList[0],stringList[1]);
                        }
                    });
                    final BufferedWriter[] bw = {null};
                    if (!teodotSH.isEmpty()){
                    teodotSH.forEach((key,value) -> {
                        System.out.println("key = "+key+"\nvalue = "+value);
                        try {
                            bw[0] = new BufferedWriter(new FileWriter("ITM_"+itmApplication.itm_6091.getText()+"_"+currentDate+"_"+key+".txt"));
                            bw[0].write("6091\t"+key+"\tמשטח לאחסנה\t\t\t\t\t\t1\t1\t1\tAVAILABLE\t\tEACH\t\t2\t1.92\tStorage\t"+value+"\t"+key+"\t\t");
                            bw[0].close();
                            JOptionPane.showMessageDialog(null,"Created Successfully.");
                            itmApplication.textArea.setText("");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } finally {

                        }
                    });

//                        "6091\t"+teoda+"\tמשטח לאחסנה\t\t\t\t\t\t1\t1\t1\tAVAILABLE\t\tEACH\t\t2\t1.92\tStorage\t"+mahsan+"\t"+teoda+"\t\t"
//                    JFileChooser saveLocationChooser = new JFileChooser();
//                    saveLocationChooser.setCurrentDirectory(new File("."));
//
//                    int response = saveLocationChooser.showSaveDialog(null);
//                    if (response == JFileChooser.APPROVE_OPTION){


                    }else JOptionPane.showMessageDialog(null,"לא נרשמו תעודות...");
                }
            }
        };

        new itmApplication(buttonActionListener);
    }

        //trying to set lookAndFeel to be like the current system native look and feel.
        //there's a need to set lookAndFeel before creating the JFileChooser, so it would get the default look and feel
        //from UIManager after you have changed it.
//
//        var fc = new JFileChooser("/Users/" + osUser + "/");
//
//        fileChooserConfigurations(fc);
//
//
//        System.out.println("Name of the current logged windows user : " + osUser + "\n");
//
//
////        fc.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                System.out.println("Action event started!");
////                if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
////                    userSelectedFile = fc.getSelectedFile();
////                    propertiesFile.setProperty("lastChosenFilePath", userSelectedFile.getPath().replaceAll("\\\\","\\"));
////                    try {
////                        propertiesFile.store(bw,"added path");
////                    } catch (IOException ex) {
////                        throw new RuntimeException(ex);
////                    }
////
////
////                    System.out.println("file path was set successfully in properties file!! (path = "+userSelectedFile.getAbsolutePath());
////                }
////            }
////        });
//
//        int returnValue = fc.showOpenDialog(null);
//        if (userSelectedFile == null) {
//            System.out.println("no file selected!");
//        } else {
//
//            System.out.println("Selected file name is : '" + userSelectedFile.getName() + "'");
//
//            if (!isFileTypeAllowed(userSelectedFile)) {
//                System.out.println("Not allowed file type!!!!");
//            } else {
//                System.out.println("The selected file is allowed :)!\nContinuing with the program");
//                //continue to check the file provided and create txt files in the format chosen by the user!
//                if (userSelectedFile.getName().toLowerCase().endsWith(".txt")) {
//                    //this is a txt file
//
//                }
//
//            }
//
//        }
//    }
//
//    private static void fileChooserConfigurations (JFileChooser fc){
//
//        Dimension dimension = new Dimension(650, 400);
//        fc.setPreferredSize(dimension);
//        String[] allowedExtensionNames = {"xlsx", "xls", "txt"};
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx, xls, txt", allowedExtensionNames);
//        System.out.println("Current allowed file extensions : " + Arrays.toString(allowedExtensionNames) + "\n");
//        fc.setFileFilter(filter);
//
//        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        fc.setRequestFocusEnabled(true);
//        fc.setAcceptAllFileFilterUsed(false);
//
//    }
//
//    private static boolean isFileTypeAllowed (File file){
//        String[] allowedExtensionNames = {"xlsx", "xls", "txt"};
//        return Arrays.stream(allowedExtensionNames).anyMatch(string -> {
//            return file.getName().toLowerCase().endsWith(string);
//        });
//    }


}