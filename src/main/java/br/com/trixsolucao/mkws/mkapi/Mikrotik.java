/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trixsolucao.mkws.mkapi;


import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;
import me.legrange.mikrotik.ResultListener;

import java.util.List;
import java.util.Map;
import javax.net.SocketFactory;

/**
 *
 * @author Renan
 */
public class Mikrotik {

    private static Mikrotik instance;

    private ApiConnection conexao;

    private Mikrotik() {

    }

    public static Mikrotik getInstance() {
        if (instance == null) {
            instance = new Mikrotik();
        }

        return instance;
    }

    public void onConectar(String host, String port, String usuario, String senha) throws MikrotikApiException {
        try {
            conexao = ApiConnection.connect(SocketFactory.getDefault(), host, Integer.parseInt(port), 2000);
            conexao.login(usuario, senha);
        } catch (MikrotikApiException ex) {
            throw ex;
        }
    }

    public void onConectar(ConnectionBean connectionBean) throws MikrotikApiException {
        try {
            conexao = ApiConnection.connect(SocketFactory.getDefault(), connectionBean.getHost(), Integer.parseInt(connectionBean.getPorta()), 2000);
            conexao.login(connectionBean.getUsuario(), connectionBean.getSenha());
        } catch (MikrotikApiException ex) {
            throw ex;
        }
    }
    
    
    @SuppressWarnings("empty-statement")
    public List<Map<String, String>> onEnviarComando(String comando) throws MikrotikApiException {
        if (conexao == null || !conexao.isConnected()) {
            throw new MikrotikApiException("Nâo conectado");
        }
        List<Map<String, String>> execute = conexao.execute(comando);
        return execute;
    }

 

    public String onEnviarComandoAssincrono(String comando, ResultListener listener) throws MikrotikApiException {
        String tag = conexao.execute("/interface/monitor-traffic where name=eth1_Internet  return rx-bits-per-second",
                listener
        );

        System.out.println(tag);

        return tag;
    }

}
