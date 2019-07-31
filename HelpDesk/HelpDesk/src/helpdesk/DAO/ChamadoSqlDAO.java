/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.DAO;

import helpdesk.HelpDesk;
import helpdesk.model.Chamado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Senai
 */
public class ChamadoSqlDAO extends ConnectionFactory implements ChamadoDAO{
    
    @Override
    public void save(Chamado chamado) throws SQLException {
        String[] codigoGerado = {"codcha"}; 
        super.preparedStatementInitialize("insert into chamado (codemp, codsol, datini, titulo, descri, priori, datfin, status, resposta) values (?,?,?,?,?,?,?,?,?)",codigoGerado);
        super.prepared.setInt(1, chamado.getCodigoEmpresa());
        super.prepared.setInt(2, chamado.getCodigoCliente());
        super.prepared.setString(3, chamado.getDataInicial());
        super.prepared.setString(4, chamado.getTitulo());
        super.prepared.setString(5, chamado.getDescricao());
        super.prepared.setString(6, chamado.getPrioridade());
        super.prepared.setString(7, chamado.getDataFinal());
        super.prepared.setString(8, chamado.getStatus());
        super.prepared.setString(9, chamado.getResposta());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar o novo chamado !");
        }
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            chamado.setCodigo(resultSetRows.getInt("codcha"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }
    

    @Override
    public void update(Chamado chamado) throws SQLException {
        super.preparedStatementInitialize("update chamado set codate = ? , datfin = ?, resposta = ?, status = ? WHERE codcha = ?");
            super.prepared.setInt(1, chamado.getCodigoAtendente());
            super.prepared.setString(2, chamado.getDataFinal());
            super.prepared.setString(3, chamado.getResposta());
            super.prepared.setString(4, chamado.getStatus());
            super.prepared.setInt(5, chamado.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar a chamada");
        }
        super.closePreparedStatement();
    }

    @Override
    public void delete(Chamado chamado) throws SQLException {
        super.preparedStatementInitialize(
                "delete from chamado where codcha = ?");
        super.prepared.setInt(1, chamado.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar o chamado");
        }
        super.closePreparedStatement();
    }

    @Override
    public List<Chamado> getAll() throws SQLException {
        
        List<Chamado> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from chamado");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()){
            rows.add(new Chamado(resultSetRows.getInt("codcha"),
                    resultSetRows.getInt("codemp"),
                    resultSetRows.getInt("codate"),
                    resultSetRows.getInt("codsol"),
                    resultSetRows.getString("datini"),
                    resultSetRows.getString("datfin"),
                    resultSetRows.getString("titulo"),
                    resultSetRows.getString("descri"),
                    resultSetRows.getString("priori"),
                    resultSetRows.getString("resposta"),
                    resultSetRows.getString("status")));     
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
      @Override
    public List<Chamado> pesquisarPorPrioridade(String prioridade) throws SQLException {
        
        List<Chamado> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from chamado where priori = ?");
        super.prepared.setString(1, prioridade);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()){
            rows.add(new Chamado(resultSetRows.getInt("codcha"),
                    resultSetRows.getInt("codemp"),
                    resultSetRows.getInt("codate"),
                    resultSetRows.getInt("codsol"),
                    resultSetRows.getString("datini"),
                    resultSetRows.getString("datfin"),
                    resultSetRows.getString("titulo"),
                    resultSetRows.getString("descri"),
                    resultSetRows.getString("priori"),
                    resultSetRows.getString("resposta"),
                    resultSetRows.getString("status")));     
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    @Override
    public List<Chamado> getAllCliente(Integer codigo) throws SQLException {
        
        List<Chamado> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from chamado where codsol = ?");
        super.prepared.setInt(1, codigo);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()){
            rows.add(new Chamado(resultSetRows.getInt("codcha"),
                    resultSetRows.getInt("codemp"),
                    resultSetRows.getInt("codate"),
                    resultSetRows.getInt("codsol"),
                    resultSetRows.getString("datini"),
                    resultSetRows.getString("datfin"),
                    resultSetRows.getString("titulo"),
                    resultSetRows.getString("descri"),
                    resultSetRows.getString("priori"),
                    resultSetRows.getString("resposta"),
                    resultSetRows.getString("status")));     
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    public Integer procurarEmpresa(String solicitante) throws SQLException {
        Integer resultado = null;
        super.preparedStatementInitialize("SELECT codemp FROM solicitante WHERE nomsol = ? ");
        super.prepared.setString(1,solicitante);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getInt(1);
            
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }
    
        public Integer procurarSolicitante(String solicitante) throws SQLException {
        Integer resultado = null;
        super.preparedStatementInitialize("SELECT codsol FROM solicitante WHERE nomsol = ? ");
        super.prepared.setString(1,solicitante);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getInt(1);
            
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }

        public String procurarNomeSolicitante(Integer solicitante) throws SQLException {
        String resultado = null;
        super.preparedStatementInitialize("SELECT nomsol FROM solicitante WHERE codsol = ? ");
        super.prepared.setInt(1,solicitante);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
            
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }
        
        public String procurarNomeEmpresa(Integer empresa) throws SQLException {
        String resultado = null;
        super.preparedStatementInitialize("SELECT nomemp FROM empresa WHERE codemp = ? ");
        super.prepared.setInt(1,empresa);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
            
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }
        
        public Integer procurarAtendente(String atendente) throws SQLException {
        Integer resultado = null;
        super.preparedStatementInitialize("SELECT codate FROM atendente WHERE nomate = ? ");
        super.prepared.setString(1,atendente);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getInt(1);
            
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }

        public String procurarNomeAtendente(Integer atendente) throws SQLException {
        String resultado = null;
        super.preparedStatementInitialize("SELECT nomate FROM atendente WHERE codate = ? ");
        super.prepared.setInt(1,atendente);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
            
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }
        
        public String email(Integer codigoSolicitante) throws SQLException {
        String resultado = null;
        super.preparedStatementInitialize("SELECT emasol FROM solicitante WHERE codsol = ? ");
        super.prepared.setInt(1,codigoSolicitante);
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
