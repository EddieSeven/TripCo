# Sprint 1 Getting Started
## Getting Started
To use this project first make sure you have `npm` or `yarn` installed. These are dependency managers that will download everything you need for the project.
 
You will also need `mvn` which is another dependency manager. You can download this as a standalone program and setup following the instructions found [here](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html). Otherwise, you have the option of installing IntelliJ and selecting the `mvn` plugin on configuration.
- Note: a `pom.xml` has already been added to the repo and does not need to be generated.
 
## Prerequisites
You must have Maven installed to build, package, and run your tests for your Java program. Maven manages library dependencies in Java. It is available on the lab machines (type `mvn -v` to verify), and it can be installed to your local machine through your default package manager on Linux or through [Homebrew](https://brew.sh/) for Mac. For example:
 
Linux: `sudo apt-get install maven` 
Mac: `brew install maven`
 
To verify maven was installed type:
 
`mvn -v`
- Note that you may have to set your JAVA_HOME environment variable to point to your JDK installation. Instructions on this can be found here (Thanks Chris W. for pointing this out)
 
`npm` or `yarn` must be installed on the machine you wish to test your web app on.
You can easily install `npm` through [Homebrew](https://brew.sh/) or by downloading [Node.js](https://nodejs.org/en/).
- Note: `npm` is already installed on the CS 120 lab machines.
 
TripCo is built with:
- React
- Webpack
- React-hot-loader
- SASS
- Java
 
## Installing
If you have not already, clone the repo and enter it:
 
`$ git clone git@github.com:csu2017fa314/[your repo].git # or use https variant`  
`$ cd [your repo]`
 
Install all `mvn` dependencies:
 
`$ mvn package`
 
This will download everything referenced in the `pom.xml` and create an executable jar.
- Note: the `pom.xml` file has already been generated for your convenience.
`mvn` is meant to be used when distributing
 
Move into the web directory:
 
`$ cd web`
 
Now run the following in the `web` directory:
 
`$ npm install`  
`# or`  
`$ yarn install`

This will download all dependencies needed to run the app.
- Note: If you encounter an `EINTEGRITY` error, run `npm cache verify`. This will verify npm's cache. If this does not resolve the problem, run `rm -rf node_modules`. This will clear the node modules.
 
## Running the backend server (java)
 
Run the following command in the root directory:
`java -cp target/[name of jar generated by maven] edu.csu2017fa314.T##.TripCo`
 
This runs the `TripCo` class from the package `edu.csu2017fa314.[Your Repo]`. By default, Maven packages the JAR in a folder called target. The general syntax for running a jar generated by Maven is:
 
`java -cp [path to jar]/[name of jar] [package name].[class name]`
 
## Running the web server
 
Run one of the following commands in the `web` directory:
 
`$ npm run dev`  
`# or`  
`$ yarn run dev`  
This will open the default web browser and navigate to `localhost:8080`.
From here, you can update the `.jsx` files and `localhost` will automagically reload your changes.
 
The webpage is built on [React.js](https://facebook.github.io/react/), a Javascript library built by Facebook for creating user interfaces. Unlike a traditional web page, the visual elements of the web page are specified in .js or .jsx files instead of .html files. You will primarily need to edit the `Home.jsx` to complete the assignment but you can modify other files if you would like to beautify your project.
 
## Advanced
This section requires a basic understanding of how networks function.
 
If you wish to test on your local browser but want to run the dev server on the remote cs machines, you have the option of dynamically port forwarding:
 
`$ ssh [eid]@denver.cs.colostate.edu -D 8008 # other port number can be used here`  
Run this command in a separate terminal from your webpack server (Follow the instructions above from the remote machine).
 
Now open your browser and add a socks proxy via the settings page with host:`localhost`, port:`8008`. The port must match the port number specified in the ssh command.
- Note: There are browser add-ons that will allow for toggling the socks proxy on and off. This will allow you to access the internet when the dynamic forwarding is active.
You should now be able to navigate to `localhost:8080` and view your app.