/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet.Graphe;

import insa.akenawi.projet.Barre;
import insa.akenawi.projet.Noeud;
import insa.akenawi.projet.NoeudAppui;
import insa.akenawi.projet.NoeudSimple;
import insa.akenawi.projet.NoeudAppuiDouble;
import insa.akenawi.projet.NoeudAppuiSimple;
import insa.akenawi.projet.Terrain;
import static insa.akenawi.projet.Treillis.Diam;
import static insa.akenawi.projet.Treillis.Epaiss;
import insa.akenawi.projet.Vecteur2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author abdallahkenawy
 */
public class Controleur 
{
    private MainPane Main;
    private Canvas Canvas;
    private GraphicsContext outil;

    /**
     * @return the Main
     */
    public MainPane getMain() {
        return Main;
    }

    /**
     * @return the Canvas
     */
    public Canvas getCanvas() {
        return Canvas;
    }

    /**
     * @return the outil
     */
    public GraphicsContext getOutil() {
        return outil;
    }
    
    public Controleur(MainPane main)
    {
        this.Main=main;
        this.Canvas=this.Main.getZonedessin().getCanvas();
        this.outil=this.Canvas.getGraphicsContext2D();
    }
    
    public void TrackMouse(Boolean state,MouseEvent t)
    {
        if(state==true)
        {
         this.getMain().getZonedessin().setCursor(Cursor.CROSSHAIR);
         this.getMain().getControlL().px.setText(String.valueOf(t.getX()));
         this.getMain().getControlL().py.setText(String.valueOf(t.getY()));
        }
    }
    
    public void DessineNoeud(MouseEvent t)
    {
        Noeud n = null;
              if(this.getMain().getControlL().NoeudSimple.isSelected())
               {
                n=new NoeudSimple(this.getMain().getTreillis().maxIdNoeud()+1,t.getX(),t.getY(),new Vecteur2D(0,0));
                this.getMain().getControlL().NoeudSimple.setSelected(false);
                this.Main.getTreillis().ajouteNoeud(n);
                this.Main.getControlL().idN.setText(String.valueOf(this.Main.getTreillis().maxIdNoeud()+1));
               }
              if(this.getMain().getControlL().NoeudAppSimple.isSelected())
               {
                n=new NoeudAppuiSimple(this.getMain().getTreillis().maxIdNoeud()+1,t.getX(),t.getY(),new Vecteur2D(0,0));
                if(this.Main.getTreillis().getTerrainList().get(this.Main.getTreillis().TerrainDuNoeud(n)).InteractionNApp(n))
                {
                 this.getMain().getControlL().NoeudAppSimple.setSelected(false);
                 this.Main.getTreillis().ajouteNoeud(n);
                 this.Main.getControlL().idN.setText(String.valueOf(this.Main.getTreillis().maxIdNoeud()+1));
                }else
                {
                    this.Main.getConsole().getOutput().appendText("Veuillez Mettre Votre Noeud Appui Simple sur le terrain"+"\n");
                }
               }
               if(this.getMain().getControlL().NoeudAppDouble.isSelected())
               {
                n=new NoeudAppuiDouble(this.getMain().getTreillis().maxIdNoeud()+1,t.getX(),t.getY(),new Vecteur2D(0,0));   
                 if(this.Main.getTreillis().getTerrainList().get(this.Main.getTreillis().TerrainDuNoeud(n)).InteractionNApp(n))
                {
                this.getMain().getControlL().NoeudAppDouble.setSelected(false);
                this.Main.getTreillis().ajouteNoeud(n);
                this.Main.getControlL().idN.setText(String.valueOf(this.Main.getTreillis().maxIdNoeud()+1));
                }else{
                   this.Main.getConsole().getOutput().appendText("Veuillez Mettre Votre Noeud Appui Double sur le terrain"+"\n"); 
                }
               }
      this.Main.getTreillis().redessine(outil);
      this.updateComboBox();
    }
    
