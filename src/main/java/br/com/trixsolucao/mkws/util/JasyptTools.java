package br.com.trixsolucao.mkws.util;

import org.jasypt.util.text.BasicTextEncryptor;
/*
*  Classe ainda não usada
* */


public class JasyptTools {

    private static BasicTextEncryptor bte;
    private static final String CODIFICACAO = "ESSA SENHA É UM SEGREDO, SABEMOS QUE NÃO DEVERIA ESTAR NO CÓDIGO";


    public static String decode(String pass) {
        bte = new BasicTextEncryptor();
        bte.setPassword(CODIFICACAO);
        return bte.decrypt(pass);

    }

    public static String encode(String pass) {
        bte = new BasicTextEncryptor();
        bte.setPassword(CODIFICACAO);
        return bte.encrypt(pass);
    }
}
