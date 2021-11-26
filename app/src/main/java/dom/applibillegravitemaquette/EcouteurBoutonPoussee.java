package dom.applibillegravitemaquette;

import android.view.View;
import android.widget.Button;

import exodecorateur_angryballs.encoremieux.modele.BillePilotee;
import mesmaths.geometrie.base.Vecteur;

/**
 * Created by Dominique on 16/09/2015.
 */
public class EcouteurBoutonPoussee implements Button.OnClickListener
{
MainActivity activité;
Vecteur poussée;        // la force de poussée appliquée à la bille à chaque clic sur le bouton
Button boutonPoussée;   // bouton déclenchant l'impulsion donnée à la bille

public EcouteurBoutonPoussee(MainActivity activité, Vecteur poussée, Button boutonPoussée)
{
this.activité = activité;
this.poussée = poussée.produit(MainActivity.coefAmplification*2e5);
this.boutonPoussée = boutonPoussée;
this.boutonPoussée.setOnClickListener(this);
}

@Override
public void onClick(View v)
{
// applique la force de poussée à la bille, c-à-d :
// place la force dans une pile (type FIFO : LinkedList) de forces interne à la bille
// (cf. classe BillePilotee)
// la méthode déplacer() de la classe Bille "consommera" cette force

((BillePilotee)(this.activité.bille)).addLast(poussée);

//this.activité.message.setText("bouton cliqué : " + boutonPoussée);
}
}
