/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Chamado {
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final IntegerProperty codigoEmpresa = new SimpleIntegerProperty();
    private final IntegerProperty codigoAtendente = new SimpleIntegerProperty();
    private final IntegerProperty codigoCliente = new SimpleIntegerProperty();
    private final StringProperty dataInicial = new SimpleStringProperty();
    private final StringProperty dataFinal = new SimpleStringProperty();
    private final StringProperty titulo = new SimpleStringProperty();
    private final StringProperty descricao = new SimpleStringProperty();
    private final StringProperty prioridade = new SimpleStringProperty();
    private final StringProperty resposta = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    
    public Chamado (){
        
    }
    
    public Chamado(Integer codigo ,Integer codigoEmpresa, Integer codigoAtendente, Integer codigoCliente, String dataInicial, String dataFinal, String titulo, String descricao, String prioridade, String resposta, String status){
        this.codigo.set(codigo);
        this.codigoEmpresa.set(codigoEmpresa);
        this.codigoAtendente.set(codigoAtendente);
        this.codigoCliente.set(codigoCliente);
        this.dataInicial.set(dataInicial);
        this.dataFinal.set(dataFinal);
        this.titulo.set(titulo);
        this.descricao.set(descricao);
        this.prioridade.set(prioridade);
        this.resposta.set(resposta);
        this.status.set(status);
    }
    
    public Chamado(Integer codigo ,Integer codigoEmpresa, Integer codigoCliente, String dataInicial,String titulo, String descricao, String prioridade){
        this.codigo.set(codigo);
        this.codigoEmpresa.set(codigoEmpresa);
        this.codigoCliente.set(codigoCliente);
        this.dataInicial.set(dataInicial);
        this.titulo.set(titulo);
        this.descricao.set(descricao);
        this.prioridade.set(prioridade);
    }
    
    public void setCodigo(Integer codigo){
        this.codigo.set(codigo);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigo(){
        return this.codigo.get();
    }
    public IntegerProperty codigoProperty(){
        return this.codigo;
    }
    
    public void setCodigoEmpresa(Integer codigoEmpresa){
        this.codigoEmpresa.set(codigoEmpresa);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigoEmpresa(){
        return this.codigoEmpresa.get();
    }
    public IntegerProperty codigoEmpresaProperty(){
        return this.codigoEmpresa;
    }
    
    public void setCodigoAtendente(Integer codigo){
        this.codigoAtendente.set(codigo);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigoAtendente(){
        return this.codigoAtendente.get();
    }
    public IntegerProperty codigoAtendenteProperty(){
        return this.codigoAtendente;
    }
    
    public void setCodigoCliente(Integer codigo){
        this.codigoCliente.set(codigo);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigoCliente(){
        return this.codigoCliente.get();
    }
    public IntegerProperty codigoClienteProperty(){
        return this.codigoCliente;
    }
    
    public void setDataInicial(String data){
        this.dataInicial.set(data);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getDataInicial(){
        return this.dataInicial.get();
    }
    public StringProperty dataInicialProperty(){
        return this.dataInicial;
    }
    
    public void setDataFinal(String data){
        this.dataFinal.set(data);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getDataFinal(){
        return this.dataFinal.get();
    }
    public StringProperty dataFinalProperty(){
        return this.dataFinal;
    }
    
    public void setTitulo(String titulo){
        this.titulo.set(titulo);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getTitulo(){
        return this.titulo.get();
    }
    public StringProperty tituloProperty(){
        return this.titulo;
    }
    
    public void setDescricao(String descricao){
        this.descricao.set(descricao);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getDescricao(){
        return this.descricao.get();
    }
    public StringProperty descricaoProperty(){
        return this.descricao;
    }
    
    public void setPrioridade(String prioridade){
        this.prioridade.set(prioridade);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getPrioridade(){
        return this.prioridade.get();
    }
    public StringProperty prioridadeProperty(){
        return this.prioridade;
    }
    
    public void setResposta(String resposta){
        this.resposta.set(resposta);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getResposta(){
        return this.resposta.get();
    }
    public StringProperty respostaProperty(){
        return this.resposta;
    }
    
    public void setStatus(String status){
        this.status.set(status);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getStatus(){
        return this.status.get();
    }
    public StringProperty StatusProperty(){
        return this.status;
    }
}
