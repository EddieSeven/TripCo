import React, {Component} from 'react';
import QueryResults from './QueryResults/QueryResults.jsx';
import Itinerary from './Itinerary/Itinerary.jsx';

class Results extends React.Component {
    render() {
        return <div className="results-wrapper">
            <div className="results-list">
                <QueryResults />
            </div>
            <div className="itinerary-list">
                <Itinerary />
            </div>
        </div>
    }
}export default Results;
