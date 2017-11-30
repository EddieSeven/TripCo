import React, {Component} from 'react';
import Search from './Search/Search.jsx';
import Results from './Results/Results.jsx';
import LoadsaveDropzone from './LoadsaveDropzone/LoadsaveDropzone.jsx';
import InlineSVG from 'svg-inline-react';

class Home extends React.Component {
    constructor(props){
            super(props);
            this.handleData = this.handleData.bind(this);
            this.onSubmit = this.onSubmit.bind(this);
			this.onSubmitItinerary = this.onSubmitItinerary.bind(this);
            this.fetch = this.fetch.bind(this);
            this.fetchItinerary = this.fetchItinerary.bind(this);
			this.handleInputChange = this.handleInputChange.bind(this);
			this.handleOptChange = this.handleOptChange.bind(this);
            this.state = {
                ids: [],
                searchQuery: '',
                queryResults: [],
                svgResults: "",
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
			opt: Opt
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
        //this.setState({
        //    searchQuery: data
        //});
        console.log("Test 1 " + data);
        this.onSubmit(data);
    }

    onSubmit(e) {
        console.log("Searched for:");
        console.log(e);
        this.fetch("select", e);
    }

    onSubmitItinerary(e) {
        if(this.state.queryResults.length > 0){
            console.log("Testing temp list: ", this.state.queryResults);
            let temp = []
            for(let k = 0; k < this.state.queryResults.length; k++){
                temp.push(this.state.queryResults[k]);
            }

            this.setState({
                ids: temp
            });
        }
        this.fetchItinerary("itinerary", this.state.ids);
    }

    setArray(tempArray){
        console.log("Passed in ", tempArray);
        this.setState({
            ids: tempArray
        });
    }

    onSave(e){
    }

    onAddClick(){
    }

    onRemoveClick(){
    }

    render() {
        let serverLocations;
        let locs;
        let tempIds = [];
        // TODO: Replace localhost with URL of remote server
        let svg = "http://localhost:2526/svg";
        let renderedSvg;

        if (this.state.queryResults.length > 0) { // if this.state.serverReturned is not null
            console.log("First Checkpoint", this.state.queryResults);
            //for(let i=0;i<this.state.queryResults.length;i++){
            //    console.log(this.state.queryResults[i]);
            //    tempIds.push(this.state.queryResults[i].attributes[0]);
            //}
            // this.state.ids = this.state.queryResults.attributes.slice();
            // console.log("The IDs are: ", this.state.queryResults.ids);
            // this.setArray(tempIds);

	    }
            if(this.state.svgResults){
                svg = this.state.svgResults;
                renderedSvg = <InlineSVG src={svg}></InlineSVG>;
            }
            console.log(svg);
            console.log(renderedSvg);
            console.log(this.state.svgResults);
            console.log("Map created.");


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
                                <button type="submit" onClick={this.onSave.bind(this)}> Save </button>
                            </li>
                            <li>
                                <LoadsaveDropzone
                                    browseFile={this.browseFile.bind(this)}
                                />
                            </li>
                            <li><button type="submit" onClick={this.onSubmitItinerary}> Plan </button></li>
                            <li><button type="submit" onClick={this.onRemoveClick()}> Reset </button></li>
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
					<div className="opt-select">

                                    <input type="radio" id="no-opt-radio" name="opt" value="0" onClick={this.handleOptChange} defaultChecked />
                                    <label for="no-opt-radio"> No Opt </label>

                                    <input type="radio" id="nearest-neighbor-radio" name="opt" value="1" onClick={this.handleOptChange} />
                                    <label for="nearest-neighbor-radio"> Nearest Neighbor </label>

                                    <input type="radio" id="two-opt-radio" name="opt" value="2" onClick={this.handleOptChange} />
                                    <label for="two-opt-radio"> 2-Opt </label>

					</div>
            </div>

            <div className="svg-container">{renderedSvg}</div>

            <Results sLocs={this.state.queryResults} itin={this.state.allPairs}/>

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
		   	itin = [];
		   	for (let i = 0; i < returnedJson.points.length; i++) {
				itin.push(returnedJson.points[i].attributes[0]);
			}
		   	this.setState({
				ids: itin
			});
            console.log(this.state.queryResults);
       } catch (e) {
           console.log("Error talking to server");
       }

    }

   async fetchItinerary(type, input){
	   console.log("Fetch itin. called")
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
                svgResults: returnedJson.svg
            });

            //console.log("Second query ", allPairs);


        } catch (e) {
            console.log("Fetch itin. error:"+e);
        }
    }
}export default Home;
