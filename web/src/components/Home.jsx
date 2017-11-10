import React, {Component} from 'react';
import Header from './Header.Header.jsx';
import Results from './Results/Results.jsx';

class Home extends React.Component {
    render() {
        return <div className="home-container">
            <div className="inner">
                <Head />
                <Results />
            </div>
        </div>
    }
}export default Home;
