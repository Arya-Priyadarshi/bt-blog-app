# bt-blog-app (Admin Blog Application)

# How to Run The Appplication and Application Info.

1. This is a springboot Application created using maven.
2. Go to the main class(BtBlogAppApplication.java) and start the application.
3. Once started, please hit the the below API end point get Users and their associated Blogs
   http://localhost:9090/bt/admin/usersAndBlogs.
4. Here I have used CompletableFuture to get  non blocking API respones from 2 different API at the same time.

# How can it be improved
1. Here i have only used one test case becuse of Time constraints.
2. As the above API has to be used only by admin, Role based access control has to be used in the above API


