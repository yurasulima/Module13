package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    static String url = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {

//        int id = createNewUser();
//        getUsersById(id);
//        getUsersByUsername("testuser");
//        updateUser(id);
//        deleteObject(id);
        int userId = 1;
        int lastPost = getLastPost(userId);
        getCommentsById(lastPost, userId);

        getTodos(userId);
    }

    public static void getTodos(int userId) {
        try {
            URL apiUrl = new URL("https://jsonplaceholder.typicode.com/users/"+userId+"/todos/");
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseBody = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }
                System.out.println("responseBody = " + responseBody);
                in.close();
                JSONArray todos = new JSONArray(responseBody.toString());
                StringBuilder text = new StringBuilder();
                for (Object userTodos : todos) {
                    JSONObject userTodo = (JSONObject) userTodos;
                    if (userTodo.getBoolean("completed")) {
                        System.out.println("usertodos = " + userTodo);
                        text.append("\n").append(userTodo.getString("title"));
                    }
                }
                System.out.println("todos = " + text);
                // return js.getJSONObject(js.length() - 1).getInt("id")
            } else {
                System.out.println("GET response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }



    public static void deleteObject(int id) {
        String url = "https://jsonplaceholder.typicode.com/users/" + id;

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("ID " + id);
            } else {
                System.out.println("DELETE response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
    public static void getUsers() {
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseBody = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }
                in.close();
                JSONArray js = new JSONArray(responseBody.toString());
                System.out.println("userId = " + js.getJSONObject(js.length() - 1).getInt("id"));
            } else {
                System.out.println("GET response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
    public static int getLastPost(int userId) {
        try {
            URL apiUrl = new URL("https://jsonplaceholder.typicode.com/users/"+userId+"/posts/");
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseBody = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }
                System.out.println("responseBody = " + responseBody);
                in.close();
                JSONArray js = new JSONArray(responseBody.toString());
                return js.getJSONObject(js.length() - 1).getInt("id");
            } else {
                System.out.println("GET response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
        return 1;
    }
    public static void getCommentsById(int post, int userId) {
        try {

            String url = "https://jsonplaceholder.typicode.com/posts/" + post + "/comments";
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseBody = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }
                in.close();
                System.out.println("COMMENTS: " + responseBody);
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("user-"+userId+"-post-"+post+"-comments.json"), StandardCharsets.UTF_8))) {
                    writer.write(responseBody.toString());
                }
            } else {
                System.out.println("GET response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
    public static void getUsersById(int idUser) {
        try {

            String url = "https://jsonplaceholder.typicode.com/users/" + idUser;
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseBody = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }

                in.close();

                System.out.println(responseBody);
            } else {
                System.out.println("GET response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
    public static void getUsersByUsername(String userName) {
        try {
            String url = "https://jsonplaceholder.typicode.com/users?username=" + userName;
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseBody = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }
                in.close();
                System.out.println(responseBody);
            } else {
                System.out.println("GET response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
    public static void updateUser(int idUser) {
        String url = "https://jsonplaceholder.typicode.com/users/" + idUser;
        String jsonInputString = "{\"name\":\"Updated Test\",\"username\":\"updTestUser\",\"email\":\"updatedtest@example.com\"}";

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.getOutputStream().write(jsonInputString.getBytes());
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseBody = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }
                in.close();
                System.out.println(responseBody);
            } else {
                System.out.println("PUT response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
    public static int createNewUser() {
        String url = "https://jsonplaceholder.typicode.com/users";
        String jsonInputString = "{\"name\":\"test\",\"username\":\"testuser\",\"email\":\"testuser@example.com\"}";

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.getOutputStream().write(jsonInputString.getBytes());
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseBody = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBody.append(inputLine);
                }
                in.close();
                System.out.println("responseBody = " + responseBody);
                JSONObject js = new JSONObject(responseBody.toString());
                return js.getInt("id");
            } else {
                System.out.println("post Resp Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
        return -1;
    }
}