import React, {Component} from 'react';
import QueryResults from './QueryResults/QueryResults.jsx';
import Itinerary from './Itinerary/Itinerary.jsx';

class Home extends React.Component {
    render() {
        return <div className="results-wrapper">
            <div className="results-list">
            //     <ul>{locs}</ul>
                <QueryResults />
            </div>
            <div className="itinerary-list">
                <Itinerary />
            </div>
        </div>
    }
}export default Home
