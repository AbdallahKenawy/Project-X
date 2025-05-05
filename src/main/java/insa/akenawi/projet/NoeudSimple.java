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

/**
 *
 * @author abdallahkenawy
 */
public class NoeudSimple extends Noeud
{

    public NoeudSimple(int id,double px,double py,Vecteur2D force)
    {
        this.setId(id);
        this.setPx(px);
        this.setPy(py);
        this.setForce(force);
    }
    
    public String toString()
    {
        return "Noeud Simple: id: "+this.getId()+", coordonnees: ("+this.getPx()+","+this.getPy()+"), Force agissante:"+this.getForce().toString();
    }

    public int nbrInconnues() 
    {
       return 0;
    }
    
    public void dessine(GraphicsContext outil)  //Les Noeud Simples sont dessines en Noir
    {
      outil.setFill(Color.BLACK);
      outil.fillOval(this.getPx()-Diam/2, this.getPy()-Diam/2, Diam, Diam);
      outil.setLineWidth(1);
      outil.setStroke(Color.WHITESMOKE);
      outil.strokeText(String.valueOf(this.getId()),this.getPx()-Diam/2, this.getPy()-Diam/2);
    }

    @Override
    public void sauvegarde(Writer out) throws IOException {
        out.append("NoeudS;"+this.getId()+";"+this.getPx()+";"+this.getPy()+";"+this.getForce().toString()+"\n");
    }
    
}
