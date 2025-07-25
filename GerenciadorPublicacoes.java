import java.util.*;
import java.io.*;

public class GerenciadorPublicacoes {
    private List<Publicacao> publicacoes;
    private final String arquivo = "publicacoes.dat";

    public GerenciadorPublicacoes() {
        publicacoes = carregarDados(); //carregar dados anteriores
    }

    public void adicionar(Publicacao p) {
        publicacoes.add(p);
        salvarDados();
    }

    public void remover(int index) {
        publicacoes.remove(index);
        salvarDados();
    }

    public void alterar(int index, Publicacao nova) {
        publicacoes.set(index, nova);
        salvarDados();
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }   //apresentar os dados na interface

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(publicacoes);
        } catch (IOException e) {
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
