package com.app.fragments.ui.components;

public class Forms {
    private String title;
    private String field1;
    private String field2;
    private String field3;

    public Forms(String title, String field1, String field2, String field3) {
        this.title = title;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
