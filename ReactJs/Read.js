import React from 'react'

export default class Read extends React.Component {
  constructor(props) {
    super(props);
    this.state = {"title":"","tags":[],"field1":"","field2":""};
  }

  componentDidMount() {
    
    var url = new URL('http://localhost:3000/api/notes/'+this.props.params.id);
    
    fetch(url, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      }
    })
    .then((response) => response.json())
    .then((responseJson) => {
      this.setState({title: responseJson._source.title});
      this.setState({tags: responseJson._source.tags});
      this.setState({field1: responseJson._source.field1});
      this.setState({field2: responseJson._source.field2});
    })
    .catch((error) => {
      console.error(error);
    });
  }  

  render() {
    return (
      <div>
        <div className="col-xs-12">
          {this.state.title}
        </div>
        <div className="col-xs-12">
          {this.state.tags.map((t) => " #".concat(t))}
        </div>
        <div className="col-xs-12">
          <pre>{this.state.field1}</pre>
        </div>
        <div className="col-xs-12">
          <pre>{this.state.field2}</pre>
        </div>
      </div>    
    );
  }
}

