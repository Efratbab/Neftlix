# Welcome to EDY-Netflix app
A video streaming platform for android OS

## How to Run the App?
1. Open the project in Android Studio.
2. Open a hotspot on your cellphone and connect your computer to your network. (Note for us- how to automatically insert it?)
3. Build and spin up the docker container and the server (Detailed instructions: --link to web github--)
4. Optional- If you have a cellphone with an android OS, you can connect your cellphone to the computer with a USB cable and run
    the app on it (SHOULD WE WRITE IT AT ALL? we will need to explain how to turn the phone to be on a developer mode etc...) if you use the emulator 
    from android, do the ip 10.0.2.2
5. Connect to MongoDB with the hotspot ipv4 
6. Click the Run button and start watching movies on EDY-Netflix!
7. HOW ABOUT DOCKER?

## An Overview of the Work Process
The app is built according to MVVM architecture, using Room and Repository pattern. The app is native android programmed.
### Backend development: 
Using Retrofit and RESTful API we managed to get a client-server communication. Using Room we created a small relational DB such that contacting the
server will happen as little as possible.
### Frontend development:
App UI: Using XML and a suitable Java file we created a flow of user- friendly screens, where the user can easily watch, search and filter movies.
If the user is an admin (required manually adding),he/she can also easily upload/delete/edit movies or categories (like horror, comedy etc).





