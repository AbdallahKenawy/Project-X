/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet;

import Jama.Matrix;
import insa.akenawi.Lire;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author abdallahkenawy
 */
public class Treillis 
{
    public static final double Diam=15; //Parametre utilise pour indiquer le diametre des Noeuds
    public static final double Epaiss=3; //Parametre utilise pour indiquer l'epaisseur des barres
    
    private ArrayList<Noeud> NoeudList;  //L'ensemble des Noeuds
    private ArrayList<Barre> BarreList;  //L'ensemble des Barres
    private ArrayList<Terrain> TerrainList;  //L'ensemble des Terrains
    private Matrix ForcesAgissantes;  //Matrice utilisee pour determiner si le treillis modelise est stable

    /**
     * @return the NoeudList
     */
    public ArrayList<Noeud> getNoeudList() {
        return NoeudList;
    }

    /**
     * @return the BarreList
     */
    public ArrayList<Barre> getBarreList() {
        return BarreList;
    }
    
    /**
     * @return the TerrainList
     */
    public ArrayList<Terrain> getTerrainList() {
        return TerrainList;
    }
    
    /**
     * @return the ForcesAgissantes
     */
    public Matrix getForcesAgissantes() {
        return ForcesAgissantes;
    }
    
    /**
     * @param ForcesAgissantes the ForcesAgissantes to set
     */
    public void setForcesAgissantes(Matrix ForcesAgissantes) {
        this.ForcesAgissantes = ForcesAgissantes;
    }
    
    public Treillis()
    {
        this.NoeudList=new ArrayList<Noeud>();
        this.BarreList=new ArrayList<Barre>();
        this.TerrainList=new ArrayList<Terrain>();
        this.ForcesAgissantes=new Matrix(0,0);
    }
    
    public String toString()
    {
        return "Ce treillis contient "+this.NoeudList.size()+" Noeuds et "+this.BarreList.size()+" Barres";
    }
    
    public int maxIdNoeud() //Fonction qui renvoie l'id du dernier Noeud ajoute
    {
        if(this.NoeudList.isEmpty())
        {
            return 0;
        }else{
        int max=this.NoeudList.get(0).getId();
        for (int i=0;i<this.NoeudList.size();i++)
        {
            if(this.NoeudList.get(i).getId()>max)
            {
              max=this.NoeudList.get(i).getId();
            } 
        }
        return max;
        }
    }
    
    public int maxIdBarre()  //Fonction qui renvoie l'id de la derniere Barre ajoutee
    {
        if(this.BarreList.isEmpty())
        {
            return 0;
        }else{
        int max=this.BarreList.get(0).getId();
        for (int i=0;i<this.BarreList.size();i++)
        {
            if(this.BarreList.get(i).getId()>max)
            {
              max=this.BarreList.get(i).getId();
            } 
        }
        return max;
       }
    }
    
    public void ajouteNoeud(Noeud n)  //Ajoute un Noeud au Treillis
    {
        if (this.NoeudList.contains(n))
        {
            System.out.println("Error, t'es con");
        }
        else
        {
            this.NoeudList.add(n);
        }
    }
    
    public void ajouteBarre(Barre b) //Ajoute une Barre au Treillis
    {
        if(this.BarreList.contains(b))
        {
            throw new Error("Barre Deja Existante");
        }
        else
        { 
            this.BarreList.add(b);
            if (!(this.NoeudList.contains(b.getDep())))
            {
                ajouteNoeud(b.getDep());
            }
            if(!(this.NoeudList.contains(b.getFin())))
            {
                ajouteNoeud(b.getFin());
            }
        }
    }
    
    public Noeud choisiNoeud(int id)  //Permet de retrouver un Noeud en connaisant son Id
    {
        Noeud n=null;
        for(int i=0;i<this.NoeudList.size();i++)
        {
            if(this.NoeudList.get(i).getId()==id)
            {
                n=NoeudList.get(i);
            }
        }
        return n;
    }

    public void clear()  //Supprimer Treillis
    {
        this.BarreList.clear();
        this.NoeudList.clear();
        this.TerrainList.clear();
    }

    public void redessine(GraphicsContext outil)  //Redessine l'entierte du Treillis
    {
        for(int i=0;i<this.getBarreList().size();i++)
        {
            this.BarreList.get(i).dessine(outil);
        }
        
        for(int i=0;i<this.getNoeudList().size();i++)
        {
              this.NoeudList.get(i).dessine(outil);
        }
        for(int i=0;i<this.getTerrainList().size();i++)
        {
            this.getTerrainList().get(i).dessine(outil);
        }
    }
    
