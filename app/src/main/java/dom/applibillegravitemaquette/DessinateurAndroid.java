package dom.applibillegravitemaquette;

import android.graphics.Canvas;
import android.graphics.Paint;

import exodecorateur_angryballs.encoremieux.modele.BilleSimple;
import exodecorateur_angryballs.encoremieux.modele.BilleSimpleAR;
import exodecorateur_angryballs.encoremieux.modele.Dessinateur;

/**
 * Created by Dominique on 16/09/2015.
 */
public class DessinateurAndroid implements Dessinateur
{
Canvas canvas;

public DessinateurAndroid(Canvas canvas)
{
this.canvas = canvas;
}

@Override
public void dessine(BilleSimple billeSimple)
{
float x,y, rayon;

x = (float)billeSimple.getPosition().x;
y = (float)billeSimple.getPosition().y;
rayon = (float)billeSimple.getRayon();
Paint paint = new Paint();
paint.setStyle(Paint.Style.FILL);
paint.setColor(billeSimple.getCouleur());
canvas.drawCircle(x,y,rayon,paint);
}

@Override
public void dessine(BilleSimpleAR billeSimpleAR)
{
/* on ne fait rien */
}
}
