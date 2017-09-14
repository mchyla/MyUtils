package main;

import javax.swing.*;
import java.io.*;

/**
 * Created by mchyl on 06/08/2017.
 */
public class ChangeSubtitles {

    public static void main(String[] args) throws UnsupportedEncodingException {

        JFileChooser chooser = MyChooser.createChooser("Select folder to change name");//new JFileChooser();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            String path = chooser.getCurrentDirectory().toString().replace("\\", "\\\\");
            File dir = chooser.getSelectedFile();
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            File[] files = dir.listFiles((d, name) -> name.endsWith(".srt"));

            for (File f : files) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
                    String line;
                    System.out.println(f.getName());

                    File fout = new File("F:\\Users\\Marcin\\Desktop\\Napisy\\" + f.getName());
                    FileOutputStream fos = new FileOutputStream(fout);

                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    while ((line = reader.readLine()) != null) {

                        line = line.replace("œ", "ś");
                        line = line.replace("¿", "ż");
                        line = line.replace("¹", "ą");
                        line = line.replace("³", "ł");
                        line = line.replace("ê", "ę");
                        line = line.replace("Œ", "Ś");
                        line = line.replace("Ÿ", "ź");
                        line = line.replace("ñ", "ń");
                        line = line.replace("æ", "ć");
                        line = line.replace("¯", "Ż");
                        line = line.replace("£", "Ł");
                        line = line.replace("Ê", "Ę");
                        line = line.replace("Æ", "Ć");
                        line = line.replace("Ñ", "Ń");

                        bw.write(line);
                        bw.newLine();
                    }
                    bw.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No Selection ");
        }
    }
}



