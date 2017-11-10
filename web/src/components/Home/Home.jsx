import React, {Component} from 'react';
import Head from './Header/Head.jsx';
import Results from './Results/Results.jsx';

class Home extends React.Component {
    render() {
        return <div className="home-container">
            <div className="inner">
                <h1>Anything!!!!</h1>
                <img src="images/tripco-logo-color-small.png" />
                <Head />
                <Results />
            </div>
        </div>
    }
}export default Home
