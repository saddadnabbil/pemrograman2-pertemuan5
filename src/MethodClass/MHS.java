/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MethodClass;

/**
 *
 * @author saddadnabbil
 */
public class MHS {
    private String nama, nim;
    private Float uts, uas;
    private double nilAkhir;
    private String grade;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public Float getUts() {
        return uts;
    }

    public void setUts(Float uts) {
        this.uts = uts;
    }

    public Float getUas() {
        return uas;
    }

    public void setUas(Float uas) {
        this.uas = uas;
    }
    
    public double getNilAkhir() {
        nilAkhir = (uts + uas) / 2;
        return nilAkhir;
    }

    public String getGrade() {
        if (nilAkhir < 50)
            grade = "E";
        else if (nilAkhir < 60)
            grade = "D";
        else if (nilAkhir < 70)
            grade = "C";
        else if (nilAkhir < 80)
            grade = "B";
        else
            grade = "A";
        return grade;
    }
}
