/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imageeditornet;
/**
 *
 * @author KNolf
 */
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver {
    public static void saveImage(Stage stage, Image image) {
   //Creates a FileChooser for selecting the file to save.
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Image File");
    //Uses the fileExtension to see what type of file the photo was loaded as, and uses png as backup.
    String format = ImageLoader.fileExtension != null ? ImageLoader.fileExtension : "png";
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(format.toUpperCase() + " files (*." + format + ")", "*." + format);
    fileChooser.getExtensionFilters().add(extFilter);
    //Shows save dialog and gets the selected file. 
    File file = fileChooser.showSaveDialog(stage);
    if (file != null) {
        try {
            //Creates a BufferedImage with same dimensions and uses PixelReader to read pixels and add to BufferedImage.
            BufferedImage bImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            PixelReader pixelReader = image.getPixelReader();
            int[] pixels = new int[(int) image.getWidth() * (int) image.getHeight()];
            pixelReader.getPixels(0, 0, (int) image.getWidth(), (int) image.getHeight(), javafx.scene.image.PixelFormat.getIntArgbInstance(), pixels, 0, (int) image.getWidth());
            bImage.setRGB(0, 0, (int) image.getWidth(), (int) image.getHeight(), pixels, 0, (int) image.getWidth());
            ImageIO.write(bImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}