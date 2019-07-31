/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import helpdesk.model.Atendente;

/**
 *
 * @author Bileko
 */
public class AtendenteSqlDAO extends ConnectionFactory implements AtendenteDAO{
    
     public void save(Atendente atendente) throws SQLException {
         System.out.println("Entrou");
        String[] codigoGerado = {"codate"};
        System.out.println(codigoGerado);
        super.preparedStatementInitialize("insert into atendente (nomate, cpfate, telate, usuate, senate, emacli) values (?,?,?,?,?,?)",codigoGerado);
        super.prepared.setString(1, atendente.getNome());
        super.prepared.setInt(2, atendente.getIdentificador());
        super.prepared.setInt(3, atendente.getTelefone());
        super.prepared.setString(4, atendente.getLogin());
        super.prepared.setString(5, atendente.getSenha());
        super.prepared.setString(6, atendente.getEmail());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar a nova conta");
        }
        
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            atendente.setCodigo(resultSetRows.getInt("codate"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Atendente atendente) throws SQLException {
        super.preparedStatementInitialize(
                "update atendente set nomate = ?, cpfate = ?, telate = ?, usuate = ?, senate = ?, emacli = ? WHERE codate = ?");
        super.prepared.setString(1, atendente.getNome());
        super.prepared.setInt(2, atendente.getIdentificador());
        super.prepared.setInt(3, atendente.getTelefone());
        super.prepared.setString(4, atendente.getLogin());
        super.prepared.setString(5, atendente.getSenha());
        super.prepared.setString(6, atendente.getEmail());
        super.prepared.setInt(7, atendente.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public void delete(Atendente atendente) throws SQLException {
        super.preparedStatementInitialize(
                "delete from atendente where codate = ?");
        super.prepared.setInt(1, atendente.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Atendente> getAll() throws SQLException {
        List<Atendente> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from atendente");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Atendente(resultSetRows.getInt("codate"),
                    resultSetRows.getString("nomate"),
                    resultSetRows.getInt("cpfate"),
                    resultSetRows.getInt("telate"),
                    resultSetRows.getString("usuate"),
                    resultSetRows.getString("senate"),
                    resultSetRows.getString("emacli")));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    public Boolean procurarLogin(String login) throws SQLException {
    
        Boolean resultado = false;
        super.preparedStatementInitialize("SELECT * FROM atendente WHERE usuate = ? ");
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
        super.preparedStatementInitialize("SELECT senate FROM atendente WHERE usuate = ? ");
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
}
