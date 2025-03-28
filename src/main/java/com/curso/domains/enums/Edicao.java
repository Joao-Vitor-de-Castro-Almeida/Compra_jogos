package com.curso.domains.enums;

public enum Edicao {


    PADRAO(0,"ROLE_PADRAO"), DELUXE(1,"ROLE_DELUXE"),
    ULTIMATE(2,"ROLE_ULTIMATE");

    private Integer id;
    private String edicao;

    Edicao(Integer id, String edicao) {
        this.id = id;
        this.edicao = edicao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public static Edicao toEnum(Integer id){
        if(id==null) return  null;
        for (Edicao x : Edicao.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Edicao Invalido");
    }
}
