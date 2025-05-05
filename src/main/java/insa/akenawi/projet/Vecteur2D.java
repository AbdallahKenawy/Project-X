package insa.akenawi.projet;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author abdallahkenawy
 */
public class Vecteur2D  //Classe contenant les forces agissantes sur les noeuds
{
    private double vx;
    private double vy;

    /**
     * @return the vx
     */
    public double getVx() {
        return vx;
    }

    /**
     * @param vx the vx to set
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * @return the vy
     */
    public double getVy() {
        return vy;
    }

    /**
     * @param vy the vy to set
     */
    public void setVy(double vy) {
        this.vy = vy;
    }
    
    
    public Vecteur2D (double vx, double vy)
    {
        this.vx=vx;
        this.vy=vy;
    }
    
    public String toString(){
     return this.getVx()+";"+this.getVy();
    }
    
    
}
