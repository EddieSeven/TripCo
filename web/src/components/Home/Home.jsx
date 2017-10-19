import React, {Component} from 'react';
import SearchBar from '../Home/SearchBar/SearchBar.jsx';
{/* import Dropzone from 'react-dropzone'
setInterval(function() {
    var myImageElement = document.getElementById('myImage');
    myImageElement.src = 'output.svg?rand=' + Math.random();
}, 5000); */}
class Home extends React.Component {
	 /* constructor(props) {
		super(props);
		this.handleInputChange=this.handleInputChange.bind(this);
	} */
    render() {
        return <div className="home-container">
            <div className="inner">
                <div className="header">
                    <div className="spacer"> </div>
                    <img src="src/images/tripco-logo-color-small.png" />
                </div>

                <SearchBar />

            </div>
        </div>
    }
}export default Home