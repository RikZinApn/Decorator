package decorators;

import java.util.Base64;

// Classe EncryptionDecorator que adiciona criptografia aos dados
public class EncryptionDecorator extends DataSourceDecorator {

    // Construtor que recebe a fonte de dados a ser decorada
    public EncryptionDecorator(DataSource source) {
        super(source); // Chama o construtor da classe base DataSourceDecorator
    }

    // Sobrescreve o método writeData para aplicar criptografia antes de escrever os dados
    @Override
    public void writeData(String data) {
        super.writeData(encode(data)); // Criptografa os dados antes de passá-los para a fonte
    }

    // Sobrescreve o método readData para aplicar a descriptografia após ler os dados
    @Override
    public String readData() {
        return decode(super.readData()); // Descriptografa os dados após lê-los da fonte
    }

    // Método privado que aplica uma criptografia simples aos dados
    private String encode(String data) {
        // Converte a string em um array de bytes
        byte[] result = data.getBytes();
        
        // Aplica uma criptografia simples, incrementando cada byte em 1
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        
        // Converte os dados criptografados para uma string codificada em Base64
        return Base64.getEncoder().encodeToString(result);
    }

    // Método privado que aplica a descriptografia correspondente
    private String decode(String data) {
        // Decodifica os dados de Base64 para bytes
        byte[] result = Base64.getDecoder().decode(data);
        
        // Aplica a descriptografia, decrementando cada byte em 1
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        
        // Converte os dados descriptografados de volta para uma string
        return new String(result);
    }
}
