package model;

public class PessoaJuridica extends Pessoa{

    private String cnpj;

    public PessoaJuridica() {
        setTipo(TipoPessoa.JURIDICA);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getDocumento() {
        return this.getCnpj();
    }
}
