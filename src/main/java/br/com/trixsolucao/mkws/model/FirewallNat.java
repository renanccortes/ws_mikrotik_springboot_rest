package br.com.trixsolucao.mkws.model;

import br.com.trixsolucao.mkws.model.mapping.MkMapping;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FirewallNat {

    @MkMapping(from ="chain")
    private String chain;

    @MkMapping(from =".id")
    private String id;

    @JsonIgnore
    private String idAux;

    @MkMapping(from ="action")
    private String action;

    @MkMapping(from ="to-ports")
    private String toPorts;

    @MkMapping(from ="dst-port")
    private String dstPort;

    @MkMapping(from ="protocol")
    private String protocol;

    @MkMapping(from ="disabled")
    private String disabled;
    @MkMapping(from = "in-interface")
    private String inInterface;

    public String getIdAux() {
        return idAux;
    }

    public void setIdAux(String idAux) {
        this.idAux = idAux;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getToPorts() {
        return toPorts;
    }

    public void setToPorts(String toPorts) {
        this.toPorts = toPorts;
    }

    public String getDstPort() {
        return dstPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getInInterface() {
        return inInterface;
    }

    public void setInInterface(String inInterface) {
        this.inInterface = inInterface;
    }

    @Override
    public String toString() {
        return "FirewallNat{" +
                "chain='" + chain + '\'' +
                ", id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", toPorts='" + toPorts + '\'' +
                ", dstPort='" + dstPort + '\'' +
                ", protocol='" + protocol + '\'' +
                ", disabled='" + disabled + '\'' +
                ", inInterface='" + inInterface + '\'' +
                '}';
    }
}
