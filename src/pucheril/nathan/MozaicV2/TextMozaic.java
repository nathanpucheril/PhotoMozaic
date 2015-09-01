package pucheril.nathan.MozaicV2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Set;

import pucheril.nathan.Mozaic.MozaicMode;

public class TextMozaic extends Mozaic {

    public TextMozaic(BufferedImage mainImage, Set<BufferedImage> subimages,
            MozaicMode mode) {
        super(mainImage, subimages, mode);


    }

    @Override
    protected BufferedImage getImageToStitch(int i, int j) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Creates an Image out of a character
     * @param letter
     * @param font
     * @param color
     * @param fontSize
     * @return BufferedImage of letter
     */
    private BufferedImage letterToImage(char letter, Font font, Color color, int fontSize) {
        String letterStr = Character.toString(letter);
        //Image file name
        String fileName = "Image";

        //create the font you wish to use
        font = new Font("Tahoma", Font.PLAIN, 11);

        //create the FontRenderContext object which helps us to measure the text
        FontRenderContext frc = new FontRenderContext(null, true, true);

        //get the height and width of the text
        Rectangle2D bounds = font.getStringBounds(letterStr, frc);
        int w = (int) bounds.getWidth();
        int h = (int) bounds.getHeight();

        //create a BufferedImage object
        BufferedImage image = new BufferedImage(w, h,   BufferedImage.TYPE_INT_RGB);

        //calling createGraphics() to get the Graphics2D
        Graphics2D g = image.createGraphics();

        //set color and other parameters
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setColor(color);
        g.setFont(font);

        g.drawString(letterStr,  (float) bounds.getX(), (float) bounds.getY());

        g.dispose();

        return image;
    }




}
