import br.com.designpatterns.docmanager.factory.DocumentFactory;
import br.com.designpatterns.docmanager.factory.ExcelDocumentFactory;
import br.com.designpatterns.docmanager.factory.PowerPointDocumentFactory;
import br.com.designpatterns.docmanager.factory.WordDocumentFactory;
import br.com.designpatterns.docmanager.model.Document;

import java.io.IOException;
import java.util.Scanner;

public class DocumentManager {
    private static final String BASE_PATH = "G:\\Meu Drive\\WORKSPACE\\PadroesDeProjetos_Final\\documents\\";
    private DocumentFactory factory;
    private Document document;

    public static void main(String[] args) {
        DocumentManager manager = new DocumentManager();
        manager.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Selecione o tipo de documento:");
            System.out.println("1. Planilha (.xlsx)");
            System.out.println("2. Texto (.docx)");
            System.out.println("3. Apresentação (.pptx)");
            System.out.println("4. Sair");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    factory = new ExcelDocumentFactory();
                    break;
                case 2:
                    factory = new WordDocumentFactory();
                    break;
                case 3:
                    factory = new PowerPointDocumentFactory();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            System.out.println("Digite o nome do arquivo (sem extensão):");
            String fileName = scanner.nextLine();
            String filePath = BASE_PATH + fileName + getExtension(choice);

            document = factory.createDocument(filePath);

            while (true) {
                System.out.println("Selecione a operação:");
                System.out.println("1. Criar novo documento");
                System.out.println("2. Abrir documento existente");
                System.out.println("3. Salvar documento");
                System.out.println("4. Manipular documento");
                System.out.println("5. Voltar ao menu principal");
                int operation = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                try {
                    switch (operation) {
                        case 1:
                            document.create();
                            System.out.println("Documento criado!");
                            break;
                        case 2:
                            document.open();
                            System.out.println("Documento aberto!");
                            break;
                        case 3:
                            document.save();
                            System.out.println("Documento salvo!");
                            break;
                        case 4:
                            document.manipulate(scanner);
                            System.out.println("Documento editado!");
                            break;
                        case 5:
                            System.out.println("Saindo.");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao processar o documento.");
                }

                if (operation == 5) {
                    break;
                }
            }
        }
    }

    private String getExtension(int choice) {
        switch (choice) {
            case 1:
                return ".xlsx";
            case 2:
                return ".docx";
            case 3:
                return ".pptx";
            default:
                return "";
        }
    }
}