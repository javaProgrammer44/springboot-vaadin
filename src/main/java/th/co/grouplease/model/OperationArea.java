package th.co.grouplease.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class OperationArea implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Operation Code is required")
    private String provinceArea;


    @Column(name = "dlt_charge")
    @NotNull(message = "DLT charge is required")
    private Double dltCharge;

    @Column(name = "wage")
    @NotNull(message = "DLT charge is required")
    private Double wage;



    public OperationArea() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceArea() {
        return provinceArea;
    }

    public void setProvinceArea(String provinceArea) {
        this.provinceArea = provinceArea;
    }

    public Double getDltCharge() {
        return dltCharge;
    }

    public void setDltCharge(Double dltCharge) {
        this.dltCharge = dltCharge;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationArea that = (OperationArea) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OperationArea{" +
                "id=" + id +
                ", provinceArea='" + provinceArea + '\'' +
                ", dltCharge=" + dltCharge +
                ", wage=" + wage +
                '}';
    }
}
