import * as React from 'react';

interface Movie {
    id: number;
    name: string;
}

interface MovieListProps {
}

interface MovieListState {
    movies: Array<Movie>;
    isLoading: boolean;
}

class MovieList extends React.Component<MovieListProps, MovieListState> {

    constructor(props: MovieListProps) {
        super(props);

        this.state = {
            movies: [],
            isLoading: false
        };
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('http://localhost:80/good-movies')      
            .then(response => response.json())
            .then(data => this.setState({movies: data, isLoading: false}));
    }

    render() {
        const {movies, isLoading} = this.state;

        if (isLoading) {
            return <p>Завантаження...</p>;
        }

        return (
            <div>
                <h2>Movie List</h2>
                {movies.map((movie: Movie) =>
                    <div key={movie.id}>
                        {movie.name}<br/>
                    </div>
                )}
            </div>
        );
    }
}

export default MovieList;