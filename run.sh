#!/bin/bash

cd web
npm run build
./ss "java -cp ../target/T25-4.0-jar-with-dependencies.jar edu.csu2017fa314.T25.TripCo" "npm start"
