package com.example.farmings_schedular.Admin;

public class Farmers {

    private final String un,pno,email;
    public String getUn() {
        return un;
    }

    public String getPno() {
        return pno;
    }

    public String getEmail() {
        return email;
    }

    public Farmers(String un, String pno, String email) {
        this.un = un;
        this.pno = pno;
        this.email = email;
    }


}
