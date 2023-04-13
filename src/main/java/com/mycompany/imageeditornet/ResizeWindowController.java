/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imageeditornet;

/**
 *
 * @author Kyle & Nick
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ResizeWindowController {

    @FXML
    private ImageView resizeImageView;
    @FXML
    private TextField widthField;
    @FXML
    private TextField heightField;
    @FXML
    private Button saveButton;
    private Image originalImage;
    private Image resizedImage;
    //Updates the ImageView with the dimensions from the TextFields.
    public void setOriginalImage(Image image) {
        originalImage = image;
        resizeImageView.setImage(image);
        widthField.setText(String.valueOf((int) image.getWidth()));
        heightField.setText(String.valueOf((int) image.getHeight()));
    }
    //Gets the resized image.
    public Image getResizedImage() {
        return resizedImage;
    }

    @FXML
    private void initialize() {
        //Sets an action listener for the save button.
        saveButton.setOnAction(event -> {
            //Collects the width and height from the TextFields.
            int newWidth = Integer.parseInt(widthField.getText());
            int newHeight = Integer.parseInt(heightField.getText());
            //Resizes the image with new dimensions
            resizedImage = resizeImage(originalImage, newWidth, newHeight);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        });
    }
    //Resizes the image to the specified dimensions.
    private Image resizeImage(Image image, int newWidth, int newHeight) {
        if (image != null) {
            //Uses PixelReader to read pixels from the image.
            PixelReader pixelReader = image.getPixelReader();
            //Creates a WritableImage with new dimensions.
            WritableImage newImage = new WritableImage(newWidth, newHeight);

            for (int x = 0; x < newWidth; x++) {
                for (int y = 0; y < newHeight; y++) {
                    double oldX = x * (image.getWidth() / newWidth);
                    double oldY = y * (image.getHeight() / newHeight);

                    Color color = pixelReader.getColor((int) oldX, (int) oldY);
                    newImage.getPixelWriter().setColor(x, y, color);
                }
            }
            //Returns the resized image.
            return newImage;
        }
        return null;
    }
}