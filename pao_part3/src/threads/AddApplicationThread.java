package threads;

import model.Repartitie;
import repository.RepartitieRepository;
import service.AuditService;



public class AddApplicationThread extends Thread
{
    private final Repartitie repartitie;
    AuditService auditService = AuditService.getInstance();

    public AddApplicationThread(String name, Repartitie repartitie) {
        this.setName(name);
        this.repartitie = repartitie;
    }

    @Override
    public void run() {
        RepartitieRepository.getInstance().add(this.repartitie);
        auditService.writeActiune("Adaugare optiune noua", auditService.getTimestamp(), Thread.currentThread().getName());

    }
}
