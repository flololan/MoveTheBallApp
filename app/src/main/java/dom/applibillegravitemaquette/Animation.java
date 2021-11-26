package dom.applibillegravitemaquette;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import exodecorateur_angryballs.encoremieux.modele.BillePilotee;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

/**
 * Created by Dominique on 15/09/2015.
 *
 *
 * Thread d'animation de la bille, prend la main à intervalles réguliers (plus ou moins) et à chaque
 * fois : met à jour la bille :
 *     déplacement vecteurs position et vitesse
 *     gestion du vecteur accélération
 *     collisions éventuelles avec les parois
 *     mise à jour de la vue
 *
 * Ces opérations  sont placées dans la boucle infinie de la méthode run()
 *
 * Dans la version avec capteur d'accéléromètre, cette classe n'a plus de raison d'exister,
 *
 * elle doit être remplacée par une classe "Ecouteur de capteur d'accéléromètre "
 *
 * Cependant les tâches effectuées par Animation devront continuer à être effectuées
 *
 */
public class Animation extends Thread
{
MainActivity activité;
long dernierInstant;                             // instant de la dernière mise à jour
static final double coef = 0.001;                // conversion des millisecondes en secondes
public Animation(MainActivity activité)
{
this.activité = activité;
this.dernierInstant = System.currentTimeMillis();

}

@Override
public void run()
{
try
{
while (!this.isInterrupted())
{
//------------------------ mise à jour de la bille -------------------------------------


long instant = System.currentTimeMillis();                   // instant actuel

double deltaT = Animation.coef * (instant - dernierInstant); // durée écoulée (en s) depuis le dernier évènement capteur
dernierInstant = instant;

activité.bille.déplacer(deltaT);                               // mise à jour du vecteur position et du vecteur vitesse
activité.bille.gestionAccélération(activité.billes, deltaT);   // mise à jour du vecteur accélération en fonction des forces appliquées à la bille
double largeurVue, hauteurVue;

largeurVue = this.activité.vueBille.getWidth();
hauteurVue = this.activité.vueBille.getHeight();

activité.bille.actionReactionContour(0, 0, largeurVue, hauteurVue);  // mise à jour en fonction de collisions éventuelles avec les bords

this.activité.vueBille.postInvalidate();   // force un appel à vueBille.onDraw()
// Si le thread dépend de l'interface graphique (comme dans un capteur), il faut faire : vueBille.invalidate()
// ici, comme le thread est indépendant de l'interface graphique, on utilise vueBille.invalidate();

Thread.sleep(5);
}//while
} //try
catch (InterruptedException exception) {/* on ne fait rien */}
}

}
