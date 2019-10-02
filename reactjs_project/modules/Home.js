import React from 'react'
import NavLink from './NavLink'

export default class Home extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (

      <div> 
          <div className="row">
          <div className="col-xs-1"></div>
          <div className="col-xs-5 border_div"><NavLink to="/search/java_example" activeStyle={{fontWeight: 'bold', color: 'black'}}><h1>Java Examples</h1></NavLink></div>
          
          <div className="col-xs-5 border_div"><NavLink to="/search/java_problem" activeStyle={{fontWeight: 'bold', color: 'black'}}><h1>Java Problems</h1></NavLink></div>
          <div className="col-xs-1"></div>
          </div>

          <div className="row">
          <div className="col-xs-1"></div>
          <div className="col-xs-5 border_div"><NavLink to="/search/python_example" activeStyle={{fontWeight: 'bold', color: 'black'}}><h1>Python Examples</h1></NavLink></div> 
          <div className="col-xs-5 border_div"><NavLink to="/search/python_problem" activeStyle={{fontWeight: 'bold', color: 'black'}}><h1>Python Problems</h1></NavLink></div>
          <div className="col-xs-1"></div>
          </div>

          <div className="row">
          <div className="col-xs-1"></div>
          <div className="col-xs-5 border_div"><NavLink to="/search/javascript_example" activeStyle={{fontWeight: 'bold', color: 'black'}}><h1>Javascript Examples</h1></NavLink></div> 
          <div className="col-xs-5 border_div"><NavLink to="/search/javascript_problem" activeStyle={{fontWeight: 'bold', color: 'black'}}><h1>Javascript Problems</h1></NavLink></div>
          <div className="col-xs-1"></div>
          </div>
      </div>
    
    );
  }
}

