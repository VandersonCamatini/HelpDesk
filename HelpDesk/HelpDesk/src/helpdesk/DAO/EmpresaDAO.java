/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.DAO;

import helpdesk.model.Empresa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface EmpresaDAO {
    
     public void save(Empresa empresa)throws SQLException;
    
    public void update(Empresa empresa)throws SQLException;
    
    public void delete(Empresa empresa)throws SQLException;
    
    public List<Empresa> getAll()throws SQLException;
    
}
