/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet.Graphe;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 *
 * @author abdallahkenawy
 */
public class ButtonsLeft extends VBox
{
    protected MainPane Main;
    
    protected RadioButton NoeudSimple;
    protected RadioButton NoeudAppSimple;
    protected RadioButton NoeudAppDouble;
    protected ComboBox Ntype;
    protected TextField idN;
    protected TextField px;
    protected TextField py;
    protected TextField fx;
    protected TextField fy;
    
    protected RadioButton Barre;
    protected TextField idB;
    protected TextField Noeuddep;
    protected TextField Noeudfin;
    protected TextField Tracmax;
    protected TextField Compmax;
    protected TextField Prix;
    
    public ButtonsLeft(MainPane main)
    {
        this.Main=main;
        
        this.NoeudSimple= new RadioButton("Noeud Simple");
        this.NoeudSimple.setOnAction((ActionEvent t)->
        {
             this.Main.getZonedessin().DessineNoeud();
        });
        this.NoeudAppSimple= new RadioButton("Noeud Appui Simple");
        this.NoeudAppSimple.setOnAction((ActionEvent t) -> 
        {
            this.Main.getZonedessin().DessineNoeud();
        });
        this.NoeudAppDouble= new RadioButton("Noeud Appui Double");
        this.NoeudAppDouble.setOnAction((ActionEvent t) -> 
        {
            this.Main.getZonedessin().DessineNoeud();
        });
        
        this.Barre= new RadioButton("Barre");
        this.Barre.setOnAction((ActionEvent t) -> 
        {
            this.Main.getZonedessin().DessineBarre();
        });
        
        ToggleGroup Elements= new ToggleGroup();
        this.NoeudSimple.setToggleGroup(Elements);
        this.NoeudAppSimple.setToggleGroup(Elements);
        this.NoeudAppDouble.setToggleGroup(Elements);
        this.Barre.setToggleGroup(Elements);
        
        this.Ntype=new ComboBox();
        this.Ntype.getItems().addAll("Noeud Simple","Noeud Appui Simple","Noeud Appui Double");
        Label IdN=new Label("Id");
        this.idN=new TextField();
        this.idN.setEditable(false);
        this.idN.setText(String.valueOf(this.Main.getTreillis().maxIdBarre()+1));
        Label Px=new Label("Px");
        this.px= new TextField();
        Label Py=new Label("Py");
        this.py= new TextField();
        Label Fx=new Label("Fx");
        this.fx= new TextField();
        Label Fy=new Label("Fy");
        this.fy= new TextField();
        Button Creer=new Button ("Creer Noeud");
        Creer.setOnAction((ActionEvent t) -> 
        {
            this.Main.getControleur().NoeudManuel();
        });
        
        Label IdB=new Label("Id Barre");
        this.idB=new TextField();
        this.idB.setEditable(false);
        this.idB.setText(String.valueOf(this.Main.getTreillis().maxIdNoeud()+1));
        Label Noeuddep=new Label("Id Noeud de depart");
        this.Noeuddep= new TextField();
        Label Noeudfin=new Label("Id Noeud d'arrivee");
        this.Noeudfin=new TextField();
        Label Tracmax=new Label("Trac Max");
        this.Tracmax=new TextField();
        Label Compmax=new Label("Comp Max");
        this.Compmax=new TextField();
        Label Prix=new Label("Prix");
        this.Prix=new TextField();
        Button CreerBarre=new Button("Creer Barre");
        CreerBarre.setOnAction((ActionEvent t) -> 
        {
            this.Main.getControleur().BarreManuel();
        });
        
        this.setSpacing(3);
        this.getChildren().addAll(this.NoeudSimple,this.NoeudAppSimple,this.NoeudAppDouble,this.Ntype,IdN,this.idN,Px,this.px,Py,this.py,Fx,this.fx,Fy,this.fy,Creer,this.Barre,IdB,this.idB,Noeuddep,this.Noeuddep,Noeudfin,this.Noeudfin,Tracmax,this.Tracmax,Compmax,this.Compmax,Prix,this.Prix,CreerBarre);
    }
    
}