    public void NoeudManuel()
    {
        Noeud n;
        if(this.Main.getControlL().Ntype.getValue().equals("Noeud Simple"))
         {
          n=new NoeudSimple(this.Main.getTreillis().maxIdNoeud()+1, Double.parseDouble(this.Main.getControlL().px.getText()), Double.parseDouble(this.Main.getControlL().py.getText()), new Vecteur2D(Double.parseDouble(this.Main.getControlL().fx.getText()), Double.parseDouble(this.Main.getControlL().fy.getText())));
          this.Main.getControlL().idN.setText(String.valueOf(this.Main.getTreillis().maxIdNoeud()+1));
          this.Main.getTreillis().ajouteNoeud(n);
         }else if(this.Main.getControlL().Ntype.getValue().equals("Noeud Appui Simple"))
         {
          n=new NoeudAppuiSimple(this.Main.getTreillis().maxIdNoeud()+1, Double.parseDouble(this.Main.getControlL().px.getText()), Double.parseDouble(this.Main.getControlL().py.getText()), new Vecteur2D(Double.parseDouble(this.Main.getControlL().fx.getText()), Double.parseDouble(this.Main.getControlL().fy.getText())));
          if(this.Main.getTreillis().getTerrainList().get(this.Main.getTreillis().TerrainDuNoeud(n)).InteractionNApp(n))
          {
           this.Main.getControlL().idN.setText(String.valueOf(this.Main.getTreillis().maxIdNoeud()+1));
           this.Main.getTreillis().ajouteNoeud(n);   
          }else{
             this.Main.getConsole().getOutput().appendText("Veuillez Mettre Votre Noeud Appui Simple sur le terrain"+"\n"); 
          }
         }
         else{
          n=new NoeudAppuiDouble(this.Main.getTreillis().maxIdNoeud()+1, Double.parseDouble(this.Main.getControlL().px.getText()), Double.parseDouble(this.Main.getControlL().py.getText()), new Vecteur2D(Double.parseDouble(this.Main.getControlL().fx.getText()), Double.parseDouble(this.Main.getControlL().fy.getText())));
          if(this.Main.getTreillis().getTerrainList().get(this.Main.getTreillis().TerrainDuNoeud(n)).InteractionNApp(n))
          {
           this.Main.getControlL().idN.setText(String.valueOf(this.Main.getTreillis().maxIdNoeud()+1));
           this.Main.getTreillis().ajouteNoeud(n);   
          }else{
            this.Main.getConsole().getOutput().appendText("Veuillez Mettre Votre Noeud Appui Double sur le terrain"+"\n"); 
          }
         }
        this.Main.getTreillis().redessine(outil);
        this.updateComboBox();
    }
    
    public void DessineBarre(MouseEvent t,MouseEvent r)
    {
        if(this.Main.getControlL().Barre.isSelected())
        {
        Noeud dep;
        Noeud fin;
      if(this.Main.getControlL().Ntype.getValue().equals("Noeud Simple"))
         {
            dep=new NoeudSimple(this.getMain().getTreillis().maxIdNoeud()+1,t.getX(),t.getY(),new Vecteur2D(0,0));
            fin=new NoeudSimple(this.getMain().getTreillis().maxIdNoeud()+2,r.getX(),r.getY(),new Vecteur2D(0,0));
         }
      else if(this.Main.getControlL().Ntype.getValue().equals("Noeud Appui Simple"))
         {
           dep=new NoeudAppuiSimple(this.getMain().getTreillis().maxIdNoeud()+1,t.getX(),t.getY(),new Vecteur2D(0,0));
           fin=new NoeudAppuiSimple(this.getMain().getTreillis().maxIdNoeud()+2,r.getX(),r.getY(),new Vecteur2D(0,0));
             
         }else{
            dep=new NoeudAppuiDouble(this.getMain().getTreillis().maxIdNoeud()+1,t.getX(),t.getY(),new Vecteur2D(0,0));
            fin=new NoeudAppuiDouble(this.getMain().getTreillis().maxIdNoeud()+2,r.getX(),r.getY(),new Vecteur2D(0,0));
      }
       Barre b=new Barre(this.getMain().getTreillis().maxIdBarre()+1,dep,fin,0,0,0);
       this.Main.getTreillis().ajouteNoeud(dep);
       this.Main.getTreillis().ajouteNoeud(fin);
       this.Main.getTreillis().ajouteBarre(b);
       this.Main.getZonedessin().Paintover();
       this.Main.getTreillis().redessine(outil);
       this.Main.getControlL().Barre.setSelected(false);
        }
     this.Main.getControlL().idB.setText(String.valueOf(this.Main.getTreillis().maxIdBarre()+1));
     this.updateComboBox();
    }
    
