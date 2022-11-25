package repository;

import model.Pessoa;
import model.PessoaJuridica;
import model.TipoPessoa;

import java.util.ArrayList;
import java.util.List;

public final class PessoaDAO implements IGenericDAO<Pessoa> {

    static List<Pessoa> pessoas = new ArrayList<>();


    public void init(){
        if (pessoas.isEmpty()){
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setNome("Bruuno");
            pessoaJuridica.setCnpj("212123132");
            pessoaJuridica.setTipo(TipoPessoa.JURIDICA);

            PessoaJuridica pessoaJuridica2 = new PessoaJuridica();
            pessoaJuridica2.setNome("Bruuno");
            pessoaJuridica2.setCnpj("212123132");
            pessoaJuridica2.setTipo(TipoPessoa.JURIDICA);

            PessoaJuridica pessoaJuridica3 = new PessoaJuridica();
            pessoaJuridica3.setNome("Bruuno");
            pessoaJuridica3.setCnpj("212123132");
            pessoaJuridica3.setTipo(TipoPessoa.JURIDICA);

            pessoas.add(pessoaJuridica);
            pessoas.add(pessoaJuridica2);
            pessoas.add(pessoaJuridica3);

        }
    }

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
        init();
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
