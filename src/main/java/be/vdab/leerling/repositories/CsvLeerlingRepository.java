package be.vdab.leerling.repositories;

import be.vdab.leerling.domain.Leerling;
import be.vdab.leerling.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class CsvLeerlingRepository implements LeerlingRepository {

    private final String pad;

    public CsvLeerlingRepository(@Value("${leerlingenCsvPad}") String pad) {
        this.pad = pad;
    }

    @Override
    public List<Leerling> findAll() {
        try (var stream = Files.lines(Path.of(pad))) {
            return stream.map(regel -> {
                var onderdelen = regel.split(",");
                return new Leerling(Integer.parseInt(onderdelen[0]), onderdelen[1], onderdelen[2]);
            }).toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new RepositoryException(ex);
        }
    }
}
