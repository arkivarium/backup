package com.arkivarium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import com.arkivarium.Noark5Parser;

/**
 * Arkivarium Backup
 */

public class ArkivariumBackup extends Application {
    @Override
    public void start(Stage stage) {
        initUI(stage);
    }
    public void importBackup(Stage stage, String hostname, String portname, String username, String password) {
	    FileChooser fileChooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
	    fileChooser.getExtensionFilters().add(extFilter);
	    File selectedImportFile = null;
	    fileChooser.setTitle("Open Noark5 Import File...");
	    fileChooser.setInitialFileName("arkivstruktur.xml");
	    selectedImportFile = fileChooser.showOpenDialog(stage);
	    if (selectedImportFile != null) {
		    System.out.println("Importing backup from " + selectedImportFile.getAbsolutePath() + "...\n" + hostname + ":" + portname + "/" + username + "/" + password);
		    File importFile = new File(selectedImportFile.getAbsolutePath());
		    Noark5Parser parser = new Noark5Parser();
		    parser.parseImport(importFile);
	    } else {
		    System.out.println("Failed to open " + selectedImportFile.getAbsolutePath() + "\n");
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
	    if (selectedExportFile != null) {
		    System.out.println("Exporting backup to " + selectedExportFile.getAbsolutePath() + "...\n" + hostname + ":" + portname + "/" + username + "/" + password);
		    File exportFile = new File(selectedExportFile.getName());
		    Noark5Parser parser = new Noark5Parser();
		    parser.storeExport(exportFile);
	    } else {
		    System.out.println("Failed to save/export backup file " + selectedExportFile.getAbsolutePath());
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
