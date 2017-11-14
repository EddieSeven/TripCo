import React, {Component} from 'react';
import InlineSVG from 'svg-inline-react';


class Search extends React.Component {
    constructor(){
        super();
        this.handleChange = this.handleChange.bind(this);
        this.submitHandler = this.submitHandler.bind(this);
        this.state = {
            searchQuery: ''
        };
    }

    submitHandler(e){
        e.preventDefault();

        this.props.handlerFromParent(this.state.searchQuery);

        this.setState({
            searchQuery: ''
        });
    }

    handleChange(event) {
        this.setState({
            searchQuery: event.target.value
        });
        console.log("Stuff called");
    }

    render() {
        return  (
            <div className = "searchbox">
                <p>What kind of places would you like to visit?</p>
                    <input
                      ref={(c) => this.query = c}
                      value = {this.state.searchQuery}
                      type="search"
                      name="destination-search"
                      size="45"
                      placeholder="Search destinations..."
                      onChange={this.handleChange}
                    />
                    <button type="submit" onClick={this.handleChange.bind(this)}>Search</button>
            </div>
        );
    }
}
export default Search;
