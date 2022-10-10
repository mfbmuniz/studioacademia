package com.example.empresasjava.enums;

import java.io.Serializable;


public enum MonthlyPaymentStatusEnum implements Serializable {
    PAGO("PAGO"),
    AGUARDANDO_PAGAMENTO("AGUARDANDO_PAGAMENTO"),
    ATRASADO("ATRASADO"),
    EM_ANALISE("EM_ANALISE"),
    NAO_RECEBIDO("NAO_RECEBIDO");

    private final String code;

    MonthlyPaymentStatusEnum(String code) {
        this.code = code;
    }

    public static MonthlyPaymentStatusEnum getByCd(String cd) {
        for(MonthlyPaymentStatusEnum e : values()) {
            if(e.code.equals(cd)) return e;
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}

