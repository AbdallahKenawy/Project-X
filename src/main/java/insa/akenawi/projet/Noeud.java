package insa.akenawi.projet;
import insa.akenawi.Lire;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import static insa.akenawi.projet.Treillis.Diam;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abdallahkenawy
 */
public abstract class Noeud   //On definit la classe Noeud propose par l'enonce de TD
{   
    private int id;
    private double px;
    private double py;
    private Vecteur2D force;
    private ArrayList<Barre> barresArrive;
    private ArrayList<Barre> barresDepart;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the px
     */
    public double getPx() {
        return px;
    }

    /**
     * @param px the px to set
     */
    public void setPx(double px) {
        this.px = px;
    }

    /**
     * @return the py
     */
    public double getPy() {
        return py;
    }

    /**
     * @param py the py to set
     */
    public void setPy(double py) {
        this.py = py;
    }

    /**
     * @return the force
     */
    public Vecteur2D getForce() {
        return force;
    }

    /**
     * @param force the force to set
     */
    public void setForce(Vecteur2D force) {
        this.force = force;
    }
    
        /**
     * @return the barresArrive
     */
    public ArrayList<Barre> getBarresArrive() {
        return barresArrive;
    }

    /**
     * @return the barresDepart
     */
    public ArrayList<Barre> getBarresDepart() {
        return barresDepart;
    }

    
    
    public Noeud(int id,double px,double py,Vecteur2D force)
    {
        this.id=id;
        this.px=px;
        this.py=py;
        this.force=force;
        this.barresArrive=new ArrayList<Barre> ();
        this.barresDepart= new ArrayList<Barre> ();
    }
    
    public Noeud(double px,double py,Vecteur2D force)
    {
        this(-1,px,py,force);
    }
    
    public Noeud(double px,double py)
    {
        this(-1,px,py,new Vecteur2D(0,0));
    }
    
    public Noeud()
    {
        this(0,0,0,new Vecteur2D(0,0));
    }
    
    public String toString()
    {
        return "id: "+this.id+", coordonnees: ("+this.px+","+this.py+"), Force agissante:"+this.force.toString();
    }
    
    public abstract int nbrInconnues();  //Mehode employee lors de la calcul matriciel
    
    public ArrayList<Barre> Barresincidentes()  //Methode qui renvoie l'ensemble des barres associees a un point;
    {
        int i;
        ArrayList<Barre> res=new ArrayList();
        for(i=0;i<this.getBarresDepart().size();i++)
         {
             res.add(this.getBarresDepart().get(i));
         }
        for(i=0;i<this.getBarresArrive().size();i++)
         {
             res.add(this.getBarresArrive().get(i));
         }
        return res;
    }
        
    public abstract void dessine(GraphicsContext outil);  //Methode pour dessiner les differents type des noeuds et ecrire leur Id dans le canvas
    
    public abstract void sauvegarde(Writer out)throws IOException;   //Methode qui determine le format utilisee lors du sauvegard des attributs du noeud souf rome:Type Noeud;Px;Py;Fx;Fy
}
