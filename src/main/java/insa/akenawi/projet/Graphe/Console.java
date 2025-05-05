/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.akenawi.projet.Graphe;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author abdallahkenawy
 */
public class Console extends VBox
{
    private TextArea output;
    
     /**
     * @return the output
     */
    public TextArea getOutput() {
        return output;
    }
    
    public Console()
    {
        this.output=new TextArea();
        this.getChildren().addAll(this.output);
        this.setSpacing(3);
        this.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        this.output.setWrapText(true);
        this.output.setEditable(false);
        this.output.appendText("Pour commencer la creation de votre Treillis, La creation d'un terrain est primordiale"+"\n"+
                               "Vous pouvez directement dessiner les composants du Treillis a l'aide des RadioButtons"+"\n"+
                               "Sinon, vous pouvez mettre directement les proprietes des composants que vous voulez creer"+"\n"+
                               "la modification des noeud et des barres est possible, aussi que la sauvegarde et la charge des fichiers"+"\n"+
                               "Une calcule matricielle permit de verifier si votre design tiendrai ou non"+"\n"+
                               "Vous pouvez desormais experimenter comme vous voulez"+"\n");
    }
    
}
