import React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import About from "../pages/About";
import Posts from "../pages/Posts";
import Input from "../pages/Input";
import Counter from "../pages/Counter";
import SimplePosts from "../pages/SimplePosts";
import Error from "../pages/Error";
import PostIdPage from "../pages/PostIdPage";
import Login from "../pages/Login";
import Sessions from "../pages/Sessions";

const AppRouter = () => {
    return (
        <Routes>
            <Route path="/" element={<About/>}/>
            <Route path="/sessions" element={<Sessions/>}/>
            {/*<Route exact path="/posts" element={<Posts/>}/>*/}
            {/*Динамічний маршрут - по ID*/}
            {/*<Route exact path="/posts/:id" element={<PostIdPage/>}/>*/}
            {/*<Route path="/input" element={<Input/>}/>*/}
            {/*<Route path="/counter" element={<Counter/>}/>*/}
            {/*<Route path="/simpleposts" element={<SimplePosts/>}/>*/}
            {/*<Route path="/login" element={<Login/>}/>*/}
            <Route path="/error" element={<Error/>}/>
            {/*<Route path='/' element={<Navigate to='/about' replace/>}/>*/}
        </Routes>
    );
};

export default AppRouter;