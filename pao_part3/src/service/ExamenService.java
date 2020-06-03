package service;

import model.ExamenAdmitere;
import repository.ExamenRepository;
import java.util.ArrayList;

public class ExamenService {
    private ExamenRepository examenRepository = ExamenRepository.getInstance();

    private static ExamenService instance = null;
    private AuditService auditService = AuditService.getInstance();

    public ExamenService(){}

    public static ExamenService getInstance()
    {
        if(instance == null)
            instance = new ExamenService();

        return instance;
    }

    public void add(ExamenAdmitere admitere)
    {
        auditService.writeActiune("Adaugare informatii admitere", auditService.getTimestamp(), Thread.currentThread().getName());
        examenRepository.add(admitere);
    }



    public void remove(int id)
    {
        auditService.writeActiune("Stergere informatii admitere", auditService.getTimestamp(), Thread.currentThread().getName());
        examenRepository.remove(id);
    }


    public ExamenAdmitere getAdmitereID(int id_aplicatie)
    {
        auditService.writeActiune("Selectare informatii admitere pentru un anumit candidat", auditService.getTimestamp(), Thread.currentThread().getName());
        return examenRepository.getAdmitereID(id_aplicatie);
    }

    public ArrayList<ExamenAdmitere> getNote_admitere()
    {
        auditService.writeActiune("Selectare informatii admitere", auditService.getTimestamp(), Thread.currentThread().getName());
        return examenRepository.getNote_admitere();
    }

    public int getLastID()
    {
        auditService.writeActiune("Returnare ultim id adaugat", auditService.getTimestamp(), Thread.currentThread().getName());
        return examenRepository.getLastID();

    }
}
