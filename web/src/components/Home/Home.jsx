/*    CS314 - Team25     Due: 10 PM MST, September 7, 2017*/
import React, {Component} from 'react';
import Dropzone from 'react-dropzone'
class Home extends React.Component {
	constructor(props) {
		super(props);
		this.toggleColumn=this.toggleColumn.bind(this);
		this.state = {categories: []};
	}
	addCategory(cName) {
		this.setState(this.state.concat([cName]));
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
		// Add all the checkboxes and table headers
			for(let i=0; i < data[0].props.allCategories.length; i++) {
				let catName = data[0].props.allCategories[i];
				addCategory(catName);
			}
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
					<input type="checkbox" onChange={this.toggleColumn('origin')} /> Origin <br/>
					<input type="checkbox" onChange={this.toggleColumn('start-id')} /> Start Id<br/>
					<input type="checkbox" onChange={this.toggleColumn('start-lat')} /> Starting Latitude<br/>
					<input type="checkbox" onChange={this.toggleColumn('start-lon')} /> Starting Longitude<br/>
					<input type="checkbox" onChange={this.toggleColumn('dest')} /> Destination<br/>
					<input type="checkbox" onChange={this.toggleColumn('dest-id')} /> Dest. ID<br/>
					<input type="checkbox" onChange={this.toggleColumn('dest-lat')} /> Dest. Latitude<br/>
					<input type="checkbox" onChange={this.toggleColumn('dest-lon')} /> Dest. Longitude<br/>
					<input type="checkbox" onChange={this.toggleColumn('dist')} /> Distance Between<br/>
					{this.state.categories.map((name) =>(
							<div><input type="checkbox" onChange={this.toggleColumn({name})}/> {name} <br/></div>
						))}
				</div>
                <table className="pair-table">
                    <thead>
                        <tr id="table-headers">
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
								{this.state.categories.map((name) =>(
									<th className={name}><h5>{name}</h5></th>
								))}
                        </tr>
                    </thead>
                    {this.props.pairs}
                    <tbody>
                        <tr>
                            <td colSpan="8">Total Distance Traversed in Itinerary:</td>
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
	toggleColumn(colName) {
		console.log("toggle column")
		let columns = document.getElementsByClassName(colName);
		for (let i =0; i < columns.length; i++) {
			if (columns[i].style.display == "block") {
				columns[i].style.display = "none";
			} else {
				columns[i].style.display = "block";
			}
		}
	}
}export default Home
