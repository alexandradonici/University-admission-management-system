package service;

import model.Facultate;
import model.Specializare;
import repository.SpecializareRepository;
import threads.AddSpecializationThread;

import java.util.ArrayList;
public class SpecializareService
{
    private SpecializareRepository specializareRepository = SpecializareRepository.getInstance();
    private static SpecializareService instance = null;

    private AuditService auditService = AuditService.getInstance();

    public SpecializareService(){}

    public static SpecializareService getInstance()
    {
        if(instance == null)
            instance = new SpecializareService();

        return instance;
    }

    public void add(Specializare specializare)
    {
       AddSpecializationThread specializationThread = new AddSpecializationThread("Adauga specializare noua", specializare);
        specializationThread.start();
    }

    public void remove(int id)
    {
        auditService.writeActiune("Stergere specializare dupa ID", auditService.getTimestamp(), Thread.currentThread().getName());
        specializareRepository.remove(id);
    }

    public void removeFacultate(int id_facultate)
    {
        auditService.writeActiune("Stergere specializare dupa facultate", auditService.getTimestamp(), Thread.currentThread().getName());
        specializareRepository.removeFacultate(id_facultate);
    }


    public Specializare getSpecializareID(int id)
    {
        auditService.writeActiune("Selectare specializare dupa ID", auditService.getTimestamp(), Thread.currentThread().getName());
        return specializareRepository.getSpecializareID(id);
    }

    public ArrayList<Specializare> getSpecializareFacultate(Facultate facultate)
    {
        auditService.writeActiune("Selectare specializare dupa facultate", auditService.getTimestamp(), Thread.currentThread().getName());
        return specializareRepository.getSpecializareFacultate(facultate);
    }

    public ArrayList<Specializare> getSpecializari()
    {
        auditService.writeActiune("Selectare specializari", auditService.getTimestamp(), Thread.currentThread().getName());
        return specializareRepository.getSpecializari();
    }

    public void AfisSpecializari()
    {
        auditService.writeActiune("Afisare specializari", auditService.getTimestamp(), Thread.currentThread().getName());
        specializareRepository.AfisSpecializari();
    }

    public void updateNume (int id_specializare, String nume)
    {
        auditService.writeActiune("Update nume specializare", auditService.getTimestamp(), Thread.currentThread().getName());
        specializareRepository.updateNume(id_specializare, nume);
    }

}
