/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.DAO;

import helpdesk.model.Chamado;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface ChamadoDAO {
    
    public void save(Chamado chamado)throws SQLException;
     
    public void update(Chamado chamado)throws SQLException;
    
    public void delete(Chamado chamado)throws SQLException;
    
    public List<Chamado> getAll()throws SQLException;
    
    public List<Chamado> getAllCliente(Integer Codigo)throws SQLException;
    
    public Integer procurarEmpresa(String solicitante) throws SQLException;
    
    public Integer procurarSolicitante(String solicitante) throws SQLException;
    
    public String procurarNomeSolicitante(Integer solicitante) throws SQLException;
    
    public String procurarNomeEmpresa(Integer solicitante) throws SQLException;
     
    public Integer procurarAtendente(String atendente) throws SQLException;
    
    public String procurarNomeAtendente(Integer atendente) throws SQLException;
    
    public List<Chamado> pesquisarPorPrioridade(String prioridade)throws SQLException;
    
    public String email(Integer codigoSolicitante) throws SQLException;
}
