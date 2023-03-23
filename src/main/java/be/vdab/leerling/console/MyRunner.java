package be.vdab.leerling.console;

import be.vdab.leerling.exceptions.RepositoryException;
import be.vdab.leerling.repositories.LeerlingRepository;
import be.vdab.leerling.repositories.LesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private final LeerlingRepository leerlingRepository;
    private final LesRepository lesRepository;

    public MyRunner(LeerlingRepository leerlingRepository, LesRepository lesRepository) {
        this.leerlingRepository = leerlingRepository;
        this.lesRepository = lesRepository;
    }

    @Override
    public void run(String... args) {
        try {
            leerlingRepository.findAll()
                    .forEach(leerling -> System.out.println(leerling.getVoornaam() + " " + leerling.getFamilienaam()));
            System.out.println();
            lesRepository.findAll()
                    .forEach(les -> System.out.println(les.getNaam()));
        } catch (RepositoryException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
