package be.vdab.leerling.repositories;

import be.vdab.leerling.domain.Les;
import be.vdab.leerling.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class LesRepository {

    private final String pad;

    public LesRepository(@Value("${lessenCsvPad}") String pad) {
        this.pad = pad;
    }

    public List<Les> findAll() {
        try (var stream = Files.lines(Path.of(pad))) {
            return stream.map(regel -> {
                var onderdelen = regel.split(",");
                return new Les(onderdelen[0], onderdelen[1]);
            }).toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new RepositoryException(ex);
        }
    }
}
