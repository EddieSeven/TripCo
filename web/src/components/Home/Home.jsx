import React, {Component} from 'react';
import Search from './Search/Search.jsx';
import Results from './Results/Results.jsx';
import LoadsaveDropzone from './LoadsaveDropzone/LoadsaveDropzone.jsx';
import Map from './Map/Map.jsx';

class Home extends React.Component {
    constructor(props){
        super(props);
        this.handleData = this.handleData.bind(this);
        this.onSubmitItinerary = this.onSubmitItinerary.bind(this);
        this.fetch = this.fetch.bind(this);
        this.fetchItinerary = this.fetchItinerary.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleOptChange = this.handleOptChange.bind(this);
        this.state = {
            ids: [],
            searchQuery: '',
            queryResults: [],
            input: "",
            allPairs: [],
            sysFile: [],
            miles: true,
            opt: 2
        };
    }

	handleOptChange(event) {
		let name = event.target.value;
		let Opt = parseInt(name);
		this.setState({
			opt: Opt,
			searchQuery: this.state.searchQuery
		});
	}

	handleInputChange(event) {
		let name = event.target.value;
		let isM = true;
		if (name === "miles") {
			isM = true;
		} else {
			isM = false;
		}
		this.setState({
			miles: isM
		});
	}

    handleData(data){
        console.log("Searched For: " + data);
        this.fetch("select", data);
    }

    handleAdd(index){
        if(index > -1){
            console.log("Element to be added: ", this.state.queryResults[index].attributes[0]);
            this.state.ids.push(this.state.queryResults[index].attributes[0]);
            console.log("ID list after add: ", this.state.ids);
            this.setState({
                ids: this.state.ids
            });
        }
    }

    handleRemove(index){
        if(index > -1){
            console.log("Element to be removed: ", index);
            this.state.ids.splice(index, 1);
            console.log("ID list after remove: ", this.state.ids);
            this.setState({
                ids: this.state.ids
            });
        }
    }

    onSubmitItinerary(e) {
        console.log("ID List to be sent to Plan: ", this.state.ids);
        this.fetchItinerary("itinerary", this.state.ids);
    }

    render() {
        let serverLocations;
        let locs;
        let tempIds = [];

		// Send locs to QueryResults.js

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
                                <button type="submit"> Save </button>
                            </li>
                            <li>
                                <LoadsaveDropzone
                                    browseFile={this.browseFile.bind(this)}
                                />
                            </li>
                            <li><button type="submit" onClick={this.onSubmitItinerary}> Plan </button></li>
                            <li><button type="submit"> Reset </button></li>
                        </ul>
                    </span>
                    <span className="toggle">
                        <table>
                            <tr>
                                <td><label> Miles <input type="radio" name="metric" value="miles" defaultChecked onClick={this.handleInputChange} /></label></td>
                            </tr>
                            <tr>
                                <td><label> Kilometers <input type="radio" name="metric" value="km" onClick={this.handleInputChange} /></label></td>
							</tr>
                        </table>
                    </span>
                </div>

            </div>

            <div className="right-head-container">

                <div className="svg-container">
                    <Map
                        containerElement={<div style={{ height: `100%` }} />}
                        mapElement={<div style={{ height: `100%` }} />}
                        allCoors = {this.state.allPairs}
                    />
                </div>

                <div className="opt-select">
                    <table>

                        <tr>
                            <td><label> No optimization <input type="radio" name="opt" value="0" onClick={this.handleOptChange} /></label></td>

                            <td><label> Nearest Neighbor <input type="radio" name="opt" value="1" onClick={this.handleOptChange} /></label></td>

                        </tr>
                        <tr>
                            <td><label> 2-Opt Algorithm <input type="radio" name="opt" value="2" defaultChecked onClick={this.handleOptChange} /></label></td>

                            <td><label> 3-Opt Algorithm<input type="radio" name="opt" value="3" onClick={this.handleOptChange} /></label></td>
                        </tr>
                    </table>

                </div>

            </div>
            
            <Results
                sLocs={this.state.queryResults}
                ids={this.state.ids}
                itin={this.state.allPairs}
                addHandlerFromHome={this.handleAdd.bind(this)}
                removeHandlerFromHome={this.handleRemove.bind(this)}
            />

        </div>
        );
    }

    async browseFile(file) {
		let Ids = [];
		for (let i = 0; i < file.destinations.length; i++) {
			let id = file.destinations[i];
			console.log("ID = " + id);
			Ids.push(id);
		}
		console.log(Ids);
		this.setState({
			ids: Ids
		});
		console.log("Load state: "+this.state.ids);
    }

    // This function sends `input` to the server and updates the state with whatever is returned
    async fetch(type, input) {
       // Create object to send to server

       /*  IMPORTANT: This object must match the structure of whatever
           object the server is reading into (in this case ServerRequest).
           Notice how we give both requests the same format */
       let clientRequest;

       clientRequest = {
           request: "select",
           description: input
       };

        console.log("Client Request: ", clientRequest);

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
           console.log("Got back ", returnedJson.points);

           // if the response field of the returned json is "queryA", that means the server responded to the SQL query request
           if (clientRequest.request === "select") {
               this.setState({
                   queryResults: returnedJson.points
               });
            }
		   	//let itin = [];
		   	//for (let i = 0; i < returnedJson.points.length; i++) {
			//	itin.push(returnedJson.points[i].attributes[0]);
			//}
		   	//this.setState({
			//	ids: itin
			//});
            console.log("queryResults populated with: ", this.state.queryResults);
            console.log("IDs populated with: ", this.state.ids);

       } catch (e) {
           console.log("Error talking to server");
       }

    }

   async fetchItinerary(type, input){
	   console.log("Fetch itin. called. Input is: ", input);
        let itineraryRequest;

        itineraryRequest = {
            request: "itinerary",
			idList: input,
			optimization: this.state.opt,
			miles: this.state.miles
        };

        try{
       		console.log("Itinerary request: ", itineraryRequest);

			let serverUrl = window.location.href.substring(0, window.location.href.length - 6) + ":2530/search";
		   console.log(serverUrl);
           let jsonReturned = await fetch(serverUrl,
               {
                   method: "POST",
                   body: JSON.stringify(itineraryRequest)
               });

           // Wait for server to return and convert it to json.
           let ret = await jsonReturned.json();
           let returnedJson = JSON.parse(ret);

			console.log("Fetch itin. Returned JSON: ");
			console.log(returnedJson);

            this.setState({
                allPairs: returnedJson.locations,
            });


            console.log("In fetchItinerary, allPairs holds:  ", this.state.allPairs);


        } catch (e) {
            console.log("Fetch itin. error:"+e);
        }
    }
}export default Home;
