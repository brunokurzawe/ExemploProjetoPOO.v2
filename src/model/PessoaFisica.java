package model;

public class PessoaFisica extends Pessoa{

    private String cpf;

    public PessoaFisica() {
        setTipo(TipoPessoa.FISICA);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getDocumento() {
        return this.getCpf();
    }
}
