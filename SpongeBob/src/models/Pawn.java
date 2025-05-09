package models;

public class Pawn {
    private String skin;

    public Pawn(String skin) {
        this.skin = skin;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    @Override
    public String toString() {
        return "Pawn{skin='" + skin + "'}";
    }
}
