package service;

import model.Repartitie;
import repository.RepartitieRepository;
import threads.AddApplicationThread;

import java.util.ArrayList;

public class RepartitieService
{
    private RepartitieRepository repartitieRepository = RepartitieRepository.getInstance();
    private static RepartitieService instance = null;

    private AuditService auditService = AuditService.getInstance();

    public RepartitieService(){}

    public static RepartitieService getInstance()
    {
        if(instance == null)
            instance = new RepartitieService();
        return instance;
    }

    public void add(Repartitie repartitie)
    {
        AddApplicationThread applicationThread = new AddApplicationThread("Adauga aplicatie noua", repartitie);
        applicationThread.start();

    }

    public void remove(int id)
    {
        auditService.writeActiune("Stergere optiune dupa ID", auditService.getTimestamp(), Thread.currentThread().getName());
        repartitieRepository.remove(id);

    }

    public void removeCandidat(int id_candidat)
    {
        auditService.writeActiune("Stergere optiune dupa candidat", auditService.getTimestamp(), Thread.currentThread().getName());
        repartitieRepository.removeCandidat(id_candidat);

    }

    public Repartitie getRepartitieID(int id_repartitie)
    {
        auditService.writeActiune("Selectare optiune dupa ID", auditService.getTimestamp(), Thread.currentThread().getName());
        return repartitieRepository.getRepartitieID(id_repartitie);

    }

    public void updateSalaAdmitere(int id_aplicatie, String salaExamen)
    {
        auditService.writeActiune("Actualizare sala examen", auditService.getTimestamp(), Thread.currentThread().getName());
        repartitieRepository.updateSalaAdmitere(id_aplicatie, salaExamen);

    }

    public ArrayList<Repartitie> getCandidatiPeSpecializari(int id_specializare)
    {
        auditService.writeActiune("Selectare optiuni dupa specializare", auditService.getTimestamp(), Thread.currentThread().getName());
        return repartitieRepository.getCandidatiPeSpecializari(id_specializare);
    }


    public ArrayList<Repartitie> getCandidatiMedieDescrescator(int specializare)
    {
        auditService.writeActiune("Selectare candidati in ordinea descrescatoare a mediilor dupa specializare", auditService.getTimestamp(), Thread.currentThread().getName());
       return repartitieRepository.getCandidatiMedieDescrescator(specializare);
    }

    public ArrayList<Repartitie> getCandidatiAlfabetic(int specializare)
    {
        auditService.writeActiune("Selectare candidati in ordinea alfabetica dupa specializare", auditService.getTimestamp(), Thread.currentThread().getName());
        return repartitieRepository.getCandidatiAlfabetic(specializare);
    }

    public ArrayList<Repartitie> getAdmisiBuget(int specializare)
    {
        auditService.writeActiune("Selectare candidati admisi la buget dupa specializare", auditService.getTimestamp(), Thread.currentThread().getName());
        return repartitieRepository.getAdmisiBuget(specializare);

    }

    public ArrayList<Repartitie> getAdmisiTaxa(int  specializare)
    {
        auditService.writeActiune("Selectare candidati admisi la taxa dupa specializare", auditService.getTimestamp(), Thread.currentThread().getName());
        return repartitieRepository.getAdmisiTaxa(specializare);

    }

    public ArrayList<Repartitie> getRespinsi(int  specializare)
    {
        auditService.writeActiune("Selectare candidati respinsi dupa specializare", auditService.getTimestamp(), Thread.currentThread().getName());
        return repartitieRepository.getRespinsi(specializare);

    }

    public ArrayList<Repartitie> getOptiuniCandidat(int candidat)
    {
        auditService.writeActiune("Selectare optiuni dupa candidat", auditService.getTimestamp(), Thread.currentThread().getName());
        return repartitieRepository.getOptiuniCandidat(candidat);
    }


    public void updateMedieAdmitere(int id, double notaExamen)
    {
        auditService.writeActiune("Update medie admitere", auditService.getTimestamp(), Thread.currentThread().getName());
        repartitieRepository.updateMedieAdmitere(id,notaExamen);

    }

    public void AfisRepartizare()
    {
        auditService.writeActiune("Afisare optiuni", auditService.getTimestamp(), Thread.currentThread().getName());
        repartitieRepository.AfisRepartizare();
    }

    public ArrayList<Repartitie> getRepartizare()
    {
        auditService.writeActiune("Selectare optiuni", auditService.getTimestamp(), Thread.currentThread().getName());
        return repartitieRepository.getRepartizare();
    }



}
