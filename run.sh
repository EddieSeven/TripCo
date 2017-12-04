#!/bin/bash

cd web
npm run build
cp index.html public/
cp -r resources/ public/

./ss "java -cp ../target/T25-4.0-jar-with-dependencies.jar edu.csu2017fa314.T25.TripCo" "npm start"
