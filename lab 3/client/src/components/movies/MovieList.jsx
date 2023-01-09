import React from 'react';
import MovieItem from "./MovieItem";
import {CSSTransition, TransitionGroup} from "react-transition-group";

const MovieList = ({movies, title, remove}) => {

    if (!movies.length) {
        return (
            <h1 style={{textAlign: 'center'}}>
                Фільми не знайдені
            </h1>
        )
    }

    return (
        <div>
            <h1 style={{textAlign: 'center'}}>{title}</h1>
            <h2>movie list</h2>
            <TransitionGroup>
                {movies.map((item, index) =>
                    <CSSTransition
                        key={item.id}
                        timeout={500}
                        classNames="item"
                    >
                        <MovieItem remove={remove} number={index + 1} movie={item}/>
                    </CSSTransition>
                )}
            </TransitionGroup>

        </div>
    );
};

export default MovieList;