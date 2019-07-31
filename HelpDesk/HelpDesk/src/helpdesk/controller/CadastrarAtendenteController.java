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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Bileko
 */
public class CadastrarAtendenteController {

    @FXML
    private TextField tfSenhaAtendente;
    @FXML
    private TextField tfEmailAtendente;
    @FXML
    private TextField tfIdentificadorAtendente;
    @FXML
    private TextField tfTelefoneAtendente;
    @FXML
    private Button btnSalvar;

    Atendente atendente = null;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfNomeAtendente;
    @FXML
    private TextField tfUsuarioAtendente;
    @FXML
    private Button btnCriarAtendente;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
        unbindDataObject(atendente);

        try {
            if (atendente != null) {
                if (verificaFields() == false) {
                    DAOFactory.getAtendenteDAO().save(atendente);
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Conta criada e salva, pode fechar a janela.");
                    alerta.showAndWait();
                    atendente = null;
                } else {
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos corretamente.");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova conta e preencha todos os campos.");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }

    }

    private void bindDataObject(Atendente atendente) {
        if (atendente != null) {
            tfCodigo.textProperty().bind(atendente.codigoProperty().asString());
            tfNomeAtendente.textProperty().bindBidirectional(atendente.nomeProperty());
            tfEmailAtendente.textProperty().bindBidirectional(atendente.emailProperty());
            tfIdentificadorAtendente.textProperty().bindBidirectional(atendente.identificadorProperty(), new NumberStringConverter());
            tfTelefoneAtendente.textProperty().bindBidirectional(atendente.telefoneProperty(), new NumberStringConverter());
            tfUsuarioAtendente.textProperty().bindBidirectional(atendente.loginProperty());
            tfSenhaAtendente.textProperty().bindBidirectional(atendente.senhaProperty());
        }
    }

    private void unbindDataObject(Atendente atendente) {
        if (atendente != null) {
            tfCodigo.textProperty().unbind();
            tfNomeAtendente.textProperty().unbindBidirectional(atendente.nomeProperty());
            tfEmailAtendente.textProperty().unbindBidirectional(atendente.emailProperty());
            tfIdentificadorAtendente.textProperty().unbindBidirectional(atendente.identificadorProperty());
            tfTelefoneAtendente.textProperty().unbindBidirectional(atendente.telefoneProperty());
            tfUsuarioAtendente.textProperty().unbindBidirectional(atendente.loginProperty());
            tfSenhaAtendente.textProperty().unbindBidirectional(atendente.senhaProperty());
        }
    }

    @FXML
    private void btncCriarAtendenteOnAction(ActionEvent event) {
        atendente = new Atendente();
        bindDataObject(atendente);
        cleanDataObject(atendente);
    }

    private void cleanDataObject(Atendente Atendente) {
        tfIdentificadorAtendente.textProperty().set(null);
        tfTelefoneAtendente.textProperty().set(null);
    }

    private Boolean verificaFields() {
        Boolean invalido = false;

        if (tfNomeAtendente.textProperty().isNull().get() == true) {
            System.out.println(tfNomeAtendente.textProperty().isNull().get());
            tfNomeAtendente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if(HelpDesk.stringContainsNumber(tfNomeAtendente.getText()) == true){
                tfNomeAtendente.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfUsuarioAtendente.textProperty().isNull().get() == true) {
            tfUsuarioAtendente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfUsuarioAtendente.getStyleClass().remove("inválido");
        }

        if (tfTelefoneAtendente.textProperty().isNull().get() == true ) {
            tfTelefoneAtendente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if(tfTelefoneAtendente.getText().length() < 9 || tfTelefoneAtendente.getText().length() > 11){
                tfIdentificadorAtendente.getStyleClass().add("inválido");
                invalido = true;
            }
            if(HelpDesk.numberContainsString(tfTelefoneAtendente.getText()) == true){
                tfNomeAtendente.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfIdentificadorAtendente.textProperty().isNull().get() == true) {
                tfIdentificadorAtendente.getStyleClass().add("inválido");
                invalido = true;
        } else {
            if(tfIdentificadorAtendente.getText().length() < 11 || tfIdentificadorAtendente.getText().length() > 14){
                tfIdentificadorAtendente.getStyleClass().add("inválido");
                invalido = true;
            }
             if(HelpDesk.numberContainsString(tfIdentificadorAtendente.getText()) == true){
                tfIdentificadorAtendente.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfEmailAtendente.textProperty().isNull().get() == true) {
            tfEmailAtendente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfEmailAtendente.getStyleClass().remove("inválido");
        }

        if (tfSenhaAtendente.textProperty().isNull().get() == true) {
            tfSenhaAtendente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfSenhaAtendente.getStyleClass().remove("inválido");
        }
        
        return invalido;
    }

}
