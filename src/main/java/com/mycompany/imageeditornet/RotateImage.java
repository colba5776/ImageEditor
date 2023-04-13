/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imageeditornet;

/**
 *
 * @author Kyle
 */
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class RotateImage {
    public static Image rotateImage(Image image, double angle, ImageView imageView) {
        //Calculates the center of image to handle rotation.
        double centerX = image.getWidth() / 2;
        double centerY = image.getHeight() / 2;
        //Creates a temp ImageView to hold the original image.
        ImageView tempImageView = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = tempImageView.snapshot(params, null);
        //Creates a rotation object.
        Rotate rotation = new Rotate(angle, centerX, centerY);
        tempImageView.getTransforms().add(rotation);
        //Takes a snapshot of the rotated image and sets it to image view, then returns image.
        rotatedImage = tempImageView.snapshot(params, null);
        imageView.setImage(rotatedImage);
        return rotatedImage;
    }
}