package taskModul13;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class MyHttp {

    public final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public final String USERS_URL = "users";

    public void createUser(User user) throws IOException, URISyntaxException, InterruptedException {

        String jsonUser = new Gson().toJson(user);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + USERS_URL + "/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonUser))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        User fromJson = new Gson().fromJson(response.body(), User.class);
        System.out.println(fromJson.toString());
    }

    public User updateUser(User user) throws URISyntaxException, IOException, InterruptedException {

        String jsonUser = new Gson().toJson(user);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + USERS_URL + "/"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonUser))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), User.class);
    }

    public void deleteUser(int idUser){
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + USERS_URL + "/" + idUser))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("response.statusCode() = " + response.statusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   public void showUserByID( int idUser){
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + USERS_URL + "/" + idUser))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            User fromJson = new Gson().fromJson(response.body(), User.class);
            System.out.println(fromJson.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User[] showUserByUsername(String username){
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + USERS_URL + "?username=" + username))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), User[].class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> showAllUsers() throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + USERS_URL + "/"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Type typeOfObjectsList = new TypeToken<List<User>>() {}.getType();
             return  new Gson().fromJson(response.body(), typeOfObjectsList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getOpenTasksForUser(int userId) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        List<String> openTasks = new ArrayList<>();
        try {
            String url = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos?completed=false";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String[] tasks = response.body().split("\\},\\s*\\{");
            for (String task : tasks) {
                openTasks.add(task);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return openTasks;
    }
}
