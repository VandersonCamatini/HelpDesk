/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.controller;

import helpdesk.DAO.DAOFactory;
import helpdesk.HelpDesk;
import helpdesk.model.Chamado;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.security.auth.callback.Callback;

/**
 *
 * @author Bileko
 */
public class TelaDoClienteController implements Initializable {

    @FXML
    private TabPane tbpJanelas;
    @FXML
    private Tab tabPaginaInicial;
    @FXML
    private TableView<Chamado> tblChamados;
    @FXML
    private TableColumn<Chamado, String> tbcDataInicial;
    @FXML
    private TableColumn<Chamado, Integer> tbcEmpresa;
    @FXML
    private TableColumn<Chamado, Integer> tbcSolicitante;
    @FXML
    private TableColumn<Chamado, String> tbcTitulo;
    @FXML
    private TableColumn<Chamado, Integer> tbcAtendente;
    @FXML
    private TableColumn<Chamado, String> tbcStatus;
    @FXML
    private TableColumn<Chamado, String> tbcDataFinal;
    @FXML
    private Text txtChamados;

    private FXMLLoader cargaDoSceneChamado = null;

    private AnchorPane painelSceneChamado = null;

    Tab abaChamado = null;

    private FXMLLoader cargaDoSceneProblemaCliente = null;

    private AnchorPane painelSceneProblemaCliente = null;

    Tab abaProblemaCliente = null;

    Chamado objeto;

    @FXML
    private void btncCarregarTudoOnAction(ActionEvent event) {
        try {
            tblChamados.setItems(FXCollections.observableList(DAOFactory.getChamadoDAO().getAllCliente(LoginController.codCliente)));
        } catch (SQLException ex) {
            Logger.getLogger(TelaDoAtendenteController.class
                    .getName()).log(Level.SEVERE, null, ex);
            Alert alerta = HelpDesk.criarAlertaSql(ex);
        }
    }

    @FXML
    private void btncAbrirChamadoOnAction(ActionEvent event) {
        cargaDoSceneChamado = new FXMLLoader(getClass().getResource("/helpdesk/view/TelaDeChamada.fxml"));
        try {
            if (abaChamado == null) {
                abaChamado = new Tab("Novo chamado");
            }
            if (painelSceneChamado == null) {
                painelSceneChamado = cargaDoSceneChamado.load();
            }
            abaChamado.setContent(painelSceneChamado);
            abaChamado.setOnCloseRequest((eventClose) -> {
                Alert alerta = HelpDesk.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                if (alerta.showAndWait().get() != ButtonType.YES) {
                    eventClose.consume();
                } else {
                    tbpJanelas.getTabs().remove(abaChamado);
                    painelSceneChamado = null;
                }
            });
            if (tbpJanelas.getTabs().contains(abaChamado) == false) {
                tbpJanelas.getTabs().add(abaChamado);
            }
            tbpJanelas.getSelectionModel().select(abaChamado);
        } catch (IOException ex) {
            Logger.getLogger(TelaDoAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = HelpDesk.criarAlertaIOEx(ex);
        }
    }

    @FXML
    private void selectRowOnAction(MouseEvent event) {
        if (event.getClickCount() == 2) {
            if (objeto == null) {
                objeto = tblChamados.getSelectionModel().getSelectedItem();
                HelpDesk.transferirObjeto(objeto);
                cargaDoSceneProblemaCliente = new FXMLLoader(getClass().getResource("/helpdesk/view/resolucaoDoCliente.fxml"));
                try {
                    if (abaProblemaCliente == null) {
                        abaProblemaCliente = new Tab("Resolução do problema");
                    }
                    if (painelSceneProblemaCliente == null) {
                        painelSceneProblemaCliente = cargaDoSceneProblemaCliente.load();
                    }
                    abaProblemaCliente.setContent(painelSceneProblemaCliente);
                    abaProblemaCliente.setOnCloseRequest((eventClose) -> {
                        Alert alerta = HelpDesk.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                        if (alerta.showAndWait().get() != ButtonType.YES) {
                            eventClose.consume();
                        } else {
                            tbpJanelas.getTabs().remove(abaProblemaCliente);
                            painelSceneProblemaCliente = null;
                            objeto = null;
                        }
                    });
                    if (tbpJanelas.getTabs().contains(abaProblemaCliente) == false) {
                        tbpJanelas.getTabs().add(abaProblemaCliente);
                    }
                    tbpJanelas.getSelectionModel().select(abaProblemaCliente);
                } catch (IOException ex) {
                    Logger.getLogger(TelaDoClienteController.class.getName()).log(Level.SEVERE, null, ex);
                    Alert alerta = HelpDesk.criarAlertaIOEx(ex);
                }
            } else {
                if (tbpJanelas.getTabs().contains(abaProblemaCliente) == false) {
                    tbpJanelas.getTabs().add(abaProblemaCliente);
                }
                tbpJanelas.getSelectionModel().select(abaProblemaCliente);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbcAtendente.setCellFactory((TableColumn<Chamado, Integer> param) -> {
            TableCell cell = new TableCell<Chamado, Integer>() {

                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    setGraphic(null);
                    if (!empty) {
                        if (item == null || item == 0) {
                            setText("Esperando");
                        } else {
                            try {
                                setText(DAOFactory.getChamadoDAO().procurarNomeAtendente(item));
                            } catch (SQLException ex) {
                                Logger.getLogger(TelaDoClienteController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }

                @Override
                public void updateSelected(boolean upd) {
                    super.updateSelected(upd);
                }

                private String getString() {
                    return getItem() == null ? "" : getItem().toString();
                }
            };
            return cell;
        });

        tbcEmpresa.setCellFactory((TableColumn<Chamado, Integer> param) -> {
            TableCell cell = new TableCell<Chamado, Integer>() {

                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    setGraphic(null);
                    if (!empty) {
                        if (item == null || item == 0) {
                            setText("Esperando");
                        } else {
                            try {
                                setText(DAOFactory.getChamadoDAO().procurarNomeEmpresa(item));
                            } catch (SQLException ex) {
                                Logger.getLogger(TelaDoClienteController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }

                @Override
                public void updateSelected(boolean upd) {
                    super.updateSelected(upd);
                }

                private String getString() {
                    return getItem() == null ? "" : getItem().toString();
                }
            };
            return cell;
        });
        
        tbcSolicitante.setCellFactory((TableColumn<Chamado, Integer> param) -> {
            TableCell cell = new TableCell<Chamado, Integer>() {

                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    setGraphic(null);
                    if (!empty) {
                        if (item == null || item == 0) {
                            setText("Esperando");
                        } else {
                            try {
                                setText(DAOFactory.getChamadoDAO().procurarNomeSolicitante(item));
                            } catch (SQLException ex) {
                                Logger.getLogger(TelaDoClienteController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }

                @Override
                public void updateSelected(boolean upd) {
                    super.updateSelected(upd);
                }

                private String getString() {
                    return getItem() == null ? "" : getItem().toString();
                }
            };
            return cell;
        });
    }
}
