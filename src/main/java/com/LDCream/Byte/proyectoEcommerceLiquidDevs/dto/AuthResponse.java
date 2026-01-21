package com.LDCream.Byte.proyectoEcommerceLiquidDevs.dto;

public class AuthResponse {
    private boolean success;
    private String mensaje;
    private UsuarioDTO usuario;

    public AuthResponse() {}

    public AuthResponse(boolean success, String mensaje, UsuarioDTO usuario) {
        this.success = success;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}
