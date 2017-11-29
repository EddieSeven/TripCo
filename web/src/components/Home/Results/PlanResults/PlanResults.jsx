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
                console.log(location.attributes[0]);
                    return <li key={location.attributes[0]}>
                        <table className="single-destination-table">
                            <thead>
                                <tr>
                                    <th> Name </th>

                                    <th> Latitude </th>

                                    <th> Longitude </th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        {location.attributes[2]}
                                    </td>
                                    <td>
                                        {location.attributes[3]}
                                    </td>
                                    <td>
                                        {location.attributes[4]}
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