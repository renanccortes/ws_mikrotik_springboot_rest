package br.com.trixsolucao.mkws.model;

public enum TypesList {

    PPPOE_LIST("pppoe_list"),
    PPPOE_ACTIVES("pppoe_actives"),
    PPPOE_PLANS("pppoe_plans");

    private String type;


    TypesList(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
