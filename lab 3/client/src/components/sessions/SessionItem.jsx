import React from 'react';
import MyButton from "../UI/button/MyButton";
import {useNavigate} from 'react-router-dom';

const SessionItem = (props) => {
    const navigate = useNavigate()
    return (
        <div>
            <div className="item">
                <div className="item__content">
                    <strong>
                        {props.number}. {props.session.time} {props.session.name}
                    </strong>
                    <div>
                        Фільм: {props.session.movieName}
                        <br/>
                        Зала: {props.session.room}
                        <br/>
                        Дата: {props.session.date}
                    </div>
                </div>
                <div className="item__btns">
                    <MyButton onClick={() => props.remove(props.session)}>Видалити</MyButton>
                    {/*<MyButton onClick={() => navigate(`${props.session.id}`)}>*/}
                    {/*    Відкрити*/}
                    {/*</MyButton>*/}
                </div>
            </div>
        </div>
    );
};

export default SessionItem;

