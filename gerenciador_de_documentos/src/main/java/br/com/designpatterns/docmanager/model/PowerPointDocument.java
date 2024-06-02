package br.com.designpatterns.docmanager.model;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class PowerPointDocument extends Document {
    private XMLSlideShow slideShow;

    public PowerPointDocument(String filePath) {
        super(filePath);
    }

    @Override
    public void create() throws IOException {
        slideShow = new XMLSlideShow();
        XSLFSlide slide = slideShow.createSlide();
        XSLFTextBox textBox = slide.createTextBox();
        textBox.setText("Slide inicial padrão");
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            slideShow.write(fileOut);
        }
    }

    @Override
    public void open() throws IOException {
        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            slideShow = new XMLSlideShow(fileIn);
        }
    }

    @Override
    public void save() throws IOException {
        if (slideShow == null) {
            throw new IllegalStateException("O documento não está aberto.");
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            slideShow.write(fileOut);
        }
    }

    @Override
    public void manipulate(Scanner scanner) {
        System.out.println("Digite o texto do novo slide:");
        String slideText = scanner.nextLine();
        XSLFSlide slide = slideShow.createSlide();
        XSLFTextBox textBox = slide.createTextBox();
        textBox.setText(slideText);
        System.out.println("Novo slide adicionado com texto: " + slideText);
    }
}