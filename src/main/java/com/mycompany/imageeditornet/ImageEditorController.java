/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imageeditornet;

/**
 *
 * @author Kyle & Nick
 */
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ImageEditorController {
    @FXML
    private ImageView imageView;
    private Stage stage;
    private Image currentImage;
    private ImageExporter imageExporter;
    private Stage currentStage;
    private MenuItem effectsButton;
    private ArrayList<Image> imageHistory;
    private int currentStateIndex;
    private MenuItem undoButton;
    private MenuItem redoButton;
    
    //Initializes the imageHistory 
    public void initialize() {
        Platform.runLater(() -> {
            currentStage = (Stage) imageView.getScene().getWindow();
        });     
        imageHistory = new ArrayList<>();
        currentStateIndex = -1;
    }
    public void setStage(Stage stage) {
        this.stage = stage;  
    }
    //Allows for the redo and undo function to use saved images in the image history.
    private void updateImage(Image newImage) {
        imageView.setImage(newImage);
        currentImage = newImage;
        if (currentStateIndex < imageHistory.size() - 1) {
            imageHistory = new ArrayList<>(imageHistory.subList(0, currentStateIndex + 1));
        }
        imageHistory.add(newImage);
        currentStateIndex++;
    }
    //Handles the Open method.
    @FXML
    private void handleOpenImage() {
        Image image = ImageLoader.loadImage(stage, imageView);
        if (image != null) {
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(image.getWidth());
            imageView.setFitHeight(image.getHeight());
            currentImage = image;
            // Add the opened image to the history
            updateImage(currentImage);
        }
    }
    //Handles the Save method.
    @FXML
    private void handleSaveImage() {
        if (currentImage != null) {
            ImageSaver.saveImage(stage, currentImage);
        }
    }
    @FXML
    //Handles the Export method.
    private void handleExportImage() {
        if (currentImage != null) {
            ImageExporter imageExporter = new ImageExporter(imageView);
            imageExporter.export(stage);
        }
    }
    //Handles the Rotate method.
    @FXML
    private void handleRotateImage() {
        if (currentImage != null) {
            currentImage = RotateImage.rotateImage(currentImage, 90, imageView);
            updateImage(currentImage);
        }
    }
    //Handles the Crop method.
    @FXML
    private void handleCropImage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CropWindow.fxml"));
            Pane cropPane = loader.load();
            CropWindowController cropWindowController = loader.getController();
            cropWindowController.setOriginalImage(imageView.getImage());
            Stage cropWindow = new Stage();
            cropWindow.setTitle("Crop Image");
            Scene cropScene = new Scene(cropPane);
            cropWindow.setScene(cropScene);
            cropWindow.initModality(Modality.APPLICATION_MODAL);
            cropWindow.showAndWait();
            Image croppedImage = cropWindowController.getCroppedImage();
            if (croppedImage != null) {
                imageView.setImage(croppedImage);
                currentImage = croppedImage;
                updateImage(currentImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Handles the Filter method. 
    @FXML
    private void handleFilterImage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FilterWindow.fxml"));
            Pane filterPane = loader.load();
            FilterWindowController filterWindowController = loader.getController();
            filterWindowController.setOriginalImage(imageView.getImage());
            Stage filterWindow = new Stage();
            filterWindow.setTitle("Filter Image");
            Scene filterScene = new Scene(filterPane);
            filterWindow.setScene(filterScene);
            filterWindow.initModality(Modality.APPLICATION_MODAL);
            filterWindow.showAndWait();
            Image filteredImage = filterWindowController.getFilteredImage();
            if (filteredImage != null) {
                imageView.setImage(filteredImage);
                currentImage = filteredImage;
                updateImage(currentImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Handles the Resize method.
    @FXML
    private void handleResizeImage(ActionEvent event) {
        try {
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ResizeWindow.fxml"));
            VBox resizeWindowRoot = loader2.load();
            ResizeWindowController resizeWindowController = loader2.getController();
            resizeWindowController.setOriginalImage(imageView.getImage());
            Stage resizeWindow = new Stage();
            resizeWindow.setTitle("Resize Image");
            Scene resizeScene = new Scene(resizeWindowRoot);
            resizeWindow.setScene(resizeScene);
            resizeWindow.initModality(Modality.APPLICATION_MODAL);
            resizeWindow.showAndWait();
            Image resizedImage = resizeWindowController.getResizedImage();
            if (resizedImage != null) {
                imageView.setImage(resizedImage);
                currentImage = resizedImage;
                updateImage(currentImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Handles the Effects method.
    @FXML
    private void handleEffects() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EffectsWindow.fxml"));
            AnchorPane root = loader.load();
            EffectsController effectsController = loader.getController();
            effectsController.initData(currentStage, imageView, this::updateImage);
            Stage effectsStage = new Stage();
            effectsStage.setTitle("Effects Window");
            effectsStage.setScene(new Scene(root));
            effectsStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Handles the Undo method.
    @FXML
    private void handleUndo() {
        if (currentStateIndex > 0) {
            currentStateIndex--;
            currentImage = imageHistory.get(currentStateIndex);
            imageView.setImage(currentImage);
        }
    }
    //Handles the Redo method.
    @FXML
    private void handleRedo() {
        if (currentStateIndex < imageHistory.size() - 1) {
            currentStateIndex++;
            currentImage = imageHistory.get(currentStateIndex);
            imageView.setImage(currentImage);
        }
    }
}