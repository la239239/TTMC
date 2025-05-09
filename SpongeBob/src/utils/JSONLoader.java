package utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Question;

public class JSONLoader {

    public static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        String json = loadJsonAsString("@../../resource/question.json");

        // Nettoyer les crochets initiaux et finaux
        json = json.substring(1, json.length() - 1); // Supprime les crochets []

        // Parsing manuel des questions
        String[] entries = json.split("\\},\\s*\\{"); // Sépare chaque question
        for (String entry : entries) {
            questions.add(parseQuestion(entry));
        }
        return questions;
    }

    private static Question parseQuestion(String jsonEntry) {
        // Exemple de parsing manuel des champs
        String theme = extractField(jsonEntry, "theme");
        double difficulty = Double.parseDouble(extractField(jsonEntry, "difficulty"));
        String text = extractField(jsonEntry, "question");
        String answer = extractField(jsonEntry, "answer");

        // Traiter les caractères échappés (en utilisant replace)
        text = unescapeCharacters(text);
        answer = unescapeCharacters(answer);

        return new Question(theme, difficulty, text, answer);
    }

    private static String unescapeCharacters(String text) {
        // Remplacer les caractères échappés spécifiques
        return text.replace("\\u2019", "’")
                   .replace("\\u2013", "–")
                   .replace("\\u2014", "—")
                   .replace("\\u2022", "•")
                   .replace("\\u00E9", "é")
                   .replace("\\u00E8", "è")
                   .replace("\\u00F4", "ô");
        // Ajoute d'autres remplacements selon tes besoins
    }

    private static String extractField(String json, String fieldName) {
        try {
            int start = json.indexOf("\"" + fieldName + "\":") + fieldName.length() + 3;
            int end = json.indexOf("\"", start);
            return json.substring(start, end).trim().replace("\"", "");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'extraction du champ: " + fieldName, e);
        }
    }


    private static String loadJsonAsString(String path) {
        try (InputStream is = JSONLoader.class.getResourceAsStream(path);
             Scanner scanner = new Scanner(is, "UTF-8")) {
            return scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            throw new RuntimeException("Erreur de chargement du JSON: " + path, e);
        }
    }
}