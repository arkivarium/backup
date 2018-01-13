package com.arkivarium;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

/**
 * Arkivarium Backup
 */

public class ArkivariumBackup extends Application {
    @Override
    public void start(Stage stage) {
        initUI(stage);
    }
    private void parseImport(File file) {
	    try {
		    FileReader fileReader = null;
		    fileReader = new FileReader(file);
		    // FIXME: Implement the tag parser
		    fileReader.close();
	    } catch (IOException ex) {
		    System.out.println("Failed to open " + file.getName() + "\n");
                    // Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }
    private void storeExport(File file) {
	    try {
		    FileWriter fileWriter = null;
		    fileWriter = new FileWriter(file);
		    fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		    fileWriter.write("<arkiv xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.arkivverket.no/standarder/noark5/arkivstruktur\" xmlns:n5mdk=\"http://www.arkivverket.no/standarder/noark5/metadatakatalog\" xsi:schemaLocation=\"http://www.arkivverket.no/standarder/noark5/arkivstruktur arkivstruktur.xsd\">\n");
		    fileWriter.write("</arkiv>\n");
		    fileWriter.close();
	    } catch (IOException ex) {
		    System.out.println("Failed to open " + file.getName() + "\n");
                    // Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }
    public void importBackup(Stage stage, String hostname, String portname, String username, String password) {
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
	    fileChooser.getExtensionFilters().add(extFilter);
	    File selectedImportFile = null;
	    fileChooser.setTitle("Open Noark5 Import File...");
	    fileChooser.setInitialFileName("arkivstruktur.xml");
	    selectedImportFile = fileChooser.showOpenDialog(stage);
	    if (fileChooser.getInitialFileName() != null) {
		    System.out.println("Importing backup from " + fileChooser.getInitialFileName() + "...\n" + hostname + ":" + portname + "/" + username + "/" + password);
		    File importFile = new File(fileChooser.getInitialFileName());
		    this.parseImport(importFile);
	    } else {
		    System.out.println("Failed to open " + fileChooser.getInitialFileName() + "\n");
	    }
    }
    public void exportBackup(Stage stage, String hostname, String portname, String username, String password) {
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
	    fileChooser.getExtensionFilters().add(extFilter);
	    File selectedExportFile = null;
	    fileChooser.setTitle("Save Noark5 Export File As...");
	    fileChooser.setInitialFileName("arkivstruktur.xml");
	    selectedExportFile = fileChooser.showOpenDialog(stage);
	    if (fileChooser.getInitialFileName() != null) {
		    System.out.println("Exporting backup to " + fileChooser.getInitialFileName() + "...\n" + hostname + ":" + portname + "/" + username + "/" + password);
		    File exportFile = new File(fileChooser.getInitialFileName());
		    this.storeExport(exportFile);
	    } else {
		    System.out.println("Failed to save/export backup file " + fileChooser.getInitialFileName());
	    }
    }
    private void initUI(Stage stage) {
        Button importbtn = new Button();
        importbtn.setText("Import");
        importbtn.setOnAction((ActionEvent event) -> {
	    this.importBackup(stage, "nikita.hioa.no", "9082", "admin", "password");
	    Platform.exit();	    
        });
	Button exportbtn = new Button();
	exportbtn.setText("Export");
	exportbtn.setOnAction((ActionEvent event) -> {
	    this.exportBackup(stage, "arkivarium.no", "9082", "admin", "password");
	    Platform.exit();
        });
        HBox root = new HBox();
        root.setPadding(new Insets(25));
        root.getChildren().add(importbtn);
        root.getChildren().add(exportbtn);	
        Scene scene = new Scene(root, 480, 320);
        stage.setTitle("Arkivarium Backup Tool");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