    public void Save(File file) throws IOException  //Methode qui permet de sauvegarder les composants du Treillis souns un format compatible afin que l'utilisateur puisse le charger apres
    {
        Writer out=new BufferedWriter(new FileWriter(file));
        out.append("Noeuds sous forme: typeNoeud, Id, Px,Py,Fx,Fy"+"\n");
        for(int i=0;i<this.getNoeudList().size();i++)  //On definie les types du Noeud
        {
            if(this.getNoeudList().get(i) instanceof NoeudSimple)
            {
                this.NoeudList.get(i).sauvegarde(out);
            }
            if(this.getNoeudList().get(i) instanceof NoeudAppuiSimple)
            {
                this.NoeudList.get(i).sauvegarde(out);
            }
            if(this.getNoeudList().get(i) instanceof NoeudAppuiDouble)
            {
                this.NoeudList.get(i).sauvegarde(out);
            }
        }
        out.append("Barres sous forme: Id, Noeud Dep Id,Noeud Fin Fd, CompMax,TracMax, Prix"+"\n");
        for(int i=0;i<this.getBarreList().size();i++)
        {
                this.BarreList.get(i).sauvegarde(out);
        }
        out.append("Terrains reduites sous forme de 3 points avec px et py pour chaque point"+"\n");
        for(int i=0;i<this.getTerrainList().size();i++)
        {
         this.TerrainList.get(i).sauvegarde(out,i);
        }
        out.close();
    }
     
