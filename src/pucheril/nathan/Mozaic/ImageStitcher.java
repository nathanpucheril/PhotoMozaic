package pucheril.nathan.Mozaic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Set;


public class ImageStitcher {

    private BufferedImage buildImage;
    private BufferedImage[][] tiles;

    private int width, height;

    public static enum StitchMode {HORIZONTAL, VERTICAL}

    public ImageStitcher(BufferedImage[][] tiles,int width, int height,int tileWidth, int tileHeight) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        BufferedImage verticalBuild;
//        for (int i = 0; i < tiles.length - 1; i++) {
//            for (int j = 0; j < tiles[i].length - 1; j++) {
//                verticalBuild = stitch(verticalBuild,)
//            }
//        }
    }

    public static BufferedImage mappedTileStitch(int width, int height, int tileCountX, int tileCountY, int[][] stitchLocations) {

        return null;
    }

    public static BufferedImage randomTileStitch(Set<BufferedImage> images, int finalWidth, int finalHeight, int tileCountX, int tileCountY) {

        return null;
    }

    public static BufferedImage stitch(BufferedImage img1, BufferedImage img2, StitchMode mode) {
        BufferedImage stitched;
        Graphics2D g;
        int stitchedWidth = 0, stitchedHeight = 0;

        int img1Width = img1.getWidth();
        int img1Height = img1.getHeight();

        int img2Width = img2.getWidth();
        int img2Height = img2.getHeight();

        if (mode == null) {
            mode = StitchMode.VERTICAL;
        }
        switch (mode) {
        case HORIZONTAL: 
            stitchedWidth = img1Width + img2Width;
            stitchedHeight = Math.max(img1Height, img2Height);
            break;
        case VERTICAL:
            stitchedHeight = img1Height + img2Height;
            stitchedWidth = Math.max(img1Width, img2Width);
            break;
        default:
            break;
        }

        stitched = new BufferedImage(stitchedWidth, stitchedHeight,BufferedImage.TYPE_INT_RGB);
        g = stitched.createGraphics();
        g.drawImage(img1, 0, 0, null);

        switch (mode) {
        case HORIZONTAL: 
            g.drawImage(img2, img1Width, 0, null);
            break;
        case VERTICAL:
            g.drawImage(img2, 0, img1Height, null);
            break;
        default:
            break;
        }
        
        g.dispose();
        return stitched;
    }

}
