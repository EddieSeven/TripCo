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

        if (this.state.queryResults) { // if this.state.serverReturned is not null
            // set local variable to results of sent query
            serverLocations = this.state.queryResults;
            console.log("State Loaded.");
            console.log(this.state);


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