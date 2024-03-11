package br.com.fiap.equipe3.secondtechchallenge.services.impl;

import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.InvalidContractingHoursException;
import br.com.fiap.equipe3.secondtechchallenge.services.ParkingCalculatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ParkingCalculatorServiceImpl implements ParkingCalculatorService {

    @Value("${gov.parking.taxes}")
    private String parkingTaxes;

    @Value("${gov.parking.each.hour}")
    private String eachHour;

    @Value("${gov.parking.max.hours}")
    private String maxTimeToContract;

    @Override
    public BigDecimal calculate(Integer hours) {
        int DECIMAL_PLACES = 2;

        if (hours > Integer.parseInt(maxTimeToContract)) {
            throw new InvalidContractingHoursException("Contrato de horas excede limite máximo permitido.");
        }

        BigDecimal parkingTaxesValue = new BigDecimal(parkingTaxes);
        BigDecimal hourValue = new BigDecimal(eachHour);

        BigDecimal liquidValue = hourValue.multiply(BigDecimal.valueOf(hours));

        BigDecimal totalValue = liquidValue.multiply(parkingTaxesValue);

        return totalValue.setScale(DECIMAL_PLACES, RoundingMode.UP);
    }
}
