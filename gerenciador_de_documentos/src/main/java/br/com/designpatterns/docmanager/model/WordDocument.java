package br.com.designpatterns.docmanager.model;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class WordDocument extends Document {
    private XWPFDocument document;

    public WordDocument(String filePath) {
        super(filePath);
    }

    @Override
    public void create() throws IOException {
        document = new XWPFDocument();
        document.createParagraph().createRun().setText("Texto padrão inicial");
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            document.write(fileOut);
        }
    }

    @Override
    public void open() throws IOException {
        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            document = new XWPFDocument(fileIn);
        }
    }

    @Override
    public void save() throws IOException {
        if (document == null) {
            throw new IllegalStateException("O documento não está aberto.");
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            document.write(fileOut);
        }
    }

    @Override
    public void manipulate(Scanner scanner) {
        System.out.println("Digite o texto do novo parágrafo:");
        String paragraphText = scanner.nextLine();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.createRun().setText(paragraphText);
        System.out.println("Novo parágrafo adicionado: " + paragraphText);
    }
}