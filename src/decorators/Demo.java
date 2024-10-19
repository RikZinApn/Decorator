package decorators;

public class Demo {
    public static void main(String[] args) {
        // Dados de exemplo que ser�o escritos (nome e sal�rio)
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";

        // Cria uma cadeia de decoradores para compress�o e criptografia
        // Primeiro aplica criptografia, depois compress�o, e escreve em um arquivo
        DataSourceDecorator encoded = new CompressionDecorator(
                                         new EncryptionDecorator(
                                             new FileDataSource("out/OutputDemo.txt")));
        
        // Escreve os dados comprimidos e criptografados no arquivo
        encoded.writeData(salaryRecords);

        // Cria uma inst�ncia simples de FileDataSource para ler o conte�do diretamente do arquivo
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
