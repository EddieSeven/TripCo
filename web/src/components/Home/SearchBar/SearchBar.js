import React, {Component} from 'react';


class SearchBar extends React.Component {
    render() {
        return  (
            <form className="searchbar">
                <input
                  type="text"
                  placeholder="Search..."
                  value={this.props.filterText}
                  ref="filterTextInput"
                  onChange={this.handleChange}
                />
          </form>
        );
    }
}

export default SearchBar;