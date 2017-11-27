import React, {Component} from 'react';


class Itinerary extends React.Component {
    constructor(props){
        super(props);
    }

    render() {
		let pairs;
		let total = 0;

        if (this.props.itin.length > 0) {
			console.log("Adding pairs");
			pairs = this.props.itin.map((pair) => {
				total += pair.distance;
				console.log("Adding new distance: " + total);
				return <tbody>
						<tr>
							<td> {pair.start.attributes[2]}</td>
							<td> {pair.end.attributes[2]}</td>
							<td> {pair.distance} </td>
							<td> {total}</td>
						</tr>
					</tbody>
			});	
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

			<table>
				<thead>
					<tr>
						<td> Start </td>
						<td> End </td>
						<td> Distance Between </td>
						<td> Cumulative Distance </td>
					</tr>
				</thead>
				{pairs}
			</table>


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
