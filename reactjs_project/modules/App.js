import React from 'react'
import NavLink from './NavLink'

export default React.createClass({
  render() {
    return (
      <div>
        <ul>
          <li><NavLink to="/search/java_example" activeStyle={{fontWeight: 'bold', color: 'black'}}>Java Examples</NavLink></li>
          <li><NavLink to="/search/java_problem" activeStyle={{fontWeight: 'bold', color: 'black'}}>Java Problems</NavLink></li>
          
          <li><NavLink to="/search/python_example"activeStyle={{fontWeight: 'bold', color: 'black'}}>Python Examples</NavLink></li> 
          <li><NavLink to="/search/python_problem" activeStyle={{fontWeight: 'bold', color: 'black'}}>Python Problems</NavLink></li>

          <li><NavLink to="/search/javascript_example" activeStyle={{fontWeight: 'bold', color: 'black'}}>Javascript Examples</NavLink></li>
          <li><NavLink to="/search/javascript_problem" activeStyle={{fontWeight: 'bold', color: 'black'}}>Javascript Problems</NavLink></li>
        </ul>
        {this.props.children}
      </div>
    )
  }
})
