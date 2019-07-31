/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk;

import helpdesk.model.Chamado;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Senai
 */
public class HelpDesk extends Application {
    
    private static Stage stage;
    private static Scene cadastrar;
    private static Scene login;
    private static Scene telaDoCliente;
    private static Scene telaDeResolucao;
    private static Scene telaDoAtendente;
    public static Chamado objeto;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
      
        stage = primaryStage;

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        login = new Scene(fxmlLogin, 800, 450);
        
        Parent fxmlTelaDoCliente = FXMLLoader.load(getClass().getResource("view/TelaDoCliente.fxml"));
        telaDoCliente = new Scene(fxmlTelaDoCliente, 800, 450);
        
        Parent fxmlTelaDeResolucao = FXMLLoader.load(getClass().getResource("view/TelaDeResolucao.fxml"));
        telaDeResolucao = new Scene(fxmlTelaDeResolucao, 800, 450);
        
        Parent fxmlTelaDoAtendente = FXMLLoader.load(getClass().getResource("view/TelaDoAtendente.fxml"));
        telaDoAtendente = new Scene(fxmlTelaDoAtendente, 800, 450);
        
        stage.setTitle("Login");
        stage.setScene(login);
        stage.show();
    }
    public static Scene mudarTela(String tela){
        Scene cenaEscolhida = null;
        switch(tela){
            case "cadastrar":
                stage.setTitle("Tela de cadastros");
                stage.setScene(cadastrar);
                cenaEscolhida = cadastrar;
                break;
            case "login":
                stage.setTitle("Login");
                stage.setScene(login);
                cenaEscolhida = login;
                break;
            case "telaDoCliente":
                stage.setTitle("Tela do Solicitante");
                stage.setScene(telaDoCliente);
                cenaEscolhida = telaDoCliente;
                break;
            case "telaDeResolucao":
                stage.setTitle("Tela de resolução de chamadas");
                stage.setScene(telaDeResolucao);
                cenaEscolhida = telaDeResolucao;
                break;
            case "telaDoAtendente":
                stage.setTitle("Tela do atendente");
                stage.setScene(telaDoAtendente);
                cenaEscolhida = telaDoAtendente;
                break;
        }
        return cenaEscolhida;
       
    }
    
     public static Alert criarAlerta(String tipoDoErro , String texto){
        
        tipoDoErro = tipoDoErro.toUpperCase();
        texto = texto.toUpperCase();
        
        Alert alerta = null;
        if(tipoDoErro == "NONE"){
           alerta = new Alert(Alert.AlertType.NONE);
           alerta.setContentText(texto);
        }
        if(tipoDoErro == "INFORMATION"){
           alerta = new Alert(Alert.AlertType.INFORMATION);
           alerta.setContentText(texto);
        }
        if(tipoDoErro == "CONFIRMATION"){
            alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setContentText(texto);
            alerta.getButtonTypes().clear();
            alerta.getButtonTypes().add(ButtonType.NO);
            alerta.getButtonTypes().add(ButtonType.YES);
        }
        return alerta;
    }
     
    public static Alert criarAlertaSql(SQLException ex){
        Alert alerta = null;
        alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
        alerta.showAndWait();
        return alerta;
    }
    
    public static Alert criarAlertaIOEx(IOException ex){
        Alert alerta = null;
        alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
        alerta.showAndWait();
        return alerta;
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    public static String getDateTime() { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	Date date = new Date(); 
	return dateFormat.format(date); 
    }
    
    public static void transferirObjeto(Chamado conta){
        objeto = conta;
    }
    
    public static void fechar(Button btn){
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
    
    public static boolean stringContainsNumber( String s ){
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }
    
    public static boolean numberContainsString( String s ){
        return Pattern.compile( "[a-zA-Z]+" ).matcher( s ).find();
    }
}
