public enum Mes {

    JANEIRO("janeiro"),
    FEVEREIRO("fevereiro"),
    MARCO("marco"),
    ABRIL("abril"),
    MAIO("maio"),
    JUNHO("junho"),
    JULHO("julho"),
    AGOSTO("agosto"),
    SETEMBRO("setembro"),
    OUTUBRO("outubro"),
    NOVEMBRO("novembro"),
    DEZEMBRO("dezembro");

    private final String mesPorExtenso;

    Mes (String mes) {
        if (mes == null || mes.trim().isEmpty())
            throw new InvalidMonthException("O nome por extenso do mês não pode ser nulo ou vazio.");
        this.mesPorExtenso = mes;
    }

    public String getMesPorExtenso() {
        return mesPorExtenso;
    }
}