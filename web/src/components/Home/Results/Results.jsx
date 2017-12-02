import React, {Component} from 'react';
import QueryResults from './QueryResults/QueryResults.jsx';
import PlanResults from './PlanResults/PlanResults.jsx';
import Itinerary from './Itinerary/Itinerary.jsx';

class Results extends React.Component {
    render() {
        console.log("In Results.jsx ", this.props);
        return <div className="full-results-wrapper">
            <div>
                <h5>Results: </h5>
                <h6>Click on an element to add it to your trip.</h6>
                <QueryResults
                    results={this.props.sLocs}
                    addHandlerFromResult={this.props.addHandlerFromHome}
                />
            </div>
            <div>
                <h5>Plan: </h5>
                <h6>Click on an element to remove it from your trip.</h6>
                <PlanResults
                    results={this.props.ids}
                    removeHandlerFromResult={this.props.removeHandlerFromHome}
                />
            </div>
            <div>
                <Itinerary itin={this.props.itin}/>
            </div>
        </div>
    }
}export default Results;
