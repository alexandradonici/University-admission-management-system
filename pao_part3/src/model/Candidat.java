package model;

public class Candidat
{
    private String nume;
    private String prenume;
    private static int id = 0;
    private int id_candidat;

    public Candidat(String nume, String prenume)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.id_candidat = ++id;
    }

    public Candidat(int id_candidat, String nume, String prenume)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.id_candidat = id_candidat;

    }

    public String getNume()
    {
        return nume;
    }

    public String getPrenume()
    {
        return prenume;
    }

    public int getId_candidat()
    {
        return id_candidat;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public void setPrenume(String prenume)
    {
        this.prenume = prenume;
    }

    @Override
    public String toString()
    {
        return "Candidat{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", id_candidat=" + id_candidat +
                '}';
    }
}
