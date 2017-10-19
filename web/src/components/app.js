import React from 'react';
import Home from './Home/Home.jsx';
import Table from './Home/Table/Table.jsx';
import Pair from './Home/Pair/Pair.jsx';


export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            allPairs: [],
            sysFile: [],
			allCat: [],
			svg: "",
			serverReturned: null
        }
    };

    render() {
        let serverLocations;
        let pairs = this.state.allPairs;
        let ps = pairs.map((pp) => {
            return <Pair {...pp}/>;
        });

        if (this.state.serverReturned){
            serverLocations = this.state.serverReturned.locations;

            locs = serverLocations.map((location) => {
                return <li>{location.name}</li>;
            });

            svg = this.state.serverReturned.svg;
        }

        return (
            <div className="app-container">
                <Home />
            </div>
        )
    }

//    async browseFile(file) {
//        console.log("Got file:", file);
//        //For loop that goes through all pairs,
//        let pairs = [];
//		let cumulDist = 0
//        for (let i = 0; i < Object.values(file).length; i++) {
//            let start = file[i].start; //get start from file i
//            let end = file[i].end; //get end from file i
//            let dist = file[i].distance;
//			cumulDist += dist;
//            let startName = file[i].startName;
//            let endName = file[i].endName;
//            let startLat = file[i].startLat;
//            let endLat = file[i].endLat;
//            let startLong = file[i].startLong;
//            let endLong = file[i].endLong;
//            let temp1 = file[i].categories;
//            let allCategories = temp1.split(',');
//            let temp2 = file[i].data[i];
//            let allData = temp2.split(',');
//            // console.log("this is the first category: " + allCategories[0]);
//            let p = { //create object with start, end, and dist variable
//                start: start,
//                end: end,
//                dist: dist,
//                startName: startName,
//                endName: endName,
//                startLat: startLat,
//                endLat: endLat,
//                startLong: startLong,
//                endLong: endLong,
//                allCategories: allCategories,
//                allData: allData,
//				cumulDist: cumulDist
//            };
//            pairs.push(p); //add object to pairs array
//            console.log("Pushing pair: ", p); //log to console
//        }

        //Here we will update the state of app.
        // Anything component (i.e. pairs) referencing it will be re-rendered
//        this.setState({
//            allPairs: pairs,
//            sysFile: file,
//			allCat: pairs[0].allCategories
//        });
//    }
}
