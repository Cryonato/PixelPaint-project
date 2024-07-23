package PixelPaint.filemanager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import PixelPaint.ui.PaintGrid;
import PixelPaint.ui.Pixel;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SingleFile {

  private String fileName;
  private PaintGrid paintGrid;

  public SingleFile(){
    paintGrid = new PaintGrid();
  }
  public void setGrid(PaintGrid paintGrid){
    this.paintGrid = paintGrid;
  }

  public void saveFile(String fileName){
    String filePath = "src/main/resources/PixelPaint/savefiles/save.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        String data = "";
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Pixel pixel = paintGrid.getPixel(x, y);
                if (pixel != null) {
                    int color = pixel.getColor();
                    data += color;
                }
            }
        }
        writer.write(fileName + ":");
        writer.write(data);
        writer.newLine();
        System.out.println("Grid saved to file successfully.");
    } catch (IOException e) {
        System.err.println("Error saving grid to file: " + e.getMessage());
    }
  }
  public void setFileName(String fileName){
    this.fileName = fileName;
  }
  public String getFileName(){
    return fileName;
  }
  public PaintGrid getGrid(){
    return paintGrid;
  }
 
  public void extractData(String data){
    char[] colors = data.toCharArray();
    
    for (int y = 0; y < 10; y++) {
        for (int x = 0; x < 10; x++) {
            this.paintGrid.setColor((int) colors[y * 10 + x], x, y);;
        }
    }
  }
  public String getData(){
    String data = "";
    for (int y = 0; y < 10; y++) {
        for (int x = 0; x < 10; x++) {
            Pixel pixel = paintGrid.getPixel(x, y);
            if (pixel != null) {
                int color = pixel.getColor();
                data += (char) color;
            }
        }
    }
    return data;
  }
}
