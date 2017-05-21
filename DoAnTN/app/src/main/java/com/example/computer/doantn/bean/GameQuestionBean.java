package com.example.computer.doantn.bean;

/**
 * Created by Computer on 5/9/2017.
 */

public class GameQuestionBean {
    private String soThuNhat;
    private String soThuHai;
    private String phepToan;
    private String dauSoSanh;
    private String ketQua;

    public GameQuestionBean() {
    }

    public GameQuestionBean(String soThuNhat, String soThuHai, String phepToan, String dauSoSanh, String ketQua) {
        this.soThuNhat = soThuNhat;
        this.soThuHai = soThuHai;
        this.phepToan = phepToan;
        this.dauSoSanh = dauSoSanh;
        this.ketQua = ketQua;
    }

    public String getSoThuNhat() {
        return soThuNhat;
    }

    public void setSoThuNhat(String soThuNhat) {
        this.soThuNhat = soThuNhat;
    }

    public String getSoThuHai() {
        return soThuHai;
    }

    public void setSoThuHai(String soThuHai) {
        this.soThuHai = soThuHai;
    }

    public String getPhepToan() {
        return phepToan;
    }

    public void setPhepToan(String phepToan) {
        this.phepToan = phepToan;
    }

    public String getDauSoSanh() {
        return dauSoSanh;
    }

    public void setDauSoSanh(String dauSoSanh) {
        this.dauSoSanh = dauSoSanh;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }
}
