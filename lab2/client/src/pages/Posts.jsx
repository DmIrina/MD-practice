import "../styles/App.css"
import {usePosts} from "../hooks/usePosts";
import {useFetching} from "../hooks/useFetching";
import PostService from "../API/PostService";
import {getPageCount} from "../utils/pages";
import PostForm from "../components/PostForm";
import MyButton from "../components/UI/button/MyButton";
import MyModal from "../components/UI/modal/MyModal";
import PostFilter from "../components/PostFilter";
import PostList from "../components/PostList";
import Loader from "../components/UI/Loader/Loader";
import Pagination from "../components/UI/pagination/Pagination";
import {useEffect, useState} from "react";

function Posts() {
    const [posts, setPosts] = useState([])

    const [filter, setFilter] = useState({sort: '', query: ''});
    const [modal, setModal] = useState(false);
    const [totalPages, setTotalPages] = useState(0);    // кількість сторінок
    const [limit, setLimit] = useState(10); // кількість елементів на сторінку
    const [page, setPage] = useState(1);    //номер сторінки

    // let pagesArray = []
    // for (let i = 0; i < totalPages; i++) {
    //     pagesArray.push(i + 1);
    //     pagesArray.push(0);
    // }
    // console.log(pagesArray);
    // console.log(totalPages);


    const sortedAndSearchedPosts = usePosts(posts, filter.sort, filter.query);

    const [fetchPosts, isPostsLoading, postError] = useFetching(async () => {
        const response = await PostService.getAll(limit, page);
        setPosts(response.data);
        const totalCount = response.headers['x-total-count'];           // кількість постів
        setTotalPages(getPageCount(totalCount, limit));
    })

    // якщо залежності - пустий масив [] - useEffect спрацює лише один раз при монтуванні компонента
    useEffect(() => {
        fetchPosts();
    }, [page])

    // callback - функція необхідна для отримання параметра  newPost з нижнього компонента
    const createPost = (newPost) => {
        setPosts([...posts, newPost])
        setModal(false)
    }

    // Отримати post з дочернього компонента
    const removePost = (post) => {
        setPosts(posts.filter(p => p.id !== post.id))
    }

    const changePage = (page) => {
        setPage(page);
        //   fetchPosts();
    }

    return (
        <div className="App">

            <MyButton style={{margin: '10px 0 10px 0'}} onClick={() => setModal(true)}>Створити користувача</MyButton>
            <MyModal visible={modal} setVisible={setModal}>
                <PostForm create={createPost}/>
            </MyModal>

            <PostFilter filter={filter} setFilter={setFilter}/>
            {postError && <h1>Виникла помилка ${postError}</h1>}

            {isPostsLoading
                ? <div style={{display: 'flex', justifyContent: 'center', marginTop: 50}}><Loader/></div>
                : <PostList remove={removePost} posts={sortedAndSearchedPosts} title="Список постів - 1"/>
            }
            {/*? <PostList remove={removePost} posts={sortedPosts} title="Список постів - 1"/>*/}

            <Pagination page={page} changePage={changePage} totalPages={totalPages}/>

        </div>
    )
}

export default Posts;
