/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.DAO;

import helpdesk.model.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface ClienteDAO {
    
     public void save(Cliente conta)throws SQLException;
    
    public void update(Cliente conta)throws SQLException;
    
    public void delete(Cliente conta)throws SQLException;
    
    public List<Cliente> getAll()throws SQLException;
    
    public Boolean procurarLogin(String login) throws SQLException;
    
    public String procurarSenha(String login) throws SQLException;
    
    public Integer procurarEmpresa(String empresa) throws SQLException;
    
    public Integer pegarCliente(String login, String senha) throws SQLException;
}
