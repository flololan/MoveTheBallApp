package dom.applibillegravitemaquette;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import exodecorateur_angryballs.encoremieux.modele.BillePilotee;
import mesmaths.geometrie.base.Vecteur;

public class EcouteurGyroscope implements SensorEventListener {
    MainActivity activity;


    public EcouteurGyroscope(MainActivity activity) {
        this.activity = activity;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(this.activity.vueBille.premierAppelAOnDraw == false){

        double y = event.values[0];
        double x = event.values[1];
        Vecteur push = new Vecteur(x,y);
        //System.out.println("push: " + push);
        //System.out.println("Values X:" + event.values[0]);
        //System.out.println("Values Y:" + event.values[1]);
        //System.out.println("Values Z:" + event.values[2]);


        ((BillePilotee)(this.activity.bille)).addLast(push.produit(MainActivity.coefAmplification * 2e4));
        System.out.println("Bille: " + this.activity.bille);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
