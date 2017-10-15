import React, {Component} from 'react';


class SearchBar extends React.Component {
    onSubmit(e) {
        e.preventDefault();
        var query = this.query.value;
        console.log("Searched for:");
        console.log(query);
    }

    render() {
        return  (
        <div className = "searchbox">
            <p>What kind of places would you like to visit?</p>
            <form>
                <input
                  ref={(c) => this.query = c}
                  type="search"
                  name="destination-search"
                  size="70"
                  placeholder="Search destinations..."
                />
                <button type="submit" onClick={this.onSubmit.bind(this)}>Search</button>
            </form>
        </div>
        );
    }
}
export default SearchBar;