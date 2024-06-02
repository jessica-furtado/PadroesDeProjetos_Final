package br.com.designpatterns.docmanager.model;

import java.io.IOException;
import java.util.Scanner;

public abstract class Document {
    protected String filePath;

    public Document(String filePath) {
        this.filePath = filePath;
    }

    public abstract void create() throws IOException;
    public abstract void open() throws IOException;
    public abstract void save() throws IOException;

    // Operações de manipulação específicas para cada tipo de documento
    public abstract void manipulate(Scanner scanner);
}