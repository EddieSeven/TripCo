/*    CS314 - Team25     Due: 10 PM MST, September 7, 2017*/
import React, {Component} from 'react';
import Dropzone from 'react-dropzone'
class Home extends React.Component {
	constructor(props) {
		super(props);
		this.handleInputChange=this.handleInputChange.bind(this);
	}
    render() {
        let total = 0;
        /* Sprint 1 Work - Algorithm/Pseudo:
         - Create a list of objects called data
             - If there's something in data
                 - Loop through each object
                 - Take the distance from each
                 - Add to total 
        */
        let data = this.props.pairs;
        if (data.length !== 0){
            for(let i =0; i < data.length; i++) {
                let distance = data[i].props.dist;
                console.log(distance); // For testing, otherwise
                total += distance;     // Can be done on 1 line

            }
        }
        return <div className="home-container">
            <div className="inner">
                <h1>TripCo</h1>
                <h2>T25</h2>
                <h3>Travel Itinerary</h3>
                <img src="../../../output.svg"/>
                <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                    <button>Open JSON File</button>
                </Dropzone>
				<div id="checkboxes">
					<input type="checkbox" name="origin" onChange={this.handleInputChange} /> Origin <br/>
					<input type="checkbox" name="start-id" onChange={this.handleInputChange} /> Start Id<br/>
					<input type="checkbox" name="start-lat" onChange={this.handleInputChange} /> Starting Latitude<br/>
					<input type="checkbox" name="start-lon" onChange={this.handleInputChange} /> Starting Longitude<br/>
					<input type="checkbox" name="dest" onChange={this.handleInputChange} /> Destination<br/>
					<input type="checkbox" name="dest-id" onChange={this.handleInputChange} /> Dest. ID<br/>
					<input type="checkbox" name="dest-lat" onChange={this.handleInputChange} /> Dest. Latitude<br/>
					<input type="checkbox" name="dest-lon" onChange={this.handleInputChange} /> Dest. Longitude<br/>
					<input type="checkbox" name="dist" onChange={this.handleInputChange} /> Distance Between<br/>
					{this.props.allCg.map((name) =>(
							<div><input type="checkbox" name={name} onChange={this.handleInputChange} /> {name} <br/></div>
						))}
				</div>
                <table className="pair-table">
                    <thead>
                        <tr>
                                <th className="origin">
                                    <h5>Origin</h5>
                                </th>
                                 <th className="start-id">
                                    <h5>Start ID</h5>
                                </th>
                                 <th className="start-lat">
                                    <h5>Starting Latitiude</h5>
                                </th>
                                 <th className="start-lon">
                                    <h5>Starting Longitude</h5>
                                </th>
                                <th className="dest">
                                    <h5>Destination</h5>
                                </th>
                                 <th className="dest-id">
                                    <h5>Dest. ID</h5>
                                </th>
                                 <th className="dest-lat">
                                    <h5>Dest. Latitude</h5>
                                </th>
                                 <th className="dest-lon">
                                    <h5>Dest. Longitude</h5>
                                </th>
                                <th className="dist">
                                    <h5>Distance Between</h5>
                                </th>
								{this.props.allCg.map((name) =>(
									<th className={name}><h5>{name}</h5></th>
								))}
                        </tr>
                    </thead>
                    {this.props.pairs}
                    <tbody>
                        <tr>
                            <td>Total Distance Traversed in Itinerary:</td>
                            <td>{total}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    }    drop(acceptedFiles) {
        console.log("Accepting drop");
        acceptedFiles.forEach(file => {
            console.log("Filename:", file.name, "File:", file);
            console.log(JSON.stringify(file));
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);
                    console.log(JsonObj);
                    this.props.browseFile(JsonObj);
                };
            })(file).bind(this);
            fr.readAsText(file);
        });
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
}export default Home
