package relatorio;

import model.Seguro;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableSeguro extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_NOME = 0;
    public static final int INDEX_SEGURADORA = 1;
    public static final int INDEX_TIPO = 2;
    public static final int INDEX_VALOR = 3;
    public static final int INDEX_PREMIO = 4;
    public static final int INDEX_ESCONDIDO = 5;

    protected String[] nomeColunas;
    protected Vector<Seguro> vetorDados;

    public TableSeguro(String[] columnNames, Vector<Seguro> vetorDados) {
        this.nomeColunas = columnNames;
        this.vetorDados = vetorDados;
    }

    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (coluna == INDEX_ESCONDIDO) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Seguro registroPessoa = (Seguro) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_NOME:
                return registroPessoa.getSegurado().getNome();
            case INDEX_SEGURADORA:
                return registroPessoa.getSeguradora().getNome();
            case INDEX_TIPO:
                return registroPessoa.getTipo();
            case INDEX_VALOR:
                return registroPessoa.getValorApolice();
            case INDEX_PREMIO:
                return registroPessoa.getValorPremio();
            default:
                return new Object();
        }
    }

    @Override
    public int getRowCount() {
        return vetorDados.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }
}
