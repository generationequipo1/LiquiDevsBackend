package com.LDCream.Byte.proyectoEcommerceLiquidDevs.controller;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.dto.PagoRequest;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.dto.RespuestaStripe;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.service.StripeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final StripeService stripeService;

    public PagoController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/crear")
    public RespuestaStripe crearPago(@RequestBody PagoRequest pagoRequest) {
        return stripeService.checkoutProducts(pagoRequest);
    }
    @GetMapping("/test")
    public String test() {
        return "Servidor activo en /api/pagos";
    }
}
