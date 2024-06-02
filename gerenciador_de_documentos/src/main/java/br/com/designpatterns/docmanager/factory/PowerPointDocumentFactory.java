package br.com.designpatterns.docmanager.factory;


import br.com.designpatterns.docmanager.model.Document;
import br.com.designpatterns.docmanager.model.PowerPointDocument;

public class PowerPointDocumentFactory implements DocumentFactory {
    @Override
    public Document createDocument(String filePath) {
        return new PowerPointDocument(filePath);
    }
}