/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.DAO;

import helpdesk.HelpDesk;
import helpdesk.model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Senai
 */
public class ClienteSqlDAO extends ConnectionFactory implements ClienteDAO{
    
    public void save(Cliente cliente) throws SQLException {
        String[] codigoGerado = {"codsol"};
        super.preparedStatementInitialize("insert into solicitante (nomsol, telsol, ususol, sensol, emasol, codemp) values (?,?,?,?,?,?)",codigoGerado);
        super.prepared.setString(1, cliente.getNome());
        super.prepared.setInt(2, cliente.getTelefone());
        super.prepared.setString(3, cliente.getLogin());
        super.prepared.setString(4, cliente.getSenha());
        super.prepared.setString(5, cliente.getEmail());
        super.prepared.setInt(6, cliente.getEmpresa());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar a nova conta");
        }
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            cliente.setCodigo(resultSetRows.getInt("codsol"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
        super.preparedStatementInitialize(
                "update solicitante set nomsol = ?, telsol = ?, ususol = ?, sensol = ?, emasol = ?, codemp = ? WHERE codsol = ?");
        super.prepared.setString(1, cliente.getNome());
        super.prepared.setInt(2, cliente.getTelefone());
        super.prepared.setString(3, cliente.getLogin());
        super.prepared.setString(4, cliente.getSenha());
        super.prepared.setString(5, cliente.getEmail());
        super.prepared.setInt(6, cliente.getEmpresa());
        super.prepared.setInt(7, cliente.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public void delete(Cliente cliente) throws SQLException {
        super.preparedStatementInitialize(
                "delete from solicitante where codsol = ?");
        super.prepared.setInt(1, cliente.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Cliente> getAll() throws SQLException {
        
        List<Cliente> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from solicitante");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Cliente(resultSetRows.getInt("codsol"),
                    resultSetRows.getString("nomsol"),
                    resultSetRows.getInt("telsol"),
                    resultSetRows.getString("ususol"),
                    resultSetRows.getString("sensol"),
                    resultSetRows.getString("emasol"),
                    resultSetRows.getInt("codemp")));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    public Boolean procurarLogin(String login) throws SQLException {
        
        Boolean resultado = false;
        super.preparedStatementInitialize("SELECT * FROM solicitante WHERE ususol = ? ");
        super.prepared.setString(1, login);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = true;
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }
    
    public String procurarSenha(String login) throws SQLException {
        String resultado = null;
        super.preparedStatementInitialize("SELECT sensol FROM solicitante WHERE ususol = ? ");
        super.prepared.setString(1,login);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }
    
    public Integer procurarEmpresa(String empresa) throws SQLException {
        Integer resultado = null;
        super.preparedStatementInitialize("SELECT codemp FROM empresa WHERE nomemp = ? ");
        super.prepared.setString(1,empresa);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getInt(1);
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }
    
    public Integer pegarCliente (String login, String senha) throws SQLException {
        Integer resultado = null;
        super.preparedStatementInitialize("select codsol from solicitante where ususol = ? and sensol = ? ");
        super.prepared.setString(1,login);
        super.prepared.setString(2,senha);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getInt(1);  
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }
}
