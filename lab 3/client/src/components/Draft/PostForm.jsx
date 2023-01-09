import React, {useState} from 'react';
import MyInput from "../UI/input/MyInput";
import MyButton from "../UI/button/MyButton";

const PostForm = ({create}) => {
    const [post, setPost] = useState({title: '', body: ''})

    const addNewPost = (e) => {
        e.preventDefault()      // відключити Submit - не оновлювати сторінку коли тиснемо на кнопку
        const newPost = {
            ...post, id: Date.now()
        }

        // callback - функція необхідна для передачі параметра newPost наверх
        create(newPost)
        setPost({title: '', body: ''})
    }

    return (
        <form>
            {/* Управляемий компонент */}
            <MyInput type="text"
                     value={post.title}
                     onChange={e => setPost({...post, title: e.target.value})}  // міняємо в обєкті post поле title
                     placeholder="Назва поста"/>

            {/* управляемий компонент */}
            <MyInput
                value={post.body}
                onChange={e => setPost({...post, body: e.target.value})}  // міняємо в обєкті post поле body
                type="text" placeholder="Опис поста"
            />
            <MyButton onClick={addNewPost}>Створити пост</MyButton>
        </form>
    );
};

export default PostForm;