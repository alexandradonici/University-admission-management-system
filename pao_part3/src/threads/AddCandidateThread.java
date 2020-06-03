package threads;

import model.Candidat;
import repository.CandidatRepository;
import service.AuditService;


public class AddCandidateThread extends Thread
{
    private final Candidat candidat;
    AuditService auditService = AuditService.getInstance();

    public AddCandidateThread(String name, Candidat candidat) {
        this.setName(name);
        this.candidat = candidat;
    }

    @Override
    public void run() {
        auditService.writeActiune("Adaugare candidat nou", auditService.getTimestamp(), Thread.currentThread().getName());
        CandidatRepository.getInstance().add(this.candidat);

    }
}
