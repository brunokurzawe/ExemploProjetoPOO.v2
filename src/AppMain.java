import model.*;
import repository.PessoaDAO;
import repository.SeguradoraDAO;
import repository.SeguroDAO;
import repository.UsuarioDAO;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AppMain {
    public static void main(String[] args) {
        Object usuarioLogado = chamaSelecaoUsuario();
        checaSenhaUsuario(usuarioLogado);
    }

    private static void chamaMenuCadastros() {
        String[] opcoesMenuCadastro = {"Pessoa", "Seguradora", "Seguro", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //Pessoa
                Pessoa pessoa = chamaCadastroPessoa();
                if (pessoa != null) getPessoaDAO().salvar(pessoa);
                chamaMenuCadastros();
                break;
            case 1: //Seguradoras
                Seguradora seguradora = chamaCadastroSeguradora();
                if (seguradora != null) getSeguradoraDAO().salvar(seguradora);
                chamaMenuCadastros();
                break;
            case 2: //Seguro
                Seguro seguro = chamaCadastroSeguro();
                if (seguro != null) getSeguroDAO().salvar(seguro);
                chamaMenuCadastros();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static Pessoa chamaCadastroPessoa() {
        Integer opcaoCrud = chamaOpcaoCrud();
        Pessoa pessoa;
        switch (opcaoCrud) {
            case 0: //Inserção
                pessoa = cadastraPessoa();
                break;
            case 1: //Alteração
                pessoa = selecaoDePessoa();
                pessoa = editaPessoa(pessoa);
                break;
            default: //Exclusão
                pessoa = selecaoDePessoa();
                getPessoaDAO().remover(pessoa);
                pessoa = null;
                break;
        }
        return pessoa;
    }

    private static Pessoa cadastraPessoa() {
        String[] opcaoPessoas = {"Fisica", "Juridica"};
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa: ");
        int tipoPessoa = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Tipo Pessoa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoPessoas, opcaoPessoas[0]);
        String tipoDocumento = getTipoDocumento(tipoPessoa);
        String documento = JOptionPane.showInputDialog(null, "Digite o " + tipoDocumento + " da pessoa: ");

        if (tipoDocumento.equals("CPF")) {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setTipo(TipoPessoa.FISICA);
            pessoaFisica.setNome(nome);
            pessoaFisica.setCpf(documento);
            return pessoaFisica;
        } else {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setTipo(TipoPessoa.JURIDICA);
            pessoaJuridica.setNome(nome);
            pessoaJuridica.setCnpj(documento);
            return pessoaJuridica;
        }
    }

    private static String getTipoDocumento(int tipoPessoa) {
        String tipoDocumento = "CPF";
        if (tipoPessoa == 1) {
            tipoDocumento = "CNPJ";
        }
        return tipoDocumento;
    }

    private static Pessoa editaPessoa(Pessoa pessoa) {

        String[] opcaoPessoas = {"Fisica", "Juridica"};
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa: ", pessoa.getNome());
        int tipoPessoa = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Tipo Pessoa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoPessoas, pessoa.getTipo().equals(TipoPessoa.FISICA) ? opcaoPessoas[0] : opcaoPessoas[1]);
        String tipoDocumento = getTipoDocumento(tipoPessoa);
        String documento = JOptionPane.showInputDialog(null, "Digite o " + tipoDocumento + " da pessoa: ", pessoa.getDocumento());

        if (tipoDocumento.equals("CPF")) {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setTipo(TipoPessoa.FISICA);
            pessoaFisica.setNome(nome);
            pessoaFisica.setCpf(documento);
            pessoaFisica.setId(pessoa.getId());
            return pessoaFisica;
        } else {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setTipo(TipoPessoa.JURIDICA);
            pessoaJuridica.setNome(nome);
            pessoaJuridica.setCnpj(documento);
            pessoaJuridica.setId(pessoa.getId());
            return pessoaJuridica;
        }
    }

    private static Integer chamaOpcaoCrud() {
        String[] opcao = {"Inserção", "Alteração", "Exclusão"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Operação no cadastro: ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
        return tipoOpcao;
    }

    private static Seguradora chamaCadastroSeguradora() {
        Integer opcaoCrud = chamaOpcaoCrud();
        Seguradora seguradora = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                seguradora = cadastroSeguradora();
                break;
            case 1: //Alteração
                seguradora = selecaoDeSeguradora();
                seguradora = editaSeguradora(seguradora);
                break;
            default: //Exclusão
                seguradora = selecaoDeSeguradora();
                getSeguradoraDAO().remover(seguradora);
                seguradora = null;
                break;
        }
        return seguradora;
    }

    private static Seguradora cadastroSeguradora() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da seguradora: ");
        String endereco = JOptionPane.showInputDialog(null, "Digite o endereço da seguradora: ");
        String site = JOptionPane.showInputDialog(null, "Digite o site da seguradora: ");

        Seguradora seguradora = new Seguradora();
        seguradora.setNome(nome);
        seguradora.setEndereco(endereco);
        seguradora.setSite(site);

        return seguradora;

    }

    private static Seguradora editaSeguradora(Seguradora seguradoraEdit) {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da seguradora: ", seguradoraEdit.getNome());
        String endereco = JOptionPane.showInputDialog(null, "Digite o endereço da seguradora: ", seguradoraEdit.getEndereco());
        String site = JOptionPane.showInputDialog(null, "Digite o site da seguradora: ", seguradoraEdit.getSite());

        Seguradora seguradora = new Seguradora();
        seguradora.setNome(nome);
        seguradora.setEndereco(endereco);
        seguradora.setSite(site);
        seguradora.setId(seguradoraEdit.getId());

        return seguradora;

    }

    private static Seguradora selecaoDeSeguradora() {
        Object[] selectionValuesSeguradora = getSeguradoraDAO().findSeguradoraInArray();
        String initialSelectionSeguradora = (String) selectionValuesSeguradora[0];
        Object selectionSeguradora = JOptionPane.showInputDialog(null, "Selecione a seguradora?",
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValuesSeguradora, initialSelectionSeguradora);
        List<Seguradora> seguradoras = getSeguradoraDAO().buscarPorNome((String) selectionSeguradora);
        return seguradoras.get(0);
    }

    private static Pessoa selecaoDePessoa() {
        Object[] selectionValues = getPessoaDAO().findPessoasInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o cliente do seguro?",
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Pessoa> pessoas = getPessoaDAO().buscarPorNome((String) selection);
        return pessoas.get(0);
    }

    private static Seguro selecaoDeSeguro() {
        Object[] selectionValues = getSeguroDAO().findSegurosInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o seguro?",
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Seguro> seguros = getSeguroDAO().buscarPorNome((String) selection);
        return seguros.get(0);
    }

    private static Seguro chamaCadastroSeguro() {
        Integer opcaoCrud = chamaOpcaoCrud();
        Seguro seguro = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                seguro = cadastroSeguro();
                break;
            case 1: //Alteração
                seguro = selecaoDeSeguro();
                seguro = editaSeguro(seguro);
                break;
            default: //Exclusão
                seguro = selecaoDeSeguro();
                getSeguroDAO().remover(seguro);
                seguro = null;
                break;
        }
        return seguro;
    }

    private static Seguro cadastroSeguro() {
        Pessoa pessoa = selecaoDePessoa();

        Object[] selectionValuesSeguradora = getSeguradoraDAO().findSeguradoraInArray();
        String initialSelectionSeguradora = (String) selectionValuesSeguradora[0];
        Object selectionSeguradora = JOptionPane.showInputDialog(null, "Selecione a seguradora?",
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValuesSeguradora, initialSelectionSeguradora);
        List<Seguradora> seguradoras = getSeguradoraDAO().buscarPorNome((String) selectionSeguradora);


        String[] opcaoSeguro = {"Automotivo", "Residencial", "Vida"};
        int tipoSeguro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Tipo Pessoa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoSeguro, opcaoSeguro[0]);

        String valorApolice = JOptionPane.showInputDialog(null, "Digite o valor da apolice de seguro: ");
        String valorPremio = JOptionPane.showInputDialog(null, "Digite o valor do premio da apolice: ");

        Seguro seguro = new Seguro();
        seguro.setSegurado(pessoa);
        seguro.setTipo(TipoSeguro.getTipoById(tipoSeguro));
        seguro.setSeguradora(seguradoras.get(0));
        seguro.setValorApolice(BigDecimal.valueOf(Double.valueOf(valorApolice)));
        seguro.setValorPremio(BigDecimal.valueOf(Double.valueOf(valorPremio)));

        return seguro;

    }

    private static Seguro editaSeguro(Seguro seguroEdit) {
        Pessoa pessoa = selecaoDePessoa();

        Object[] selectionValuesSeguradora = getSeguradoraDAO().findSeguradoraInArray();
        String initialSelectionSeguradora = (String) selectionValuesSeguradora[0];
        Object selectionSeguradora = JOptionPane.showInputDialog(null, "Selecione a seguradora?",
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValuesSeguradora, initialSelectionSeguradora);
        List<Seguradora> seguradoras = getSeguradoraDAO().buscarPorNome((String) selectionSeguradora);


        String[] opcaoSeguro = {"Automotivo", "Residencial", "Vida"};
        int tipoSeguro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Tipo Pessoa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoSeguro, opcaoSeguro[0]);

        String valorApolice = JOptionPane.showInputDialog(null, "Digite o valor da apolice de seguro: ");
        String valorPremio = JOptionPane.showInputDialog(null, "Digite o valor do premio da apolice: ");

        Seguro seguro = new Seguro();
        seguro.setSegurado(pessoa);
        seguro.setTipo(TipoSeguro.getTipoById(tipoSeguro));
        seguro.setSeguradora(seguradoras.get(0));
        seguro.setValorApolice(BigDecimal.valueOf(Double.valueOf(valorApolice)));
        seguro.setValorPremio(BigDecimal.valueOf(Double.valueOf(valorPremio)));

        return seguro;

    }

    private static void chamaMenuProcessos() {
        String[] opcoesMenuProcesso = {"Gerar Sinistro", "Baixar Seguro", "Voltar"};
        int menu_processos = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Processos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuProcesso, opcoesMenuProcesso[0]);

        switch (menu_processos) {
            case 0: //Gerar Sinistro
                Seguro seguro = processarSinistro();
                getSeguroDAO().salvar(seguro);
                chamaMenuProcessos();
                break;
            case 1: //Baixar Seguro
                Seguro seguro2 = processarBaixa();
                getSeguroDAO().salvar(seguro2);
                chamaMenuProcessos();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static Seguro processarSinistro() {
        Object[] selectionValues = getSeguroDAO().findSegurosInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o seguro?",
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Seguro> seguros = getSeguroDAO().buscarPorNome((String) selection);
        String motivoSinistro = JOptionPane.showInputDialog(null, "Digite o motivo do sinistro: ");

        Seguro seguro = seguros.get(0);
        seguro.setMotivoSinistro(motivoSinistro);
        seguro.setDataSinistro(LocalDate.now());
        seguro.setTemSinistro(Boolean.TRUE);

        return seguro;
    }

    private static Seguro processarBaixa() {
        Object[] selectionValues = getSeguroDAO().findSegurosInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o seguro?",
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Seguro> seguros = getSeguroDAO().buscarPorNome((String) selection);
        String motivoSinistro = JOptionPane.showInputDialog(null, "Digite o motivo da baixa: ");

        Seguro seguro = seguros.get(0);
        seguro.setMotivoBaixa(motivoSinistro);
        seguro.setBaixado(Boolean.TRUE);

        return seguro;
    }

    public static void chamaMenuRelatorios() {
        String[] opcoesMenuProcesso = {"Pessoas", "Seguradoras", "Seguros", "Voltar"};
        int menu_processos = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Relatórios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuProcesso, opcoesMenuProcesso[0]);

        switch (menu_processos) {
            case 0: //Pessoa
                chamaRelatorioPessoa();
                break;
            case 1: //Seguradoras
                chamaRelatorioSeguradora();
                break;
            case 2: //Seguros
                chamaRelatorioSeguro();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaRelatorioPessoa() {
        List<Pessoa> pessoas = getPessoaDAO().buscarTodos();
        RelatorioPessoaForm.emitirRelatorio(pessoas);
    }

    private static void chamaRelatorioSeguradora() {
        List<Seguradora> seguradoras = getSeguradoraDAO().buscarTodos();
        RelatorioSeguradoraForm.emitirRelatorio(seguradoras);
    }

    private static void chamaRelatorioSeguro() {
        List<Seguro> seguros = getSeguroDAO().buscarTodos();
        RelatorioSeguroForm.emitirRelatorio(seguros);
    }

    private static void chamaMenuPrincipal() {
        String[] opcoesMenu = {"Cadastros", "Processos", "Relatorios", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        switch (opcao) {
            case 0: //Cadastros
                chamaMenuCadastros();
                break;
            case 1: //Processos
                chamaMenuProcessos();
                break;
            case 2: //Relatorios
                chamaMenuRelatorios();
                break;
            case 3: //SAIR
                System.exit(0);
                break;
        }
    }

    private static void checaSenhaUsuario(Object usuarioLogado) {
        String senhaDigitada = JOptionPane.showInputDialog(null,
                "Informe a senha do usuario (" + usuarioLogado + ")");
        Usuario usuarioByLogin = UsuarioDAO.findUsuarioByLogin((String) usuarioLogado);

        if (usuarioByLogin.getSenha().equals(senhaDigitada)) {
            chamaMenuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Senha incorreta!");
            checaSenhaUsuario(usuarioLogado);
        }
    }


    private static Object chamaSelecaoUsuario() {
        Object[] selectionValues = UsuarioDAO.findUsuariosSistemaInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o usuario?",
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        return selection;
    }

    public static PessoaDAO getPessoaDAO() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO;
    }

    public static SeguradoraDAO getSeguradoraDAO() {
        SeguradoraDAO seguradoraDAO = new SeguradoraDAO();
        return seguradoraDAO;
    }

    public static SeguroDAO getSeguroDAO() {
        SeguroDAO seguroDAO = new SeguroDAO();
        return seguroDAO;
    }
}