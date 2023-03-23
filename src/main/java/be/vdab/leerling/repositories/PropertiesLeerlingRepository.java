package be.vdab.leerling.repositories;

import be.vdab.leerling.domain.Leerling;
import be.vdab.leerling.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component @Primary
public class PropertiesLeerlingRepository implements LeerlingRepository {

    private final String pad;

    public PropertiesLeerlingRepository(@Value("${leerlingenPropertiesPad}") String pad) {
        this.pad = pad;
    }

    @Override
    public List<Leerling> findAll() {
        try (var stream = Files.lines(Path.of("/data/leerlingen.properties"))) {
            return stream.map(regel -> regel.split(":"))
                .map(onderdelen -> {
                    var onderdelenNaDubbelePunt = onderdelen[1].split(",");
                    return new Leerling(
                            Integer.parseInt(onderdelen[0]),
                            onderdelenNaDubbelePunt[0],
                            onderdelenNaDubbelePunt[1]);
            }).toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new RepositoryException(ex);
        }
    }
}
