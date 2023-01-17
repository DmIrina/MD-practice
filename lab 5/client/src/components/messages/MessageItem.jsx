import React from 'react';
import MyButton from "../UI/button/MyButton";
import {useNavigate} from 'react-router-dom';

const MessageItem = (props) => {
    const navigate = useNavigate()
    return (
        <div>
            <div className="item">
                <div className="item__content">
                    <strong>
                        {props.number}.
                    </strong>
                    <div>
                        Повідомлення: {props.message.text}
                        <br/>
                        Тип запиту: {props.message.requestType}
                        <br/>
                        URL: {props.message.url}
                        <br/>
                        Дата і час: {props.message.dateTime}
                    </div>
                </div>
                <div className="item__btns">
                    <MyButton onClick={() => props.remove(props.message)}>Видалити</MyButton>
                </div>
            </div>
        </div>
    );
};

export default MessageItem;

