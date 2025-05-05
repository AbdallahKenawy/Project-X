/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet.Graphe;

import insa.akenawi.projet.Treillis;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
/**
 *
 * @author abdallahkenawy
 */
public class MainPane extends BorderPane //On definit la fenetre principale et ses composants
{
    private Treillis treillis;
    private DessinPane zonedessin;
    private ButtonsLeft controlL;
    private ButtonsTop controlT;
    private Console console;
    private Controleur Controleur;
    //private enregistrer;

    /**
     * @return the treillis
     */
    public Treillis getTreillis() {
        return treillis;
    }

    /**
     * @param treillis the treillis to set
     */
    public void setTreillis(Treillis treillis) {
        this.treillis = treillis;
    }

    /**
     * @return the zonedessin
     */
    public DessinPane getZonedessin() {
        return zonedessin;
    }

    /**
     * @param zonedessin the zonedessin to set
     */
    public void setZonedessin(DessinPane zonedessin) {
        this.zonedessin = zonedessin;
    }

    /**
     * @return the controlL
     */
    public ButtonsLeft getControlL() {
        return controlL;
    }

    /**
     * @param controlL the controlL to set
     */
    public void setControlL(ButtonsLeft controlL) {
        this.controlL = controlL;
    }

    /**
     * @return the controlT
     */
    public ButtonsTop getControlT() {
        return controlT;
    }

    /**
     * @param controlT the controlT to set
     */
    public void setControlT(ButtonsTop controlT) {
        this.controlT = controlT;
    }

    /**
     * @return the console
     */
    public Console getConsole() {
        return console;
    }

    /**
     * @param console the console to set
     */
    public void setConsole(Console console) {
        this.console = console;
    }
    
    public MainPane(Treillis treillis)
    {
     this.treillis=treillis;
     this.zonedessin=new DessinPane(this);
     this.controlL=new ButtonsLeft(this);
     this.controlT=new ButtonsTop(this);
     this.console=new Console();
     this.Controleur=new Controleur(this);
     this.setCenter(this.zonedessin);
     this.setLeft(this.controlL);
     this.setTop(this.controlT);
     this.setBottom(console);
     this.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE,CornerRadii.EMPTY,Insets.EMPTY)));
    }

    /**
     * @return the Controleur
     */
    public Controleur getControleur() {
        return Controleur;
    }
}
