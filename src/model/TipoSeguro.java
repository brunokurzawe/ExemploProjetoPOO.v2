package model;

public enum TipoSeguro {

    AUTOMOTIVO,
    RESIDENCIAL,
    VIDA;

    public static TipoSeguro getTipoById(Integer opcao) {
        if (opcao == 1) {
            return AUTOMOTIVO;
        } else if (opcao == 2) {
            return RESIDENCIAL;
        } else {
            return VIDA;
        }
    }
}
