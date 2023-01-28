# City-Application
Configuration

1.Create a database named "testapp".

2.Import the project to a IDE as a Maven project.

3.Run the "CityApplication.java".

4.Run Sql commands in the "sql.txt" file.

5.Use the REST Api client tool(postman) to test below endpoints.

6.use the users in the sql.txt file to login.

"http://localhost:8080/login" - POST Request to generate the JWT Token

![image](https://user-images.githubusercontent.com/23737031/215235556-a3169e44-029d-4ed6-9de7-3ada41c6738e.png)

"http://localhost:8080/api/cities" - GET Request to get paginated cities

![image](https://user-images.githubusercontent.com/23737031/215235807-c61e5af1-a0ba-4528-9d07-dd94b01f9d1f.png)

![image](https://user-images.githubusercontent.com/23737031/215235881-82a0435e-974e-47ad-96e3-cf9d99ef416e.png)

"http://localhost:8080/api/city" - PUT Request to update city by name or image(link)

![image](https://user-images.githubusercontent.com/23737031/215236328-08828525-3850-4e56-b5a1-1bd1804958d2.png)

"http://localhost:8080/api/city/{cityName}" - GET Request to search by name

![image](https://user-images.githubusercontent.com/23737031/215236409-31e67de9-d938-4e6a-b39a-470c4b9a2086.png)




