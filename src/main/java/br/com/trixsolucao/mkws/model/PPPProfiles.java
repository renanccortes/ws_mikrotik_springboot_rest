/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trixsolucao.mkws.model;


import br.com.trixsolucao.mkws.model.mapping.MkMapping;

/**
 *
 * @author Renan
 */
public class PPPProfiles {

    @MkMapping(from = "id")
    private String id;
    @MkMapping(from = "name")
    private String name;
    @MkMapping(from = "local-address")
    private String localAddress;
    @MkMapping(from = "remote-address")
    private String remoteAddress;
    @MkMapping(from = "use-mpls")
    private String useMpls;
    @MkMapping(from = "use-compression")
    private String useCompression;
    @MkMapping(from = "use-encryption")
    private String useEncryption;
    @MkMapping(from = "only-one")
    private String onlyOne;
    @MkMapping(from = "change-tcp-mss")
    private String changeTcpMss;
    @MkMapping(from = "use-upnp")
    private String useUpnp;
    @MkMapping(from = "address-list")
    private String addressList;
    @MkMapping(from = "dns-server")
    private String dnsServcer;
    @MkMapping(from = "on-up")
    private String onUp;
    @MkMapping(from = "on-down")
    private String onDown;
    @MkMapping(from = "rate-limit")
    private String rateLimit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getUseMpls() {
        return useMpls;
    }

    public void setUseMpls(String useMpls) {
        this.useMpls = useMpls;
    }

    public String getUseCompression() {
        return useCompression;
    }

    public void setUseCompression(String useCompression) {
        this.useCompression = useCompression;
    }

    public String getUseEncryption() {
        return useEncryption;
    }

    public void setUseEncryption(String useEncryption) {
        this.useEncryption = useEncryption;
    }

    public String getOnlyOne() {
        return onlyOne;
    }

    public void setOnlyOne(String onlyOne) {
        this.onlyOne = onlyOne;
    }

    public String getChangeTcpMss() {
        return changeTcpMss;
    }

    public void setChangeTcpMss(String changeTcpMss) {
        this.changeTcpMss = changeTcpMss;
    }

    public String getUseUpnp() {
        return useUpnp;
    }

    public void setUseUpnp(String useUpnp) {
        this.useUpnp = useUpnp;
    }

    public String getAddressList() {
        return addressList;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList;
    }

    public String getDnsServcer() {
        return dnsServcer;
    }

    public void setDnsServcer(String dnsServcer) {
        this.dnsServcer = dnsServcer;
    }

    public String getOnUp() {
        return onUp;
    }

    public void setOnUp(String onUp) {
        this.onUp = onUp;
    }

    public String getOnDown() {
        return onDown;
    }

    public void setOnDown(String onDown) {
        this.onDown = onDown;
    }

    public String getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(String rateLimit) {
        this.rateLimit = rateLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
