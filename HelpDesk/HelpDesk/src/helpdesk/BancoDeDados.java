/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk;

import java.util.ArrayList;
import java.util.List;
import helpdesk.model.Atendente;
import helpdesk.model.Chamado;
import helpdesk.model.Cliente;
import helpdesk.model.Empresa;
/**
 *
 * @author Bileko
 */
public class BancoDeDados {
    public static List<Empresa> empresas = new ArrayList();
    public static List<Atendente> atendentes = new ArrayList();
    public static List<Cliente> clientes = new ArrayList();
    public static List<Chamado> chamados = new ArrayList();
}
