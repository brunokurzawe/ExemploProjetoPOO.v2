package model;

public abstract class Pessoa extends Entity{


    private TipoPessoa tipo;
    private String nome;

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "tipo=" + tipo +
                ", nome='" + nome + '\'' +
                '}';
    }

    public String getDocumento() {
        return "";
    }
}
