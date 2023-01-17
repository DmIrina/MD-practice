import React from 'react';
import MessageItem from "./MessageItem";
import {CSSTransition, TransitionGroup} from "react-transition-group";

const MessageList = ({messages, title, remove}) => {

    if (!messages.length) {
        return (
            <h1 style={{textAlign: 'center'}}>
                Повідомлення не знайдені
            </h1>
        )
    }

    return (
        <div>
            <h1 style={{textAlign: 'center'}}>{title}</h1>
            <h2>message list</h2>
            <TransitionGroup>
                {messages.map((item, index) =>
                    <CSSTransition
                        key={item.id}
                        timeout={500}
                        classNames="item"
                    >
                        <MessageItem remove={remove} number={index + 1} message={item}/>
                    </CSSTransition>
                )}
            </TransitionGroup>

        </div>
    );
};

export default MessageList;