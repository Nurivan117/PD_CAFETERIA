/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.*;

public class AccionesMBD {

    protected String sqlQuery;
    protected String table;
    protected Statement executer;
    protected ResultSet dataSet;
    protected Conexion conexion;

    public AccionesMBD() {
        conexion = new Conexion("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/bdcafeteria", "root", "6*8ny3FEtihK");
    }

    public String registrarAlta(String nombreTabla, String consultaSQL) {
        int i;
        String resultado;
        table = nombreTabla;
        try {
            executer = conexion.ObtenerConexion().createStatement();
            dataSet = executer.executeQuery("SELECT * FROM " + table + " LIMIT 1");
            sqlQuery = "INSERT INTO " + nombreTabla + " (";
            for (i = 1; i <= dataSet.getMetaData().getColumnCount() - 1; i++) {
                sqlQuery = sqlQuery + dataSet.getMetaData().getColumnName(i) + ",";
            }
            sqlQuery = sqlQuery + dataSet.getMetaData().getColumnName(i) + ") VALUES (" + consultaSQL + ")";
            executer.executeUpdate(sqlQuery);
            resultado = "Datos almacenados exitosamente.";
        } catch (SQLException e) {
            resultado = e.toString() + "\n" + sqlQuery;
        } finally {
            try {
                dataSet.close();
                executer.close();
            } catch (SQLException e) {
                System.out.print(e.toString());
            }
        }
        return resultado;
    }
}
