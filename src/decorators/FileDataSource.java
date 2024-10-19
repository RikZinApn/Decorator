package decorators;

import java.io.*;

public class FileDataSource implements DataSource {
    private String name;

    public FileDataSource(String name) {
        this.name = name;
    }

    @Override
    public void writeData(String data) {
        // Cria um objeto File associado ao nome do arquivo
        File file = new File(name);

        // Cria os diretórios pai caso eles não existam
        file.getParentFile().mkdirs();
        
        // Tenta abrir um FileOutputStream para escrever os dados no arquivo
        try (OutputStream fos = new FileOutputStream(file)) {
            // Escreve os dados (convertidos em bytes) no arquivo
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException ex) {
            // Caso ocorra um erro durante a escrita, a mensagem do erro será exibida
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String readData() {
        char[] buffer = null;
        File file = new File(name);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new String(buffer);
    }
}
