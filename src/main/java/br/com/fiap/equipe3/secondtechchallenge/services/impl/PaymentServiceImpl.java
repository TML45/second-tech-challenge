package br.com.fiap.equipe3.secondtechchallenge.services.impl;

import br.com.fiap.equipe3.secondtechchallenge.models.Payment;
import br.com.fiap.equipe3.secondtechchallenge.services.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean Realize(Payment payment) {
        try {
            this.fakeDelay();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private void fakeDelay() throws InterruptedException {
        Random rand = new Random();
        int MAX_DELAY_IN_MILLISECONDS = 51;
        int fakeDelay = rand.nextInt(MAX_DELAY_IN_MILLISECONDS);
        TimeUnit.MILLISECONDS.sleep(fakeDelay);
    }
}
