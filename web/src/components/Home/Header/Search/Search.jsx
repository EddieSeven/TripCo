import React, {Component} from 'react';
import InlineSVG from 'svg-inline-react';


class Search extends React.Component {
    render() {
        return  (
            <div className = "searchbox">
                <p>What kind of places would you like to visit?</p>
                <form>
                    <input
                      ref={(c) => this.query = c}
                      type="search"
                      name="destination-search"
                      size="45"
                      placeholder="Search destinations..."
                    />
                    <button type="submit" onClick={this.props.submitFunc}>Search</button>
                </form>
            </div>
        );
    }
}
export default Search;
