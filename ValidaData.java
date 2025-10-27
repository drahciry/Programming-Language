// Importaçoes uteis ao programa
import java.time.LocalDate;

/**
 * Classe com metodos estaticos voltados para a validaçao de data, com metodos como:
 * Validar dia inteiro ou string, validar mes inteiro ou string, validar ano inteiro ou string,
 * validar uma data inteira (dia, mes e ano) com entradas inteiras ou strings.
 */
public class ValidaData {
    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Metodo estatico que recebe um mes como String e
     * converte para o valor correspondente em inteiro.
     * 
     * @param mes (String): Mes escrito por extenso.
     * 
     * @return int: Retorna o valor inteiro correspondente ao mes.
     */
    private static int converteMes(String mes) {
        String mesAuxiliar = mes.toLowerCase();
        Mes mesEnum = Mes.valueOf(mesAuxiliar);
        return 1;
    }

    /**
     * Metodo estatico que recebe um dia e verifica se eh um dia valido.
     * 
     * @param dia (int): Dia que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o dia validado.
     */
    public static boolean isDia(int dia) {
        if (dia < 1 || dia > 31) return false;
        return true;
    }

    /**
     * Metodo estatico que recebe um mes e verifica se eh um mes valido.
     * 
     * @param mes (int): Mes que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o mes validado.
     */
    public static boolean isMes(int mes) {
        if (mes < 1 || mes > 12) return false;
        return true;
    }

    /**
     * Metodo estatico que recebe um ano e verifica se eh um ano valido.
     * 
     * @param ano (int): Ano que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o ano validado.
     */
    public static boolean isAno(int ano) {
        LocalDate now = LocalDate.now();
        if (ano < (now.getYear() - 120) || ano > now.getYear()) return false;
        return true;
    }

    /**
     * Metodo estatico que recebe um dia e verifica se eh um dia valido.
     * 
     * @param dia (String): Dia que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o dia validado.
     */
    public static boolean isDia(String dia) {
        if (dia.length() > 2) return false;
        int diaInteiro;
        try {
            diaInteiro = Integer.parseInt(dia);
        } catch (NumberFormatException e) {
            return false;
        }
        if (diaInteiro < 1 || diaInteiro > 31) return false;
        return true;
    }

    /**
     * Metodo estatico que recebe um mes e verifica se eh um mes valido.
     * O mes pode ser no formato inteiro ou por extenso.
     * 
     * @param mes (String): Mes que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o mes validado.
     */
    public static boolean isMes(String mes) {
        int mesInteiro;
        try {
            mesInteiro = Integer.parseInt(mes);
        } catch (NumberFormatException e) {
            mesInteiro = converteMes(mes);
        }
        if (mesInteiro < 1 || mesInteiro > 12) return false;
        return true;
    }

    /**
     * Metodo estatico que recebe um ano e verifica se eh um ano valido.
     * 
     * @param ano (String): Ano que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o ano validado.
     */
    public static boolean isAno(String ano) {
        if (ano.length() != 4) return false;
        int anoInteiro;
        try {
            anoInteiro = Integer.parseInt(ano);
        } catch (NumberFormatException e) {
            return false;
        }
        LocalDate now = LocalDate.now();
        if (anoInteiro < (now.getYear() - 120) || anoInteiro > now.getYear()) return false;
        return true;
    }
}