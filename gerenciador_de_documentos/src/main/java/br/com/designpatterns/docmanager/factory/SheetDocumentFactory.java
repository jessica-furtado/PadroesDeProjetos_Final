package br.com.designpatterns.docmanager.factory;

import br.com.designpatterns.docmanager.model.Document;

public class SheetDocumentFactory implements DocumentManager{

    @Override
    public Document createDocument(String tipo) {
        return null;
    }

    @Override
    public Document openDocument(String nome) {
        return null;
    }

    @Override
    public void saveDocument(Document documento) {

    }

    @Override
    public void deleteDocument(String nome) {

    }
}
