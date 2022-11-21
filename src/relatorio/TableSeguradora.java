package relatorio;

import model.Seguradora;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableSeguradora extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_NOME = 0;
    public static final int INDEX_SITE = 1;
    public static final int INDEX_ENDERECO = 2;
    public static final int INDEX_ESCONDIDO = 3;

    protected String[] nomeColunas;
    protected Vector<Seguradora> vetorDados;

    public TableSeguradora(String[] columnNames, Vector<Seguradora> vetorDados) {
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
        Seguradora registroPessoa = (Seguradora) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_NOME:
                return registroPessoa.getNome();
            case INDEX_SITE:
                return registroPessoa.getSite();
            case INDEX_ENDERECO:
                return registroPessoa.getEndereco();
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
