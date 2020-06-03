package model;

public class Specializare
{
    private String nume;
    private static int id = 0;
    private int id_specializare;
    private Facultate facultate;
    private int locuri_buget;
    private int locuri_taxa;

    public Specializare(String nume, Facultate facultate, int locuri_buget, int locuri_taxa)
    {
        this.nume = nume;
        this.id_specializare = ++id;
        this.facultate = facultate;
        this.locuri_buget = locuri_buget;
        this.locuri_taxa = locuri_taxa;
    }

    public Specializare(int id_specializare, String nume, Facultate facultate, int locuri_buget, int locuri_taxa)
    {
        this.nume = nume;
        this.id_specializare = id_specializare;
        this.facultate = facultate;
        this.locuri_buget = locuri_buget;
        this.locuri_taxa = locuri_taxa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getId_specializare() {
        return id_specializare;
    }

    public void setId_specializare(int id_specializare) {
        this.id_specializare = id_specializare;
    }

    public Facultate getFacultate() {
        return facultate;
    }

    public void setFacultate(Facultate facultate) {
        this.facultate = facultate;
    }

    public int getLocuri_buget() {
        return locuri_buget;
    }

    public void setLocuri_buget(int locuri_buget) {
        this.locuri_buget = locuri_buget;
    }

    public int getLocuri_taxa() {
        return locuri_taxa;
    }

    public void setLocuri_taxa(int locuri_taxa) {
        this.locuri_taxa = locuri_taxa;
    }

    @Override
    public String toString() {
        return "Specializare{" +
                "nume='" + nume + '\'' +
                ", id_specializare=" + id_specializare +
                ", facultate=" + facultate +
                ", locuri_buget=" + locuri_buget +
                ", locuri_taxa=" + locuri_taxa +
                '}';
    }
}
