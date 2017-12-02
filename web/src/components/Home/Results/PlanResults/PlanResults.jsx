import React, {Component} from 'react';

class PlanResults extends React.Component {
    constructor(props){
        super(props);
    }

    // Removes index from ID list
    removeHandler(e){
        //e.preventDefault();
        console.log("Remove Handler was reached, index is: ", e);
        this.props.removeHandlerFromResult(e);
    }

    render() {
        let planLocations;
        let locs;

        if (this.props.results) {
            // set local variable to results
            planLocations = this.props.results;

            console.log("In new component", planLocations);

            // Put the onClick on the li element
            locs = planLocations.map((location, index) => {
                console.log("Airport ID in plan: ", location);
                    return <li key={location} onClick={this.removeHandler.bind(this, index)}>
                        <table className="single-destination-table">
                            <thead>
                                <tr>
                                    <th> Airport ID </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        {location}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </li>;
            });
        }

        return  (
        <div className = "results-wrapper">
            <table className="results-table">
                <tbody>
                    <tr>
                        <td>
                            <ul>{locs}</ul>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        );
    }
}
export default PlanResults;