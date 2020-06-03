package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Repartitie
{
    private static int id=0;
    private int id_repartitie;
    private Candidat candidat;
    private Specializare specializare;

    private Bacalaureat bac;
    private ExamenAdmitere examen;

    double medieAdmitere;



    public Repartitie(Candidat candidat, Specializare specializare, Bacalaureat bac, ExamenAdmitere examen)
    {
        this.candidat = candidat;
        this.specializare = specializare;
        this.bac = bac;
        this.examen = examen;

        if(specializare.getFacultate().getTip_facultate() == 0)
        {
            medieAdmitere = bac.getMedieBac();
        }
        else
        {
            medieAdmitere = 0;
        }


        this.id_repartitie=++id;
    }

    public Repartitie(int id_repartitie, Candidat candidat, Specializare specializare, Bacalaureat bac, ExamenAdmitere examen)
    {
        this.candidat = candidat;
        this.specializare = specializare;
        this.bac = bac;
        this.examen = examen;

        this.id_repartitie=id_repartitie;

        if(specializare.getFacultate().getTip_facultate() == 0)
        {
            medieAdmitere = bac.getMedieBac();
        }
        else
        {
            medieAdmitere = bac.getMedieBac()*20/100 + examen.getNotaExamen()*80/100 ;
        }

    }

    public int getId_repartitie() {
        return id_repartitie;
    }

    public void setId_repartitie(int id_repartitie) {
        this.id_repartitie = id_repartitie;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Specializare getSpecializare() {
        return specializare;
    }

    public void setSpecializare(Specializare specializare) {
        this.specializare = specializare;
    }

    public Bacalaureat getBac() {
        return bac;
    }

    public void setBac(Bacalaureat bac) {
        this.bac = bac;
    }

    public ExamenAdmitere getExamen() {
        return examen;
    }

    public void setExamen(ExamenAdmitere examen) {
        this.examen = examen;
    }

    public double getMedieAdmitere() {
        return medieAdmitere;
    }

    public void setMedieAdmitere(double medieAdmitere) {
        this.medieAdmitere = medieAdmitere;
    }

    @Override
    public String toString() {
        return "Repartitie{" +
                "id_repartitie=" + id_repartitie +
                ", candidat=" + candidat +
                ", specializare=" + specializare +
                ", bac=" + bac +
                ", examen=" + examen +
                ", medieAdmitere=" + medieAdmitere +
                '}';
    }
}
