import React, {Component} from 'react';
import Dropzone from 'react-dropzone';


class QueryResults extends React.Component {
    constructor(props){
        super(props);
    }

    // Adds index to ID list
    addHandler(e){
        //e.preventDefault();
        console.log("Add Handler was reached, index is: ", e);
        this.props.addHandlerFromResult(e);
    }

    render() {
        let total = 0;
        let serverLocations;
        let locs;
        let svg = "http://localhost:2526/svg";
        let renderedSvg;

        if (this.props.results) {
            // set local variable to results of sent query
            serverLocations = this.props.results;


            console.log("serverLocations populated with: ", serverLocations);

            /* Create an array of HTML list items. The Array.map function in Javascript passes each individual element
            * of an array (in this case serverLocations is the array and "location" is the name chosen for the individual element)
            * through a function and returns a new array with the mapped elements.
            * In this case f: location -> <li>location.name</li>, so the array will look like:
            * [<li>[name1]</li>,<li>[name2]</li>...]
            */

            locs = serverLocations.map((location, index) => {
                // console.log("Current ID: ", index);
                    return <li key={location.attributes[0]} onClick={this.addHandler.bind(this, index)}>
                        <table className="single-result-table">
                            <thead>
                                <tr>
                                    <th> Airport ID </th>

                                    <th> Airport Name </th>
                                    
                                    <th> Region </th>

                                    <th> USA </th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        {location.attributes[0]}
                                    </td>
                                    <td>
                                        {location.attributes[2]}
                                    </td>
                                    <td>
                                        {location.attributes[7]}
                                    </td>
                                    <td>
                                        {location.attributes[8]}
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
export default QueryResults;
