package be.vdab.leerling.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ExtendWith(SpringExtension.class)
@PropertySource("application.properties")
@Import(LesRepository.class)
public class LesRepositoryTest {

    private final LesRepository lesRepository;
    private final String pad;

    LesRepositoryTest(LesRepository lesRepository, @Value("${lessenCsvPad}") String pad) {
        this.lesRepository = lesRepository;
        this.pad = pad;
    }

    @Test
    void erZijnEvenveelLessenAlsRegels() throws IOException {
        try (var stream = Files.lines(Path.of(pad))) {
            assertThat(lesRepository.findAll().size()).isEqualTo(stream.count());
        }
    }

    @Test
    void deEersteLesBevatDeDataUitDeEersteRegel() throws IOException {
        try (var stream = Files.lines(Path.of(pad))) {
            stream.findFirst().ifPresent(regel -> {
                var eersteLes = lesRepository.findAll().get(0);
                assertThat(eersteLes.getCode() + "," + eersteLes.getNaam()).isEqualTo(regel);
            });
        }
    }
}
