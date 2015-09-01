package pucheril.nathan.MozaicV2;

import java.awt.image.BufferedImage;

import pucheril.nathan.Mozaic.ImageStitcher;
import pucheril.nathan.Mozaic.ImageStitcher.StitchMode;

public abstract class Mozaic {
    
    protected BufferedImage mosaic;
    protected boolean built = false;
    protected int tileSize = 10;
    
    
    protected BufferedImage draw(int horizontalTiles, int verticalTiles) {
        BufferedImage stitched = null;
        BufferedImage verticalRender = null;
        BufferedImage img2;
        BufferedImage finalImg;
        // draws each image at the correct position in the final image
        for (int i = 0 ; i < horizontalTiles; i++) {
            System.out.println("Building Image....");

            BufferedImage img1 = getImageToStitch(i,0);
            for (int j = 1; j < verticalTiles - 1; j++) {
                img2 = getImageToStitch(i,j);
                stitched = ImageStitcher.stitch(img2, img1, StitchMode.VERTICAL);
                img1 = stitched;
            }
            if (i == 0) {
                verticalRender = stitched;
            } else {
                verticalRender = ImageStitcher.stitch(verticalRender, stitched, StitchMode.HORIZONTAL);
            }
        }   
        finalImg = verticalRender;
        return finalImg;        
    }
    
    protected abstract BufferedImage getImageToStitch(int i, int j);

    /**
     * Returns Mosaic as BufferedImage if built. 
     * If not built, returns null
     */
    public BufferedImage getImage() {
        if (built) return mosaic;
        return null;
    }

}
