import React, {Component} from 'react';

class PlanResults extends React.Component {
    constructor(props){
        super(props);
    }

    render() {
        let planLocations;
        let locs;

        if (this.props.results) {
            // set local variable to results
            planLocations = this.props.results;

            console.log("In new component", planLocations);

            // Put the onClick on the li element
            locs = planLocations.map((location) => {
                console.log(location);
                    return <li key={location}>
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

    /*async GetInitialState(element){
        this.setState({
            plan: element
        });
        console.log("This is the plan: ", this.state.plan);
    }*/
}
export default PlanResults;