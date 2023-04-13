/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imageeditornet;

/**
 *
 * @author Kyle & Nick
 */
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EffectsController {
    @FXML
    private ImageView imageView;
    @FXML
    private Button grayscaleButton;
    @FXML
    private Button sepiaButton;
    @FXML
    private Button blurButton;
    @FXML
    private Button resetButton;
    private Image originalImage;
    private Image editedImage;
    private Consumer<Image> updateImageCallback;
    @FXML
    private Button saveButton;
    //Method to initalize the effects with the required data.
    public void initData(Stage stage, ImageView imageView, Consumer<Image> updateImageCallback) {
        Image image = imageView.getImage();
        if (image != null) {
            originalImage = image;
            this.imageView.setImage(originalImage);
            editedImage = originalImage;
            this.updateImageCallback = updateImageCallback;
        }
    }
    @FXML
    private void applyGrayscale() {
        // Create the grayscale effect
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1);
        // Apply the effect to the image
        applyEffect(grayscale);
    }
    @FXML
    private void applySepia() {
        // Create the sepia effect
        SepiaTone sepia = new SepiaTone();
        sepia.setLevel(0.5);
        // Apply the effect to the image
        applyEffect(sepia);
    }
    @FXML
    private void applyBlur() {
        // Create the blur effect
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(10);
        // Apply the effect to the image
        applyEffect(blur);
    }
    @FXML
    private void reset() {
        // Resets the image to the original
        imageView.setImage(originalImage);
        imageView.setEffect(null);
        editedImage = originalImage;
    }
    private void applyEffect(Effect effect) {
        //Applies the effect to the Image View.
        imageView.setEffect(effect);
        //Takes a snapshot of the imageView with the applied effect
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        editedImage = imageView.snapshot(snapshotParameters, null);
    }
    @FXML
    //Saves the effect added.
    private void save() {
        updateImageCallback.accept(editedImage);
        ((Stage) saveButton.getScene().getWindow()).close();
    }
    //Updates the image.
    public void updateImage(Image newImage) {
        imageView.setImage(newImage);
    }   
}
