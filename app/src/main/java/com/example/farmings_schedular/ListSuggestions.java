package com.example.farmings_schedular;

public class ListSuggestions {

    private final String c_id,disease,precaution;

    public String getC_id() {
        return c_id;
    }

    public ListSuggestions(String c_id, String disease,String precaution) {
        this.c_id = c_id;
        this.disease = disease;
        this.precaution = precaution;
    }

    public String getPrecaution() {
        return precaution;
    }

    public String getDisease() {
        return disease;
    }
}
