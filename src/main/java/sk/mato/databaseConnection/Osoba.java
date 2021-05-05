package sk.mato.databaseConnection;

import java.util.Date;

public class Osoba implements Comparable<Osoba> {

    private String meno;
    private String priezvisko;
    private int vek;
    private Date datumNarodenia;

    public Osoba(String meno, String priezvisko, int vek, Date datumNarodenia) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.vek = vek;
        this.datumNarodenia = datumNarodenia;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

    public Date getDatumNarodenia() {
        return datumNarodenia;
    }

    public void setDatumNarodenia(Date datumNarodenia) {
        this.datumNarodenia = datumNarodenia;
    }

    @Override
    public int compareTo(Osoba o) {
        return 0;
    }
}
