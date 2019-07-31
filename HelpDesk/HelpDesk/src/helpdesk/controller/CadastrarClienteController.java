/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.controller;

import helpdesk.DAO.DAOFactory;
import helpdesk.HelpDesk;
import helpdesk.model.Atendente;
import helpdesk.model.Cliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Bileko
 */
public class CadastrarClienteController {

    @FXML
    private TextField tfCodigoDaEmpresa;
    @FXML
    private Button btnSalvar1;
    @FXML
    private TextField tfSenhaCliente;
    @FXML
    private TextField tfEmailCliente;
    @FXML
    private TextField tfTelefoneCliente;
    @FXML
    private TextField tfUsuarioCliente;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfNomeCliente;
    @FXML
    private Button btnCriarCliente;
    
    Cliente cliente = null;
    
    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
        try {
            Integer retorno = DAOFactory.getClienteDAO().procurarEmpresa(tfCodigoDaEmpresa.getText());
            if(retorno == null){
                Alert alerta = HelpDesk.criarAlerta("INFORMATION","O nome da empresa que você informou não existe, ou não esta cadastrada em nosso sistema.");
                alerta.showAndWait();
            }else{
                cliente.setEmpresa(retorno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
           unbindDataObject(cliente);
        try {
            if (cliente != null) {
                if (verificaFields() == false) {
                DAOFactory.getClienteDAO().save(cliente);
                cliente = null;
                Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Conta criada e salva, pode fechar a janela.");
                alerta.showAndWait();
                } else {
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos.");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Primeiro clique em Criar nova conta e preencha todos os campos.");
                    alerta.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }
    }


    @FXML
    private void btncCriarClienteOnAction(ActionEvent event) {
        cliente = new Cliente();
        bindDataObject(cliente);
        cleanDataObject(cliente);
    }
    
    private void bindDataObject(Cliente cliente) {
        if (cliente != null) {
            tfCodigo.textProperty().bind(cliente.codigoProperty().asString());
            tfNomeCliente.textProperty().bindBidirectional(cliente.nomeProperty());
            tfEmailCliente.textProperty().bindBidirectional(cliente.emailProperty());
            tfTelefoneCliente.textProperty().bindBidirectional(cliente.telefoneProperty(), new NumberStringConverter());
            tfUsuarioCliente.textProperty().bindBidirectional(cliente.loginProperty());
            tfSenhaCliente.textProperty().bindBidirectional(cliente.senhaProperty());
        }
    }

    private void unbindDataObject(Cliente Cliente) {
        if (cliente != null) {
            tfCodigo.textProperty().unbind();
            tfNomeCliente.textProperty().unbindBidirectional(Cliente.nomeProperty());
            tfEmailCliente.textProperty().unbindBidirectional(Cliente.emailProperty());
            tfTelefoneCliente.textProperty().unbindBidirectional(Cliente.telefoneProperty());
            tfUsuarioCliente.textProperty().unbindBidirectional(Cliente.loginProperty());
            tfSenhaCliente.textProperty().unbindBidirectional(Cliente.senhaProperty());
        }
    }
    
    private void cleanDataObject(Cliente cliente){
        tfTelefoneCliente.textProperty().set(null);
    }
    
        private Boolean verificaFields() {
        Boolean invalido = false;

        if (tfCodigoDaEmpresa.textProperty().isNull().get() == true) {
            tfCodigoDaEmpresa.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfCodigoDaEmpresa.getStyleClass().remove("inválido");
        }

        if (tfSenhaCliente.textProperty().isNull().get() == true) {
            tfSenhaCliente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfSenhaCliente.getStyleClass().remove("inválido");
        }

        if (tfEmailCliente.textProperty().isNull().get() == true) {
            tfEmailCliente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfEmailCliente.getStyleClass().remove("inválido");
        }

        if (tfTelefoneCliente.textProperty().isNull().get() == true) {
            tfTelefoneCliente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if(tfTelefoneCliente.getText().length() < 9 || tfTelefoneCliente.getText().length() > 11){
                tfTelefoneCliente.getStyleClass().add("inválido");
                invalido = true;
            }
            if(HelpDesk.numberContainsString(tfTelefoneCliente.getText()) == true){
                tfTelefoneCliente.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfUsuarioCliente.textProperty().isNull().get() == true) {
            tfUsuarioCliente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfUsuarioCliente.getStyleClass().remove("inválido");
        }

        if (tfNomeCliente.textProperty().isNull().get() == true) {
            tfNomeCliente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if(HelpDesk.stringContainsNumber(tfNomeCliente.getText()) == true){
                tfNomeCliente.getStyleClass().add("inválido");
                invalido = true;
            }
        }
        return invalido;
    }
}
