import React, {Component} from 'react';
import Dropzone from 'react-dropzone';


class QueryResults extends React.Component {
    constructor(props){
        super(props);
    }

    render() {
        let total = 0;
        let serverLocations;
        let locs;
        // TODO: Replace localhost with URL of remote server
        let svg = "http://localhost:2526/svg";
        let renderedSvg;
        //let pairs = this.state.allPairs;
        //let ps = pairs.map((pp) => {
        //    return <Pair {...pp}/>;
        //});

            // set local variable to results of sent query
            serverLocations = this.props.results;


            console.log("come on", results);

            /* Create an array of HTML list items. The Array.map function in Javascript passes each individual element
            * of an array (in this case serverLocations is the array and "location" is the name chosen for the individual element)
            * through a function and returns a new array with the mapped elements.
            * In this case f: location -> <li>location.name</li>, so the array will look like:
            * [<li>[name1]</li>,<li>[name2]</li>...]
            */

            locs = serverLocations.map((location) => {
                console.log(location.start.name);
                    return <li key={location.start.id}>
                        <table className="results-table">
                        <thead>
                            <tr>
                                <th> Name </th>

                                <th> Latitude </th>

                                <th> Longitude </th>

                                <th> Distance </th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    {location.start.name}
                                </td>
                                <td>
                                    {location.start.latitude}
                                </td>
                                <td>
                                    {location.start.longitude}
                                </td>
                                <td>
                                    {location.distance}
                                </td>
                            </tr>
                        </tbody>
                        </table>

                    </li>;
            });

        return  (
        <div className="table-container">

            <div className = "results-wrapper">
                <table className="results-table">
                    <thead>
                        <tr>
                            <th>
                                <h5>Results:</h5>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <ul>{this.props.results}</ul>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        );
    }
}
export default QueryResults;