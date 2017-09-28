import React, {Component} from 'react';
let Pair = ({startName, start, startLat, startLong, endName, end, endLat, endLong, dist, allCategories, allData}) => <tbody
    className="pair">
    <tr className="data-row">
        <td className="origin">
            <h5>{startName}</h5>
        </td>
         <td className="start-id">
            <h5>{start}</h5>
        </td>
         <td className="start-lat">
            <h5>{startLat}</h5>
        </td>
         <td className="start-lon">
            <h5>{startLong}</h5>
        </td>
        <td className="dest">
            <h5>{endName}</h5>
        </td>
         <td className="dest-id">
            <h5>{end}</h5>
        </td>
         <td className="dest-lat">
            <h5>{endLat}</h5>
        </td>
         <td className="dest-lon">
            <h5>{endLong}</h5>
        </td>
        <td className="dist">
            <h5>{dist}</h5>
        </td>
		{allData.map((inf, index) => (
			<td className={allCategories[{index}]}> <h5> {inf} </h5> </td>
		))}
    </tr>
</tbody>;
export default Pair;
