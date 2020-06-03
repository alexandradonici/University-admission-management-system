package service;

import model.Bacalaureat;
import repository.BacalaureatRepository;

import java.util.ArrayList;

public class BacalaureatService
{
    private BacalaureatRepository bacalaureatRepository = BacalaureatRepository.getInstance();
    private static BacalaureatService instance = null;
    private AuditService auditService = AuditService.getInstance();


    public BacalaureatService(){}

    public static BacalaureatService getInstance()
    {
        if(instance == null)
            instance = new BacalaureatService();

        return instance;
    }

    public void add(Bacalaureat bac)
    {
        auditService.writeActiune("Adaugare note bacalaureat", auditService.getTimestamp(), Thread.currentThread().getName());
        bacalaureatRepository.add(bac);
    }

    public void remove(int id)
    {
        auditService.writeActiune("Stergere note bacalaureat", auditService.getTimestamp(),Thread.currentThread().getName());
        bacalaureatRepository.remove(id);
    }


    public Bacalaureat getBacID(int id_aplicatie)
    {
        auditService.writeActiune("Selectare note bacalaureat dupa id candidat", auditService.getTimestamp(),Thread.currentThread().getName());
        return bacalaureatRepository.getBacID(id_aplicatie);
    }

    public ArrayList<Bacalaureat> getNote_bac()
    {
        auditService.writeActiune("Selectare note bacalaureat", auditService.getTimestamp(), Thread.currentThread().getName());
        return bacalaureatRepository.getNote_bac();
    }

    public int getLastID()
    {
        auditService.writeActiune("Returnare ultim id adaugat", auditService.getTimestamp(), Thread.currentThread().getName());
        return bacalaureatRepository.getLastID();

    }


}
