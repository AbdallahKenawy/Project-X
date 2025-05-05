/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet.Graphe;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author abdallahkenawy
 */
public class DessinPane extends Pane //Zone de dessin ou on exploite l'objet Canvas
{
   private MainPane main;
   private Canvas canvas;
   private GraphicsContext outil;
   private Color BGColor;

    /**
     * @return the main
     */
    public MainPane getMain() {
        return main;
    }

    /**
     * @return the canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @return the outil
     */
    public GraphicsContext getGc() {
        return outil;
    }
    
    public Color getBGColor() {
        return BGColor;
    }
   
   final int par=20;

   public DessinPane(MainPane main)
   {
       this.main=main;
       this.canvas=new Canvas();
       this.outil=this.canvas.getGraphicsContext2D();
       
       this.canvas.heightProperty().bind(this.heightProperty()); //On associe la taille du Canvas a la taille de la zone de dessin
       this.canvas.widthProperty().bind(this.widthProperty());    
       Rectangle dess= new Rectangle();
       dess.heightProperty().bind(this.heightProperty());
       dess.widthProperty().bind(this.widthProperty());
       this.setClip(dess);
       this.BGColor=Color.LIGHTSLATEGREY;
       
       
       this.setBackground(new Background(new BackgroundFill(this.BGColor,CornerRadii.EMPTY,Insets.EMPTY)));
       this.getChildren().addAll(this.canvas);
       this.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
   }
   
   public void DessineNoeud() //On dessine Noeud en constamment reafficher les coordonnes du souris
   {
       this.canvas.setOnMouseMoved(e->
        {
         this.main.getControleur().TrackMouse(true,e);
        });
       this.getCanvas().setOnMousePressed(e->
        {
          this.main.getControleur().DessineNoeud(e);
        });
   }
   
  public void DessineBarre() //On definit une maniere Drag-Release pour dessiner une barre
   {
       this.getCanvas().setOnMouseMoved(e->
        {
            this.main.getControleur().TrackMouse(true,e);
        });
       this.getCanvas().setOnMousePressed(e->
        {
            this.getCanvas().setOnMouseReleased(r->{
                    this.main.getControleur().DessineBarre(e, r);
            });
        });
   }
   
   public void Paintover()  //On reintialise la zone de dessin comme elle l'etait au debut du lancement du programme
   {
        this.outil.setFill(this.BGColor);
        this.outil.fillRect(0,0,this.getWidth(), this.getHeight());
   }
   
   public void DessinTerrain() //Lors de 3 clicks du Souris, un terrain est cree
   {
         ArrayList ME= new ArrayList();
         this.getCanvas().setOnMouseMoved(e->
        {
            this.main.getControleur().TrackMouse(true,e);
        });
         this.canvas.setOnMouseClicked(e->
        {
            this.main.getControleur().CreerTerrain(e,ME);
        });
   }
}
