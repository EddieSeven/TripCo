import React, {Component} from 'react';
import Head from './Header/Head.jsx';
import Results from './Results/Results.jsx';

class Home extends React.Component {

	async fetch(type, input) {
       // Create object to send to server

       /*  IMPORTANT: This object must match the structure of whatever
           object the server is reading into (in this case ServerRequest).
           Notice how we give both requests the same format */
       let clientRequest;

       clientRequest = {
           request: "queryA",
           description: input
       };

       try {
		    console.log("Sending query: ");
            console.log(clientRequest);

           // Attempt to send `clientRequest` via a POST request
           // Notice how the end of the url below matches what the server is listening on (found in java code)
           // By default, Spark uses port 4567
            //TODO: Replace localhost with name of remote server
           let serverUrl = window.location.href.substring(0, window.location.href.length - 6) + ":4567/search";
           let jsonReturned = await fetch(serverUrl,
               {
                   method: "POST",
                   body: JSON.stringify(clientRequest)
               });

           // Wait for server to return and convert it to json.
           let ret = await jsonReturned.json();
           let returnedJson = JSON.parse(ret);

           // Log the received JSON to the browser console
           console.log("Got back ", ret);

           // if the response field of the returned json is "queryA", that means the server responded to the SQL query request
           if (clientRequest.request === "queryA") {
               this.setState({
                   queryResults: returnedJson.locations,
                   svgResults: returnedJson.svg
               });

           // if it's not, we assume the response field is "svg" and contains the an svg image
           } else {
              this.setState({
                  svgResults: JSON.parse(ret)
              })
           }

           console.log(returnedJson);

           // Print on console what was returned
           // Update the state so we can see it on the web
       } catch (e) {
           console.error("Error talking to server");
           console.error(e);
       }
    }

    render() {
        return <div className="home-container">
            <div className="inner">
                <Head fetchFromServer={this.fetch.bind(this)}/>
		<br />
                <Results />
            </div>
        </div>
    }
}export default Home;
