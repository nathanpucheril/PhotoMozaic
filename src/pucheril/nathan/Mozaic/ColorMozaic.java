package pucheril.nathan.Mozaic;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;

public class ColorMozaic extends Mozaic {
    //range for rgb val maps is 17
    private Map<Integer, Set<BufferedImage>> rMaps; // Map from Red Value to Set of Images that fit criteria
    private Map<Integer, Set<BufferedImage>> gMaps; // Map from Intensity Value to Set of Images that fit criteria
    private Map<Integer, Set<BufferedImage>> bMaps; // Map from Intensity Value to Set of Images that fit criteria
    
    public ColorMozaic(BufferedImage mainImage, Set<BufferedImage> subimages,
            MozaicMode mode) {
    }

}
