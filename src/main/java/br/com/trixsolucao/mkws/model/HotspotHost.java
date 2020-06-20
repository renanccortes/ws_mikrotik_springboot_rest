/*
 * Mikrotik4J, Intregrate Java and Mikrotik RouterOS
 *
 * Copyright (c) 2012, Eits It Solutions and Arthur 
 * or third-party contributors as indicated by the @author tags or express 
 * copyright attribution statements applied by the authors.  All third-party 
 * contributions are distributed under license by Eits It Solutions.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package br.com.trixsolucao.mkws.model;


import br.com.trixsolucao.mkws.model.mapping.MkMapping;

/**
 * @author Renan
 */
public class HotspotHost {

    @MkMapping(from = "mac-address")
    private String macAddress;
    @MkMapping(from = "address")
    private String ipAddress;
    @MkMapping(from = "to-address")
    private String toAddress;
    @MkMapping(from = "server")
    private String server;
    @MkMapping(from = "uptime")
    private String uptime;
    @MkMapping(from = "found-by")
    private String foundBy;
    @MkMapping(from = "authorized")
    private boolean autorized;
    @MkMapping(from = "bypassed")
    private boolean bypassed;

    /**
     * Default constructor, do nothing...
     */
    public HotspotHost() {
        // do nothing
    }

    /**
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * @param macAddress the macAddress to set
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the toAddress
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * @param toAddress the toAddress to set
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return the uptime
     */
    public String getUptime() {
        return uptime;
    }

    /**
     * @param uptime the uptime to set
     */
    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    /**
     * @return the foundBy
     */
    public String getFoundBy() {
        return foundBy;
    }

    /**
     * @param foundBy the foundBy to set
     */
    public void setFoundBy(String foundBy) {
        this.foundBy = foundBy;
    }

    /**
     * @return the autorized
     */
    public boolean isAutorized() {
        return autorized;
    }

    /**
     * @param autorized the autorized to set
     */
    public void setAutorized(boolean autorized) {
        this.autorized = autorized;
    }

    /**
     * @return the bypassed
     */
    public boolean isBypassed() {
        return bypassed;
    }

    /**
     * @param bypassed the bypassed to set
     */
    public void setBypassed(boolean bypassed) {
        this.bypassed = bypassed;
    }
}
