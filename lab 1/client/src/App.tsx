import * as React from 'react';
import './App.css';
import MovieList from './MovieList';

class App extends React.Component<{}, any> {
  render() {
    return (
        <div className="App">
          <header className="App-header">
            <h1 className="App-title">Welcome to the cinema!</h1>
          </header>
          <MovieList/>
        </div>
    );
  }
}

export default App;
