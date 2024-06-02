package br.com.designpatterns.docmanager.factory;


import br.com.designpatterns.docmanager.model.Document;
import br.com.designpatterns.docmanager.model.WordDocument;

public class WordDocumentFactory implements DocumentFactory {
    @Override
    public Document createDocument(String filePath) {
        return new WordDocument(filePath);
    }
}
