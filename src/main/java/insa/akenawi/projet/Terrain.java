/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author abdallahkenawy
 */
public class Terrain  //On definit une classe Terrain 
{
    private ArrayList <MouseEvent> MouseClicks;  //ArrayList contenant l'ensemble des clicque de souris
    private List <Double> TPx;  //Contient les coordonnees d'abscisses des clique de souris
    private List <Double> TPy;  //Contient les coordonnees d'ordonnees des clique de souris

    /**
     * @return the MouseClicks
     */
    public ArrayList <MouseEvent> getMouseClicks() {
        return MouseClicks;
    }

    
    /**
     * @return the TPx
     */
    public List <Double> getTPx() {
        return TPx;
    }

    /**
     * @return the TPy
     */
    public List <Double> getTPy() {
        return TPy;
    }
    
    /**
     * @param TPx the TPx to set
     */
    public void setTPx(List <Double> TPx) {
        this.TPx = TPx;
    }

    /**
     * @param TPy the TPy to set
     */
    public void setTPy(List <Double> TPy) {
        this.TPy = TPy;
    }
    
    public Terrain()
    {
        this.MouseClicks=new ArrayList<MouseEvent>();
        this.TPx=new ArrayList<Double>();
        this.TPy=new ArrayList<Double>();
    }
    
    
    public Terrain(ArrayList<MouseEvent> ME,GraphicsContext outil)
    {
        this.MouseClicks=ME;
        this.TPx=new ArrayList<Double>();
        this.TPy=new ArrayList<Double>();
        this.updateTPoints();
        this.dessine(outil);
    }
    
    
    public void clear()  //On definit une methode pour effacer le terrain lorssqu'on efface l'entierte de treillis
    {
       this.MouseClicks.clear();
       this.TPx.clear();
       this.TPy.clear();
    }
    
    public void updateTPoints()  //On met a jour les liste TPx et TPy
    {
        this.getTPx().clear();
        this.getTPy().clear();
         for(int i=0;i<this.MouseClicks.size();i++)
        {
            this.getTPx().add(this.MouseClicks.get(i).getX());
            this.getTPy().add(this.MouseClicks.get(i).getY());
        }
    }
    
    public boolean InteractionNApp(Noeud n)  //Methode qui permet de determiner si un noued appui est suffisament proche du terrain
    {
        double Ymin=10000;
        int max1 = 0,max2 = 0;  //On retrouve les deux points ayant les plus petites ordonnes, ce qui correspond sur l'ecran aux points les plus hautes places dans la zone de dessin
        for(int i=0;i<this.MouseClicks.size();i++)  
        {
            if(this.TPy.get(i)<Ymin)
            {
                Ymin=this.TPy.get(i);
                max1=i;            
            }
        }
        Ymin=10000;
        for(int i=0;i<this.MouseClicks.size();i++)
        {
          if(this.TPy.get(i)<Ymin && i!=max1)
            {
                Ymin=this.TPy.get(i);
                max2=i;  
            }
        }
        double DistTerrain=Dist(this.TPx.get(max1),this.TPx.get(max2),this.TPy.get(max1),this.TPy.get(max2));  // Si A-N+N-B=A-B renvoie true
        double DistANoeud=Dist(n.getPx(),this.TPx.get(max2),n.getPy(),this.TPy.get(max2));  //false sinon
        double DistNoeudA=Dist(this.TPx.get(max1),n.getPx(),this.TPy.get(max1),n.getPy());
        if((int)DistTerrain==(int)(DistANoeud+DistNoeudA))
        {
          return true;
        }else
        {
          return false;
        }  
    }
    
    public double Ang()  //Fonction qui calcule l'angle que fait le terrain avec un Noeud Appui Simple
    {
       double Ymin=10000;
       int max1 = 0,max2 = 0;
       for(int i=0;i<this.MouseClicks.size();i++)
        {
            if(this.TPy.get(i)<Ymin)
            {
                Ymin=this.TPy.get(i);
                max1=i;            
            }
        }
       Ymin=10000;
       for(int i=0;i<this.MouseClicks.size();i++)
        {
          if(this.TPy.get(i)<Ymin && i!=max1)
            {
                Ymin=this.TPy.get(i);
                max2=i;  
            }
        }
       double dx=this.TPx.get(max1)-this.TPx.get(max2);
       double dy=this.TPy.get(max1)-this.TPy.get(max2);
       return Math.atan2(dy, dx)+Math.PI/2;
    }
    
    public static double Dist(double ax,double bx,double ay,double by)   // Pour calculer la distance entre deux points directement au lieu d'employer le syntaxe relativement long a chaque fois qu'on en a besoin
    {
     double Dist=Math.sqrt(Math.pow(ax-bx,2)+Math.pow(ay-by, 2)); 
     return Dist;
    }
    
    public void dessine(GraphicsContext outil)  //On dessine le Terrain definie a l'aide d'un polygon, le terrain est definie par 3 points
    {
      double[] PosX= new double[3];
      double[] PosY= new double[3];
      int s=0;
        for (int i=0;i<this.nbrPoints();i++)
       {
         PosX[s]=this.getTPx().get(i);
         PosY[s]=this.getTPy().get(i);
         s=s+1;
         if(s==3)
         {
          outil.setLineWidth(3);
          outil.setStroke(Color.DARKGREEN);
          outil.setFill(Color.LIMEGREEN);
          outil.fillPolygon(PosX, PosY, 3); //Parametre 3 correspond aux nombres de points dans la lsite
          outil.strokePolygon(PosX, PosY, 3);
          s=0;
         }
       }
    }
    
    public int nbrPoints()  //Fonction qui renvoie quelle liste est de plus grande taille, utilise lorsqu'on charge un fichier deja existant
    {
        int max=this.MouseClicks.size();
        if(this.TPx.size()>max)
        {
            max=this.TPx.size();
        }
        return max;
    }
    
    public double maxX()  //On renvoie l'abscisse max du terrain
    {
      double maxX=this.TPx.get(0);
       for(int i=0;i<this.TPx.size();i++)
       {
         if(this.TPx.get(i)>maxX)
         {
             maxX=this.TPx.get(i);
         }
       }
       return maxX;
    }
    
    public double minX() //On renvoie l'abscisse min du terrain
    {
     double minX=this.TPy.get(0);
       for(int i=0;i<this.TPx.size();i++)
       {
         if(this.TPx.get(i)<minX)
         {
             minX=this.TPx.get(i);
         }
       }
       return minX;  
    }
    
public void sauvegarde(Writer out,int id) throws IOException {  //Sauvegarde sous forme:Terrain;P1X,;P1Y,P2X,P2Y,P3X,P3Y
    out.append(id+";Terrain;");
    for(int i=0;i<this.getMouseClicks().size();i++)
        {
            out.append(String.valueOf(this.getTPx().get(i))+";"+String.valueOf(this.getTPy().get(i))+";");
        }
    out.append("\n");
    }
}
