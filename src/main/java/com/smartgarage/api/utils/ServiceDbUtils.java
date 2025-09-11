package com.smartgarage.api.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ServiceDbUtils {

    private static final String URL = "jdbc:mariadb://localhost:3307/smart_garage";
    private static final String USER = "root";
    private static final String PASS = "root";

    private ServiceDbUtils() {
    }

    public static void setBaseService(int serviceId, int baseServiceId) {
        String sql = "UPDATE services SET base_service_id=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, baseServiceId);
            ps.setInt(2, serviceId);
            int updated = ps.executeUpdate();

            if (updated != 1) {
                throw new RuntimeException("Expected 1 row to update, but instead: " + updated);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set base_service_id to serviceId=" + serviceId, e);
        }
    }
}