    public void BarreManuel()
    {
       Noeud dep=this.Main.getTreillis().getNoeudList().get((int) Double.parseDouble(this.Main.getControlL().Noeuddep.getText())-1);
       Noeud fin=this.Main.getTreillis().getNoeudList().get((int) Double.parseDouble(this.Main.getControlL().Noeudfin.getText())-1);
       Barre b=new Barre(this.getMain().getTreillis().maxIdBarre()+1,dep,fin,Double.parseDouble(this.Main.getControlL().Tracmax.getText()), Double.parseDouble(this.Main.getControlL().Compmax.getText()), Double.parseDouble(this.Main.getControlL().Prix.getText()));
       this.Main.getControlL().idB.setText(String.valueOf(this.Main.getTreillis().maxIdBarre()+1));
       this.Main.getTreillis().ajouteBarre(b);
       this.Main.getZonedessin().Paintover();
       this.Main.getTreillis().redessine(outil); 
       this.updateComboBox();
    }
    
    public void updateComboBox()
    {
      this.Main.getControlT().getBoxN().getItems().clear();
      this.Main.getControlT().getBoxB().getItems().clear();
      for(int i=0;i<this.Main.getTreillis().getNoeudList().size();i++)
        {
              this.Main.getControlT().getBoxN().getItems().add(this.Main.getTreillis().getNoeudList().get(i).getId());
        }
      for(int i=0;i<this.Main.getTreillis().getBarreList().size();i++)
        {
              this.Main.getControlT().getBoxB().getItems().add(this.Main.getTreillis().getBarreList().get(i).getId());
        } 
    }
    
    public void updateId()
    {
      for(int i=0;i<this.Main.getTreillis().getNoeudList().size();i++)
        {
           this.Main.getTreillis().getNoeudList().get(i).setId(i+1);
        }
      for(int i=0;i<this.Main.getTreillis().getBarreList().size();i++)
        {
           this.Main.getTreillis().getBarreList().get(i).setId(i+1);
        } 
    }
    
    public void Select()
    {
      this.Main.getControlL().Ntype.setValue(null);
      this.Canvas.setOnMouseMoved(t->
              {
                  if(this.Main.getControlT().getSelect().isSelected())
                  {
                    this.TrackMouse(false, t);
                  }else
                  {
                      this.TrackMouse(true, t);
                  }
              });
      if(this.Main.getControlT().getBoxN().getSelectionModel().isEmpty()!=true)
      {
      Noeud n = this.Main.getTreillis().getNoeudList().get((Integer)this.Main.getControlT().getBoxN().getValue()-1);
      this.Main.getControlL().idN.setText(String.valueOf(n.getId()));
      this.Main.getControlL().px.setText(String.valueOf(n.getPx()));
      this.Main.getControlL().py.setText(String.valueOf(n.getPy()));
      this.Main.getControlL().fx.setText(String.valueOf(n.getForce().getVx()));
      this.Main.getControlL().fy.setText(String.valueOf(n.getForce().getVy()));
      }
      if(this.Main.getControlT().getBoxB().getSelectionModel().isEmpty()!=true)
      {
      Barre b=this.Main.getTreillis().getBarreList().get((Integer)this.Main.getControlT().getBoxB().getValue()-1);
      this.Main.getControlL().idB.setText(String.valueOf(b.getId()));
      this.Main.getControlL().Noeuddep.setText(String.valueOf(b.getDep().getId()));
      this.Main.getControlL().Noeudfin.setText(String.valueOf(b.getFin().getId()));
      this.Main.getControlL().Tracmax.setText(String.valueOf(b.getTracMax()));
      this.Main.getControlL().Compmax.setText(String.valueOf(b.getCompMax()));
      this.Main.getControlL().Prix.setText(String.valueOf(b.getPrix()));
      }
    }
    
