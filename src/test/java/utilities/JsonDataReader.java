package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import testdata.UserTestData;

public class JsonDataReader {

    public static List<UserTestData> readUsers(String fileName) {

        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream =
                JsonDataReader.class
                        .getClassLoader()
                        .getResourceAsStream("testdata/" + fileName)) {

            if (inputStream == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            return objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<UserTestData>>() {
                    });

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read JSON file: " + fileName,
                    e);
        }
    }
}