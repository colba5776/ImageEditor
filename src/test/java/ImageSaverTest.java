/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.imageeditornet.ImageSaver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageSaverTest {
    private static WritableImage img;
    private static Stage stage;
    
    public ImageSaverTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        Platform.startup(() -> {
            img = new WritableImage(100, 100);
            img.getPixelWriter().setColor(0, 0, Color.BLACK);
            stage = new Stage(); // create a mock Stage object
        });
    }
    
    @AfterAll
    public static void tearDownClass() {
        Platform.exit();
    }

    @Test
    public void imageSaving() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ImageView imageView = new ImageView(img);

         Platform.runLater(() -> {
            try {
                ImageSaver.saveImage(stage, imageView.getImage());
            } catch (Exception ex) {
                fail(ex.getMessage());
            } finally {
                latch.countDown();
            }
        });

        latch.await(10, TimeUnit.SECONDS);
    }
}