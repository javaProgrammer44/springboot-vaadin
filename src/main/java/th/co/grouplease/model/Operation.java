package th.co.grouplease.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Operation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Operation Code is required")
    private String operationCode;


    @NotNull(message = "Operation Description is required")
    private String operationDescription;


    @NotNull(message = "Price is required")
    private Double price;


    @Column(name = "default_dlt_charge")
    @NotNull(message = "Default DLT charge is required")
    private Double defaultDltCharge;


    @Column(name = "default_wage")
    @NotNull(message = "Default Wage is required")
    private Double defaultWage;


    public Operation() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDefaultDltCharge() {
        return defaultDltCharge;
    }

    public void setDefaultDltCharge(Double defaultDltCharge) {
        this.defaultDltCharge = defaultDltCharge;
    }

    public Double getDefaultWage() {
        return defaultWage;
    }

    public void setDefaultWage(Double defaultWage) {
        this.defaultWage = defaultWage;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", operationCode='" + operationCode + '\'' +
                ", operationDescription='" + operationDescription + '\'' +
                ", price=" + price +
                ", defaultDltCharge=" + defaultDltCharge +
                ", defaultWage=" + defaultWage +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return id == operation.id &&
                Objects.equals(operationCode, operation.operationCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, operationCode);
    }
}
