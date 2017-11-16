import React, {Component} from 'react';
import Search from './Search/Search.jsx';
import Results from './Results/Results.jsx';
import LoadsaveDropzone from './LoadsaveDropzone/LoadsaveDropzone.jsx';


class Home extends React.Component {
    constructor(props){
            super(props);
            this.handleData = this.handleData.bind(this);
            this.onSubmit = this.onSubmit.bind(this);
            this.fetch = this.fetch.bind(this);
            this.state = {
                searchQuery: '',
                queryResults: [],
                svgResults: "",
                input: "",
                allPairs: [],
                sysFile: []
            };
    }

    handleData(data){
        //this.setState({
        //    searchQuery: data
        //});
        console.log("Test 1 " + data);
        this.onSubmit(data);
    }

    onSubmit(e) {
        console.log("Searched for:");
        console.log(e);
        this.fetch("queryA", e);
    }

    onSave(e){
    }

    onAddAll(){
    }

    onRemoveAll(){
    }

    render() {
        let serverLocations;
        let locs;
        // TODO: Replace localhost with URL of remote server
        let svg = "http://localhost:2526/svg";
        let renderedSvg;
        let pairs = this.state.allPairs;
        let ps = pairs.map((pp) => {
            return <Pair {...pp}/>;
        });

        if (this.state.queryResults) { // if this.state.serverReturned is not null

            if(this.state.svgResults){
                svg = this.state.svgResults;
                renderedSvg = <InlineSVG src={svg}></InlineSVG>;
            }
            console.log(svg);
            console.log(renderedSvg);
            console.log(this.state.svgResults);
            console.log("Map created.");


            // Send locs to QueryResults.jsx
        }

        return (
        <div className="header-wrapper">
            <div className="header">
                <div className="logo">
                    <img src="../images/tripco-logo-color-small.png" />
                </div>


                <Search handlerFromParent={this.handleData.bind(this)}/>


                <div className="buttons-container">
                    <span className="buttons">
                        <ul>
                            <li>
                                <button type="submit" onClick={this.onSave.bind(this)}>Save</button>
                            </li>
                            <li>
                                <LoadsaveDropzone
                                    browseFile={this.browseFile.bind(this)}
                                    pairs = {ps}
                                />
                            </li>
                            <li><button type="submit" onClick={this.onAddAll()}>Add</button></li>
                            <li><button type="submit" onClick={this.onRemoveAll()}>Delete </button></li>
                        </ul>
                    </span>
                    <span className="toggle">
                        <table>
                            <tr>
                                <td><label> Miles <input type="radio" name="miles-radio" defaultChecked onClick={this.handleInputChange} /></label></td>
                            </tr>
                            <tr>
                                <td><label> Kilometers <input type="radio" name="km-radio" onClick={this.handleInputChange} /></label></td>

                            </tr>
                        </table>
                    </span>
                </div>
            </div>

            <div className="svg-container"><img src="../images/world.svg" /><br />{renderedSvg}</div>


            <Results sLocs={this.state.queryResults} />

        </div>
        );
    }

    async browseFile(file) {
        console.log("Got file: ", file);
        let pairs = [];
        for (let i = 0; i < Object.values(file).length; i++) {
            let start = file[i].start; //get start from file i
            let end = file[i].end; //get end from file i
            let dist = file[i].distance;
            let p = { //create object with start, end, and dist variable
                start: start,
                end: end,
                dist: dist
            };
            pairs.push(p); //add object to pairs array
            console.log("Pushing pair: ", p); //log to console
        }
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
            //TODO: Replace localhost with name of remote server
           let serverUrl = window.location.href.substring(0, window.location.href.length - 6) + ":2530/search";
           let jsonReturned = await fetch(serverUrl,
               {
                   method: "POST",
                   body: JSON.stringify(clientRequest)
               });

           // Wait for server to return and convert it to json.
           let ret = await jsonReturned.json();
           let returnedJson = JSON.parse(ret);

           // Log the received JSON to the browser console
           console.log("Got back ", returnedJson);

           // if the response field of the returned json is "queryA", that means the server responded to the SQL query request
           if (clientRequest.request === "queryA") {
               this.setState({
                   queryResults: returnedJson.locations,
                   svgResults: returnedJson.svg
               });
            }
            console.log(this.state.queryResults);
       } catch (e) {
           console.log("Error talking to server");
       }
    }
}export default Home;
