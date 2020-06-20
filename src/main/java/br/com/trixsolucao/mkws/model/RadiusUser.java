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
public class RadiusUser {
     
  
    @MkMapping(from = "username")
    private String user;
    @MkMapping(from = "password")
    private String password;
    @MkMapping(from = "shared-users")
    private String sharedUsers;
    @MkMapping(from = "actual-profile")
    private String profile;   
    
    @MkMapping(from = "customer")
    private String customer;
    
    @MkMapping(from = "first-name")
    private String nome;
    
    @MkMapping(from = "disabled")
    private String disabled;
    
    private String newUser;

    
    public String getUserPropert() {
        return "username";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPasswordPropert() {
        return "password";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getProfilePropert() {
        return "actual-profile";
    }

    public String getSharedUsers() {
        return sharedUsers;
    }

    public void setSharedUsers(String sharedUsers) {
        this.sharedUsers = sharedUsers;
    }
    
    public String getSharedUsersPropert() {
        return "shared-users";
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNewUser() {
        return newUser;
    }

    public void setNewUser(String newUser) {
        this.newUser = newUser;
    }
    
    public String getCustomerPropert() {
        return "customer";
    }

    public String getCustomer() {
        if(customer == null || customer.equals("")) customer = "admin";
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNomePropert() {
        return "first-name";
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisabledPropert() {
        return "disabled";
    }
    
    public String getDisabled() {
        return disabled;
    }
    
    public String getDisabledString() {
        if(disabled == null) return "";
        if(disabled.equals("false")) return "ATIVO";
        else return "BLOQUEADO";
      
    }
    
    public boolean isAtivo() {
        if(disabled.equals("false")) return true;
        else return false;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
    
    
    
    
    
}
