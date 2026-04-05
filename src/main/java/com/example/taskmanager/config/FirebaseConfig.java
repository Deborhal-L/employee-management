package com.example.taskmanager.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {

            String firebaseConfig = System.getenv("FIREBASE_CONFIG");

            if (firebaseConfig == null) {
                throw new RuntimeException("FIREBASE_CONFIG env variable not set");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(
                            new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8))
                    ))
                    .setDatabaseUrl("https://task-manager-f2e6a-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            System.out.println("Firebase Connected ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}