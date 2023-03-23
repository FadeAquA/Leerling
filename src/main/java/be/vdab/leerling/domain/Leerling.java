package be.vdab.leerling.domain;

public class Leerling {

    private final int nummer;
    private final String voornaam;
    private final String familienaam;

    public Leerling(int nummer, String voornaam, String familienaam) {
        this.nummer = nummer;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
    }

    public int getNummer() {
        return nummer;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }
}
