package com.example.empresasjava.models.RequestEntity;

import com.example.empresasjava.models.Exercise;
import com.example.empresasjava.models.Plans;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PlansRequest {


    private Long planId;

    private String planCode;
    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String name ;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String contractedDays;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String price;
    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String description;




    public static Plans toPlans(PlansRequest plan) {
        return new Plans(

                plan.planCode,
                plan.name,
                Integer.parseInt(plan.contractedDays),
                Double.parseDouble(plan.getPrice()),
                plan.description

        );
    }

}