    public void ModifierNoeud()
    {
        this.Main.getControlT().getBoxB().setValue(null);
        Noeud n=this.Main.getTreillis().getNoeudList().get((Integer)this.Main.getControlT().getBoxN().getValue()-1);
        n.setPx(Double.parseDouble(this.Main.getControlL().px.getText()));
        n.setPy(Double.parseDouble(this.Main.getControlL().py.getText()));
        n.setForce(new Vecteur2D(Double.parseDouble(this.Main.getControlL().fx.getText()),Double.parseDouble(this.Main.getControlL().fy.getText())));
        this.Main.getZonedessin().Paintover();
        this.Main.getTreillis().redessine(outil);
    }
    
    public void ModifierBarre()
    {
     this.Main.getControlT().getBoxN().setValue(null);
     Barre b=this.Main.getTreillis().getBarreList().get((Integer)this.Main.getControlT().getBoxB().getValue()-1);
     this.Main.getZonedessin().Paintover();
     b.setNoeudDepart(this.Main.getTreillis().getNoeudList().get((int)Double.parseDouble(this.Main.getControlL().Noeuddep.getText())-1));
     b.setNoeudArrivee(this.Main.getTreillis().getNoeudList().get((int)Double.parseDouble(this.Main.getControlL().Noeudfin.getText())-1));
     b.setTracMax(Double.parseDouble(this.Main.getControlL().Tracmax.getText()));
     b.setCompMax(Double.parseDouble(this.Main.getControlL().Compmax.getText()));
     b.setPrix(Double.parseDouble(this.Main.getControlL().Prix.getText()));
     this.Main.getTreillis().redessine(outil);
    }
    
    public void SupprimerNoeud(int idN)
    {
      Noeud n=this.Main.getTreillis().getNoeudList().get(idN);
      for(int i=0;i<n.Barresincidentes().size();i++)
      {
       Barre b=n.Barresincidentes().get(i);
       this.Main.getTreillis().getBarreList().remove(b);
      }
      this.Main.getTreillis().getNoeudList().remove(n);
      this.Main.getZonedessin().Paintover();
      this.updateId();
      this.updateComboBox();
      this.Main.getTreillis().redessine(outil);
    }
    
    public void SupprimerBarre(int idB)
    {
       Barre b=this.Main.getTreillis().getBarreList().get(idB);
       this.Main.getZonedessin().Paintover();
       this.Main.getTreillis().getBarreList().remove(b);
       this.updateId();
       this.updateComboBox();
       this.Main.getTreillis().redessine(outil);
    }
    
     public void DeleteAll()
    {
         this.Main.getTreillis().clear();
         this.Main.getZonedessin().Paintover();
         this.Main.getConsole().getOutput().clear();
         this.Main.getConsole().getOutput().appendText("Treillis supprimee"+"\n");
         this.updateId();
         this.updateComboBox();
    }
    
