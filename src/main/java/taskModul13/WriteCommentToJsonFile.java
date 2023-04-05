package taskModul13;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class WriteCommentToJsonFile {

    private final String URL = "https://jsonplaceholder.typicode.com/users/";

    public void downloadCommentsForLastPostOfUser(int userId) {

        String postsJson = getUrlContents(URL + userId + "/posts");
        int lastPostId = getLastPostId(postsJson); // метод для пошуку останнього поста

        String commentsUrl = "https://jsonplaceholder.typicode.com/posts/" + lastPostId + "/comments";
        String commentsJson = getUrlContents(commentsUrl);
        String filename = "user-" + userId + "-post-" + lastPostId + "-comments.json";
        writeToFile(filename, commentsJson);
    }

    private String getUrlContents(String urlString) {
        try {
            URL url = new URL(urlString);
            InputStream stream = url.openStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\Z");
            return scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    private int getLastPostId(String postsJson) {
        String[] posts = postsJson.split("\\},\\s*\\{");
        String lastPost = posts[posts.length - 1];
        return extractIntFromJsonField(lastPost, "id");
    }

    private int extractIntFromJsonField(String jsonString, String fieldName) {
        int index = jsonString.indexOf("\"" + fieldName + "\":");
        int startIndex = jsonString.indexOf(":", index) + 1;
        int endIndex = jsonString.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = jsonString.indexOf("}", startIndex);
        }
        String fieldValue = jsonString.substring(startIndex, endIndex).trim();
        return Integer.parseInt(fieldValue);
    }

    private void writeToFile(String filename, String content) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
