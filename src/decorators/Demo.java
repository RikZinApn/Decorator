package decorators;

public class Demo {
    public static void main(String[] args) {
        // Dados de exemplo que serão escritos (nome e salário)
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";

        // Cria uma cadeia de decoradores para compressão e criptografia
        // Primeiro aplica criptografia, depois compressão, e escreve em um arquivo
        DataSourceDecorator encoded = new CompressionDecorator(
                                         new EncryptionDecorator(
                                             new FileDataSource("out/OutputDemo.txt")));
        
        // Escreve os dados comprimidos e criptografados no arquivo
        encoded.writeData(salaryRecords);

        // Cria uma instância simples de FileDataSource para ler o conteúdo diretamente do arquivo
        DataSource plain = new FileDataSource("out/OutputDemo.txt");

        // Imprime os dados de entrada originais
        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);

        // Imprime os dados codificados (comprimidos e criptografados) lidos diretamente do arquivo
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());

        // Imprime os dados decodificados (descomprimidos e descriptografados)
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
