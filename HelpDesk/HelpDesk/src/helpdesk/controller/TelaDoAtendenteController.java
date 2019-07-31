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
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Senai
 */
public class TelaDoAtendenteController implements Initializable {

    @FXML
    private TableView<Chamado> tblChamados;
    @FXML
    private TableColumn<Chamado, Integer> tbcCodigo;
    @FXML
    private TableColumn<Chamado, Integer> tbcEmpresa;
    @FXML
    private TableColumn<Chamado, Integer> tbcSolicitante;
    @FXML
    private TableColumn<Chamado, String> tbcPrioridade;
    @FXML
    private TableColumn<Chamado, String> tbcTitulo;
    @FXML
    private TableColumn<Chamado, Integer> tbcAtendente;
    @FXML
    private TableColumn<Chamado, String> tbcStatus;
    @FXML
    private TableColumn<Chamado, String> tbcDataInicial;
    @FXML
    private TableColumn<Chamado, String> tbcDataFinal;
    @FXML
    private TextField tfProcurar;
    @FXML
    private Text txtChamados;
    @FXML
    private Tab tabPaginaInicial;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TabPane tbpJanelas = null;

    private FXMLLoader cargaDoSceneAtendente = null;

    private AnchorPane painelSceneAtendente = null;

    Tab abaAtendente = null;

    private FXMLLoader cargaDoSceneCliente = null;

    private AnchorPane painelSceneCliente = null;

    Tab abaCliente = null;

    private FXMLLoader cargaDoSceneEmpresa = null;

    private AnchorPane painelSceneEmpresa = null;

    Tab abaEmpresa = null;

    private FXMLLoader cargaDoSceneProblema = null;

    private AnchorPane painelSceneProblema = null;

    Tab abaProblema = null;

    Chamado objeto = null;
    @FXML
    private MenuItem mnCadastrarAtendente;
    @FXML
    private MenuItem mnCadastrarCliente;
    @FXML
    private MenuItem mnCadastrarEmpresa;

    @FXML
    private void btnPesquisarOnAction(ActionEvent event) {
        try {
            tblChamados.setItems(FXCollections.observableList(DAOFactory.getChamadoDAO().pesquisarPorPrioridade(tfProcurar.getText())));

        } catch (SQLException ex) {
            Logger.getLogger(TelaDoAtendenteController.class
                    .getName()).log(Level.SEVERE, null, ex);
            Alert alerta = HelpDesk.criarAlertaSql(ex);
        }
    }

