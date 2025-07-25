import java.io.*;
import java.util.*;

public class GerenciadorPublicacoes {
    private List<Publicacao> publicacoes; //listas onde sao armazenadas as publicação em tempo de execucao
    private final String arquivo = "publicacoes.dat";

    public GerenciadorPublicacoes() {
        publicacoes = carregarDados(); //carregar dados anteriores
    }

    public void adicionar(Publicacao p) {
        publicacoes.add(p); // metodo da interface Listr
        salvarDados();
    }

    public void remover(int index) {
        publicacoes.remove(index);//metodo da interface list
        salvarDados();
    }

    public void alterar(int index, Publicacao nova) {
        publicacoes.set(index, nova); // set substitui o elemento da publicação no index selecionado
        salvarDados();
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }   //apresentar os dados na interface

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {  // oos objeto do tipo objectoutputstream
            oos.writeObject(publicacoes); //file abre para leitura , objetct traduz o arquivo , wrtie object receber o objeto (publicacao) e oos vai traduzir para byts
        } catch (IOException e) {  // tratamento de de exessoes para tratar erros e evitar o que programa pare de funcionar 
            e.printStackTrace();
        }
    }

    private List<Publicacao> carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Publicacao>) ois.readObject();  //lê o arquivo e reconstroi a lista de objetos
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();  //retorna lista vazia caso n exista o arquivo ou corrompido
        }
    }
}
