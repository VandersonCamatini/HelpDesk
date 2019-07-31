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


public class Atendente {

    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty email= new SimpleStringProperty();
    private final IntegerProperty identificador = new SimpleIntegerProperty();
    private final IntegerProperty telefone = new SimpleIntegerProperty();
    private final StringProperty login = new SimpleStringProperty();
    private final StringProperty senha = new SimpleStringProperty();
    
    public Atendente(){
        
    }
    
    public Atendente(Integer codigo, String nome, Integer identificador, Integer telefone, String login, String senha, String email){
        this.codigo.set(codigo);
        this.nome.set(nome);
        this.email.set(email);
        this.identificador.set(identificador);
        this.telefone.set(telefone);
        this.login.set(login);
        this.senha.set(senha);
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
    
    public void setNome(String nome){
        this.nome.set(nome);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getNome(){
        return this.nome.get();
    }
    public StringProperty nomeProperty(){
        return this.nome;
    }
    
    public void setEmail(String email){
        this.email.set(email);
    }
    public String getEmail(){
        return this.email.get();
    }
    public StringProperty emailProperty(){
        return this.email;
    }
    
    public void setIdentificador(Integer identificador){
        this.identificador.set(identificador);
    }
    public Integer getIdentificador(){
        return this.identificador.get();
    }
    public IntegerProperty identificadorProperty(){
        return this.identificador;
    }
    
    public void setTelefone(Integer telefone){
        this.telefone.set(telefone);
    }
    public Integer getTelefone(){
        return this.telefone.get();
    }
    public IntegerProperty telefoneProperty(){
        return this.telefone;
    }
    
    public void setLogin(String login){
        this.login.set(login);
    }
    public String getLogin(){
        return this.login.get();
    }
    public StringProperty loginProperty(){
        return this.login;
    }
    
    public void setSenha(String senha){
        this.senha.set(senha);
    }
    public String getSenha(){
        return this.senha.get();
    }
    public StringProperty senhaProperty(){
        return this.senha;
    }
    
}
