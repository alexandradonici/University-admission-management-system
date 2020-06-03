package threads;

import model.Facultate;
import repository.FacultateRepository;
import service.AuditService;


public class AddFacultyThread extends Thread {
    private final Facultate facultate;
    AuditService auditService = AuditService.getInstance();

    public AddFacultyThread(String name, Facultate facultate) {
        this.setName(name);
        this.facultate = facultate;
    }

    @Override
    public void run() {
        FacultateRepository.getInstance().add(this.facultate);
        auditService.writeActiune("Adaugare facultate noua", auditService.getTimestamp(), Thread.currentThread().getName());

    }
}