package com.curso.domains.enums;

public enum Completude {
    cem(0,"100% completado"),setentacinco(1,"75% completado"),cinquenta(2,"50% completado")
    ,vintecinco(3,"25% completado"),zero(4,"0% completado");

    private Integer id;
    private String situacao;

    Completude(Integer id, String situacao) {
        this.id = id;
        this.situacao = situacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public static Completude toEnum(Integer id){
        if(id==null) return null;
        for(Completude x : Completude.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Completude invalido");
    }
}
