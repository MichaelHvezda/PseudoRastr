package pseudoraster;

import rasterdata.Raster;
import snake.BodBarva;
import transforms.Col;
import transforms.Point2D;

import java.awt.*;
import java.util.List;

public class PSRaster {

    private int velikost;
    private int xRastru;
    private int yRastru;
    private int velikostCtverecku;
    private int xPSRasteru;
    private int yPSRasteru;
    private Color bila = new Color(255,255,255);

    public PSRaster(int velikost, int xRastru, int yRastru) {
        this.velikost = velikost;
        this.xRastru = xRastru;
        this.yRastru = yRastru;
        this.velikostCtverecku = (this.velikost-1)*2+1;

        this.xPSRasteru = this.xRastru/velikostCtverecku;
        this.yPSRasteru = this.yRastru/velikostCtverecku;

    }


    public Raster toRaster(Raster raster, List<BodBarva> had){
        Raster vysledek = raster;
        for (BodBarva a: had) {
            vysledek = kosticka(vysledek,a);
        }

        return vysledek;
    }



    public Raster orezRastr(Raster raster){
        Raster vysledek=raster;
        if(xRastru %velikostCtverecku!=0){
            vysledek=vybarveniLineX(vysledek);
            return orezRastr(vysledek);
        }

        if(yRastru %velikostCtverecku!=0){
           vysledek = vybarveniLineY(vysledek);
           return orezRastr(vysledek);
        }
        return vysledek;
    }



    public int getVelikost() {
        return velikost;
    }

    public void setVelikost(int velikost) {
        this.velikost = velikost;
    }

    public int getxRastru() {
        return xRastru;
    }

    public void setxRastru(int xRastru) {
        this.xRastru = xRastru;
    }

    public int getyRastru() {
        return yRastru;
    }

    public void setyRastru(int yRastru) {
        this.yRastru = yRastru;
    }


    private Raster vybarveniLineX(Raster raster){
        setxRastru(xRastru-1);
        for (int i = 0; i< yRastru ; i++){
            raster = raster.withValue(xRastru,i,bila);
        }

        return raster;
    }

    private Raster vybarveniLineY(Raster raster){
        setyRastru(yRastru-1);
        for (int i = 0; i< xRastru ; i++){
            raster = raster.withValue(i,yRastru,bila);
        }

        return raster;
    }

    private Raster kosticka(Raster raster, BodBarva a){
        for (int u = 0; u<velikostCtverecku;u++){
            for (int i = 0; i<velikostCtverecku;i++){
                raster = raster.withValue((int)(a.getPoint2D().getX())*velikostCtverecku+i,(int)(a.getPoint2D().getY())*velikostCtverecku+u,a.getColor());

            }
        }



       return raster ;
    }



}
