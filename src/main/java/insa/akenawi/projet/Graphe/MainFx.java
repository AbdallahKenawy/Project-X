/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet.Graphe;

import insa.akenawi.projet.Treillis;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author abdallahkenawy
 */
public class MainFx extends Application{
    
public void start(Stage stage) throws Exception 
{
    Treillis test=new Treillis();
    MainPane main=new MainPane(test);
    Scene sc = new Scene(main);
    stage.setScene(sc);
    stage.setTitle("Appli Treillis");
    stage.setFullScreen(true);
    stage.show();
}
    public static void main(String[] args) {
        launch(args);
    }
}