package com.curso.domains.enums;

public enum FormaPagameno {

    CREDITO(0,"CREDITO"), DEBITO(1,"DEBITO");

    private Integer id;
    private String formaPagameno;

    FormaPagameno(Integer id, String formaPagameno) {
        this.id = id;
        this.formaPagameno = formaPagameno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormaPagameno() {
        return formaPagameno;
    }

    public void setFormaPagameno(String formaPagameno) {
        this.formaPagameno = formaPagameno;
    }

    public static FormaPagameno toEnum(Integer id){
        if(id==null) return  null;
        for (FormaPagameno x : FormaPagameno.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Pagamento Invalido");
    }
}
