package pucheril.nathan.Mozaic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import MyLibrary

import pucheril.nathan.Mozaic.ImageStitcher.StitchMode;


public class TextMozaic extends Mozaic {
    char[] characters;
    int currLetterPosition = 0;
    public TextMozaic(BufferedImage mainImage, String phrase) {
        super(mainImage);
        
        characters = phrase.toCharArray();
    }

    public void build() {
        getMainImageColorTiles(tileSize);
        draw();
        built = true;
    }

    private void draw() {
        BufferedImage stitched = null;
        BufferedImage verticalRender = null;
        BufferedImage img2;
        // draws each image at the correct position in the final image
        for (int i = 0 ; i < colorTiles.length; i++) {


            BufferedImage img1 = getImageToStitch(i,0);
            for (int j = 1; j < colorTiles[i].length-1; j++) {
                img2 = getImageToStitch(i,j);
                stitched = ImageStitcher.stitch(img2, img1, StitchMode.VERTICAL);
                System.out.println("Building Images....");
                img1 = stitched;
            }
            if (i == 0) {
                verticalRender = stitched;
            } else {
                verticalRender = ImageStitcher.stitch(verticalRender, stitched, StitchMode.HORIZONTAL);
            }
        }   
        mosaic = verticalRender;

    }

    private BufferedImage getImageToStitch(int x, int y) {
        char letter = characters[currLetterPosition];
        currLetterPosition++;
        if (currLetterPosition == characters.length) {
            currLetterPosition = 0;
        }
        Color letterColor = colorTiles[x][y];
                
        BufferedImage imageToStitch = 
        return imageToStitch;
    }


}