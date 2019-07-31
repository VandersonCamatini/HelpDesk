/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.controller;

import helpdesk.DAO.DAOFactory;
import helpdesk.HelpDesk;
import helpdesk.model.Atendente;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Senai
 */
public class LoginController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfUsername;
    @FXML
    private Button btnEnter;
    @FXML
    private Button btnExit;
    @FXML
    private CheckBox cbCliente;
    @FXML
    private CheckBox cbAtendente;
    @FXML
    private PasswordField pfPassword;
    
    public static Integer codCliente ;
    
     public static Integer codAtendente;
    
    Atendente atendente = null;
    

 
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnEnterOnAction(ActionEvent event) throws IOException {
        
        if(cbAtendente.isSelected() == false && cbCliente.isSelected() == false){
            Alert alerta = HelpDesk.criarAlerta("INFORMATION","Primeiro selecione a caixa atendente ou cliente de acordo com o tipo do seu perfil.");
            alerta.showAndWait();
        }
        if(cbAtendente.isSelected() == true ){
            Boolean resultadoAtendenteLogin = null;
            String resultadoAtendenteSenha = null;
            try {
                resultadoAtendenteLogin = DAOFactory.getAtendenteDAO().procurarLogin(tfUsername.getText());
                if (resultadoAtendenteLogin == true){
                    resultadoAtendenteSenha = DAOFactory.getAtendenteDAO().procurarSenha(tfUsername.getText());
                    if (resultadoAtendenteSenha.equals(pfPassword.getText()) == true){
                        HelpDesk.mudarTela("telaDoAtendente");
                        codAtendente = DAOFactory.getClienteDAO().pegarCliente(tfUsername.getText(),pfPassword.getText());
                    }else{
                        Alert alerta = HelpDesk.criarAlerta("INFORMATION","Sua senha está incorreta.");
                        alerta.showAndWait();
                    } 
                }else{
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION","Seu usuário está incorreto.");
                    alerta.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else if(cbCliente.isSelected() == true ){
            Boolean resultadoClienteLogin = null;
            String resultadoClienteSenha = null;
            try {
                resultadoClienteLogin = DAOFactory.getClienteDAO().procurarLogin(tfUsername.getText());
                if (resultadoClienteLogin == true){
                    resultadoClienteSenha = DAOFactory.getClienteDAO().procurarSenha(tfUsername.getText());
                    if (resultadoClienteSenha.equals(pfPassword.getText()) == true){
                        codCliente = DAOFactory.getClienteDAO().pegarCliente(tfUsername.getText(),pfPassword.getText());
                        HelpDesk.mudarTela("telaDoCliente");
                    }else{
                        Alert alerta = HelpDesk.criarAlerta("INFORMATION","Sua senha está incorreta.");
                        alerta.showAndWait();
                    } 
                }else{
                    Alert alerta = HelpDesk.criarAlerta("INFORMATION","Seu usuário está incorreto.");
                    alerta.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else{
            Alert alerta = HelpDesk.criarAlerta("INFORMATION","Primeiro selecione a caixa atendente ou cliente de acordo com o tipo do seu perfil.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnExitOnAction(ActionEvent event) {
        Alert alerta = HelpDesk.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
        if (alerta.showAndWait().get() == ButtonType.YES){
            System.exit(0);
        }          
    }

    @FXML
    private void cbClienteOnAction(ActionEvent event) {
        if(cbCliente.isSelected()){
            cbCliente.setSelected(true);
            cbAtendente.setSelected(false);
            
        }
    }

    @FXML
    private void cbAtendenteOnAction(ActionEvent event) {
        if(cbAtendente.isSelected()){
            cbCliente.setSelected(false);
            cbAtendente.setSelected(true); 
        }
    }

   

}
