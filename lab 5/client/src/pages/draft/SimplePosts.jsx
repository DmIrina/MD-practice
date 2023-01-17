import React, {useMemo, useState} from 'react';
import axios from "axios";
import PostForm from "../../components/Draft/PostForm";
import Loader from "../../components/UI/Loader/Loader";
import PostList from "../../components/Draft/PostList";
import MyButton from "../../components/UI/button/MyButton";

function SimplePosts() {
    const [posts, setPosts] = useState([
        {id: 1, title: 'Javascript', body: 'Мультипарадигменный язык программирования'},
        {id: 2, title: 'Java', body: 'Description'},
        {id: 3, title: 'C#', body: 'C# - Description'},
    ])

    const [filter, setFilter] = useState({sort: '', query: ''});

    // useMemo виконує обчислення (сортування), запам"ятовує та кешує результат. Виконується коли змінюється масив залежностей
    const sortedPosts = useMemo(() => {
        if (filter.sort) {
            return [...posts].sort((a, b) => a[filter.sort].localeCompare(b[filter.sort], 'uk'))
        }
        return posts;
    }, [filter.sort, posts])

    const sortedAndSearchedPosts = useMemo(() => {
        return sortedPosts.filter((post => post.title.toLowerCase().includes(filter.query.toLowerCase())))
    }, [filter.query, sortedPosts])

    const [isPostsLoading, setIsPostsLoading] = useState(false);

    const createPost = (newPost) => {
        setPosts([...posts, newPost])
    }

    // Отримати post з дочернього компонента
    const removePost = (post) => {
        setPosts(posts.filter(p => p.id !== post.id))
    }

    async function fetchPosts() {
        setIsPostsLoading(true);
        const response = await axios.get('https://jsonplaceholder.typicode.com/posts', {
            params: {
                _limit: 10,
                _page: 1
            }
        });

        setPosts(response.data);
        setIsPostsLoading(false);
    }

    return (
        <div className="App">
            <h1>Проста сторінка з постами</h1>
            <PostForm create={createPost}/>
            <hr style={{margin: '15px 0'}}/>

            <MyButton onClick={fetchPosts}>Fetch</MyButton>

            {isPostsLoading
                ? <div style={{display: 'flex', justifyContent: 'center', marginTop: 50}}><Loader/></div>
                : <PostList remove={removePost} posts={sortedAndSearchedPosts} title="Список постів - 1"/>
            }

        </div>
    );
};


export default SimplePosts;