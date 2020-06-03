package model;

public class ExamenAdmitere
{
    private static int id = 0;
    private int id_examen;

    private double notaExamen;
    private String salaExamen;

    private int id_aplicatie;

    public ExamenAdmitere()
    {
        this.notaExamen = 0.0;
        this.salaExamen ="-";
        this.id_examen = ++id;
    }

    public ExamenAdmitere( String salaExamen, double notaExamen)
    {
        this.notaExamen = notaExamen;
        this.salaExamen = salaExamen;
        this.id_examen = ++id;
    }

    public ExamenAdmitere( String salaExamen, double notaExamen, int id_aplicatie, int id_examen)
    {
        this.notaExamen = notaExamen;
        this.salaExamen = salaExamen;
        this.id_aplicatie = id_aplicatie;
        this.id_examen = id_examen;

    }

    public double getNotaExamen()
    {
        return notaExamen;
    }

    public String getSalaExamen()
    {
        return salaExamen;
    }

    public void setNotaExamen(double notaExamen)
    {
        this.notaExamen = notaExamen;
    }

    public void setSalaExamen(String salaExamen)
    {
        this.salaExamen = salaExamen;
    }

    public int getId_examen() {
        return id_examen;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public int getid_aplicatie() {
        return id_aplicatie;
    }

    public void setid_aplicatie(int id_aplicatie) {
        this.id_aplicatie = id_aplicatie;
    }

    @Override
    public String toString()
    {
        return "ExamenAdmitere{" +
                "notaExamen=" + notaExamen +
                ", salaExamen='" + salaExamen + '\'' +
                '}';
    }
}
