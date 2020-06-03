package model;

public class Facultate
{
    private String nume;
    private static int id = 0;
    private int tip_facultate;
    private int id_facultate;

    public Facultate(String nume, int tip_facultate)
    {
        this.nume = nume;
        this.tip_facultate = tip_facultate;
        this.id_facultate = ++id;
    }

    public Facultate(int id_facultate, String nume, int tip_facultate)
    {
        this.nume = nume;
        this.id_facultate = id_facultate;
        this.tip_facultate = tip_facultate;

    }

    public String getNume()
    {
        return nume;
    }

    public int getId_facultate()
    {
        return id_facultate;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public int getTip_facultate() {
        return tip_facultate;
    }

    public void setTip_facultate(int tip_facultate) {
        this.tip_facultate = tip_facultate;
    }

    public void setId_facultate(int id_facultate) {
        this.id_facultate = id_facultate;
    }

    @Override
    public String toString()
    {
        return "Facultate{" +
                "nume='" + nume + '\'' +
                ", id_facultate=" + id_facultate +
                '}';
    }
}
