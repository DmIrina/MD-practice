import React from 'react';
import MyButton from "../UI/button/MyButton";
import { useNavigate } from 'react-router-dom';


const PostItem = (props) => {
    const navigate = useNavigate()
    return (
        <div>
            <div className="item">
                <div className="item__content">
                    <strong>{props.number}. ({props.post.id}) {props.post.title}</strong>
                    <div>
                        {props.post.body}
                    </div>
                </div>
                <div className="item__btns">
                    <MyButton onClick={() => props.remove(props.post)}>Видалити</MyButton>
                    <MyButton onClick={() => navigate(`${props.post.id}`)}>
                        Відкрити
                    </MyButton>
                </div>
            </div>
        </div>
    );
};

export default PostItem;

