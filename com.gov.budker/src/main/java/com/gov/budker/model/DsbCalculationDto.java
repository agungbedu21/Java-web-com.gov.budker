package com.gov.budker.model;



public class DsbCalculationDto {
    private String tahun;
    private String kalkulasi;
    private Integer ikuTarget;
    private Double achTotal;
    private Double achIku;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKalkulasi() {
        return kalkulasi;
    }

    public void setKalkulasi(String kalkulasi) {
        this.kalkulasi = kalkulasi;
    }

    public Integer getIkuTarget() {
        return ikuTarget;
    }

    public void setIkuTarget(Integer ikuTarget) {
        this.ikuTarget = ikuTarget;
    }

    public Double getAchTotal() {
        return achTotal;
    }

    public void setAchTotal(Double achTotal) {
        this.achTotal = achTotal;
    }

    public Double getAchIku() {
        return achIku;
    }

    public void setAchIku(Double achIku) {
        this.achIku = achIku;
    }
}
