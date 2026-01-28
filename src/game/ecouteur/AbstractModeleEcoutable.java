package game.ecouteur;
import java.util.ArrayList;

public abstract class AbstractModeleEcoutable {
    private ArrayList<EcouteurModele> ecouteurs = new ArrayList<>();

    /**
     * Ajoute un instance d'ecouteur à la liste ecouteurs
     * @param e l'instance à ajouter;
     */
    public void ajoutEcouteur(EcouteurModele e){
        ecouteurs.add(e);
    }

    /**
     * Supprime un instance d'ecouteur de la liste ecouteurs
     * @param e l'instance à supprimer
     */
    public void removeEcouteur(EcouteurModele e){
        ecouteurs.remove(e);
    }

    /**
     * Notifie chaque ecouteurs dans la liste que le modele a ete modifie
     */
    protected void grilleChangement(){
        for (EcouteurModele e:ecouteurs){
            e.modeleMisAJour(this);
        }
    }
}
