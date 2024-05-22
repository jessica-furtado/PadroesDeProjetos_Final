package br.com.designpatterns.docmanager.factory;

import br.com.designpatterns.docmanager.model.Document;

public interface DocumentManager {
    Document createDocument(String tipo);
    Document openDocument(String nome);
    void saveDocument(Document documento);
    void deleteDocument(String nome);
}
