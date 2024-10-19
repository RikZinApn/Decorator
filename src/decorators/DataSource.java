package decorators;

// Interface DataSource define as operações básicas de escrita e leitura de dados
public interface DataSource {
    
    // Método para escrever dados em uma fonte
    void writeData(String data);
    
    // Método para ler dados de uma fonte
    String readData();
}
