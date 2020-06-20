/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trixsolucao.mkws.mkapi;

import me.legrange.mikrotik.ApiConnection;

/**
 *
 * @author Renan
 */
public class ConnectionBean {

    private String usuario;
    private String senha;
    private String porta;
    private String host;

    private Object objetoAcao;

    public ConnectionBean() {
        porta = ApiConnection.DEFAULT_PORT + "";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Object getObjetoAcao() {
        return objetoAcao;
    }

    public void setObjetoAcao(Object objetoAcao) {
        this.objetoAcao = objetoAcao;
    }

}
