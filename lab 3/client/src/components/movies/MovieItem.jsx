import React from 'react';
import MyButton from "../UI/button/MyButton";
import {useNavigate} from 'react-router-dom';

const MovieItem = (props) => {
    const navigate = useNavigate()
    return (
        <div>
            <div className="item">
                <div className="item__content">
                    <strong>
                        {props.number}. {props.movie.name}
                    </strong>
                    <div>
                        Опис: {props.movie.description}
                    </div>
                </div>
                <div className="item__btns">
                    <MyButton onClick={() => props.remove(props.movie)}>Видалити</MyButton>
                </div>
            </div>
        </div>
    );
};

export default MovieItem;

