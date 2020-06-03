package service;

import model.*;

import repository.FacultateRepository;
import threads.AddFacultyThread;

import java.util.ArrayList;

public class FacultateService
{
    private FacultateRepository facultateRepository = FacultateRepository.getInstance();
    private static FacultateService instance = null;

    private AuditService auditService = AuditService.getInstance();

    public FacultateService(){}

    public static FacultateService getInstance()
    {
        if(instance == null)
            instance = new FacultateService();

        return instance;
    }

    public void add(Facultate facultate)
    {
        AddFacultyThread facultyThread = new AddFacultyThread("Adauga facultate noua", facultate);
        facultyThread.start();
    }

    public void remove(int id)
    {
        auditService.writeActiune("Stergere facultate dupa ID", auditService.getTimestamp(), Thread.currentThread().getName());
        facultateRepository.remove(id);
    }

    public Facultate getFacultateID(int id)
    {
        auditService.writeActiune("Selectare facultate dupa ID", auditService.getTimestamp(), Thread.currentThread().getName());
        return facultateRepository.getFacultateID(id);
    }


    public ArrayList<Facultate> getFacultati()
    {
        auditService.writeActiune("Selectare facultati", auditService.getTimestamp(), Thread.currentThread().getName());
        return facultateRepository.getFacultati();
    }

    public void AfisFacultati()
    {
        auditService.writeActiune("Afisare facultati", auditService.getTimestamp(), Thread.currentThread().getName());
        facultateRepository.AfisFacultati();
    }

    public ArrayList<Facultate> getFacultatiCuAdmitere()
    {
        auditService.writeActiune("Selectare facultati cu admitere", auditService.getTimestamp(), Thread.currentThread().getName());
        return  facultateRepository.getFacultatiCuAdmitere();
    }

    public ArrayList<Facultate> getFacultatiFaraAdmitere() {
        auditService.writeActiune("Selectare facultati fara admitere", auditService.getTimestamp(), Thread.currentThread().getName());
        return facultateRepository.getFacultatiFaraAdmitere();

    }

    public void updateNume (int id_facultate, String nume)
    {
        auditService.writeActiune("Update nume facultate", auditService.getTimestamp(), Thread.currentThread().getName());
        facultateRepository.updateNume(id_facultate, nume);
    }

}
