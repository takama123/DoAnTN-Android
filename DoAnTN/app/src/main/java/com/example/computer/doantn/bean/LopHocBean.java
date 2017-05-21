package com.example.computer.doantn.bean;

/**
 * Created by Computer on 5/9/2017.
 */

public class LopHocBean {
    private int idLop;
    private String tenLop;

    public LopHocBean() {
    }

    public LopHocBean(int idLop, String tenLop) {
        this.idLop = idLop;
        this.tenLop = tenLop;
    }

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
