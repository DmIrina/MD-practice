import React from 'react';
import MyInput from "../../components/UI/input/MyInput";
import MyButton from "../../components/UI/button/MyButton";

const Login = () => {
    return (
        <div>
           <h1>Сторінка авторизації</h1>
            <form>
                <MyInput type="text" placeholder="Введіть логін"/>
                <MyInput type="password" placeholder="Введіть пароль"/>
                <MyButton>Увійти</MyButton>
            </form>
        </div>
    );
};

export default Login;