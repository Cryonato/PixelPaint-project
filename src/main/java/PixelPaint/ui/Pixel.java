package PixelPaint.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Pixel extends StackPane implements GridCell{
  private int x;
  private int y;
  private int color = 1;
  
  public Pixel(int x, int y){
    super();
    this.x = x;
    this.y = y;
    this.getStyleClass().add("pixel");
    this.setMinSize(30, 30);
    this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    //event handler for mouse click
    this.setOnMouseClicked(event -> changeColor());
  }
  
  public void changeColor(){
    if (this.color == 0){
      this.color = 1;
      setStyle("-fx-background-color: #FFFFFF");
    } else {
      this.color = 0;
      setStyle("-fx-background-color: #000000");
    }
  }
  public void setColor(int color){
    this.color = color;
    if (color == 0){
      setStyle("-fx-background-color: #000000");
    } else {
      setStyle("-fx-background-color: #FFFFFF");
    }
  }
  public int getColor(){
    return this.color;
  }
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
}
