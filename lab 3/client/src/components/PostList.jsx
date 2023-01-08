import React from 'react';
import PostItem from "./PostItem";
import {CSSTransition, TransitionGroup} from "react-transition-group";

// props - це об'єкт тому можемо викорнати декомпозицію та вказати {posts}
const PostList = ({posts, title, remove}) => {

    if (!posts.length) {
        return (
            <h1 style={{textAlign: 'center'}}>
                Пости не знайдені
            </h1>
        )
    }

    return (
        <div>
            <h1 style={{textAlign: 'center'}}>{title}</h1>

            <TransitionGroup>
                {posts.map((post, index) =>
                    <CSSTransition
                        key={post.id}
                        timeout={500}
                        classNames="item"
                        >
                        <PostItem remove={remove} number={index + 1} post={post} />
                    </CSSTransition>
                )}
            </TransitionGroup>

            {/*{posts.map((post, index) =>*/}
            {/*    <PostItem remove={remove} number={index + 1} post={post} key={post.id}/>)}*/}
        </div>
    );
};

export default PostList;