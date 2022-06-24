package br.edu.univas.si7.leohenrique.model.support;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.edu.univas.si7.leohenrique.model.enums.PaymentStatus;

@Converter(autoApply = true)
public class PaymentConverter implements AttributeConverter<PaymentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentStatus paymentStatus) {
        return paymentStatus == null ? null : paymentStatus.getCode();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(Integer code) {
        return code == null ? null : PaymentStatus.toEnum(code);
    }
}