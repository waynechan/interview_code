import React from 'react'
import { render } from 'react-dom'
import { Router, Route, hashHistory } from 'react-router'
import App from './modules/App'
import Home from './modules/Home'
import SearchForm from './modules/SearchForm'
import Read from './modules/Read'

render((
  <Router history={hashHistory}>
    <Route component={App}>
      <Route path="/"  component={Home}/>
      <Route path="/search/:category" component={SearchForm}/>
      <Route path="/read/:id" component={Read}/>      
    </Route>
  </Router>
), document.getElementById('app'))
