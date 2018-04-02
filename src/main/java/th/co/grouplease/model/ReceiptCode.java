package th.co.grouplease.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class ReceiptCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Operation Code is required")
    private String code;


    @OneToMany(mappedBy = "receiptCode", cascade = CascadeType.ALL)
    private Set<Operation> operations;


    public ReceiptCode(@NotNull(message = "Operation Code is required") String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }
}
