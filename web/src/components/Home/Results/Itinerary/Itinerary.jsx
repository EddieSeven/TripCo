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
							<td> {pair.start.attributes[2]} </td>
							<td> {pair.start.attributes[3]} </td>
							<td> {pair.start.attributes[4]} </td>
							<td> {pair.end.attributes[2]} </td>
							<td> {pair.end.attributes[3]} </td>
							<td> {pair.end.attributes[4]} </td>
							<td> {pair.distance} </td>
							<td> {total} </td>
						</tr>
					</tbody>
			});	
        }

        return  (
        <div className = "itinerary-wrapper">

            <div className="checkboxes">
                    <label> Origin <input type="checkbox" name="origin" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> Start Lat. <input type="checkbox" name="start-lat" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> Start Long. <input type="checkbox" name="start-long" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> Destination <input type="checkbox" name="destination" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> End Lat. <input type="checkbox" name="end-lat" defaultChecked onChange={this.handleInputChange} /></label>
                    <label> End Long <input type="checkbox" name="end-long" defaultChecked onChange={this.handleInputChange} /></label><br/>
            </div>

			<table className="itinerary-table">
				<thead>
					<tr>
						<td className="origin"> Origin </td>
						<td className="start-lat"> Start Lat. </td>
						<td className="start-long"> Start Long. </td>
						<td className="destination"> Destination </td>
						<td className="end-lat"> End Lat. </td>
						<td className="end-long"> End Long. </td>
						<td className="distance-between"> Distance Between </td>
						<td className="cumulative"> Cumulative Distance </td>
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
