package com.softulp.app.inmobiliariagutierrezj.ui.login;

public class Visible {
    private boolean isVisible;
    private int imagen;
    private int tipoInput;
    private int cursorPosition;

    public Visible() {
    }

    public Visible(boolean isVisible, int imagen, int tipoInput, int cursorPosition) {
        this.isVisible = isVisible;
        this.imagen = imagen;
        this.tipoInput = tipoInput;
        this.cursorPosition = cursorPosition;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getTipoInput() {
        return tipoInput;
    }

    public void setTipoInput(int tipoInput) {
        this.tipoInput = tipoInput;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void setCursorPosition(int cursorPosition) {
        this.cursorPosition = cursorPosition;
    }
}
