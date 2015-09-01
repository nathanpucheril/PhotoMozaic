package pucheril.nathan.Mozaic.TestFiles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import pucheril.nathan.Mozaic.*;


public class ImageAnalyticsTester {
    private static JFileChooser fc = new JFileChooser();
    private static File imageFile;
    private static BufferedImage img;
    public static void main(String[] args) 
    {
        // TODO Auto-generated method stub
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
        imageFile = fc.getSelectedFile();
        long startTime = System.nanoTime();
        try {
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime); 

        Color c = ImageAnalytics.getAvgColor(img);
        System.out.println(c.getBlue() +  " " +c.getGreen() +  " " +c.getRed());

        
        startTime = System.nanoTime();
        Color c2 = ImageAnalytics.getAvgColor(img,500);
        System.out.println(c2.getBlue() +  " " +c2.getGreen() +  " " +c2.getRed());
        endTime = System.nanoTime();
        System.out.println(endTime - startTime); 
    }

}
