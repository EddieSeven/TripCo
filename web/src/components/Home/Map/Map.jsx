import React, { Component } from 'react';

// This imports all of the external functionality we want from react-google-maps
import {GoogleMap, Polyline, withGoogleMap} from 'react-google-maps';


class Map extends React.Component {

    constructor(){
        super();
        this.state = {
            latCoor: [],
            longCoor: [],
            googCoor: []
        };
    }

    // Render method of the Map component
    render() {

        console.log(this.state.latCoor);
        console.log(this.state.longCoor);

        for(var i=0; i< this.state.latCoor.length; i++){
            this.state.googCoor.push({lat: this.state.latCoor[i],lng: this.state.longCoor[i] });
        }
        this.state.googCoor.push({lat: this.state.latCoor[0],lng: this.state.longCoor[0]});

        console.log(this.state.googCoor);

        const coordinates = [
            {lat: 37.772, lng: -122.214},
            {lat: 21.291, lng: -157.821},
            {lat: -18.142, lng: 178.431},
            {lat: -27.467, lng: 153.027}
        ];

        // Return the stuff we actually want rendered on the page
        return (
            { /*GoogleMap is an imported component from react-google-maps*/ },
            <GoogleMap
                defaultCenter={{lat: 0, lng: 0}}
                defaultZoom={1}
            >
                {/*only access every other piece of data, add the first one to the end*/}
            {/* Everything that is in between <GoogleMap> and </GoogleMap> get rendered onto the
                map. Polyline is an easy google library that draws lines from coordiates.*/ }
                <Polyline
                    visible={true /*Make sure the map is visable on screen*/}

                    path={this.state.googCoor}

                    options={{
                        /* This is a list of optional things line line color and line weight this does not
                        need to be included. See documentation for more options*/
                        strokeColor: '#ff2527',
                        strokeWeight: 4,
                    }}
                />

            { /*Close our GoogleMap*/}
            </GoogleMap>
        )
    }
}
// This is important what this does is it wraps the Map module in
// a withGoogleMap module. Without this the map will not load
export default withGoogleMap(Map)
