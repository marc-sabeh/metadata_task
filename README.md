# metadata_task

Here is the task asked of me for mete data company <br>
All tech stacks asked in the description are used
##Start the app

You can start by running the Dockerized project

# install dependencies
```bash
# docker compose the first container to run the postgres image 
$ docker-compose up postgresMetadata
```

When the container is running please go to maven and then install
```bash
# or run
$ mvn install
```

then when the target folder is available you can now run
```bash
# docker compose to run all containers which will automatically run the application 
# and you can start to check it out
$ docker-compose up --build
```

Now that the application is running you can open it on localhost:8080

<hr>
##For the apis
3 students and 3 courses will be automatically added to the db, so you can check the get requests

###Students Crud


```bash
# Get all students
Get http://localhost:8080/api/v1/student 
```

```bash
# will insert a new student to the db 
Post http://localhost:8080/api/v1/student 

Payload:{
    "name": "sami",
    "email": "sami@gmail.com",
    "phone": "8193737",
    "address": "Germany",
    "dob": "1995-12-17"
}
```

```bash
# will update the student data 
Put http://localhost:8080/api/v1/student/:studentId

Payload:{
    "name": "john",
    "email": "john@gmail.com",
    "phone": "8193737",
    "address": "Germany",
    "dob": "1995-12-17"
}
```

```bash
# will delete a student
Delete http://localhost:8080/api/v1/student/:studentId
```

<hr>

###Course Crud


```bash
# Get all courses
Get http://localhost:8080/api/v1/course 
```

```bash
# will insert a new course to the db 
Post http://localhost:8080/api/v1/course 

Payload:{
    "title": "algorithm",
    "semester": "2021",
    "location": "block b",
    "instructor": "John",
    "start_date": "2021-09-05"
}
```

```bash
# will update the course data
Put http://localhost:8080/api/v1/course/:courseId

Payload:{
    "title": "english",
    "semester": "2021",
    "location": "block d",
    "instructor": "jane"
}
```

```bash
# will delete a course
Delete http://localhost:8080/api/v1/course/:courseId
```


<hr>

###Course and Students


```bash
# Get all student course
Get http://localhost:8080/api/v1/studentcourse 
```

```bash
# will insert a new course for a student
# we can call this api for multiple courses in a loop we call this api 
#It has limitation like only 50 students per course and 5 courses per student
Post http://localhost:8080/api/v1/studentcourse

Payload:{
    "student": {
        "name": "alex"
    },
     "course": {
        "title": "math"
    }
}
```

```bash
## All those are the same api call with different params

# This is the report api it will get all students and courses relations
Get http://localhost:8080/api/v1/studentcourse/report 

#This one will get the course that do not have any students
Get http://localhost:8080/api/v1/studentcourse/report?Null=course

#This one will get the student that are not enrolled in any course
Get http://localhost:8080/api/v1/studentcourse/report?Null=course

#This one will get all courses of Mariam
Get http://localhost:8080/api/v1/studentcourse/report?fieldName=studentName&fieldValue=Mariam

#This one will get all students enrolled in the math course
Get http://localhost:8080/api/v1/studentcourse/report?fieldName=courseTitle&fieldValue=math
```
<hr>

##Unit Testing

Unit testing are done in the test folder for all methods that were done <br>
Using Junit5 and Mockito

<br>
Thank you for your time!