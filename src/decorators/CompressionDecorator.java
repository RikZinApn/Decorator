package decorators;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

// Classe CompressionDecorator implementa o padr�o Decorator para compress�o de dados
public class CompressionDecorator extends DataSourceDecorator {
    // N�vel de compress�o padr�o (6) - valores entre 0 e 9 (0 = sem compress�o, 9 = compress�o m�xima)
    private int compLevel = 6;

    // Construtor que recebe a fonte de dados (decorada)
    public CompressionDecorator(DataSource source) {
        super(source); // Chama o construtor da classe DataSourceDecorator
    }

    // Retorna o n�vel atual de compress�o
    public int getCompressionLevel() {
        return compLevel;
    }

    // Define um novo n�vel de compress�o
    public void setCompressionLevel(int value) {
        compLevel = value;
    }

    // M�todo sobrescrito que escreve dados na fonte, com compress�o aplicada
    @Override
    public void writeData(String data) {
        super.writeData(compress(data)); // Compress�o aplicada antes da escrita
    }

    // M�todo sobrescrito que l� dados da fonte e os descomprime
    @Override
    public String readData() {
        return decompress(super.readData()); // Descompress�o aplicada ap�s a leitura
    }

    // M�todo privado que realiza a compress�o de uma string
    private String compress(String stringData) {
        byte[] data = stringData.getBytes(); // Converte a string para bytes
        try {
            // ByteArrayOutputStream para armazenar dados comprimidos temporariamente
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            
            // DeflaterOutputStream para comprimir os dados usando o n�vel de compress�o especificado
            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compLevel));
            
            dos.write(data); // Escreve os dados comprimidos no fluxo
            dos.close(); // Fecha o fluxo de compress�o
            bout.close(); // Fecha o ByteArrayOutputStream
            
            // Converte os dados comprimidos em uma string base64 para facilitar o armazenamento
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException ex) {
            return null; // Retorna null se ocorrer uma exce��o
        }
    }

    // M�todo privado que realiza a descompress�o de uma string
    private String decompress(String stringData) {
        // Decodifica os dados em base64 de volta para bytes
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            // ByteArrayInputStream para ler os dados comprimidos
            InputStream in = new ByteArrayInputStream(data);
            
            // InflaterInputStream para descomprimir os dados
            InflaterInputStream iin = new InflaterInputStream(in);
            
            // ByteArrayOutputStream para armazenar os dados descomprimidos
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            
            // L� os dados descomprimidos byte a byte
            int b;
            while ((b = iin.read()) != -1) {
                bout.write(b); // Escreve os dados no ByteArrayOutputStream
            }
            
            in.close(); // Fecha o fluxo de entrada
            iin.close(); // Fecha o InflaterInputStream
            bout.close(); // Fecha o ByteArrayOutputStream
            
            // Retorna os dados descomprimidos como uma string
            return new String(bout.toByteArray());
        } catch (IOException ex) {
            return null; // Retorna null se ocorrer uma exce��o
        }
    }
}
