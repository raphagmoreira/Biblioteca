package br.biblioteca.livros.business;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class util {
	public static String retornaMensagem(String chave, Object... params) {
        Locale locale = recuperaLocale();

        ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
        if (rb.containsKey(chave)) {
            if (params.length > 0) {
                return MessageFormat.format(rb.getString(chave), params);
            } else {
                return rb.getString(chave);
            }
        }

        return null;
    }
	
	public static Locale recuperaLocale() {
        return new Locale("pt", "BR");
    }
}
