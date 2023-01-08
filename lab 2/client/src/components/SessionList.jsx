import React from 'react';
import SessionItem from "./SessionItem";
import {CSSTransition, TransitionGroup} from "react-transition-group";

const SessionList = ({sessions, title, remove}) => {

    if (!sessions.length) {
        return (
            <h1 style={{textAlign: 'center'}}>
                Сеанси не знайдені
            </h1>
        )
    }

    return (
        <div>
            <h1 style={{textAlign: 'center'}}>{title}</h1>
            <h2>session list</h2>
            <TransitionGroup>
                {sessions.map((item, index) =>
                    <CSSTransition
                        key={item.id}
                        timeout={500}
                        classNames="item"
                    >
                        <SessionItem remove={remove} number={index + 1} session={item}/>
                    </CSSTransition>
                )}
            </TransitionGroup>

        </div>
    );
};

export default SessionList;