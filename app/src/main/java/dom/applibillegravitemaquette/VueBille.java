package dom.applibillegravitemaquette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.ImageView;

import java.util.Vector;

import exodecorateur_angryballs.encoremieux.modele.Bille;
import exodecorateur_angryballs.encoremieux.modele.BilleFrottement;
import exodecorateur_angryballs.encoremieux.modele.BillePilotee;
import exodecorateur_angryballs.encoremieux.modele.BilleRebond;
import exodecorateur_angryballs.encoremieux.modele.BilleSimple;
import exodecorateur_angryballs.encoremieux.modele.Dessinateur;
import mesmaths.geometrie.base.Vecteur;

/**
 * Created by Dominique on 15/09/2015.
 */
public class VueBille extends ImageView
{
MainActivity activité;
boolean premierAppelAOnDraw;

public VueBille(Context context, MainActivity activité)
{
super(context);
this.activité = activité;
this.premierAppelAOnDraw = true;

this.setBackgroundColor(Color.CYAN);
}


/**
 * Les éléments initialisés dans cette fonction ont besoin de connaître la largeur et la hauteur de
 * la vue, or ces valeurs ne sont pas calculées avant le 1er appel à onDraw()
 *
 * */
public void initialise()
{
double x ,y;
x = this.getWidth()/2;
y = this.getHeight()/2;                    // les dimensions de VueBille ne sont pas connues avant

Vecteur centre = new Vecteur(x,y);         // la bille sera placée au centre de la vue
double rayon = x/10;
//Vecteur vitesse = new Vecteur(200,200);  // déterminé expérimentalement
Vecteur vitesse = Vecteur.VECTEURNUL;
int couleur = Color.RED;
activité.bille = new BilleSimple(centre,rayon,vitesse,couleur);
activité.bille = new BilleRebond(activité.bille);
double coefFrottement = MainActivity.coefAmplification * 40;              // à établir expérimentalement
activité.bille = new BilleFrottement(activité.bille,coefFrottement);

activité.bille = new BillePilotee(activité.bille);
activité.billes = new Vector<Bille>();                     // la liste de toutes les billes : ici réduite à une seule bille
activité.billes.add(activité.bille);

//-------- on active l'animation -------------

activité.animation.start();
}

@Override
protected void onDraw(Canvas canvas)
{
super.onDraw(canvas);
if (premierAppelAOnDraw)
   {
   premierAppelAOnDraw = false;
   initialise();    // création de la bille et activation du capteur

   }

// on dessine la bille
Dessinateur dessinateur = new DessinateurAndroid(canvas);
activité.bille.dessine(dessinateur);
}
}
