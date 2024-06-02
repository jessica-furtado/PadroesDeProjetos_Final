import br.com.designpatterns.docmanager.factory.DocumentFactory;
import br.com.designpatterns.docmanager.factory.ExcelDocumentFactory;
import br.com.designpatterns.docmanager.factory.PowerPointDocumentFactory;
import br.com.designpatterns.docmanager.factory.WordDocumentFactory;
import br.com.designpatterns.docmanager.model.Document;

import java.io.IOException;
import java.util.Scanner;

public class DocumentManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DocumentFactory factory = null;
        Document document = null;

        System.out.println("Escolha o tipo de documento:");
        System.out.println("1. Planilha (.xlsx)");
        System.out.println("2. Documento de Texto (.docx)");
        System.out.println("3. Apresentação (.pptx)");
        int documentTypeChoice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        System.out.println("Digite o caminho e nome do arquivo:");
        String filePath = scanner.nextLine();

        switch (documentTypeChoice) {
            case 1:
                factory = new ExcelDocumentFactory();
                break;
            case 2:
                factory = new WordDocumentFactory();
                break;
            case 3:
                factory = new PowerPointDocumentFactory();
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        document = factory.createDocument(filePath);

        while (true) {
            System.out.println("Escolha a operação:");
            System.out.println("1. Criar um novo documento");
            System.out.println("2. Abrir um documento existente");
            System.out.println("3. Salvar o documento");
            System.out.println("4. Manipular o documento");
            System.out.println("5. Sair");
            int operationChoice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            try {
                if (operationChoice == 1) {
                    document.create();
                    System.out.println("Documento criado com sucesso!");
                } else if (operationChoice == 2) {
                    document.open();
                    System.out.println("Documento aberto com sucesso!");
                } else if (operationChoice == 3) {
                    document.save();
                    System.out.println("Documento salvo com sucesso!");
                } else if (operationChoice == 4) {
                    document.manipulate(scanner);
                } else if (operationChoice == 5) {
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao processar o documento.");
            }
        }

        scanner.close();
    }
}
