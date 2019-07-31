/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.controller;

import helpdesk.DAO.DAOFactory;
import helpdesk.HelpDesk;
import static helpdesk.HelpDesk.getDateTime;
import helpdesk.model.Chamado;
import helpdesk.model.Cliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Senai
 */
public class TelaDeChamadaController {

    @FXML
    private TextField tfSolicitante;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextArea tfDescricao;
    @FXML
    private Button btnEnviar;

    Chamado chamado = null;

    Integer retornoEmpresa;

    Integer retornoCliente;
    @FXML
    private TextField tfCodigo;
    @FXML
    private CheckBox cbBaixa;
    @FXML
    private CheckBox cbMedia;
    @FXML
    private CheckBox cbAlta;

    @FXML
    private void btnEnviarOnAction(ActionEvent event) {
        unbindDataObject(chamado);
        try {
            if (chamado != null) {
                if (verificaFields() == false) {
                DAOFactory.getChamadoDAO().save(chamado);
                chamado = null;
                Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Chamado enviado, pode fechar a janela.");
                alerta.showAndWait();
                } else {
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos.");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Primeiro clique no botão de Abrir chamado e preencha todos os campos.");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {

            Logger.getLogger(TelaDeChamadaController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnNovoChamado(ActionEvent event) {
        chamado = new Chamado();
        bindDataObject(chamado);
    }

    private void bindDataObject(Chamado chamado) {
        if (chamado != null) {
            tfCodigo.textProperty().bindBidirectional(chamado.codigoProperty(), new NumberStringConverter());
            tfTitulo.textProperty().bindBidirectional(chamado.tituloProperty());
            tfDescricao.textProperty().bindBidirectional(chamado.descricaoProperty());
        }
    }

    private void unbindDataObject(Chamado chamado) {
        if (chamado != null) {
            tfCodigo.textProperty().unbind();
            tfTitulo.textProperty().unbindBidirectional(chamado.tituloProperty());
            tfDescricao.textProperty().unbindBidirectional(chamado.descricaoProperty());
            try {
                retornoEmpresa = DAOFactory.getChamadoDAO().procurarEmpresa(tfSolicitante.getText());
            } catch (SQLException ex) {
                Logger.getLogger(TelaDeChamadaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                retornoCliente = DAOFactory.getChamadoDAO().procurarSolicitante(tfSolicitante.getText());
            } catch (SQLException ex) {
                Logger.getLogger(TelaDeChamadaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            chamado.setCodigoEmpresa(retornoEmpresa);
            chamado.setCodigoCliente(retornoCliente);
            chamado.setStatus("Esperando");
            chamado.setDataFinal("Esperando");
            chamado.setResposta("Esperando");
            chamado.setDataInicial(getDateTime());

        }
    }

    @FXML
    private void baixaOnAction(ActionEvent event) {
        if (cbBaixa.isSelected()) {
            chamado.setPrioridade("Baixa");
            cbBaixa.setSelected(true);
            cbMedia.setSelected(false);
            cbAlta.setSelected(false);
        }
    }

    @FXML
    private void mediaOnAction(ActionEvent event) {
        if (cbMedia.isSelected()) {
            chamado.setPrioridade("Média");
            cbMedia.setSelected(true);
            cbBaixa.setSelected(false);
            cbAlta.setSelected(false);
        }
    }

    @FXML
    private void altaOnAction(ActionEvent event) {
        if (cbAlta.isSelected()) {
            chamado.setPrioridade("Alta");
            cbAlta.setSelected(true);
            cbMedia.setSelected(false);
            cbBaixa.setSelected(false);
        }
    }

    private Boolean verificaFields() {
        Boolean invalido = false;
        
        if (tfSolicitante.textProperty().isNull().get()) {
            tfSolicitante.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if(HelpDesk.stringContainsNumber(tfSolicitante.getText()) == true){
                tfSolicitante.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfTitulo.textProperty().isNull().get()) {
            tfTitulo.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfTitulo.getStyleClass().remove("inválido");
        }

        if (tfDescricao.textProperty().isNull().get()) {
            tfDescricao.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfDescricao.getStyleClass().remove("inválido");
        }

        if (chamado.getPrioridade() == null) {
            invalido = true;
        } 
        return invalido;
    }
}
