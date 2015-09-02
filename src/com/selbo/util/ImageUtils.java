package com.selbo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Josh on 9/1/2015.
 */
public class ImageUtils {
    private static final String IMAGES_DIRECTORY = "Resources/Images";

    public static Image loadImage(String path) {
        String fullPath = String.format("%s/%s", IMAGES_DIRECTORY, path);
        try {
            return ImageIO.read(new File(fullPath));
        } catch (IOException e) {
            System.err.printf("Error loading image: '%s'\n", fullPath);
        }
        return null;
    }
}
