import model.Pessoa;
import relatorio.TablePessoa;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

public class RelatorioPessoaForm extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Nome", "Tipo", "Documento", ""};

    protected JTable table;
    protected JScrollPane scroller;
    protected TablePessoa tabela;

    public RelatorioPessoaForm(Vector<Pessoa> vetorDados) {
        iniciarComponentes(vetorDados);
    }

    public void iniciarComponentes(Vector<Pessoa> vetorDados) {
        tabela = new TablePessoa(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(TablePessoa.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public static void emitirRelatorio(List<Pessoa> pessoas) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatorio");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                  frame.setVisible(false);
                  AppMain.chamaMenuRelatorios();
                }
            });
            Vector<Pessoa> vetorDados = new Vector<Pessoa>();
            for (Pessoa pessoa : pessoas) {
                vetorDados.add(pessoa);
            }

            frame.getContentPane().add(new RelatorioPessoaForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}