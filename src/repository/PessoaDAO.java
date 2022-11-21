package repository;

import model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public final class PessoaDAO implements IGenericDAO<Pessoa> {

    static List<Pessoa> pessoas = new ArrayList<>();


    @Override
    public void salvar(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            pessoa.setId((long) (pessoas.size() + 1));
        } else {
            pessoas.remove((int) (pessoa.getId() - 1));
        }
        pessoas.add(pessoa);
    }

    @Override
    public void remover(Pessoa pessoa) {
        if (pessoa.getId() != null) {
            pessoas.remove((int) (pessoa.getId() - 1));
        }
    }

    @Override
    public List<Pessoa> buscarTodos() {
        System.out.println(pessoas);
        return pessoas;
    }

    @Override
    public List<Pessoa> buscarPorNome(String nome) {
        List<Pessoa> pessoasFiltradas = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().contains(nome)) {
                pessoasFiltradas.add(pessoa);
            }
        }
        return pessoasFiltradas;
    }

    public Object[] findPessoasInArray() {
        List<Pessoa> pessoas = buscarTodos();
        List<String> pessoasNomes = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            pessoasNomes.add(pessoa.getNome());
        }

        return pessoasNomes.toArray();
    }

}
