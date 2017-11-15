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

    // Submits the value currently present in searchQuery
    submitHandler(e){
        e.preventDefault();

        this.props.handlerFromParent(this.state.searchQuery);

        console.log("Submit Handler was reached.");
    }

    // Detects a change in the search bar input and appends it to the searchQuery variable.
    handleChange(event) {
        this.setState({
            searchQuery: event.target.value
        });
        console.log("Setting searchQuery character by character.");
    }

    // The input for the search bar has an onChange command that calls handleChange when submit is clicked
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
                    <button type="submit" onClick={this.submitHandler.bind(this)}>Search</button>
            </div>
        );
    }
}
export default Search;
