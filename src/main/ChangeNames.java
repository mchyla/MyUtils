package main;

import javax.swing.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mchyl on 14/09/2017.
 */
public class ChangeNames {

    public static void main(String[] args) throws UnsupportedEncodingException {

        JFileChooser chooser = MyChooser.createChooser("Select folder to change name");//new JFileChooser();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getCurrentDirectory().toString().replace("\\", "\\\\");
            File dir = chooser.getSelectedFile();
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            File[] files = dir.listFiles();

            for (File f : files) {
                if (f.isDirectory()) {

                    for (int i = 0; i < f.listFiles().length; i++) {
                        System.out.println("FileName " + changeFileNames(f.listFiles()[i]));
                    }

                    String newName = changeName(f);

                } else {
                    //TODO
                }
            }
        } else {
            System.out.println("No selection");
        }
    }

    public static String changeName(File f) {
        String newDirectoryName = getNameWithoutUnnesseseryThings(f);
        String path = f.getParent();
        f.renameTo(new File(f.getParentFile(), newDirectoryName));

        return newDirectoryName;
    }

    public static String changeFileNames(File f) {
        String extension = "";
        String path = f.getParent();
        int i = f.getName().lastIndexOf('.');

        if (i > 0) {
            extension = f.getName().substring(i + 1);
        }

        String newFileName = getNameWithoutUnnesseseryThings(f);
        newFileName = newFileName + "." + extension;
        f.renameTo(new File(f.getParentFile(), newFileName));

        return newFileName;
    }


    public static String getNameWithoutUnnesseseryThings(File f) {
        String newDirectoryName = f.getName().replaceAll("(\\[(.*?)\\] \\- )|(\\[(.*?)\\] )|(\\[(.*?)\\])", "");
        newDirectoryName = newDirectoryName.replaceAll(" ", "");
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher m = pattern.matcher(newDirectoryName);
        
        if (m.find()) {
            newDirectoryName = newDirectoryName.replaceAll("\\.[0-9]{4}(.*)", " " + m.group(1));
            newDirectoryName = newDirectoryName.replace(".", " ");
        }

        return newDirectoryName;
    }
}

