import React, {Component} from 'react';
import QueryResults from './QueryResults/QueryResults.jsx';
import Itinerary from './Itinerary/Itinerary.jsx';

class Results extends React.Component {
    render() {
        console.log("In Results.jsx ", this.props);
        return <div className="full-results-wrapper">
            <QueryResults results={this.props.sLocs} />
            <QueryResults results={this.props.sLocs} />
            <Itinerary itin={this.props.itin}/>
        </div>
    }
}export default Results;
