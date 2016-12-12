## Building the code

<a name="Build_Requirements"></a>
#### Build Requirements

* [Scala 2.11.8+] (http://scala-lang.org/download/)

#### Building the application

```bash
$ sbt clean "project backend" fastOptJS::webpack
```

#### Running the application

```bash
$ cd ./app-backend
$ node ./server.js    
```

The above will startup the application on port 1337 by default. To listen/bind to a different port. Set the "port" environment
variable.
