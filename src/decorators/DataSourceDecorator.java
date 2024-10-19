package decorators;

// Classe abstrata DataSourceDecorator implementa a interface DataSource
// Ela serve como base para decoradores que v�o adicionar funcionalidades extras
public abstract class DataSourceDecorator implements DataSource {
    
    // Atributo wrappee � a inst�ncia de DataSource que est� sendo "decorada"
    private DataSource wrappee;

    // Construtor que recebe um objeto DataSource a ser decorado
    DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    // M�todo sobrescrito da interface DataSource para escrever dados
    // Ele delega a opera��o de escrita para o objeto DataSource decorado
    @Override
    public void writeData(String data) {
        wrappee.writeData(data); // Chama o m�todo writeData() do objeto decorado
    }

    // M�todo sobrescrito da interface DataSource para ler dados
    // Ele delega a opera��o de leitura para o objeto DataSource decorado
    @Override
    public String readData() {
        return wrappee.readData(); // Chama o m�todo readData() do objeto decorado
    }
}
