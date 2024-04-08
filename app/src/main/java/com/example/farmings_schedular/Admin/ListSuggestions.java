package com.example.farmings_schedular.Admin;

public class ListSuggestions {

    private final String c_id,disease,precaution;
    private final long id;

    public ListSuggestions(String c_id, String disease, String precaution, long id) {
        this.c_id = c_id;
        this.disease = disease;
        this.precaution = precaution;
        this.id = id;
    }

    public String getC_id() {
        return c_id;
    }

    public String getDisease() {
        return disease;
    }

    public String getPrecaution() {
        return precaution;
    }

    public long getId() {
        return id;
    }
}
