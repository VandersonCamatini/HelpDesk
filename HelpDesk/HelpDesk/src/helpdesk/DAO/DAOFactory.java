
package helpdesk.DAO;

public class DAOFactory {
    
    public static AtendenteDAO getAtendenteDAO(){
        return new AtendenteSqlDAO();
    }
    public static ClienteDAO getClienteDAO(){
        return new ClienteSqlDAO();
    }
    
    public static EmpresaDAO getEmpresaDAO(){
        return new EmpresaSqlDAO();
    }
    
    public static ChamadoDAO getChamadoDAO(){
        return new ChamadoSqlDAO();
    }
}

