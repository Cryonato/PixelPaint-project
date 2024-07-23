package PixelPaint.filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import PixelPaint.ui.PaintGrid;

public class SavedFiles {
  private String filePath = "src/main/resources/PixelPaint/savefiles/save.txt";

  private SingleFile[] savedFiles;

  public SavedFiles(){
    savedFiles = new SingleFile[10];
  }
  public void addFile(SingleFile file){
    for (int i = 0; i < savedFiles.length; i++) {
      if (savedFiles[i] == null) {
        savedFiles[i] = file;
        break;
      }
    }
  }
  public SingleFile[] getSavedFiles(){
    return savedFiles;
  }
  public void initialize(){
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {        
        String[] parts = line.split(":");
        if (parts.length != 2) {
          throw new IllegalArgumentException("Invalid file data");
        }
        String name = parts[0];
        String data = parts[1];

        SingleFile file = new SingleFile();
        if (name.isEmpty() || data.isEmpty()) {
          throw new IllegalArgumentException("Invalid file data");
        }
        file.setFileName(name);
        file.extractData(data);
        System.out.println(name + ": " + data);
        addFile(file);
      }

    } catch (IOException e) {
        e.printStackTrace();
        return;
    }
  }
  public SingleFile loadGrid(String fileName){
    for (SingleFile file : savedFiles) {
      if (file != null && file.getFileName().equals(fileName)) {
        return file;
      }
    } 
    return null;   
  }
  public void clearFiles(){
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        writer.write("");
        System.out.println("Saved files cleared successfully.");
    } catch (IOException e) {
        System.err.println("Error writing to file: " + e.getMessage());
    }
  }
}
