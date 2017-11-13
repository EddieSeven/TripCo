import React, {Component} from 'react';
import Search from './Search/Search.jsx';
import Results from '../Results/Results.jsx';
import LoadsaveDropzone from './Search/LoadsaveDropzone/LoadsaveDropzone.jsx';

class Head extends React.Component {
    constructor(props){
            super(props);
            this.state = {
                searchQuery: '',
                queryResults: [],
                svgResults: "",
                input: "",
                allPairs: [],
                sysFile: []
            };
            this.handleData = this.handleData.bind(this);
            this.onSubmit = this.onSubmit.bind(this);
    }

    handleData(data){
        /*this.setState({
            searchQuery: data
        });*/
        console.log("Test 1 " + this.state.searchQuery);
        this.onSubmit(this.state.searchQuery);
    }

    onSubmit(e) {
        console.log("Searched for:");
        console.log(e);
        this.props.fetchFromServer("queryA", e);
    }

    onSave(e){
    }

    onAddAll(){
    }

    onRemoveAll(){
    }
	
	collectSearchString(event){
		console.log(event.target.value);
		this.setState({
            searchQuery: event.target.value
        });
			
	}
    
    render() {
        let serverLocations;
        let locs;
        // TODO: Replace localhost with URL of remote server
        let svg = "http://localhost:2530/svg";
        let renderedSvg;
        let pairs = this.state.allPairs;
        let ps = pairs.map((pp) => {
            return <Pair {...pp}/>;
        });

        if (this.state.queryResults) { // if this.state.serverReturned is not null
            // set local variable to results of sent query
            serverLocations = this.state.queryResults;

            // console.log(serverLocations);

            /* Create an array of HTML list items. The Array.map function in Javascript passes each individual element
            * of an array (in this case serverLocations is the array and "location" is the name chosen for the individual element)
            * through a function and returns a new array with the mapped elements.
            * In this case f: location -> <li>location.name</li>, so the array will look like:
            * [<li>[name1]</li>,<li>[name2]</li>...]
            */

            locs = serverLocations.map((location) => {
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

            // Send locs to QueryResults.jsx
        }

        return (
        <div className="header-wrapper">
            <div className="header">
                <div className="logo">
                    <img src="../images/tripco-logo-color-small.png" />
                </div>

                <Search handlerFromParent={this.handleData.bind(this)}/>
                
                
                <input  type="text" onChange={this.collectSearchString.bind(this)}/>
				<button onClick={this.handleData.bind(this)}>Simple Submit</button>
				
				
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
                        <form>
                            <table>
                                <tr>
                                    <td><label> Miles <input type="radio" name="miles-radio" defaultChecked onClick={this.handleInputChange} /></label></td>
                                </tr>
                                <tr>
                                    <td><label> Kilometers <input type="radio" name="km-radio" onClick={this.handleInputChange} /></label></td>

                                </tr>
                            </table>
                        </form>
                    </span>
                </div>
            </div>

            <div className="svg-container"><img src="../images/world.svg" />{renderedSvg}</div>

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
    
}export default Head;
