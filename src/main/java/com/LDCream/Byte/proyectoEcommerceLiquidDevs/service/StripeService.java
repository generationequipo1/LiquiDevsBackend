package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.dto.PagoRequest;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.dto.RespuestaStripe;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.secret}")
    private String stripeKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    public RespuestaStripe checkoutProducts(PagoRequest pagoRequest) {

        try {
            // Crear el producto y el precio
            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName(pagoRequest.getName())
                            .build();

            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency(pagoRequest.getCurrency() != null ? pagoRequest.getCurrency() : "usd")
                            .setUnitAmount(pagoRequest.getAmount())  // en centavos
                            .setProductData(productData)
                            .build();

            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams.LineItem.builder()
                            .setPriceData(priceData)
                            .setQuantity(pagoRequest.getQuantity())
                            .build();

            // Crear la sesión de pago
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("http://localhost:8080/success")
                            .setCancelUrl("http://localhost:8080/cancel")
                            .addLineItem(lineItem)
                            .build();

            Session session = Session.create(params);

            // Construir respuesta
            RespuestaStripe respuesta = new RespuestaStripe();
            respuesta.setStatus("SUCCESS");
            respuesta.setMessage("Payment session created");
            respuesta.setSessionId(session.getId());
            respuesta.setSessionUrl(session.getUrl());
            return respuesta;

        } catch (StripeException e) {
            e.printStackTrace();
            RespuestaStripe respuestaError = new RespuestaStripe();
            respuestaError.setStatus("FAILED");
            respuestaError.setMessage("Error al crear sesión de pago: " + e.getMessage());
            return respuestaError;
        }
    }
}
