package ch.hackathon.recipe.billscanning;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageInterface {


    public interface StorageService {

        void init();

        void store(MultipartFile file);

        Stream<Path> loadAll();

        Path load(String filename);

        Resource loadAsResource(String filename);

        void deleteAll();

    }
}
