package br.natanael.android.notificacoesfirebase.model;

public class NotificacaoDados {

    private String to;
    private Notificacao notification;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notificacao getNotification() {
        return notification;
    }

    public void setNotification(Notificacao notification) {
        this.notification = notification;
    }

    public NotificacaoDados() {
    }

    public NotificacaoDados(String to, Notificacao notification) {
        this.to = to;
        this.notification = notification;
    }
}
