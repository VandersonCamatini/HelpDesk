/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.DAO;

import helpdesk.HelpDesk;
import helpdesk.model.Empresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Senai
 */
public class EmpresaSqlDAO extends ConnectionFactory implements EmpresaDAO{
    
    public void save(Empresa empresa) throws SQLException {
        String[] codigoGerado = {"codemp"};
        super.preparedStatementInitialize("insert into empresa (nomemp, cnpjemp, telemp, emaemp) values (?,?,?,?)",codigoGerado);
        super.prepared.setString(1, empresa.getNome());
        super.prepared.setInt(2, empresa.getIdentificador());
        super.prepared.setInt(3, empresa.getTelefone());
        super.prepared.setString(4, empresa.getEmail());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar a nova conta");
        }
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            empresa.setCodigo(resultSetRows.getInt("codemp"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Empresa empresa) throws SQLException {
        super.preparedStatementInitialize(
                "update empresa set nomemp = ?, cnpjemp = ?, telemp = ?, emaemp = ? WHERE codemp = ?");
        super.prepared.setString(1, empresa.getNome());
        super.prepared.setInt(2, empresa.getIdentificador());
        super.prepared.setInt(3, empresa.getTelefone());
        super.prepared.setString(4, empresa.getEmail());
        super.prepared.setInt(5, empresa.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public void delete(Empresa empresa) throws SQLException {
        super.preparedStatementInitialize(
                "delete from empresa where codemp = ?");
        super.prepared.setInt(1, empresa.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Empresa> getAll() throws SQLException {
        
        List<Empresa> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from empresa");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Empresa(resultSetRows.getInt("codemp"),
                    resultSetRows.getString("nomemp"),
                    resultSetRows.getInt("cnpjemp"),
                    resultSetRows.getInt("telemp"),
                    resultSetRows.getString("emaemp")));       
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
     
}
