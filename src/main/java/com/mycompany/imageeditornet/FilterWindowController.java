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
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FilterWindowController {
    @FXML
    private ImageView filterImageView;
    @FXML
    private Slider brightnessSlider;
    @FXML
    private Slider contrastSlider;
    @FXML
    private Slider hueSlider;
    @FXML
    private Slider saturationSlider;
    @FXML
    private Button saveFilterButton;
    private Image filteredImage;
    //Sets the original image to ImageView.
    public void setOriginalImage(Image originalImage) {
        filterImageView.setImage(originalImage);
    }
    //Returns filtred image.
    public Image getFilteredImage() {
        return filteredImage;
    }
    @FXML
    public void initialize() {
        //Creates ColorAdjust Effect and Applies to ImageView.
        ColorAdjust colorAdjust = new ColorAdjust();
        filterImageView.setEffect(colorAdjust);
        //Adds a slider to adjust Brightness.
        brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setBrightness(newValue.doubleValue());
        });
        //Adds a slider to adjust Contrast.
        contrastSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setContrast(newValue.doubleValue());
        });
        //Adds a slider to adjust the Hue.
        hueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setHue(newValue.doubleValue());
        });
        //Adds a slider to adjust the saturation.
        saturationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            colorAdjust.setSaturation(newValue.doubleValue());
        });
        //Saves the image with filters added.
        saveFilterButton.setOnAction(event -> {
            filteredImage = filterImageView.snapshot(null, null);
            
            ((Stage) filterImageView.getScene().getWindow()).close();
        });
    }
}