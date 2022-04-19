/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.dao;

import util.ConexaoUtil;

/**
 *
 * @author Desenvolvedor
 */
public class GeraTabela {
    public static void main(String[] args) {
        ConexaoUtil conn = new ConexaoUtil();
        conn.conectar();
        conn.desconectar();
    }
}
