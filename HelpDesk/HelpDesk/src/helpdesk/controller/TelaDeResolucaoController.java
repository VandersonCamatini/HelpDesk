/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.controller;

import helpdesk.DAO.DAOFactory;
import helpdesk.EnviarEmail;
import helpdesk.HelpDesk;
import helpdesk.model.Chamado;
import helpdesk.model.Cliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Senai
 */
public class TelaDeResolucaoController {

    @FXML
    private TextField tfEmpresa;
    @FXML
    private TextField tfAtendente;
    @FXML
    private TextField tfSolicitante;
    @FXML
    private TextField tfDataInicial;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextArea tfResolucaoDoProblema;
    @FXML
    private TextArea tfProblemaSolicitante;
    @FXML
    private TextField tfPrioridade;

    Chamado chamado;

    @FXML
    private void btnIniciarResolucaoOnAction(ActionEvent event) {
        chamado = HelpDesk.objeto;
        bindDataObject(chamado);
    }

    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
        unbindDataObject(chamado);
        try {
            if (chamado != null) {
                if (verificaFields() == false) {
                    DAOFactory.getChamadoDAO().update(chamado);
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Chamado atualizado e salvo, pode fechar a janela.");
                    alerta.showAndWait();
                    chamado = null;
                } else {
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos.");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = HelpDesk.criarAlerta("INFORMATION", "Primeiro clique no botão Iniciar resolução e preencha todos os campos.");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaDeResolucaoController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }
    }

    private void bindDataObject(Chamado chamado) {
        if (chamado != null) {

            tfDataInicial.textProperty().bindBidirectional(chamado.dataInicialProperty());
            tfTitulo.textProperty().bindBidirectional(chamado.tituloProperty());
            tfResolucaoDoProblema.textProperty().bindBidirectional(chamado.respostaProperty());
            tfProblemaSolicitante.textProperty().bindBidirectional(chamado.descricaoProperty());
            tfPrioridade.textProperty().bindBidirectional(chamado.prioridadeProperty());
            try {
                tfEmpresa.setText(DAOFactory.getChamadoDAO().procurarNomeEmpresa(chamado.getCodigoEmpresa()));
            } catch (SQLException ex) {
                Logger.getLogger(TelaDeResolucaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                tfSolicitante.setText(DAOFactory.getChamadoDAO().procurarNomeSolicitante(chamado.getCodigoCliente()));
            } catch (SQLException ex) {
                Logger.getLogger(TelaDeResolucaoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void unbindDataObject(Chamado chamado) {
        if (chamado != null) {
            tfDataInicial.textProperty().unbindBidirectional(chamado.dataInicialProperty());
            tfTitulo.textProperty().unbindBidirectional(chamado.tituloProperty());
            tfResolucaoDoProblema.textProperty().unbindBidirectional(chamado.respostaProperty());
            tfProblemaSolicitante.textProperty().unbindBidirectional(chamado.descricaoProperty());
            tfPrioridade.textProperty().unbindBidirectional(chamado.prioridadeProperty());
            try {
                chamado.setCodigoAtendente(DAOFactory.getChamadoDAO().procurarAtendente(tfAtendente.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(TelaDeResolucaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            chamado.setDataFinal(HelpDesk.getDateTime());
            chamado.setStatus("Concluído");

        }
    }

    private Boolean verificaFields() {
        Boolean invalido = false;

        if (tfAtendente.textProperty().isNull().get()) {
            tfAtendente.getStyleClass().add("inválido");
            invalido = true;
        } else {
            if (HelpDesk.stringContainsNumber(tfAtendente.getText()) == true) {
                tfAtendente.getStyleClass().add("inválido");
                invalido = true;
            }
        }

        if (tfResolucaoDoProblema.textProperty().isNull().get()) {
            tfResolucaoDoProblema.getStyleClass().add("inválido");
            invalido = true;
        } else {
            tfResolucaoDoProblema.getStyleClass().remove("inválido");
        }

        return invalido;
    }
    
    
}
