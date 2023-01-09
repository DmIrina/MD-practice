import React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import About from "../pages/About";
import Posts from "../pages/draft/Posts";
import Input from "../pages/draft/Input";
import Counter from "../pages/draft/Counter";
import SimplePosts from "../pages/draft/SimplePosts";
import Error from "../pages/Error";
import PostIdPage from "../pages/draft/PostIdPage";
import Login from "../pages/draft/Login";
import Sessions from "../pages/Sessions";
import Movies from "../pages/Movies";

const AppRouter = () => {
    return (
        <Routes>
            <Route path="/" element={<About/>}/>
            <Route path="/sessions" element={<Sessions/>}/>
            <Route path="/movies" element={<Movies/>}/>
            {/*Динамічний маршрут - по ID*/}
            {/*<Route exact path="/posts/:id" element={<PostIdPage/>}/>*/}
            <Route path="/error" element={<Error/>}/>
            {/*<Route path='/' element={<Navigate to='/about' replace/>}/>*/}
        </Routes>
    );
};

export default AppRouter;