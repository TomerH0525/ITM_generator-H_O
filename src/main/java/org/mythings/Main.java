package org.mythings;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class Main {

    public static String osUser = System.getProperty("user.name");
    public static File userSelectedFile;
    public static FileInputStream propertiesStream;
    public static BufferedWriter bw;

    public static void main(String[] args) {

        //trying to set lookAndFeel to be like the current system native look and feel.
        //there's a need to set lookAndFeel before creating the JFileChooser, so it would get the default look and feel
        //from UIManager after you have changed it.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("Failed to set systems native FeelAndLook UI , Error stacktrace above ^^");
        }
        var fc = new JFileChooser("/Users/" + osUser + "/");

        fileChooserConfigurations(fc);


        System.out.println("Name of the current logged windows user : " + osUser + "\n");


//        fc.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Action event started!");
//                if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
//                    userSelectedFile = fc.getSelectedFile();
//                    propertiesFile.setProperty("lastChosenFilePath", userSelectedFile.getPath().replaceAll("\\\\","\\"));
//                    try {
//                        propertiesFile.store(bw,"added path");
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//
//
//                    System.out.println("file path was set successfully in properties file!! (path = "+userSelectedFile.getAbsolutePath());
//                }
//            }
//        });

        int returnValue = fc.showOpenDialog(null);
        if (userSelectedFile == null) {
            System.out.println("no file selected!");
        } else {

            System.out.println("Selected file name is : '" + userSelectedFile.getName() + "'");

            if (!isFileTypeAllowed(userSelectedFile)) {
                System.out.println("Not allowed file type!!!!");
            } else {
                System.out.println("The selected file is allowed :)!\nContinuing with the program");
                //continue to check the file provided and create txt files in the format chosen by the user!
                if (userSelectedFile.getName().toLowerCase().endsWith(".txt")) {
                    //this is a txt file

                }

            }

        }
    }

    private static void fileChooserConfigurations (JFileChooser fc){

        Dimension dimension = new Dimension(650, 400);
        fc.setPreferredSize(dimension);
        String[] allowedExtensionNames = {"xlsx", "xls", "txt"};
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx, xls, txt", allowedExtensionNames);
        System.out.println("Current allowed file extensions : " + Arrays.toString(allowedExtensionNames) + "\n");
        fc.setFileFilter(filter);

        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setRequestFocusEnabled(true);
        fc.setAcceptAllFileFilterUsed(false);

    }

    private static boolean isFileTypeAllowed (File file){
        String[] allowedExtensionNames = {"xlsx", "xls", "txt"};
        return Arrays.stream(allowedExtensionNames).anyMatch(string -> {
            return file.getName().toLowerCase().endsWith(string);
        });
    }


}