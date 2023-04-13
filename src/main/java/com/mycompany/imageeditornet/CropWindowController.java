/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imageeditornet;

/**
 *
 * @author Kyle
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class CropWindowController {
    @FXML
    private Pane cropPane;
    @FXML
    private ImageView cropImageView;
    @FXML
    private Rectangle cropRect;
    @FXML
    private Button cropButton;
    private Image originalImage;
    private Image croppedImage;
    private Consumer<Image> onCrop;
    
    //Sets the image to the ImageView.
    public void setOriginalImage(Image originalImage) {
        this.originalImage = originalImage;
        cropImageView.setImage(originalImage);
    }
    //Returns the cropped image. 
    public Image getCroppedImage() {
        return croppedImage;
    }
    //Sets the callback for cropping.
    public void setOnCrop(Consumer<Image> onCrop) {
        this.onCrop = onCrop;
    }

    @FXML
    public void initialize() {
        //Sets the rectangle for cropping.
        cropRect.setStroke(Color.RED);
        cropRect.setStrokeWidth(2);
        cropRect.setFill(Color.TRANSPARENT);
        final double[] startX = new double[1];
        final double[] startY = new double[1];
        //Sets the mouse pressed event for the crop.
        cropPane.setOnMousePressed(event -> {
            startX[0] = event.getX();
            startY[0] = event.getY();
            cropRect.setX(startX[0]);
            cropRect.setY(startY[0]);
            cropRect.setWidth(0);
            cropRect.setHeight(0);
        });
        //Sets the mouse dragged event handler for the crop pane. 
        cropPane.setOnMouseDragged(event -> {
            double x = Math.min(event.getX(), cropImageView.getImage().getWidth());
            double y = Math.min(event.getY(), cropImageView.getImage().getHeight());
            double w = Math.abs(event.getX() - startX[0]);
            double h = Math.abs(event.getY() - startY[0]);
            cropRect.setX(startX[0]);
            cropRect.setY(startY[0]);
            cropRect.setWidth(w);
            cropRect.setHeight(h);
        });
        //Allows for crop button to perform the cropping.
        cropButton.setOnAction(event -> {
            croppedImage = crop(originalImage, (int) cropRect.getX(), (int) cropRect.getY(), (int) cropRect.getWidth(), (int) cropRect.getHeight());
            if (onCrop != null) {
                onCrop.accept(croppedImage);
            }
            Stage stage = (Stage) cropPane.getScene().getWindow();
            stage.close();
        });
    }
    //Crops the image using the coordinates and dimensions. 
    private static Image crop(Image image, int x, int y, int width, int height) {
        PixelReader reader = image.getPixelReader();
        WritableImage newImage = new WritableImage(reader, x, y, width, height);
        return newImage;
    }
}