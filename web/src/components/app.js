import React from 'react';
import Home from './Home/Home.jsx';
import Table from './Home/Table/Table.jsx';
import Pair from './Home/Pair/Pair.jsx';
// import InlineSVG from 'svg-inline-react';


export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
			serverReturned: null
        }
    };

    render() {
        let serverLocations;
        let locs;
        let svgString;
        let renderedSvg;

        if (this.state.serverReturned){
            serverLocations = this.state.serverReturned.locations;

            locs = serverLocations.map((location) => {
                return <li>{location.name}</li>;
            });

            svgString = this.state.serverReturned.svg;
            // renderedSvg = <InlineSVG src={svgString.svg}></InlineSVG>;
        }

        return (
            <div className="app-container">
                <div className="svg-container">{this.state.svgString}</div>
                <Home />
            </div>
        )
    }
}
