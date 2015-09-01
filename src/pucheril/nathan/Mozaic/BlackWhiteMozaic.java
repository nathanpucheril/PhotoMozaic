package pucheril.nathan.Mozaic;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Set;

import pucheril.nathan.Mozaic.ImageStitcher.StitchMode;


public class BlackWhiteMozaic extends Mozaic {
    

    private boolean preloadedIcons = false;
    private int tileSize = 8; //CHANGE CHANGE CHANGE

    public BlackWhiteMozaic(BufferedImage mainImage, Set<BufferedImage> subimages, MozaicMode mode) {
        super(mainImage, subimages, mode);
    }

    public void build() {
        intensityMaps = new HashMap<Integer, Set<BufferedImage>>();
        if(preloadedIcons) {
            this.subimagesWidth = 20;
            this.subimagesWidth = 20;
        }

        getSubimageIntensities();
        getMainImageIntensityTiles(tileSize);
        draw();
        built = true;
    }

    private void draw() {
        BufferedImage stitched = null;
        BufferedImage verticalRender = null;
        BufferedImage img2;
        // draws each image at the correct position in the final image
        for (int i = 0 ; i < intensityTiles.length; i++) {


            BufferedImage img1 = getImageToStitch(i,0);
            for (int j = 1; j < intensityTiles[i].length-1; j++) {
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
        BufferedImage imageToStitch;
        int requiredIntensity = intensityTiles[x][y];
        int intensityKey = (int) Math.floor(requiredIntensity/15);
        Set<BufferedImage> validImages;
        do {
            validImages = intensityMaps.get(intensityKey);
            intensityKey++;
        } while (validImages == null);
        imageToStitch = pickHashSetElem(validImages);

        return imageToStitch;
    }

}
