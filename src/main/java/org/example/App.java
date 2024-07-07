package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Transaction transaction = null;
        try (FileInputStream fis = new FileInputStream(new File("erfefr.xlsx"))) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = rows.next();
                int id = (int) row.getCell(0).getNumericCellValue();
                double amount = row.getCell(1).getNumericCellValue();
                FinancialRecord record = new FinancialRecord(id, amount);
                session.save(record);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}