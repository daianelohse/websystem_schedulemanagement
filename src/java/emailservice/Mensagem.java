package emailservice;

public class Mensagem {
    private String destino;
    private String titulo;
    private String mensagem;

    public Mensagem() {
    }

    public Mensagem(String destino, String titulo, String mensagem) {
        this.destino = destino;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
    
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
}