    @FXML
    private void mnCadastrarAtendenteOnAction(ActionEvent event) {
        cargaDoSceneAtendente = new FXMLLoader(getClass().getResource("/helpdesk/view/CadastrarAtendente.fxml"));
        try {
            if (abaAtendente == null) {
                abaAtendente = new Tab("Cadastro de Atendente");
            }
            if (painelSceneAtendente == null) {
                painelSceneAtendente = cargaDoSceneAtendente.load();
            }
            abaAtendente.setContent(painelSceneAtendente);
            abaAtendente.setOnCloseRequest((eventClose) -> {
                Alert alerta = HelpDesk.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                if (alerta.showAndWait().get() != ButtonType.YES) {
                    eventClose.consume();
                } else {
                    tbpJanelas.getTabs().remove(abaAtendente);
                    painelSceneAtendente = null;
                }
            });
            if (tbpJanelas.getTabs().contains(abaAtendente) == false) {
                tbpJanelas.getTabs().add(abaAtendente);
            }
            tbpJanelas.getSelectionModel().select(abaAtendente);
        } catch (IOException ex) {
            Logger.getLogger(TelaDoAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = HelpDesk.criarAlertaIOEx(ex);
        }
    }

    @FXML
    private void mnCadastrarClienteOnAction(ActionEvent event) {
        cargaDoSceneCliente = new FXMLLoader(getClass().getResource("/helpdesk/view/CadastrarCliente.fxml"));
        try {
            if (this.abaCliente == null) {
                this.abaCliente = new Tab("Cadastro de Solicitante");
            }
            if (painelSceneCliente == null) {
                painelSceneCliente = cargaDoSceneCliente.load();
            }
            this.abaCliente.setContent(painelSceneCliente);
            this.abaCliente.setOnCloseRequest((eventClose) -> {
                Alert alerta = HelpDesk.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                if (alerta.showAndWait().get() != ButtonType.YES) {
                    eventClose.consume();
                } else {
                    this.tbpJanelas.getTabs().remove(this.abaCliente);
                    painelSceneCliente = null;
                }
            });
            if (this.tbpJanelas.getTabs().contains(this.abaCliente) == false) {
                this.tbpJanelas.getTabs().add(this.abaCliente);
            }
            this.tbpJanelas.getSelectionModel().select(this.abaCliente);
        } catch (IOException ex) {
            Logger.getLogger(TelaDoAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = HelpDesk.criarAlertaIOEx(ex);
        }
    }

    @FXML
    private void mnCadastrarEmpresaOnAction(ActionEvent event) {
        cargaDoSceneEmpresa = new FXMLLoader(getClass().getResource("/helpdesk/view/CadastrarEmpresa.fxml"));
        try {
            if (abaEmpresa == null) {
                abaEmpresa = new Tab("Cadastro de Empresa");
            }
            if (painelSceneEmpresa == null) {
                painelSceneEmpresa = cargaDoSceneEmpresa.load();
            }
            abaEmpresa.setContent(painelSceneEmpresa);
            abaEmpresa.setOnCloseRequest((eventClose) -> {
                Alert alerta = HelpDesk.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                if (alerta.showAndWait().get() != ButtonType.YES) {
                    eventClose.consume();
                } else {
                    tbpJanelas.getTabs().remove(abaEmpresa);
                    painelSceneEmpresa = null;
                }
            });
            if (tbpJanelas.getTabs().contains(abaEmpresa) == false) {
                tbpJanelas.getTabs().add(abaEmpresa);
            }
            tbpJanelas.getSelectionModel().select(abaEmpresa);
        } catch (IOException ex) {
            Logger.getLogger(TelaDoAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = HelpDesk.criarAlertaIOEx(ex);
        }
    }

    @FXML
    private void btncCarregarTudoOnAction(ActionEvent event) {
        try {
            tblChamados.setItems(FXCollections.observableList(DAOFactory.getChamadoDAO().getAll()));

        } catch (SQLException ex) {
            Logger.getLogger(TelaDoAtendenteController.class
                    .getName()).log(Level.SEVERE, null, ex);
            Alert alerta = HelpDesk.criarAlertaSql(ex);
        }

    }

    @FXML
    private void selectRowOnAction(MouseEvent event) {

        if (event.getClickCount() == 2) {
            if (objeto == null) {
                objeto = tblChamados.getSelectionModel().getSelectedItem();
                HelpDesk.transferirObjeto(objeto);
                cargaDoSceneProblema = new FXMLLoader(getClass().getResource("/helpdesk/view/TelaDeResolucao.fxml"));
                try {
                    if (abaProblema == null) {
                        abaProblema = new Tab("Solucionar problema");
                    }
                    if (painelSceneProblema == null) {
                        painelSceneProblema = cargaDoSceneProblema.load();
                    }
                    abaProblema.setContent(painelSceneProblema);
                    abaProblema.setOnCloseRequest((eventClose) -> {
                        Alert alerta = HelpDesk.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                        if (alerta.showAndWait().get() != ButtonType.YES) {
                            eventClose.consume();
                        } else {
                            tbpJanelas.getTabs().remove(abaProblema);
                            painelSceneProblema = null;
                            objeto = null;
                        }
                    });
                    if (tbpJanelas.getTabs().contains(abaProblema) == false) {
                        tbpJanelas.getTabs().add(abaProblema);
                    }
                    tbpJanelas.getSelectionModel().select(abaProblema);
                } catch (IOException ex) {
                    Logger.getLogger(TelaDoAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
                    Alert alerta = HelpDesk.criarAlertaIOEx(ex);
                }
            } else {
                if (tbpJanelas.getTabs().contains(abaProblema) == false) {
                    tbpJanelas.getTabs().add(abaProblema);
                }
                tbpJanelas.getSelectionModel().select(abaProblema);
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
