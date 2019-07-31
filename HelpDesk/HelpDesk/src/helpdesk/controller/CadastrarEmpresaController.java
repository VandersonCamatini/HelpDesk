/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.controller;

import helpdesk.DAO.DAOFactory;
import helpdesk.HelpDesk;
import helpdesk.model.Atendente;
import helpdesk.model.Empresa;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Bileko
 */
public class CadastrarEmpresaController {

    @FXML
    private TextField tfNomeEmpresa;
    @FXML
    private TextField tfCNPJ;
    @FXML
    private TextField tfTelefone;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfEmail;
    
    Empresa empresa = null;

    @FXML
    private void btnNovaEmpresaOnAction(ActionEvent event) {
        empresa = new Empresa();
        bindDataObject(empresa);
        cleanDataObject(empresa);
    }

    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
            unbindDataObject(empresa);
        try {
            if (empresa != null) {
                 if (verificaFields() == false) {
                    DAOFactory.getEmpresaDAO().save(empresa);
                    empresa = null;
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Empresa criada e salva, pode fechar a janela.");
                    alerta.showAndWait();
                } else {
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos.");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Primeiro clique no botão Nova empresa e preencha todos os campos.");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }
    }

    private void btnCancelarOnAction(ActionEvent event) {
        System.out.close();
    }
    
    private void bindDataObject(Empresa empresa) {
        if (empresa != null) {
            tfCodigo.textProperty().bind(empresa.codigoProperty().asString());
            tfNomeEmpresa.textProperty().bindBidirectional(empresa.nomeProperty());
            tfEmail.textProperty().bindBidirectional(empresa.emailProperty());
            tfCNPJ.textProperty().bindBidirectional(empresa.identificadorProperty(),new NumberStringConverter());
            tfTelefone.textProperty().bindBidirectional(empresa.telefoneProperty(), new NumberStringConverter());
            
        }
    }

    private void unbindDataObject(Empresa empresa) {
        if (empresa != null) {
            tfCodigo.textProperty().unbind();
            tfNomeEmpresa.textProperty().unbindBidirectional(empresa.nomeProperty());
            tfEmail.textProperty().unbindBidirectional(empresa.emailProperty());
            tfCNPJ.textProperty().unbindBidirectional(empresa.identificadorProperty());
            tfTelefone.textProperty().unbindBidirectional(empresa.telefoneProperty());
            
        }
    }

    private void cleanDataObject(Empresa Empresa){
        tfCNPJ.textProperty().set(null);    
        tfTelefone.textProperty().set(null);
    }
    
    private Boolean verificaFields() {
        Boolean invalido = false;

        if (tfNomeEmpresa.textProperty().isNull().get() == true) {
            tfNomeEmpresa.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if(HelpDesk.stringContainsNumber(tfNomeEmpresa.getText()) == true){
                tfNomeEmpresa.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfCNPJ.textProperty().isNull().get() == true) {
            tfCNPJ.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if(tfCNPJ.getText().length() < 11 || tfCNPJ.getText().length() > 14){
                tfCNPJ.getStyleClass().add("inválido");
                invalido = true;
            }
            if(HelpDesk.numberContainsString(tfCNPJ.getText()) == true){
                tfCNPJ.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfTelefone.textProperty().isNull().get() == true) {
            tfTelefone.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if(HelpDesk.numberContainsString(tfTelefone.getText()) == true){
                tfTelefone.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfEmail.textProperty().isNull().get() == true) {
            tfEmail.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfEmail.getStyleClass().remove("inválido");
        }  
        return invalido;
    }
}
