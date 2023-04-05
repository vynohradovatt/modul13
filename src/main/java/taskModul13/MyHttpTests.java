package taskModul13;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class MyHttpTests {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        User ross = new User("Ross", "Geller", "ross@mail.com",
                new Adress("5 avenue", "566", "New York", "432424"),
                new Geo("-54.545", "87.495"),
                "45353534", "paleontologist.com", new Company("test", "test", "test"));

        MyHttp myHttp = new MyHttp();
        myHttp.createUser(ross);// задача 1.1 {створити новий об'єкт} - id повинно бути = 11

        User updateUser = myHttp.updateUser(ross);  // задача 1.2. {оновити об'єкт}
        System.out.println(updateUser.toString());

        myHttp.deleteUser(1); // задача 1.3 {видалити об'єкт}

        List<User> users = myHttp.showAllUsers(); // задача 1.4 {отримати інформацію про всіх користувачів}

        for (User user : users) {
            System.out.println(user.toString());
        }

        myHttp.showUserByID(1); // задача 1.5 {показати користувача за id}

        User[] antonette = myHttp.showUserByUsername("Antonette"); // задача 1.6 {показати юзера за його username}
        System.out.println(Arrays.toString(antonette));

        // задача 2
        WriteCommentToJsonFile writeCommentToJsonFile = new WriteCommentToJsonFile();
        writeCommentToJsonFile.downloadCommentsForLastPostOfUser(2);

        // задача 3
        List<String> tasksForUser = myHttp.getOpenTasksForUser(3);
        for (String tasks : tasksForUser) {
            System.out.println(tasks);
        }

    }
}
