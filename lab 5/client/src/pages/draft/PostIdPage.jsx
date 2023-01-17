import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {useFetching} from "../../hooks/useFetching";
import PostService from "../../API/draft/PostService";
import Loader from "../../components/UI/Loader/Loader";

const PostIdPage = () => {
    const params = useParams(); // получение параметров маршрута
    const [post, setPost] = useState({});
    const [comments, setComments] = useState([]);

    // Пост
    const [fetchPostById, isLoading, error] = useFetching(async (id) => {
        const response = await PostService.getById(params.id);
        setPost(response.data);
    })

    // Коментар
    const [fetchComments, isComLoading, comError] = useFetching(async () => {
        const response = await PostService.getCommentsByPostId(params.id);
        setComments(response.data);
    })

    useEffect(() => {
        fetchPostById();
        fetchComments();

    }, [])

    return (
        <div>
            <h1>Ви відкрили сторінку поста з ID = {params.id}</h1>
            {isLoading
                ? <Loader/>
                : <div>{post.id}.{post.title}</div>
            }
            <h1>Коментарі:</h1>
            {isComLoading
                ? <Loader/>
                : <div>
                    {comments.map(comm =>
                        <div key={comm.id}
                            style={{marginTop: 15}}>
                            <h5>{comm.email}</h5>
                            <div>{comm.body}</div>
                        </div>
                    )}
                </div>
            }
        </div>
    );
};

export default PostIdPage;