    public void Sauvegarde()
    {
        GridPane gpane=new GridPane();
        gpane.setAlignment(Pos.CENTER);
        gpane.setHgap(25);
        gpane.setVgap(25);
        gpane.setPadding(new Insets(25));
        gpane.setBackground(new Background(new BackgroundFill(Color.MINTCREAM,CornerRadii.EMPTY,Insets.EMPTY)));
        
        TextArea filename=new TextArea();
        Label indication=new Label("Veuillez preciser le nom du fichier");
        Button valide=new Button("Valider");
        
        gpane.add(indication, 0, 0);
        gpane.add(filename, 0, 1);
        gpane.add(valide, 0, 2);
        
        Scene sc=new Scene(gpane);
        Stage st=new Stage();
        st.setTitle("Sauvegarde");
        st.setScene(sc);
        st.show();
        
        valide.setOnAction(e->{
            File f=new File(String.valueOf(filename.getText()));
            try {
        this.Main.getTreillis().Save(f);
        st.close();
        this.Main.getConsole().getOutput().appendText("Fichier sauvegarde ici: "+f.getCanonicalPath()+"\n");
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void Load()
    {
     GridPane gpane=new GridPane();
     gpane.setAlignment(Pos.CENTER);
     gpane.setHgap(25);
     gpane.setVgap(25);
     gpane.setPadding(new Insets(25));
     gpane.setBackground(new Background(new BackgroundFill(Color.MINTCREAM,CornerRadii.EMPTY,Insets.EMPTY)));
        
     TextArea filename=new TextArea();
     Label indication=new Label("Load File");
     Button load=new Button("Load");
        
     gpane.add(indication, 0, 0);
     gpane.add(filename, 0, 1);
     gpane.add(load, 0, 2);
        
     Scene sc=new Scene(gpane);
     Stage st=new Stage();
     st.setTitle("Load");
     st.setScene(sc);
     st.show();
     
     load.setOnAction(e->{
         this.Main.getTreillis().clear();
         this.Main.getZonedessin().Paintover();
         File f=new File(String.valueOf(filename.getText()));
         try {
             this.Main.getTreillis().Load(f);
         } catch (IOException ex) {
             Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
         }
         st.close();
         this.Main.getConsole().getOutput().clear();
         this.Main.getConsole().getOutput().appendText("Fichier charge correctement"+"\n");
         this.Main.getTreillis().redessine(outil);
         this.updateComboBox();
        });
        
    }
    
    public void CreerTerrain(MouseEvent e,ArrayList <MouseEvent> ME)
    {
     if(this.Main.getControlT().getTerrain().isSelected())
       {
        ME.add(e);
        this.outil.setFill(Color.BLACK);
        this.outil.fillOval(e.getX()-3, e.getY()-3, 6, 6);
         if(ME.size()==3)
        {
         this.Main.getControlT().getTerrain().setSelected(false);
         this.Main.getTreillis().getTerrainList().add(new Terrain(ME,this.outil));
         this.Main.getZonedessin().Paintover();
        }
       }
     this.Main.getTreillis().redessine(outil);
    }
    
    public void EtatsControlL(boolean state)
    {
        this.Main.getControlL().Barre.setSelected(state);
        this.Main.getControlL().NoeudSimple.setSelected(state);
        this.Main.getControlL().NoeudAppSimple.setSelected(state);
        this.Main.getControlL().NoeudAppDouble.setSelected(state);      
    }
    
    public void AfficheElements()
    {
      this.Main.getConsole().getOutput().clear();
      this.Main.getConsole().getOutput().appendText("Liste des Noeuds"+"\n");
      for (int i=0;i<this.Main.getTreillis().getNoeudList().size();i++)
       {
        this.Main.getConsole().getOutput().appendText(this.Main.getTreillis().getNoeudList().get(i).toString()+"\n");
       }
     this.Main.getConsole().getOutput().appendText("Liste des Barres"+"\n");
       for (int i=0;i<this.Main.getTreillis().getBarreList().size();i++)
       {
        this.Main.getConsole().getOutput().appendText(this.Main.getTreillis().getBarreList().get(i).toString()+"\n");
        }
    }
    
    public void TestTreillis()
    {
        this.Main.getTreillis().MatriceTreillis();
        this.Main.getTreillis().getForcesAgissantes().print(5, 5);
        this.Main.getConsole().getOutput().appendText("Pour voir les details de calculs, Veuillez voir le console du NetBeans"+"\n");
        for(int i=0;i<this.Main.getTreillis().getBarreList().size();i++)
        {
            double MatResult=this.Main.getTreillis().getForcesAgissantes().get(i, 0);
            if(MatResult>0)
            {
             if(MatResult>this.Main.getTreillis().getBarreList().get(i).getTracMax())
             {
               this.Main.getConsole().getOutput().appendText("Barre "+(i+1)+" supporte une traction superieure a sa capacite"+"\n");
             }
            }else
            {
                if(Math.abs(MatResult)>this.Main.getTreillis().getBarreList().get(i).getCompMax())
                {
                  this.Main.getConsole().getOutput().appendText("Barre "+(i+1)+" supporte une compression superieure a sa capacite"+"\n");  
                }
            }
        }
    }
}
