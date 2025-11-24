package co.edu.udec.almacen.infrastructure.adapter.out.postgres;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionPostgreSQL {

    private static final String URL = "jdbc:postgresql://localhost:5432/almacendistriluz";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
