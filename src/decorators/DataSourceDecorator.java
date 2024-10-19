package decorators;

// Classe abstrata DataSourceDecorator implementa a interface DataSource
// Ela serve como base para decoradores que vão adicionar funcionalidades extras
public abstract class DataSourceDecorator implements DataSource {
    
    // Atributo wrappee é a instância de DataSource que está sendo "decorada"
    private DataSource wrappee;

    // Construtor que recebe um objeto DataSource a ser decorado
    DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    // Método sobrescrito da interface DataSource para escrever dados
    // Ele delega a operação de escrita para o objeto DataSource decorado
    @Override
    public void writeData(String data) {
        wrappee.writeData(data); // Chama o método writeData() do objeto decorado
    }

    // Método sobrescrito da interface DataSource para ler dados
    // Ele delega a operação de leitura para o objeto DataSource decorado
    @Override
    public String readData() {
        return wrappee.readData(); // Chama o método readData() do objeto decorado
    }
}
