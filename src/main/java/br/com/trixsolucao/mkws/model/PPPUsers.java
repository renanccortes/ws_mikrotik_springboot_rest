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


import br.com.trixsolucao.mkws.mkapi.ConnectionBean;
import br.com.trixsolucao.mkws.model.mapping.MkMapping;

/**
 * @author Renan
 */
public class PPPUsers   {

    @MkMapping(from = ".id")
    private String id;
    @MkMapping(from = "service")
    private String service;
    @MkMapping(from = "name")
    private String user;
    @MkMapping(from = "password")
    private String password;
    @MkMapping(from = "address")
    private String ipAddress;
    @MkMapping(from = "caller-id")
    private String macAddress;
    @MkMapping(from = "profile")
    private String profile;

    @MkMapping(from = "routes")
    private String routes;

    @MkMapping(from = "limit-bytes-in")
    private String limitIn;

    @MkMapping(from = "limit-bytes-out")
    private String limitOut;

    @MkMapping(from = "last-logged-out")
    private Boolean lastLogout;

    private String newUser;

    @MkMapping(from = "comment")
    private String comment;

    private ConnectionBean connectionBean;


    /**
     * Default constructor, do nothing...
     */
    public PPPUsers() {
        // do nothing
    }

    public ConnectionBean getConnectionBean() {
        return connectionBean;
    }

    public void setConnectionBean(ConnectionBean connectionBean) {
        this.connectionBean = connectionBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }

    public String getLimitIn() {
        return limitIn;
    }

    public void setLimitIn(String limitIn) {
        this.limitIn = limitIn;
    }

    public String getLimitOut() {
        return limitOut;
    }

    public void setLimitOut(String limitOut) {
        this.limitOut = limitOut;
    }

    public Boolean isLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(Boolean lastLogout) {
        this.lastLogout = lastLogout;
    }

    public String getNewUser() {
        if (newUser == null || newUser.equals("")) {
            newUser = user;
        }

        return newUser;
    }

    public void setNewUser(String newUser) {
        this.newUser = newUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
