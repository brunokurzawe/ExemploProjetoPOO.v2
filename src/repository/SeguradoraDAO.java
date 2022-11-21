package repository;

import model.Seguradora;

import java.util.ArrayList;
import java.util.List;

public final class SeguradoraDAO implements IGenericDAO<Seguradora> {

    static List<Seguradora> seguradoras = new ArrayList<>();

    @Override
    public void salvar(Seguradora seguradora) {
        if (seguradora.getId() == null) {
            seguradora.setId((long) (seguradoras.size() + 1));
        } else {
            seguradoras.remove((int) (seguradora.getId() - 1));
        }
        seguradoras.add(seguradora);
    }

    @Override
    public void remover(Seguradora seguradora) {
        if (seguradora.getId() != null) {
            seguradoras.remove((int) (seguradora.getId() - 1));
        }
    }

    @Override
    public List<Seguradora> buscarTodos() {
        System.out.println(seguradoras);
        return seguradoras;
    }

    @Override
    public List<Seguradora> buscarPorNome(String nome) {
        List<Seguradora> filtradas = new ArrayList<>();
        for (Seguradora seguradora : seguradoras) {
            if (seguradora.getNome().contains(nome)) {
                filtradas.add(seguradora);
            }
        }
        return filtradas;
    }

    public Object[] findSeguradoraInArray() {
        List<Seguradora> seguradoras = buscarTodos();
        List<String> seguradorasNomes = new ArrayList<>();

        for (Seguradora seguradora : seguradoras) {
            seguradorasNomes.add(seguradora.getNome());
        }

        return seguradorasNomes.toArray();
    }

}
