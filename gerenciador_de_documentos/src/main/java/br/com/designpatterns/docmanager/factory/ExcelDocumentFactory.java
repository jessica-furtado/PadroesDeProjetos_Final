package br.com.designpatterns.docmanager.factory;


import br.com.designpatterns.docmanager.model.Document;
import br.com.designpatterns.docmanager.model.ExcelDocument;

public class ExcelDocumentFactory implements DocumentFactory {
    @Override
    public Document createDocument(String filePath) {
        return new ExcelDocument(filePath);
    }
}