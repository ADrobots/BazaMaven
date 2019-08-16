package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {


public List<String> readFile(Path path){
    try {
        return Files.readAllLines(path);
    }catch (IOException e){
        e.printStackTrace();
        return new ArrayList<>();
    }
}


}
