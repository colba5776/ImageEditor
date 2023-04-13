/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imageeditornet;

/**
 *
 * @author Kyle
 */
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
public class ImageLoader {
    public static String fileExtension = null;
    public static Image loadImage(Stage stage, ImageView imageView) {
        //Creates a FileChooser for selecting an image file to open.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        //Displays the dialog to get the selected file. 
        File selectedFile = fileChooser.showOpenDialog(stage);
        //Sets the selected file into ImageView.
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(800);
            imageView.setFitHeight(600);
            //Stores file extension of selected file. 
            fileExtension = getFileExtension(selectedFile);
            return image;
        }
        return null;
    }
    //Method to store the fileType once loaded in
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}