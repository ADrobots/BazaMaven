import db.Company;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ParseCompany {
Map<String, Company> mapCompany=new HashMap<>();


    /*Map<String, Company> parce(List<String> list){
    String[] endLine=null;
    list.remove(0);
    for (String line:list){
        endLine=line.split(";");

        *//*System.out.println();                 //перебор значений из файла
        for (int i=0; i<endLine.length; i++){
            System.out.print(endLine[i]+" ");
        }*//*

        Company company=new Company();


        company.setCompanyId(Integer.parseInt(endLine[0]));
        company.setCompanyName(endLine[1]);
        company.setCompanyInn(Long.parseLong(endLine[2]));//перевели из String в Int
        company.setCompanyMailingAdress(endLine[3]);
        company.setCompanyEmail(endLine[4]);
        company.setCompanyNamber(endLine[5]);
        mapCompany.put(company.getCompanyName(), company);


        *//*for (Company map:mapCompany.values()) {                                       //перебор значений из hashmap
            System.out.print("id "+map.getCompanyId()+"\n");
            System.out.println("Наименование: "+map.getCompanyName()+" ");
            System.out.println("Инн: "+map.getCompanyInn()+" ");
            System.out.println("Почтовый адрес: "+map.getCompanyMailingAdress()+" ");
            System.out.println("Email: "+map.getCompanyEmail()+" ");
            System.out.println("Телефон: "+map.getCompanyNamber()+" ");
        }*//*
    }




    return null;
}*/


    //Apache POI 3.11;









}
