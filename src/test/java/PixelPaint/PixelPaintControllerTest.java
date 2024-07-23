package PixelPaint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import PixelPaint.ui.Pixel;
import javafx.application.Application;

import javafx.scene.control.TextField;
import PixelPaint.ui.PaintGrid;
import PixelPaint.PixelPaintController;
import PixelPaint.filemanager.SingleFile;

public class PixelPaintControllerTest {

    private PixelPaintController controller = new PixelPaintController();
    SingleFile testFile = new SingleFile();

    private PaintGrid paintGrid = new PaintGrid();
    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testClearGrid() {
        
        // Manually set some pixels to color 0
        Pixel pixel1 = new Pixel(0, 0);
        Pixel pixel2 = new Pixel(1, 1);
        Pixel pixel3 = new Pixel(2, 2);
        controller.getPaintGrid().setPixel(pixel1, 0, 0);
        controller.getPaintGrid().setPixel(pixel2, 1, 1);
        controller.getPaintGrid().setPixel(pixel3, 2, 2);
        
        // Clear the grid
        controller.clearGrid();

        // Check if all pixels are now the default color (1)
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                assertEquals(1, controller.getPaintGrid().getPixel(x, y).getColor());
            }
        }
    }

    @Test
    public void testSaveAndLoad() {

        String data = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        paintGrid.extractData(data);

        controller.setGrid(paintGrid);


        // Check if the loaded grid matches the original one
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                assertEquals(controller.getPaintGrid().getPixel(x, y).getColor(), 1);
            }
        }
    }
}