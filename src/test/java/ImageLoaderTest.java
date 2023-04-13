/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.imageeditornet.ImageLoader;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageLoaderTest {
    private WritableImage img;
    private Stage stage;
    
    public ImageLoaderTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        Platform.startup(() -> {
            img = new WritableImage(100, 100);
            img.getPixelWriter().setColor(0, 0, Color.BLACK);
        });
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void imageLoading() {
        ImageView imageView = new ImageView();
        Platform.runLater(() -> {
            assertSame(img, ImageLoader.loadImage(stage, imageView));
        });
    }
}
