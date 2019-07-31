/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.controller;

import helpdesk.DAO.DAOFactory;
import helpdesk.HelpDesk;
import helpdesk.model.Chamado;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Bileko
 */
public class resolucaoDoClienteController {

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
    
    Chamado chamado = HelpDesk.objeto;

    @FXML
    private void verResolucaoOnAction(ActionEvent event) {
            
            tfDataInicial.textProperty().bindBidirectional(chamado.dataInicialProperty());
            tfTitulo.textProperty().bindBidirectional(chamado.tituloProperty());
            tfResolucaoDoProblema.textProperty().bindBidirectional(chamado.respostaProperty());
            tfProblemaSolicitante.textProperty().bindBidirectional(chamado.descricaoProperty());
            tfPrioridade.textProperty().bindBidirectional(chamado.prioridadeProperty());
             try {
                tfEmpresa.setText(DAOFactory.getChamadoDAO().procurarNomeEmpresa(chamado.getCodigoEmpresa()));
            } catch (SQLException ex) {
                Logger.getLogger(resolucaoDoClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                tfSolicitante.setText(DAOFactory.getChamadoDAO().procurarNomeSolicitante(chamado.getCodigoCliente()));
            } catch (SQLException ex) {
                Logger.getLogger(resolucaoDoClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                tfAtendente.setText(DAOFactory.getChamadoDAO().procurarNomeAtendente(chamado.getCodigoAtendente()));
            } catch (SQLException ex) {
                Logger.getLogger(resolucaoDoClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    
}
