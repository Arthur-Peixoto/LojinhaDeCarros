package Utils;

import java.io.Serializable;

public class Carro implements Serializable{
    private String renavam;
    private String nome;
    private int categoria; //1= economico2=executivo3=executivo
    private int ano;
    private double preco;
    private int quant;


    public String toString(){
        return "Nome=" + nome + ", renavan=" + renavam + ", categoria=" + categoria + 
      ", ano=" + ano + ", preco=" + preco + ", quantidade disponivel=" + quant;
    }
    
    public Carro(String renavam, String nome, int categoria, int ano, double preco, int quant) {
        this.renavam = renavam;
        this.nome = nome;
        this.categoria = categoria;
        this.ano = ano;
        this.preco = preco;
        this.quant = quant;
    }

    public String getRenavam() {
        return renavam;
    }
    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getQuant() {
        return quant;
    }
    public void setQuant(int quant) {
        this.quant = quant;
    }


}
