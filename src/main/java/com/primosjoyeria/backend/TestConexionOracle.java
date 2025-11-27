package com.primosjoyeria.backend;

import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexionOracle {

    public static void main(String[] args) {

        System.out.println("Intentando conectar a Oracle...");

        // Ruta ABSOLUTA al directorio del wallet
        String walletPath = "C:/oracle/wallet_primos"; // <-- CAMBIA A TU RUTA REAL

        // Propiedades para el wallet
        System.setProperty("oracle.net.tns_admin", walletPath);
        System.setProperty("oracle.net.ssl_server_dn_match", "true");

        // Alias tal como aparece en tnsnames.ora (ej: PRIMOS_HIGH, DB2024_HIGH, etc.)
        String url = "jdbc:oracle:thin:@PRIMOS_HIGH"; // <-- CAMBIA AL TUYO
        String user = "TU_USUARIO";
        String pass = "TU_PASSWORD";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("✅ Conexión exitosa a Oracle");
        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL al conectar:");
            e.printStackTrace();
        }
    }
}
