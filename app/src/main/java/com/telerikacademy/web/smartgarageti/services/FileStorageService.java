package com.telerikacademy.web.smartgarageti.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Service
public class FileStorageService {

    private static final Path FILE_PATH = Paths.get("appointment_requests.csv");

    public void saveAppointmentRequest(
            String name, String email, String phone,
            String vehicleYear, String vehicleMake, String vehicleMileage,
            String appointmentDate, String timeFrame, String[] services,
            String message
    ) throws IOException {

        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
            String header = "Time,Name,Email,Phone,Vehicle Year,Vehicle Make,Vehicle Mileage,Appointment Date,Time Frame,Services,Message\n";
            Files.write(FILE_PATH, header.getBytes(), StandardOpenOption.APPEND);
        }

        StringJoiner line = new StringJoiner(",");
        line.add(LocalDateTime.now().toString());
        line.add(escape(name));
        line.add(escape(email));
        line.add(escape(phone));
        line.add(escape(vehicleYear));
        line.add(escape(vehicleMake));
        line.add(escape(vehicleMileage));
        line.add(escape(appointmentDate));
        line.add(escape(timeFrame));
        line.add(escape(services != null ? String.join(" | ", services) : ""));
        line.add(escape(message));

        Files.write(FILE_PATH, (line.toString() + "\n").getBytes(), StandardOpenOption.APPEND);
    }

    private String escape(String value) {
        return value == null ? "" : value.replace(",", " ").replace("\n", " ").trim();
    }

    public void saveContactRequestToFile(
            String name,
            String email,
            String phone,
            String message,
            MultipartFile pdfFile
    ) throws IOException {

        Path rootPath = Paths.get(System.getProperty("user.dir")); // директорията, от която е стартирано приложението
        Path dataDir = rootPath.resolve("data");
        Files.createDirectories(dataDir);

        Path uploadsDir = dataDir.resolve("uploaded_cvs");
        Files.createDirectories(uploadsDir);

        Path csvFile = dataDir.resolve("contact_requests.csv");
        if (!Files.exists(csvFile)) {
            Files.createFile(csvFile);
            String header = "Time,Name,Email,Phone,Message,CV Filename\n";
            Files.write(csvFile, header.getBytes(), StandardOpenOption.APPEND);
        }

        String filename = "";
        if (pdfFile != null && !pdfFile.isEmpty()) {
            filename = System.currentTimeMillis() + "_" + pdfFile.getOriginalFilename();
            Path savedFile = uploadsDir.resolve(filename);
            System.out.println("Saving to: " + rootPath.toAbsolutePath());
            pdfFile.transferTo(savedFile.toFile()); // записваме файла на коректното място
        }

        StringJoiner line = new StringJoiner(",");
        line.add(LocalDateTime.now().toString());
        line.add(escape(name));
        line.add(escape(email));
        line.add(escape(phone));
        line.add(escape(message));
        line.add(escape(filename));

        Files.write(csvFile, (line.toString() + "\n").getBytes(), StandardOpenOption.APPEND);
    }
}