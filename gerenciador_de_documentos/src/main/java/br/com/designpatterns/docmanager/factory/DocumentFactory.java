package br.com.designpatterns.docmanager.factory;

import br.com.designpatterns.docmanager.model.Document;

public interface DocumentFactory {
    Document createDocument(String filePath);
}
