import React, {useState} from 'react';
import MyInput from "../UI/input/MyInput";
import MyButton from "../UI/button/MyButton";

const MessageForm = ({create}) => {
    const [message, setMessage] = useState({text: '', request_type: '', url: '', date_time: ''})

    const addNewMessage = (e) => {
        e.preventDefault()      // відключити Submit - не оновлювати сторінку коли тиснемо на кнопку

        const newMessage = {
            ...message
        }
        create(newMessage);
    }

    return (
        <form>
            {/*/!* Управляємий компонент *!/*/}

            {/*<MyInput type="text"*/}
            {/*         value={message.name}*/}
            {/*         onChange={e => setMessage({...message, name: e.target.value})}*/}
            {/*         placeholder="Фільм"/>*/}

            {/*<MyInput type="text" value={message.description}*/}
            {/*         onChange={e => setMessage({...message, description: e.target.value})}*/}
            {/*         placeholder="Опис"/>*/}

            {/*<MyButton onClick={addNewMessage}>Новий фільм</MyButton>*/}
        </form>
    );
};

export default MessageForm;