package com.arkivarium;

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

    public void importBackup(Stage stage, String hostname, String portname, String username, String password) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open Noark5 Import File...");
	    fileChooser.showOpenDialog(stage);
	    System.out.println("Importing backup...\n" + hostname + ":" + portname + "/" + username + "/" + password);
    }

    public void exportBackup(Stage stage, String hostname, String portname, String username, String password) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Save Noark5 Export File As...");
	    fileChooser.showOpenDialog(stage);
	    System.out.println("Exporting backup...\n" + hostname + ":" + portname + "/" + username + "/" + password);
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
	    this.exportBackup(stage, "nikita.hioa.no", "9082", "admin", "password");
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
