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
public class RadiusProfile {
    
    @MkMapping(from= "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
