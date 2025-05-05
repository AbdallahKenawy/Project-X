/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet;

import insa.akenawi.Lire;
import static insa.akenawi.projet.Treillis.Epaiss;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author abdallahkenawy
 */
public class Barre //On definit la classe et les methodes presentees en TP
{
    private int id;
    private Noeud dep;
    private Noeud fin;
    private double compMax;
    private double tracMax;
    private double prix;

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
     * @return the dep
     */
    public Noeud getDep() {
        return dep;
    }

    /**
     * @param dep the dep to set
     */
    public void setDep(Noeud dep) {
        this.dep = dep;
    }

    /**
     * @return the fin
     */
    public Noeud getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(Noeud fin) {
        this.fin = fin;
    }

    /**
     * @return the compMax
     */
    public double getCompMax() {
        return compMax;
    }

    /**
     * @param compMax the compMax to set
     */
    public void setCompMax(double compMax) {
        this.compMax = compMax;
    }

    /**
     * @return the tracMax
     */
    public double getTracMax() {
        return tracMax;
    }

    /**
     * @param tracMax the tracMax to set
     */
    public void setTracMax(double tracMax) {
        this.tracMax = tracMax;
    }

    /**
     * @return the prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public Barre (int id, Noeud Dep, Noeud Fin, double tracMax, double compMax, double prix)
    {
        this.id=id;
        this.dep=Dep;
        Dep.getBarresDepart().add(this);
        this.fin=Fin;
        Fin.getBarresArrive().add(this);
        this.tracMax=tracMax;
        this.compMax=compMax;
        this.prix=prix;
    }
    
    public String toString()
      {
        return "Barre: id: "+this.id+", Depart:"+this.dep.toString()+"), Fin:"+this.fin.toString()+", Traction maximale: "+this.tracMax+", Compression maximale:"+this.compMax+", cout au metre:"+this.prix;     
      }
    
    public void setNoeudDepart(Noeud n)
    {
        this.dep.getBarresDepart().remove(this);
        this.setDep(n);
        n.getBarresDepart().add(this);
        
    }
    
    public void setNoeudArrivee(Noeud n)
    {
        this.fin.getBarresDepart().remove(this);
        this.setFin(n);
        n.getBarresArrive().add(this);
    }
    
    public Noeud noeudOppose(Noeud n)
    {
        if (n==this.dep)
        {
            return this.fin;
        }
        else
        {
            return this.dep;
        }
    }
    
    public double angle(Noeud n)
    {
        Noeud oppose=this.noeudOppose(n);
        double vert=n.getPy()-oppose.getPy();
        double abs=n.getPx()-oppose.getPx();
        double res=Math.atan2(vert, abs);
        return res;
    }
    
    public void sauvegarde (Writer out) throws IOException  //Sauvegarde sous forme: Barre;Id;IdNoeudDep;IdNoeudFin;CompMax;TracMax;Prix
    {
        out.append("Barre;"+this.id+";"+this.dep.getId()+";"+this.fin.getId()+";"+this.compMax+";"+this.tracMax+";"+this.prix+"\n");
    }
    
    public void dessine(GraphicsContext outil)  //On dessine la barre et son id
    {
     outil.setLineWidth(Epaiss);
     outil.setStroke(Color.MEDIUMBLUE);
     outil.strokeLine(this.dep.getPx(),this.dep.getPy(),this.fin.getPx(), this.fin.getPy());
     double xmoy=Math.abs((this.fin.getPx()+this.dep.getPx())/2);
     double ymoy=Math.abs((this.fin.getPy()+this.dep.getPy())/2);
     outil.setLineWidth(1);
     outil.setStroke(Color.BLACK);
     outil.strokeText(String.valueOf(this.getId()),xmoy, ymoy);
    }

}
