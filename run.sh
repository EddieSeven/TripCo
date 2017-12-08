#!/bin/bash

cd web
npm run build
cp index.html public/
cp styles.css public/
cp -r resources/images/ public/

./ss "java -cp ../target/T25-5.0-jar-with-dependencies.jar edu.csu2017fa314.T25.TripCo" "npm start"
