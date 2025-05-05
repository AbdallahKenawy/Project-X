/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet;

import static insa.akenawi.projet.Treillis.Diam;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author abdallahkenawy
 */
public class NoeudAppuiDouble extends NoeudAppui
{
    public NoeudAppuiDouble(int id, double px,double py,Vecteur2D force)
    {
        this.setId(id);
        this.setPx(px);
        this.setPy(py);
        this.setForce(force);
    }
    
        @Override
    public String toString()
    {
        return "Noeud Appui Double: id: "+this.getId()+", coordonnees: ("+this.getPx()+","+this.getPy()+"), Force agissante:"+this.getForce().toString();
    }

        @Override
    public int nbrInconnues() 
    {
       return 2;
    }

    public void dessine(GraphicsContext outil) //Les Noeud Appui Double sont dessines en Orange
    {
      outil.setFill(Color.ORANGE);
      outil.fillOval(this.getPx()-Diam/2, this.getPy()-Diam/2, Diam, Diam);
      outil.setLineWidth(1);
      outil.setStroke(Color.WHITESMOKE);
      outil.strokeText(String.valueOf(this.getId()),this.getPx()-Diam/2, this.getPy()-Diam/2);
    }

    @Override
    public void sauvegarde(Writer out) throws IOException { 
       out.append("NoeudAD;"+this.getId()+";"+this.getPx()+";"+this.getPy()+";"+this.getForce().toString()+"\n");
    }
    
}
