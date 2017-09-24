/*
    CS314 - Team25 
    Due: 10 PM MST, September 7, 2017
*/

import React, {Component} from 'react';
import Dropzone from 'react-dropzone'

class Home extends React.Component {
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
                <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                    <button>Open JSON File</button>
                </Dropzone>
                <table className="pair-table">
                    {this.props.pairs}
                    <tbody>
                        <tr>
                            <td colSpan="2">Total:</td>
                            <td>{total}</td>
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>
    }
    /*<img src="../data/col.svg" ></img>*/
    drop(acceptedFiles) {
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


}export default Home