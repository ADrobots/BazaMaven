package io;

import db.Company;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParseCompany {
/*Map<String, Company> mapCompany=new HashMap<>();*/
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
    public static Map<String, Company> mapCompany=new HashMap<>();

    public static Map<String, Company> parce(String fileName) throws Exception, IOException {

        String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workbook = null;

        inputStream = new FileInputStream(fileName);
        workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();

        if (it.hasNext()) it.next();//пропускаем шапку таблицы
        while (it.hasNext()) {

            Company company=new Company();
            Row row = it.next();
            company.setCompanyId((int)(row.getCell(0).getNumericCellValue()));
            company.setCompanyName(row.getCell(1).getStringCellValue());
            company.setCompanyInn((long)(row.getCell(2).getNumericCellValue()));
            company.setCompanyMailingAdress(row.getCell(3).getStringCellValue());
            company.setCompanyEmail(row.getCell(4).getStringCellValue());
            mapCompany.put(company.getCompanyName(), company);
            }

        for (Company map:mapCompany.values()) {                                       //перебор значений из hashmap
            System.out.print("id "+map.getCompanyId()+"\n");
            System.out.println("Наименование: "+map.getCompanyName()+" ");
            System.out.println("Инн: "+map.getCompanyInn()+" ");
            System.out.println("Почтовый адрес: "+map.getCompanyMailingAdress()+" ");
            System.out.println("Email: "+map.getCompanyEmail()+" ");
            System.out.println("Телефон: "+map.getCompanyNamber()+" ");
        }

        return mapCompany;
    }










}
