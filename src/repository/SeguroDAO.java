package repository;

import model.Seguro;

import java.util.ArrayList;
import java.util.List;

public final class SeguroDAO implements IGenericDAO<Seguro> {

    static List<Seguro> seguros = new ArrayList<>();

    @Override
    public void salvar(Seguro seguro) {
        if (seguro.getId() == null) {
            seguro.setId((long) (seguros.size() + 1));
        } else {
            seguros.remove((int) (seguro.getId() - 1));
        }
        seguros.add(seguro);
    }

    @Override
    public void remover(Seguro seguro) {
        if (seguro.getId() != null) {
            seguros.remove((int) (seguro.getId() - 1));
        }
    }

    @Override
    public List<Seguro> buscarTodos() {
        System.out.println(seguros);
        return seguros;
    }

    @Override
    public List<Seguro> buscarPorNome(String nome) {
        List<Seguro> filtradas = new ArrayList<>();
        for (Seguro seguro : seguros) {
            if (seguro.getSegurado().getNome().contains(nome)) {
                filtradas.add(seguro);
            }
        }
        return filtradas;
    }

    public Object[] findSegurosInArray() {
        List<Seguro> seguros = buscarTodos();
        List<String> segurosNomes = new ArrayList<>();

        for (Seguro seguro : seguros) {
            segurosNomes.add(seguro.getSegurado().getNome());
        }

        return segurosNomes.toArray();
    }

}
