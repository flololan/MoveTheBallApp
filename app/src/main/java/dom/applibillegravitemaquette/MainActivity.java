package dom.applibillegravitemaquette;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorDirectChannel;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Vector;

import exodecorateur_angryballs.encoremieux.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

public class MainActivity extends AppCompatActivity
{
FrameLayout cadre;
TextView message;
VueBille vueBille;

Button boutonPousséeGauche, boutonPousséeDroite, boutonPousséeHaut, boutonPousséeBas;

EcouteurBoutonPoussee écouteurBoutonPousséeGauche, écouteurBoutonPousséeDroite,
                      écouteurBoutonPousséeHaut, écouteurBoutonPousséeBas;
EcouteurGyroscope ecouteurGyroscope;
Gyroscope gyroscope;

//-------------------------- objets métier : gestion de la bille -------------------

final static double coefAmplification = 2e5;    // à déterminer expérimentalement en fonction de l'appareil mobile
                                                // cette valeur devrait être placée dans res
Bille bille;
Vector<Bille> billes;  // la liste des billes réduite à une seule bille

//--------------------------- gestion de l'animation ---------------------------------

Animation animation;

@Override
protected void onCreate(Bundle savedInstanceState)
{
super.onCreate(savedInstanceState);

this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


//----------------- gestion des composants graphiques -------------------

setContentView(R.layout.layout2);
cadre = (FrameLayout)(this.findViewById(R.id.cadre));
message = (TextView)(this.findViewById(R.id.message));
boutonPousséeGauche = (Button)(this.findViewById(R.id.boutonPousseAGauche));
boutonPousséeDroite = (Button)(this.findViewById(R.id.boutonPousseADroite));
boutonPousséeHaut = (Button)(this.findViewById(R.id.boutonPousseHaut));
boutonPousséeBas = (Button)(this.findViewById(R.id.boutonPousseBas));


int largeur,hauteur;
largeur = hauteur = ViewGroup.LayoutParams.MATCH_PARENT;
ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(largeur,hauteur);
vueBille = new VueBille(this,this);
cadre.addView(vueBille, params);


Log.i("AppliBilleGravite", "largeur vue = " + this.vueBille.getWidth());
Log.i("AppliBilleGravite","hauteur vue = " +
        this.vueBille.getHeight());

//-------------------------------- création de la bille -------------------------------

// fait dans VueBille.initialise();

//---------------------- installation de l'animation ----------------------

this.animation = new Animation(this);

// l'activation de l'animation est faite dans VueBille.initialise();

//------------------------ installation des écouteurs de boutons ----------------

écouteurBoutonPousséeGauche = new EcouteurBoutonPoussee(this,new Vecteur(-1,0),boutonPousséeGauche);
écouteurBoutonPousséeDroite = new EcouteurBoutonPoussee(this,new Vecteur(1,0),boutonPousséeDroite);
écouteurBoutonPousséeHaut = new EcouteurBoutonPoussee(this,new Vecteur(0,-1), boutonPousséeHaut);
écouteurBoutonPousséeBas = new EcouteurBoutonPoussee(this,new Vecteur(0,1), boutonPousséeBas);

//instanciation de Gyroscope


    this.ecouteurGyroscope = new EcouteurGyroscope(this);
    this.gyroscope = new Gyroscope(this);

}

@Override
public boolean onCreateOptionsMenu(Menu menu)
{
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.menu_main, menu);
return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item)
{
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
int id = item.getItemId();

//noinspection SimplifiableIfStatement
if (id == R.id.action_settings)
{
return true;
}

return super.onOptionsItemSelected(item);
}
}