    public void Load(File filein) throws IOException  //Methode permettant de reconstruire un treillis a parti d'un fichier qui possed un format compatible
    { 
       BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filein));
        } catch (FileNotFoundException ex) {
            Alert a= new Alert(AlertType.ERROR);
            a.setTitle("Warning");
            a.setContentText("Fichier non trouve");
            a.show();
        }
       String line;
       int id,iddep,idfin;
       double px,py,fx,fy,Tracmax,Compmax,Prix;
       while((line=in.readLine())!= null && line.length()!=0)
           {
               String[] data=line.split(";");
               if(data.length>1)
               {
                if(data[0].equals("NoeudS"))
                {
                  id=Integer.parseInt(data[1]);
                  px=Double.parseDouble(data[2]);
                  py=Double.parseDouble(data[3]);
                  fx=Double.parseDouble(data[4]);
                  fy=Double.parseDouble(data[5]);
                  Noeud n=new NoeudSimple(id,px,py,new Vecteur2D(fx,fy));
                  this.ajouteNoeud(n);
                }
                if(data[0].equals("NoeudAS"))
                {
                  id=Integer.parseInt(data[1]);
                  px=Double.parseDouble(data[2]);
                  py=Double.parseDouble(data[3]);
                  fx=Double.parseDouble(data[4]);
                  fy=Double.parseDouble(data[5]);
                  Noeud n=new NoeudAppuiSimple(id,px,py,new Vecteur2D(fx,fy));
                  this.ajouteNoeud(n);
                }
                if(data[0].equals("NoeudAD"))
                {
                  id=Integer.parseInt(data[1]);
                  px=Double.parseDouble(data[2]);
                  py=Double.parseDouble(data[3]);
                  fx=Double.parseDouble(data[4]);
                  fy=Double.parseDouble(data[5]);
                  Noeud n=new NoeudAppuiDouble(id,px,py,new Vecteur2D(fx,fy));
                  this.ajouteNoeud(n);
                }
                if(data[0].equals("Barre"))
                {
                 id=Integer.parseInt(data[1]);
                 Noeud dep=this.NoeudList.get(Integer.parseInt(data[2])-1);
                 Noeud fin=this.NoeudList.get(Integer.parseInt(data[3])-1);
                 Tracmax=Double.parseDouble(data[4]);
                 Compmax=Double.parseDouble(data[5]);
                 Prix=Double.parseDouble(data[6]);
                 Barre b=new Barre(id,dep,fin,Tracmax,Compmax,Prix);
                 this.ajouteBarre(b); 
                }
                if(data[1].equals("Terrain"))
                {
                 Terrain Ter=new Terrain();
                 ArrayList TPx=new ArrayList();
                 TPx.add(Double.parseDouble(data[2]));
                 TPx.add(Double.parseDouble(data[4]));
                 TPx.add(Double.parseDouble(data[6]));
                 Ter.setTPx(TPx);
                 ArrayList TPy=new ArrayList();
                 TPy.add(Double.parseDouble(data[3]));
                 TPy.add(Double.parseDouble(data[5]));
                 TPy.add(Double.parseDouble(data[7]));
                 Ter.setTPy(TPy);
                 this.TerrainList.add(Integer.parseInt(data[0]), Ter);
                }
             }
           }
    }
    
    public int TerrainDuNoeud(Noeud n)  //S'il existe plusieurs Terrains; Permet de retrouver le noeud appui se trouve surquel Terrain, afin de calculer son angle apres et de pouvoir correctement parametrer sa position par rapport a ce terrain
    {
        int id=0;
        for(int i=0;i<this.TerrainList.size();i++)
        {
            if(this.TerrainList.get(i).minX()<n.getPx() && n.getPx()<this.TerrainList.get(i).maxX())
            {
                id=i;
            }
        }
        return id;
    }
    
    public void MatriceTreillis()  //Calcul Matriciel pour determiner les inconnus du treillis
    {
        int nbrI,nbrB,nbrN,nbrNAS = 0,nbrNAD = 0;
        int ColBarre,dec=0;
        double ang1,ang2;
        ArrayList<Barre> BInc;
        Matrix MatriceTreillis, SecMembre;
        nbrN=this.NoeudList.size();
        nbrB=this.BarreList.size();
        for(int i=0;i<this.NoeudList.size();i++)  //On definit le nombre de Noeud Appui Simple et Noeud Appui Double afin de determiner le nombre d'inconnus;
        {
            if(this.NoeudList.get(i) instanceof NoeudAppuiSimple)
            {
                nbrNAS++;
            }else if(this.NoeudList.get(i) instanceof NoeudAppuiDouble){
                nbrNAD++;
            }
        }
        nbrI=nbrB+nbrNAS+2*nbrNAD;  
        if(2*nbrN==nbrI)  //Si le treilllis n'est pas hyperstatique;
        {
         Matrix SecMem=new Matrix(2*nbrN,1);  //Matrice Colonne contenant les forces sur les Noeud
         for(int i=0;i<nbrN;i++)
        {
            SecMem.set(2*i, 0, -this.NoeudList.get(i).getForce().getVx());
            SecMem.set(2*i+1, 0, -this.NoeudList.get(i).getForce().getVy());
        }
        
        MatriceTreillis= new Matrix(2*nbrN,nbrI); //Matrice carree dont l'inverse nours permettra des calaculer les inconnus du treillis
         for(int i=0;i<this.NoeudList.size();i++)
        {
            Noeud n=this.NoeudList.get(i);
            BInc=n.Barresincidentes();
            for(int s=0;s<BInc.size();s++)  //Pour chaque Barre incidente, on en retrouve ;l'angle avec ce neoud et on l'ajoute a la mtrice
            {
              ColBarre=this.BarreList.indexOf(BInc.get(s)); //Permet de preciser la colonne ou se trouve la barre puisque une barre peut avoir deux noeuds
              ang1=BInc.get(s).angle(n);
              
              MatriceTreillis.set(2*i, ColBarre, Math.cos(ang1));
              MatriceTreillis.set(2*i+1, ColBarre, Math.sin(ang1));
            }      //On ajoute les forces des Reactions dans les colonnes suivants les tractions associees aux barres     
            if(n instanceof NoeudAppuiSimple)  //S'il s'agit d'un Noeud Appui Simple, il faut tenir compte de la force du reaction du terrain colineaire a la normale du terrain 
            {
             ang2= this.TerrainList.get(this.TerrainDuNoeud(n)).Ang();
             MatriceTreillis.set(2*i, this.BarreList.size()+dec, Math.cos(ang2));
             MatriceTreillis.set(2*i+1,this.BarreList.size()+dec, Math.sin(ang2));   
             dec++;   
            }else if (n instanceof NoeudAppuiDouble){  //S'il s'agit d'un Noeud Appui Double, la reaction du terrain se projete une fois sur et une fois sut Y, d'ou le decalage
             MatriceTreillis.set(2*i, this.BarreList.size()+dec,1);
             dec++;
             MatriceTreillis.set(2*i+1,this.BarreList.size()+dec,1);
             dec++;
            }      
        }
         Matrix inv=MatriceTreillis.inverse();
         this.setForcesAgissantes(inv.times(SecMem)); //AX=Y -> X=A^-1*Y
        }else{ //Si le treillis est hyperstatique, renvoie une erreur
            Alert a= new Alert(AlertType.ERROR);
            a.setTitle("Warning");
            a.setContentText("Treillis Hyperstatique");
            a.show();
        }
    }
}
