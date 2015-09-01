package pucheril.nathan.Mozaic;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageAnalytics {

    public static Color getAvgColor(BufferedImage img) {
        long red = 0, green = 0, blue = 0;
        long height = img.getHeight(), width = img.getWidth();
        long pixels = height * width;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(img.getRGB(i, j)); 
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }
        }
        return new Color((int)(red/pixels),(int)(green/pixels),(int)(blue/pixels));
    }

    public static Color getAvgColor(BufferedImage img, int stepSize) {
        long red = 0, green = 0, blue = 0;
        long height = img.getHeight(), width = img.getWidth();
        long pixels = 0;

        for (int i = 0; i < width; i+= stepSize) {
            for (int j = 0; j < height; j+=stepSize) {
                Color c = new Color(img.getRGB(i, j)); 
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
                pixels++;
            }
        }

        return new Color((int)(red/pixels),(int)(green/pixels),(int)(blue/pixels));
    }

    public static Color getAvgColor(BufferedImage img, int x, int y, int w, int h) {
        long red = 0, green = 0, blue = 0;
        long pixels = w*h;

        for (int i = x; i <= w; i++) {
            for (int j = y; j <= h; j++) {
                Color c = new Color(img.getRGB(i, j)); 
                System.out.println(c.getRed());
                System.out.println(c.getGreen());
                System.out.println(c.getBlue());
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }
        }

        return new Color((int)(red/pixels),(int)(green/pixels),(int)(blue/pixels));
    }

    public static int getIntensityOfPixel(BufferedImage img, int x, int y) {
        Color pixelColor = new Color(img.getRGB(x, y));
        int r = pixelColor.getRed();
        int g = pixelColor.getGreen();
        int b = pixelColor.getBlue();

        int intensity = (int) Math.round(0.21 * r + 0.71 * g + 0.07 * b);


        return intensity;
    }

    public static int getAvgImageIntensity(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int numPixels = width * height;
        int intensities = 0;
        int avgIntensity;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                intensities += getIntensityOfPixel(img, i, j);
            }
        }
        avgIntensity = (int)( intensities / numPixels);

        return avgIntensity;
    }

    public static int getAvgImageIntensity(BufferedImage img, int stepSize) {
        int width = img.getWidth();
        int height = img.getHeight();
        int numPixels = width * height;
        int intensities = 0;
        int avgIntensity;

        for (int i = 0; i < width; i+= stepSize) {
            for (int j = 0; j < height; j+= stepSize) {
                intensities += getIntensityOfPixel(img, i, j);
            }
        }
        avgIntensity = (int)( intensities / numPixels);

        return avgIntensity;
    }

    public static int getAvgImageIntensity(BufferedImage img, int x, int y, int width, int height) {
        int numPixels = width * height;
        int intensities = 0;
        int avgIntensity;

        try {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    intensities += getIntensityOfPixel(img, i, j);
                }
            }
        } catch(IndexOutOfBoundsException e) {}
        avgIntensity = (int)( intensities / numPixels);

        return avgIntensity;
    }


    public static int getAvgImageIntensity(BufferedImage img, int x, int y, int width, int height, int stepSize) {
        int numPixels = width * height;
        int intensities = 0;
        int avgIntensity;
        try {
            for (int i = 0; i < width; i+=stepSize) {
                for (int j = 0; j < height; j+=stepSize) {
                    intensities += getIntensityOfPixel(img, i, j);
                }
            }
        } catch(IndexOutOfBoundsException e) {}
        avgIntensity = (int)( intensities / numPixels);

        return avgIntensity;
    }
    
    
}
