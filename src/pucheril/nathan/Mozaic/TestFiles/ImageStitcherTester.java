package pucheril.nathan.Mozaic.TestFiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import pucheril.nathan.Mozaic.*;

public class ImageStitcherTester {
    private static JFileChooser fc = new JFileChooser();
    private static File imageFile;
    private static File imageFile2;
    private static BufferedImage img;
    private static BufferedImage img2;
    
    
    public static void main(String[] args) 
    {
        // TODO Auto-generated method stub
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
        imageFile = fc.getSelectedFile();
        fc.showOpenDialog(null);
        imageFile2 = fc.getSelectedFile();
        
        
        try {
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            img2 = ImageIO.read(imageFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedImage img3 = ImageStitcher.stitch(img, img2, null);
        
        try {
            File outputfile = new File("/Users/nathanpucheril/Desktop/saved.png");
            ImageIO.write(img3, "png", outputfile);
        } catch (IOException e) {
        }
        
    }

}
