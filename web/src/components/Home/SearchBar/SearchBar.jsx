import React, {Component} from 'react';
import InlineSVG from 'svg-inline-react';


class SearchBar extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            queryResults: [],
            svgResults: null,
            input: ""
        };
    }
    onSubmit(e) {
        e.preventDefault();
        var query = this.query.value;
        // var image = this.svg;
        console.log("Searched for:");
        console.log(query);
        // console.log(image);
        this.fetch("queryA", query);
        // this.fetch("svg", image);
    }

    render() {
        let serverLocations;
        let locs;
        let svg;
        let renderedSvg;

        if (this.state.queryResults) { // if this.state.serverReturned is not null
            // set local variable to results of sent query
            serverLocations = this.state.queryResults;
            console.log("State Loaded.");
            console.log(this.state);

            // console.log(serverLocations);

            /* Create an array of HTML list items. The Array.map function in Javascript passes each individual element
            * of an array (in this case serverLocations is the array and "location" is the name chosen for the individual element)
            * through a function and returns a new array with the mapped elements.
            * In this case f: location -> <li>location.name</li>, so the array will look like:
            * [<li>[name1]</li>,<li>[name2]</li>...]
            */

            locs = serverLocations.map((location) => {
                console.log(location.start.name);
                    return <li key={location.start.id}>
                        <table className="results-table">
                        <thead>
                            <tr>
                                <th> Name </th>

                                <th> ID </th>

                                <th> Latitude </th>

                                <th> Longitude </th>

                                <th> Municipality </th>

                                <th> Type </th>

                                <th> Wiki Link </th>

                                <th> Home Link </th>

                                <th> Distance </th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    {location.start.name}
                                </td>
                                <td>
                                    {location.start.id}
                                </td>
                                <td>
                                    {location.start.latitude}
                                </td>
                                <td>
                                    {location.start.longitude}
                                </td>
                                <td>
                                    {location.start.municipality}
                                </td>
                                <td>
                                    {location.start.type}
                                </td>
                                <td>
                                    {location.start.wikipedia_link}
                                </td>
                                <td>
                                    {location.start.home_link}
                                </td>
                                <td>
                                    {location.distance}
                                </td>
                            </tr>
                        </tbody>
                        </table>

                    </li>;
            });

            if(this.state.svgResults){
                svg = this.state.svgResults;
                renderedSvg = <InlineSVG src={svg.contents}></InlineSVG>;
            }
            console.log(svg);
            console.log(renderedSvg);
            console.log(this.state.svgResults);
            console.log("Map created.");

        }
        // Once the server sends back an SVG, set the local variable "renderedSvg" to be the image
        //if (this.state.svgResults) {
        //   svg = this.state.svgResults;
        //   renderedSvg = <InlineSVG src={svg.contents}></InlineSVG>;
        //}


        return  (
        <div className = "searchbox">
            <p>What kind of places would you like to visit?</p>
            <form>
                <input
                  ref={(c) => this.query = c}
                  type="search"
                  name="destination-search"
                  size="70"
                  placeholder="Search destinations..."
                />
                <button type="submit" onClick={this.onSubmit.bind(this)}>Search</button>
            </form>
            <div className="svg-container">{renderedSvg}</div>
            <div className="results-list">
                <ul>{locs}</ul>
            </div>
        </div>
        );
    }

    // This function sends `input` to the server and updates the state with whatever is returned
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
            console.log(clientRequest);

           // Attempt to send `clientRequest` via a POST request
           // Notice how the end of the url below matches what the server is listening on (found in java code)
           // By default, Spark uses port 4567
           let jsonReturned = await fetch(`http://localhost:4567/search`,
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
}
export default SearchBar;