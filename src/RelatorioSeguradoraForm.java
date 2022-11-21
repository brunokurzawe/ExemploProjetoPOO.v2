import model.Seguradora;
import relatorio.TableSeguradora;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

public class RelatorioSeguradoraForm extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Nome", "Site", "Endereço", ""};

    protected JTable table;
    protected JScrollPane scroller;
    protected TableSeguradora tabela;

    public RelatorioSeguradoraForm(Vector<Seguradora> vetorDados) {
        iniciarComponentes(vetorDados);
    }

    public void iniciarComponentes(Vector<Seguradora> vetorDados) {
        tabela = new TableSeguradora(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(TableSeguradora.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public static void emitirRelatorio(List<Seguradora> pessoas) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatorio");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    frame.setVisible(false);
                    AppMain.chamaMenuRelatorios();
                }
            });
            Vector<Seguradora> vetorDados = new Vector<Seguradora>();
            for (Seguradora pessoa : pessoas) {
                vetorDados.add(pessoa);
            }

            frame.getContentPane().add(new RelatorioSeguradoraForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}