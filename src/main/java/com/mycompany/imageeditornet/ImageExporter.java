/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imageeditornet;

/**
 *
 * @author Kyle & Colton
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.stage.Stage;

public class ImageExporter {

    private final ImageView imageView;

    public ImageExporter(ImageView imageView) {
        this.imageView = imageView;
    }

    public void export(Stage stage) {
        //Creates a FileChooser to select file.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image File");
        //Adds extension filters to support various image formats.
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"),
            new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
            new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp"),
            new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif")
        );
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                //Gets the image from ImageView, and creates a Buffered Image, where it gets the pixels from PixelWrtier and sets them to Buffered Image.
                Image image = imageView.getImage();
                BufferedImage bImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                PixelReader pixelReader = image.getPixelReader();
                int[] pixels = new int[(int) image.getWidth() * (int) image.getHeight()];
                pixelReader.getPixels(0, 0, (int) image.getWidth(), (int) image.getHeight(), javafx.scene.image.PixelFormat.getIntArgbInstance(), pixels, 0, (int) image.getWidth());
                bImage.setRGB(0, 0, (int) image.getWidth(), (int) image.getHeight(), pixels, 0, (int) image.getWidth());
                //Sets the Buffered Image to the desired file format. 
                String fileName = file.getName();
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
                String format = fileExtension.toUpperCase();
                ImageIO.write(bImage, format, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
