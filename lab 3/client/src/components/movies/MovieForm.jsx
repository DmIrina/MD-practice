import React, {useState} from 'react';
import MyInput from "../UI/input/MyInput";
import MyButton from "../UI/button/MyButton";

const MovieForm = ({create}) => {
    const [movie, setMovie] = useState({name: '', description: ''})

    const addNewMovie = (e) => {
        e.preventDefault()      // відключити Submit - не оновлювати сторінку коли тиснемо на кнопку

        const newMovie = {
            ...movie
        }
        create(newMovie);
        // setMovie({name: 'Аватар', description: 'Минуло вже більше десяти років відколи Джейк Саллі залишився ' +
        //         'на Пандорі. Разом зі своєю коханою Нейтірі вони завжди мріяли про мирне життя у гармонії з природою. ' +
        //         'Однак на їхню планету знову зазіхають люди. Сюди прибуває експедиція, ще більш чисельна ' +

        //         'і небезпечна, аби захопити планету.'})


    }

    return (
        <form>
            {/* Управляємий компонент */}

            <MyInput type="text"
                     value={movie.name}
                     onChange={e => setMovie({...movie, name: e.target.value})}
                     placeholder="Фільм"/>

            <MyInput type="text" value={movie.description}
                     onChange={e => setMovie({...movie, description: e.target.value})}
                     placeholder="Опис"/>

            <MyButton onClick={addNewMovie}>Новий фільм</MyButton>
        </form>
    );
};

export default MovieForm;