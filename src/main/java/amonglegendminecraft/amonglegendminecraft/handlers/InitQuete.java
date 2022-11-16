package amonglegendminecraft.amonglegendminecraft.handlers;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class InitQuete {

    private ArrayList<Quete> quetes;
    private int nbquete;

    public InitQuete(int nbquete){
        this.nbquete = nbquete;
        quetes = new ArrayList<>();
    }

    public ArrayList<Quete> getQuetes() {
        return quetes;
    }

    public int getNbquete() {
        return nbquete;
    }

    public void setNbquete(int nbquete) {
        this.nbquete = nbquete;
    }

    public ArrayList<Quete> initiateQuete() throws Exception {

        Quete queteBois = new Quete("Bois","Avoir 32 bûche de chêne", false);
        Quete queteCobblestone = new Quete("Cobblestone","Avoir 4 stacks de cobblestone", false);
        Quete queteZombie = new Quete("Zombie", "Tuer 10 zombies", false);
        Quete queteEnderman = new Quete("Enderman", "Tuer 2 Enderman", false);
        ArrayList<Quete> queteToAdd = new ArrayList<>();

        queteToAdd.add(queteBois);
        queteToAdd.add(queteCobblestone);
        queteToAdd.add(queteZombie);
        queteToAdd.add(queteEnderman);

        if (queteToAdd.size() <= nbquete){
            Collections.shuffle(queteToAdd);
            for (int i = 0; i < nbquete; i++){
                quetes.add(queteToAdd.get(i));
            }

        }else{
            throw new Exception("ntm fdp, trop de quêtes");
        }
        return quetes;
    }


}