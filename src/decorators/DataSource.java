package decorators;

// Interface DataSource define as opera��es b�sicas de escrita e leitura de dados
public interface DataSource {
    
    // M�todo para escrever dados em uma fonte
    void writeData(String data);
    
    // M�todo para ler dados de uma fonte
    String readData();
}
