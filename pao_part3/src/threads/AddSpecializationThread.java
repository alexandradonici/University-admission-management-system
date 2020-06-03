package threads;

import model.Specializare;
import repository.SpecializareRepository;
import service.AuditService;

public class AddSpecializationThread extends Thread {
    private final Specializare specializare;
    AuditService auditService = AuditService.getInstance();

    public AddSpecializationThread(String name, Specializare specializare) {
        this.setName(name);
        this.specializare = specializare;
    }

    @Override
    public void run() {

        auditService.writeActiune("Adaugare specializare noua", auditService.getTimestamp(), Thread.currentThread().getName());
        SpecializareRepository.getInstance().add(this.specializare);
    }
}
