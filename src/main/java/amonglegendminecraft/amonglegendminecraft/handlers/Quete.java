package amonglegendminecraft.amonglegendminecraft.handlers;

public class Quete {

    private String nameQuete;
    private String objectif;
    private boolean isDone;

    public Quete (String nameQuete, String objectif, boolean isDone){
        this.nameQuete = nameQuete;
        this.objectif = objectif;
        this.isDone = isDone;
    }

    public String getNameQuete() {
        return nameQuete;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getObjectif() {
        return objectif;
    }

    public String queteDesc(String nameQuete){
        return nameQuete+ " \n objectif : " + objectif;
    }

}