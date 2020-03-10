package io;

import db.Company;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class WriteFile {

    private String path="C:/Users/work/Desktop/ДОКУМЕНТЫ/2019";
    public void writeFiles(String text, String company){
        try {
            Files.write(Paths.get(path + "/" + company + ".txt"), text.toString().getBytes());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
