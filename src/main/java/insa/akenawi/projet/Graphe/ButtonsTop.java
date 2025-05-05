/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet.Graphe;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author abdallahkenawy
 */
public class ButtonsTop extends HBox
{
    private MainPane Main;
    private ToggleButton Select;
    private ComboBox BoxN;
    private ComboBox BoxB;
    private ToggleButton Terrain;
    
    /**
     * @return the Main
     */
    public MainPane getMain() {
        return Main;
    }
    
    /**
     * @return the Select
     */
    public ToggleButton getSelect() {
        return Select;
    }

    /**
     * @return the BoxN
     */
    public ComboBox getBoxN() {
        return BoxN;
    }

    /**
     * @return the BoxB
     */
    public ComboBox getBoxB() {
        return BoxB;
    }

    /**
     * @return the Terrain
     */
    public ToggleButton getTerrain() {
        return Terrain;
    }
    
    public ButtonsTop(MainPane main)
    {
        this.Main=main;
        
        Button AfficheElements=new Button("Elements Treillis");
        AfficheElements.setOnAction((ActionEvent t)->
        {
            this.Main.getControleur().AfficheElements();
        });
        
        this.Select=new ToggleButton("Select");
        Button ModifierN=new Button("Modifier Noeud");
        Button ModifierB=new Button("Modifier Barre");
        ModifierN.setVisible(false);
        ModifierB.setVisible(false);
        
        Button SupprimerN= new Button("Supprime Noeud");
        SupprimerN.setStyle("-fx-text-fill: #ff0000");
        Button SupprimerB=new Button("Supprime Barre");
        SupprimerB.setStyle("-fx-text-fill: #ff0000");
        SupprimerN.setVisible(false);
        SupprimerB.setVisible(false);
        
        Label Noeuds= new Label("Noeuds");
        this.BoxN=new ComboBox();
        Label Barres=new Label("Barres");
        this.BoxB=new ComboBox();
        
        this.Select.setOnAction((ActionEvent t)->
        {
            ModifierN.setVisible(!ModifierN.isVisible());
            ModifierB.setVisible(!ModifierB.isVisible());
            SupprimerN.setVisible(!SupprimerN.isVisible());
            SupprimerB.setVisible(!SupprimerB.isVisible());
            this.Main.getControleur().Select();
            ModifierN.setOnAction((ActionEvent s)->
                    {
                        this.Main.getControleur().ModifierNoeud();
                    });
            SupprimerN.setOnAction((ActionEvent s)->
                    {
                        this.Main.getControleur().SupprimerNoeud((Integer)this.BoxN.getValue()-1);
                    });
            ModifierB.setOnAction((ActionEvent s)->
                    {
                        this.Main.getControleur().ModifierBarre();
                    });
            SupprimerB.setOnAction((ActionEvent s)->
                    {
                        this.Main.getControleur().SupprimerBarre((Integer)this.BoxB.getValue()-1);
                    });
        });
        
        this.Terrain= new ToggleButton("Terrain");
        this.Terrain.setStyle("-fx-text-fill: #00ff00");
        this.Terrain.setOnAction((ActionEvent s)->
          {
              this.Main.getConsole().getOutput().appendText("Veuillez cliquer trois fois pour creer un terrain"+"\n");
              this.Main.getControleur().EtatsControlL(false);
              this.Main.getZonedessin().DessinTerrain();
          });
        
        HBox BFichier=new HBox();
        
        Button TestMat=new Button("Tester le modele");
        TestMat.setOnAction((ActionEvent s)->
          {
            this.Main.getControleur().TestTreillis();
          });
       
        Button Sauvegarde=new Button("Sauvegarde");
        Sauvegarde.setOnAction((ActionEvent s)->
          {
            this.Main.getControleur().Sauvegarde();
          });
        
        Button Load=new Button("Load");
        Load.setOnAction((ActionEvent s)->
          {
            this.Main.getControleur().Load();
          });
        
        Button Clear=new Button("Supprime fichier");
        Clear.setStyle("-fx-text-fill: #ff0000");
        Clear.setOnAction((ActionEvent s)->
          {
            this.Main.getControleur().DeleteAll();
          });
        
        BFichier.getChildren().addAll(TestMat,Sauvegarde,Load,Clear);
        BFichier.setAlignment(Pos.CENTER_RIGHT);
        BFichier.setSpacing(3);
        
        HBox.setHgrow(BFichier, Priority.ALWAYS);
        
        this.setSpacing(3);
        this.getChildren().addAll(Terrain,AfficheElements,Select,Noeuds,this.BoxN,ModifierN,SupprimerN,Barres,this.BoxB,ModifierB,SupprimerB,BFichier);
        
    } 
}