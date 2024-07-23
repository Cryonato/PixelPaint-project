package PixelPaint;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import PixelPaint.ui.PaintGrid;
import PixelPaint.ui.Pixel;
import PixelPaint.filemanager.SavedFiles;
import PixelPaint.filemanager.SingleFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//javafx imports
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;


public class PixelPaintController {
    @FXML
    private Button clearButton;
    @FXML
    private GridPane gridpane;
    @FXML
    private Button saveButton;
    @FXML
    private ChoiceBox<String> loadChoiceBox;
    @FXML
    public TextField fileName;
    @FXML
    private Button deleteButton;

    private PaintGrid paintGrid = new PaintGrid();

    private SavedFiles savedFiles;

    @FXML
    public void initialize() {
        loadChoiceBox.setValue("Load file");
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Pixel pixel = new Pixel(x, y);
                gridpane.add(pixel, x, y);
                paintGrid.setPixel(pixel, x, y);}
        }
        //loading saved files
        savedFiles = new SavedFiles();
        savedFiles.initialize();

        for (SingleFile file : savedFiles.getSavedFiles()) {
            if (file != null){
                loadChoiceBox.getItems().add(file.getFileName());
            }
        }

    }
    @FXML
    private void paintButton(Pixel pixel){
        pixel.changeColor();
    }
    @FXML
    public void clearGrid(){
        paintGrid.clearGrid();
    }
    
    
    @FXML   
    public void save(){
        SingleFile saveFile = new SingleFile();
        saveFile.setGrid(paintGrid);
        String name = fileName.getText();
        if (name.isEmpty()) {
            fileName.requestFocus();
            return; 
        }

        fileName.clear();
        saveFile.setFileName(name);
        saveFile.saveFile(name);
        loadChoiceBox.getItems().add(name);
        savedFiles.initialize();

        paintGrid.clearGrid();
    }
    @FXML
    public void load(){
        String fileName = loadChoiceBox.getValue();
        if (fileName.equals("Load file") || fileName == null) {
            return;
        }
        SingleFile file = savedFiles.loadGrid(fileName);
        if (file == null) {
            // Handle the case where the file could not be loaded
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load file");
            alert.setContentText("The file could not be loaded.");
            alert.showAndWait();
            return;
        }
        String colors = file.getData();
        System.out.println(colors);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                int colorIndex = y * 10 + x;
                Pixel pixel = paintGrid.getPixel(x, y);
                if (colors.charAt(colorIndex) == '1') {
                    pixel.setColor(1);
                } else {
                    pixel.setColor(0);
                }
            }
        }
    }
    @FXML
    public void setGrid(PaintGrid paintGrid){
        this.paintGrid = paintGrid;
    }
    @FXML
    public PaintGrid getPaintGrid(){
        return this.paintGrid;
    }
    @FXML
    private void clearFiles(){
        savedFiles.clearFiles();
        loadChoiceBox.getItems().clear();
        loadChoiceBox.setValue("Load file");
        paintGrid.clearGrid();
    }
}
