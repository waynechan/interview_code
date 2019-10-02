import React from 'react'
import NavLink from './NavLink'

export default class SearchForm extends React.Component {
    constructor(props) {
      super(props);
      this.state = {"tags":"","content":"", "results":[],"total":0,"page":0,"numPerPage":10};

      this.handleContentChange = this.handleContentChange.bind(this);
      this.handleTagsChange = this.handleTagsChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
      this.handleNext = this.handleNext.bind(this);
      this.handlePrevious = this.handlePrevious.bind(this);
    }

    componentWillReceiveProps() {
      //call when back button or search button is clicked
      window.location.reload(false);
    }

    componentDidMount() {
      var tags = this.props.location.query.tags;
      var content = this.props.location.query.content;
      var page = this.props.location.query.page;

      if(typeof tags == 'undefined' || typeof content == 'undefined') {
        return;
      }

      this.setState({tags: tags});
      this.setState({content: content});
      this.setState({page: page});
      
      var url = new URL('http://localhost:3000/api/notes/'+this.props.params.category+'/_search');
      var params = {page:page, tags:tags, content:content};
      url.search = new URLSearchParams(params);

      fetch(url, {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        }
      })
      .then((response) => response.json())
      .then((responseJson) => {
        this.setState({results: responseJson.hits.hits});
        this.setState({total: responseJson.hits.total});
      })
      .catch((error) => {
        console.error(error);
      });
    }

    handleContentChange(event) {
      this.setState({content: event.target.value});
    }

    handleTagsChange(event) {
      this.setState({tags: event.target.value});
    }

    handleSubmit(event) {

      event.preventDefault();

      var params = {page:1, tags:this.state.tags, content:this.state.content};
      var searchParams = new URLSearchParams(params);
      var url = '/search/'+this.props.params.category+'?'+searchParams.toString();

      this.props.history.push(url); 
    }

    handleTagClick(tag, event) {

      event.preventDefault();

      var params = {page:1, tags:tag, content:''};
      var searchParams = new URLSearchParams(params);
      var url = '/search/'+this.props.params.category+'?'+searchParams.toString();

      this.props.history.push(url); 
    }

    handleNext(event) {

      event.preventDefault();

      var nextPage = parseInt(this.state.page) + 1;

      var params = {page:nextPage, tags:this.state.tags, content:this.state.content};
      var searchParams = new URLSearchParams(params);
      var url = '/search/'+this.props.params.category+'?'+searchParams.toString();

      this.props.history.push(url); 
    }

    handlePrevious(event) {

      event.preventDefault();

      var previousPage = parseInt(this.state.page) - 1;

      var params = {page:previousPage, tags:this.state.tags, content:this.state.content};
      var searchParams = new URLSearchParams(params);
      var url = '/search/'+this.props.params.category+'?'+searchParams.toString();

      this.props.history.push(url); 
    }

  render() {
    return (
      <div>    
        <form>
          <div className="form-group col-xs-12">
            <label>Tags:</label>
            <input type="text" className="form-control" value={this.state.tags} onChange={this.handleTagsChange} />
          </div>
          <div className="form-group col-xs-12">
            <label>Content:</label>
            <input type="text" className="form-control" value={this.state.content} onChange={this.handleContentChange} />
          </div>
          <button type="button" className="btn btn-primary" onClick={this.handleSubmit}>Search</button>
        </form>

        {this.state.total > 0 &&
          <div className="col-xs-12">
          <label>Total:</label>
          {this.state.total}
        </div>
        }
        {this.state.results.map((r) => (
          <div key={r._id}>
            <div className="col-xs-12">
              {this.props.params.category.endsWith('example') ? (
                <text>{r._source.title}</text>
              ) : (
                <NavLink to={'/read/'.concat(r._id)}>{r._source.title}</NavLink>
              )}
            </div>
            <div className="col-xs-12">
              {r._source.tags.map((t) => <a onClick={(e) => this.handleTagClick(" #".concat(t), e)}>{" #".concat(t)}</a>)}
            </div>
            <div className="col-xs-12">
              <pre>{r._source.field1}</pre>
            </div>
          </div>
        ))}

        {this.state.page > 1 &&
          <button type="button" className="btn btn-primary" onClick={this.handlePrevious}>Previous</button>
        }

        {this.state.total > this.state.page * this.state.numPerPage &&
          <button type="button" className="btn btn-primary" onClick={this.handleNext}>Next</button>
        }
        
      </div>
      
    );
  }
}