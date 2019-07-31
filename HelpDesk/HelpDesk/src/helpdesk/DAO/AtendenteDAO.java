/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.DAO;

import helpdesk.model.Atendente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bileko
 */
public interface AtendenteDAO {
    
    public void save(Atendente conta)throws SQLException;
    
    public void update(Atendente conta)throws SQLException;
    
    public void delete(Atendente conta)throws SQLException;
    
    public List<Atendente> getAll()throws SQLException;
    
    public Boolean procurarLogin(String login) throws SQLException;
    
    public String procurarSenha(String login) throws SQLException;
}
