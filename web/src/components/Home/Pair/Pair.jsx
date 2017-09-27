import React, {Component} from 'react';
let Pair = ({startName, start, startLat, startLong, endName, end, endLat, endLong, dist, allCategories, allData}) => <tbody
    className="pair">
    <tr>
        <td>
            <h5>{startName}</h5>
        </td>
         <td>
            <h5>{start}</h5>
        </td>
         <td>
            <h5>{startLat}</h5>
        </td>
         <td>
            <h5>{startLong}</h5>
        </td>
        <td>
            <h5>{endName}</h5>
        </td>
         <td>
            <h5>{end}</h5>
        </td>
         <td>
            <h5>{endLat}</h5>
        </td>
         <td>
            <h5>{endLong}</h5>
        </td>
        <td>
            <h5>{dist}</h5>
        </td>
    </tr>
</tbody>;
export default Pair;
