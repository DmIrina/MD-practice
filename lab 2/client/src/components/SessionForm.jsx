import React, {useState} from 'react';
import MyInput from "./UI/input/MyInput";
import MyButton from "./UI/button/MyButton";

const SessionForm = ({create}) => {
    const [session, setSession] = useState({name: 'Ранковий сеанс', room: 'Велика блакитна', date: new Date().toLocaleDateString(), time: '08:00'})

    const addNewSession = (e) => {
        e.preventDefault()      // відключити Submit - не оновлювати сторінку коли тиснемо на кнопку

        const newSession = {
            ...session, "movie":{ "id": Math.random() * 3 + 1 }         //TODO - потім movie треба буде вибрати
        }
        create(newSession);      // callback - функція необхідна для передачі параметра newSession наверх
        setSession({name: 'Ранковий сеанс', room: 'Велика блакитна', date: new Date().toLocaleDateString(), time: '08:00'})
    }

    return (
        <form>
            {/* Управляємий компонент */}
            <MyInput type="text"
                     value={session.name}
                     onChange={e => setSession({...session, name: e.target.value})}  // міняємо в обєкті поле name
                     placeholder="Назва сеанса"/>

            <MyInput type="text" value={session.room} onChange={e => setSession({...session, room: e.target.value})}
                     placeholder="Зала"/>

            <MyInput type="text" value={session.date} onChange={e => setSession({...session, date: e.target.value})}
                     placeholder="Дата"/>

            <MyInput type="text" value={session.time} onChange={e => setSession({...session, time: e.target.value})}
                     placeholder="Час"/>

            <MyButton onClick={addNewSession}>Новий сеанс</MyButton>
        </form>
    );
};

export default SessionForm;