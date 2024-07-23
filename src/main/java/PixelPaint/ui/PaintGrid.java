package PixelPaint.ui;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import PixelPaint.filemanager.SingleFile;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PaintGrid extends GridPane{
  private final Pixel[][] pixelGrid = new Pixel[10][10];
  
  public PaintGrid(){
    for (int y = 0; y < 10; y++) {
        for (int x = 0; x < 10; x++) {
            Pixel pixel = new Pixel(x, y);
            
            getChildren().add(pixel);
            pixelGrid[x][y] = pixel;
        }
    } 
  }
  public Pixel getPixel(int x, int y) {
    if (isValidCoordinate(x, y)) {
        return pixelGrid[x][y];
    } else {
        throw new IllegalArgumentException("Invalid coordinates");
    }
  }
  public void extractData(String data){
    char[] colors = data.toCharArray();
    
    for (int y = 0; y < 10; y++) {
        for (int x = 0; x < 10; x++) {
            Pixel pixel = new Pixel(x, y);
            pixel.setColor((int) colors[y * 10 + x]);
            pixelGrid[x][y] = pixel;
        }   
    }
  }

  
  public void setPixel(Pixel pixel, int x, int y) {
    if (isValidCoordinate(x, y) && pixel != null){
        pixelGrid[x][y] = pixel;
    } else {
        throw new IllegalArgumentException("Invalid coordinates");
    }
  }
  public void setColor(int color, int x, int y) {
    if (isValidCoordinate(x, y)) {
        pixelGrid[x][y].setColor(color);
    } else {
        throw new IllegalArgumentException("Invalid coordinates");
    }

  }
  public void clearGrid(){
    for (int y = 0; y < 10; y++) {
        for (int x = 0; x < 10; x++) {
          if (pixelGrid[x][y] != null) {
            pixelGrid[x][y].setColor(1);
            pixelGrid[x][y].setStyle("-fx-background-color: #FFFFFF");
        }
        }
    } 
  }
  private boolean isValidCoordinate(int x, int y) {
    return x >= 0 && x < 10 && y >= 0 && y < 10;
  }

  
}


