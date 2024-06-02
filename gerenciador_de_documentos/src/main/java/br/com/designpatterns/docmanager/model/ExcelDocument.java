package br.com.designpatterns.docmanager.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ExcelDocument extends Document {
    private Workbook workbook;

    public ExcelDocument(String filePath) {
        super(filePath);
    }

    @Override
    public void create() throws IOException {
        workbook = new XSSFWorkbook();
        workbook.createSheet("Sheet1");
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

    @Override
    public void open() throws IOException {
        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fileIn);
        }
    }

    @Override
    public void save() throws IOException {
        if (workbook == null) {
            throw new IllegalStateException("O documento não está aberto.");
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

    @Override
    public void manipulate(Scanner scanner) {
        System.out.println("Escolha a operação para a planilha:");
        System.out.println("1. Adicionar uma nova planilha");
        System.out.println("2. Adicionar um valor a uma célula");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        switch (choice) {
            case 1:
                System.out.println("Digite o nome da nova planilha:");
                String sheetName = scanner.nextLine();
                workbook.createSheet(sheetName);
                System.out.println("Nova planilha adicionada: " + sheetName);
                break;
            case 2:
                System.out.println("Digite o nome da planilha:");
                String existingSheetName = scanner.nextLine();
                Sheet sheet = workbook.getSheet(existingSheetName);
                if (sheet == null) {
                    System.out.println("Planilha não encontrada.");
                    return;
                }
                System.out.println("Digite o número da linha (começando em 0):");
                int rowNum = scanner.nextInt();
                System.out.println("Digite o número da coluna (começando em 0):");
                int colNum = scanner.nextInt();
                scanner.nextLine();  // Consume the newline
                System.out.println("Digite o valor a ser adicionado:");
                String cellValue = scanner.nextLine();
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    row = sheet.createRow(rowNum);
                }
                Cell cell = row.createCell(colNum);
                cell.setCellValue(cellValue);
                System.out.println("Valor adicionado à célula: " + cellValue);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}