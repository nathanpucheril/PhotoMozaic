package pucheril.nathan.Mozaic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

abstract class Mozaic {
    
    protected Map<Integer, Set<BufferedImage>> intensityMaps; // Map from Intensity Value to Set of Images that fit criteria
    protected int[][] intensityTiles;
    protected Color[][] colorTiles;
    
    protected BufferedImage mosaic;
    protected BufferedImage mainImage;
    protected Set<BufferedImage> subimages;
    protected Graphics2D g;
    protected int mainImageWidth, mainImageHeight;
    protected int subimagesWidth, subimagesHeight;
    protected boolean built = false;
    protected int tileSize = 10;

    

    public Mozaic(BufferedImage mainImage, Set<BufferedImage> subimages, MozaicMode mode) {
        this.mainImage = mainImage;
        this.subimages = subimages; 
        
        this.mainImageHeight = mainImage.getHeight();
        this.mainImageWidth = mainImage.getWidth();
   }
    
    public Mozaic(BufferedImage mainImage) {
        this.mainImage = mainImage;
        
        this.mainImageHeight = mainImage.getHeight();
        this.mainImageWidth = mainImage.getWidth();
   }
    
    
    protected <K> K pickHashSetElem(Set<K> set) {
        int size = set.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for(K elem : set)
        {
            if (i == item) {
                return elem;
            }
            i++;
        }
        System.out.println("UNREACHABLE CODE REACHED!");
        return null;//Should be unreachable
    }

    protected void getMainImageIntensityTiles(int tileSize) {
        intensityTiles = new int [mainImageHeight / tileSize][mainImageWidth / tileSize]; // Instantiates array for image intensities
        int k = 0;

        // algorithm that finds average pixel intensities for all pixels within the specified block
        for (int j = 0; j < mainImageHeight - (mainImageHeight % tileSize); j++) {
            int f = 0;
            for (int i = 0; i < mainImageWidth  - (mainImageWidth % tileSize); i++) {
                if (i % tileSize == 0 && i != 0) 
                    f++;

                int intensity = ImageAnalytics.getIntensityOfPixel(mainImage, i, j);
                intensityTiles[k][f] += intensity;

            }

            if (j % tileSize == 0 && j != 0) 
                k++;
        }

        for (int i = 0; i < intensityTiles.length; i++) {
            for (int j = 0; j < intensityTiles[i].length; j++) {
                intensityTiles[i][j] /= (tileSize * tileSize);
            }
        }
    }
    
    protected void getMainImageColorTiles(int tileSize) {
        colorTiles = new Color [mainImageHeight / tileSize][mainImageWidth / tileSize]; // Instantiates array for image intensities
        
        for (int i = 0; i < (mainImageWidth / tileSize)-1; i++) {
            for (int j = 0; j < (mainImageHeight / tileSize)-1; j++){
               colorTiles[i][j] = ImageAnalytics.getAvgColor(mainImage, i*tileSize, j*tileSize, tileSize, tileSize);
            }
        }
    }
    
    protected void getSubimageIntensities() {
        for (BufferedImage subimage : subimages) {
            int intensity = ImageAnalytics.getAvgImageIntensity(subimage);

            int intensityKey = (int) Math.floor(intensity/15);

            if (intensityMaps.containsKey(intensityKey)) {
                intensityMaps.get(intensityKey).add(subimage);
            } else {
                HashSet<BufferedImage> hs = new HashSet<BufferedImage>();
                hs.add(subimage);
                intensityMaps.put(intensityKey, hs);
            } 
        }
    }
    
    /**Returns Mosaic as BufferedImage if built. If not built, throws NullPointerException.
     * @exception NullPointerException
     */
    public BufferedImage getImage() {
        if (built) {
            return mosaic;
        } else {
            throw new NullPointerException();
        }
    }


}


