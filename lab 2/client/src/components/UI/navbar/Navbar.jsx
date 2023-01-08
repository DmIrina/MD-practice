import React from 'react';
import {Link} from "react-router-dom";

const Navbar = () => {
    return (
        <div className="navbar">
            <div className="navbar__links">
                <Link to="/sessions">Сеанси </Link>
                {/*<Link to="/posts">Пости </Link>*/}
                {/*<Link to="/input">Поле </Link>*/}
                {/*<Link to="/counter">Лічильник </Link>*/}
                {/*<Link to="/simpleposts">Прості_Пости </Link>*/}
                <Link to="/">Про сайт </Link>
                {/*<Link to="/login">Логін </Link>*/}
            </div>
        </div>
    );
};

export default Navbar;