package sk.mato.testForOrmFramework.entits;

public class Film {
    String nazov;
    String reziser;
    String vHlavnejUlohe;

    public Film() {
        this.nazov = nazov;
        this.reziser = reziser;
        this.vHlavnejUlohe = vHlavnejUlohe;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public String getvHlavnejUlohe() {
        return vHlavnejUlohe;
    }

    public void setvHlavnejUlohe(String vHlavnejUlohe) {
        this.vHlavnejUlohe = vHlavnejUlohe;
    }
}
