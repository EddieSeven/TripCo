import React, {Component} from 'react';


class Itinerary extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            queryResults: [],
            svgResults: "",
            input: "",
            allPairs: [],
            sysFile: []
        };
    }

    render() {
        let serverLocations;
        let locs;
        let total = 0;
        // let pairs = this.state.allPairs;
        // let ps = pairs.map((pp) => {
        //    return <Pair {...pp}/>;
        // });

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
                                <th> Start </th>

                                <th> End </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <ul>
                                        <li>
                                            {location.start.name}
                                        </li>
                                        <li>
                                            {location.start.id}
					</li>
                                        <li>
                                            {location.start.latitude}
                                        </li>
                                        <li>
                                            {location.start.longitude}
                                        </li>
                                        <li>
                                            {location.start.municipality}
                                        </li>
                                        <li>
                                            {location.start.type}
                                        </li>
                                        <li>
                                            {location.start.wikipedia_link}
                                        </li>
                                        <li>
                                            {location.start.home_link}
                                        </li>
                                    </ul>
                                </td>

                                <td>
                                    <ul>
                                        <li>
                                            {location.end.name}
                                        </li>
                                        <li>
                                            {location.end.id}
                                        </li>
					<li>
                                            {location.end.latitude}
                                        </li>
                                        <li>
                                            {location.end.longitude}
                                        </li>
                                        <li>
                                            {location.end.municipality}
                                        </li>
                                        <li>
                                            {location.end.type}
                                        </li>
                                        <li>
                                            {location.end.wikipedia_link}
                                        </li>
                                        <li>
                                            {location.end.home_link}
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h4>Distance Between: {location.distance}</h4>
                                </td>
                                <td>
                                    <h4>Distance Thus Far: {this.total}</h4>
                                </td>
                            </tr>
                        </tbody>
                        </table>

                    </li>;
            });

            console.log("Map created.");

        }

        return  (
        <div className = "itinerary-table">

            <div className="checkboxes">
                    <label> Airport Code <input type="checkbox" name="airport-code" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> Latitude <input type="checkbox" name="latitude" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> Longitude <input type="checkbox" name="longitude" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> Elevation <input type="checkbox" name="elevation" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> Airport Website <input type="checkbox" name="dest-id" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> Airport Wiki <input type="checkbox" name="dest-lat" defaultChecked onChange={this.handleInputChange} /></label><br/>
            </div>

            <br />

            <div className="results-wrapper">
                <div className="results-list">
                    <ul>{locs}</ul>
                </div>
                <div className="itinerary-list"></div>
            </div>
        </div>
        );
    }

    handleInputChange(event) {
        let target=event.target;
        let disp = ""
        if (target.checked) {
            disp = "table-cell"
        } else {
            disp = "none"
        }
        let colName = target.name;
        let columns = document.getElementsByClassName(colName);
        for (let i =0; i < columns.length; i++) {
            columns[i].style.display = disp;
        }
    }
}
export default Itinerary;
