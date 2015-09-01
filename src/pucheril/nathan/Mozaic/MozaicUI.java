package pucheril.nathan.Mozaic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MozaicUI {
    private static BufferedImage mosaic;
    private static BufferedImage mainImage;
    private static Set<BufferedImage> subImages;
    private static JFileChooser fileSelecter;
    private static JFileChooser folderSelecter;
    private static JFileChooser fileSaver;
    private static File imageFile;
    private static File imagesDirectory;
    private static File saveFile;
    private static MozaicMode mode;

    public static void main(String[] args) {
        initializeComponents();
        
        while(true) {
            JOptionPane.showMessageDialog(null, 
                    "              Welcome to Mozaic Builder.             \n"
                            + "You will be prompted to select the image you want to \n"
                            + "use as your base image. You will then select your sub\n"
                            + "images.Your sub images will be stitched together to  \n"
                            + "build your base image. Smaller images will be easier \n"
                            + "to build. Suggested tile size is 10. You can choose  \n"
                            + "to make the mosaic Black and White, in which case you\n"
                            + "can also use the built in selection of icons",
                            "Mozaic Builder", JOptionPane.PLAIN_MESSAGE);
            try {
                chooseMainImage();
                chooseSubImages();
            } catch(IOException e) {
                JOptionPane.showMessageDialog(null, "There was an Error in selecteing"
                        + " images. Try Again!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

            try {
                readImages();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "There was an Error in reading the images"
                        + " images. Try Again!", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            BlackWhiteMozaic maker = new BlackWhiteMozaic(mainImage, subImages, MozaicMode.BLACK_AND_WHITE);
            //TextMozaic maker = new TextMozaic(mainImage, "Hello");
            maker.build();
            mosaic = maker.getImage();
            save();
            
//            int fileSaverState;
//            do {
//                fileSaverState = fileSaver.showSaveDialog(null);
//                saveFile = fileSaver.getSelectedFile();
//            } while (fileSaverState == JFileChooser.APPROVE_OPTION);
//
//            
//
//            save(saveFile);
            
        }
    }

    private static void readImages() throws IOException {
        mainImage = ImageIO.read(imageFile);

        for (File image : imagesDirectory.listFiles()) {
            System.out.println("Reading Image: " + image.getAbsolutePath());
            BufferedImage img = ImageIO.read(image);
            subImages.add(img);
        }
    }

    private static void initializeComponents() {
        subImages = new HashSet<BufferedImage>();

        fileSelecter = new JFileChooser();
        fileSelecter.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        fileSelecter.setDialogTitle("Pick the Main Image");

        folderSelecter = new JFileChooser();
        folderSelecter.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderSelecter.setDialogTitle("Pick the library of sub images."); // informs user of what to pick

        fileSaver = new JFileChooser();
        fileSaver.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        fileSaver.setDialogTitle("Save Your Mosaic");
    }

    private static void save(/*File outputFile*/) {
        try {
            File outputfile = new File("collage.png");
            ImageIO.write(mosaic, "jpg", outputfile);
        } catch(IOException e) {
            System.out.println("error");
        }
    }

    public static void chooseMainImage() throws IOException {
        int fileChooserState = fileSelecter.showOpenDialog(null);
        if (fileChooserState == JFileChooser.APPROVE_OPTION) { // if acceptable file is chosen shows user what file they picked in JOptionPane window
            JOptionPane.showMessageDialog(null,"You opened file: " + fileSelecter.getSelectedFile().getPath());
            imageFile  = fileSelecter.getSelectedFile();
        } else {
            System.out.println("Image not Chosen");
            chooseMainImage();
        }
    }

    // Prompts the user through JFileChooser to pick a library of images to make the final image out of.
    public static void chooseSubImages() throws IOException {
        int fileChooserState = folderSelecter.showOpenDialog(null);
        if (fileChooserState == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "You opened this library: " + folderSelecter.getSelectedFile().getPath()); // informs user which library was opened
            imagesDirectory = folderSelecter.getSelectedFile();
        } else {
            System.out.println("Directory not Chosen");
            chooseSubImages();
        }
    }

}
