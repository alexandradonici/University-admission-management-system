package service;

import model.Candidat;
import repository.CandidatRepository;
import threads.AddCandidateThread;

import java.util.ArrayList;

public class CandidatService
{
    private CandidatRepository candidatRepository = CandidatRepository.getInstance();
    private static CandidatService instance = null;

    private AuditService auditService = AuditService.getInstance();

    public CandidatService(){}

    public static CandidatService getInstance()
    {
        if(instance == null)
            instance = new CandidatService();

        return instance;
    }
    public Candidat getCandidatNume(String nume, String prenume)
    {
        auditService.writeActiune("Selectare candidat dupa nume", auditService.getTimestamp(), Thread.currentThread().getName());
        return candidatRepository.getCandidatNume(nume, prenume);

    }

    public Candidat getCandidatID(int id)
    {
        auditService.writeActiune("Selectare candidat dupa ID", auditService.getTimestamp(), Thread.currentThread().getName());
        return candidatRepository.getCandidatID(id);

    }

    public void add(Candidat candidat)
    {
        AddCandidateThread candidateThread = new AddCandidateThread("Adauga candidat nou", candidat);
        candidateThread.start();
    }

    public ArrayList<Candidat> getCandidati()
    {
        auditService.writeActiune("Selectare candidati", auditService.getTimestamp(), Thread.currentThread().getName());
        return candidatRepository.getCandidati();
    }


    public void remove(int id)
    {
        auditService.writeActiune("Stergere candidat", auditService.getTimestamp(), Thread.currentThread().getName());
        candidatRepository.remove(id);

     }

    public void AfisCandidati()
    {
        auditService.writeActiune("Afisare candidati", auditService.getTimestamp(), Thread.currentThread().getName());
        candidatRepository.AfisCandidati();
    }

    public void updateNume (int id_candidat, String nume, String prenume)
    {
        auditService.writeActiune("Update nume candidat", auditService.getTimestamp(), Thread.currentThread().getName());
        candidatRepository.updateNume(id_candidat, nume, prenume);
    }


}
