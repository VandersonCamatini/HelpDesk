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

/**
 *
 * @author Bileko
 */
public class Cliente {
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final IntegerProperty empresa = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty email= new SimpleStringProperty();
    private final IntegerProperty telefone = new SimpleIntegerProperty();
    private final StringProperty login = new SimpleStringProperty();
    private final StringProperty senha = new SimpleStringProperty();
    
    public Cliente(){
        
    }
    public Cliente(Integer codigo, String nome,Integer telefone, String login, String senha, String email, Integer empresa){
        this.codigo.set(codigo);
        this.nome.set(nome);
        this.email.set(email);
        this.telefone.set(telefone);
        this.login.set(login);
        this.senha.set(senha);
        this.empresa.set(empresa);
    }
    
    public void setEmpresa(Integer empresa){
        this.empresa.set(empresa);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getEmpresa(){
        return this.empresa.get();
    }
    public IntegerProperty empresaProperty(){
        return this.empresa;
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
